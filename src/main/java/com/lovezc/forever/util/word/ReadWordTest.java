package com.lovezc.forever.util.word;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ReadWordTest {
    /**
     * Word中的大纲级别，可以通过getPPr().getOutlineLvl()直接提取，但需要注意，Word中段落级别，通过如下三种方式定义：
     * 1、直接对段落进行定义；
     * 2、对段落的样式进行定义；
     * 3、对段落样式的基础样式进行定义。
     * 因此，在通过“getPPr().getOutlineLvl()”提取时，需要依次在如上三处读取。
     *
     * @param doc
     * @param para
     * @return
     */
    private static String getTitleLvl(XWPFDocument doc, XWPFParagraph para) {
        String titleLvl = "";
        try {
            //判断该段落是否设置了大纲级别
            if (para.getCTP().getPPr().getOutlineLvl() != null) {
                return String.valueOf(para.getCTP().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {
        }
        try {
            //判断该段落的样式是否设置了大纲级别
            if (doc.getStyles().getStyle(para.getStyle()).getCTStyle().getPPr().getOutlineLvl() != null) {
                return String.valueOf(doc.getStyles().getStyle(para.getStyle()).getCTStyle().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {
        }
        try {
            //判断该段落的样式的基础样式是否设置了大纲级别
            if (doc.getStyles().getStyle(doc.getStyles().getStyle(para.getStyle()).getCTStyle().getBasedOn().getVal())
                    .getCTStyle().getPPr().getOutlineLvl() != null) {
                String styleName = doc.getStyles().getStyle(para.getStyle()).getCTStyle().getBasedOn().getVal();
                return String.valueOf(doc.getStyles().getStyle(styleName).getCTStyle().getPPr().getOutlineLvl().getVal());
            }
        } catch (Exception e) {

        }
        try {
            if (para.getStyleID() != null) {
                return para.getStyleID();
            }
        } catch (Exception e) {

        }

        return titleLvl;
    }


    public static void main(String[] args) throws Exception {
        File file = new File("D:\\评估报告.docx");
        FileInputStream fis = new FileInputStream(file);
        XWPFDocument xdoc = new XWPFDocument(fis);
        List<XWPFParagraph> paragraphs = xdoc.getParagraphs();
        List<ReadDto> readDtos = new ArrayList<>();
        Stack<ReadDto> stack = new Stack<>();
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
            String titleLvl = getTitleLvl(xdoc, paragraph);
            if (StringUtils.isNotEmpty(titleLvl)) {
                int level = Integer.parseInt(titleLvl);
                ReadDto readDto = new ReadDto();
                readDto.setText(text);
                readDto.setTitleLevel(level);
                readDto.setUuid(UUID.randomUUID().toString());
                if (stack.size() != 0) {
                    ReadDto peek = stack.peek();
                    while ((level - 1) != peek.getTitleLevel() && !stack.isEmpty()) {
                        stack.pop();
                        if (!stack.isEmpty()) {
                            peek = stack.peek();
                        } else {
                            peek = null;
                            break;
                        }
                    }
                    if (peek != null) {
                        readDto.setParentUuid(peek.getUuid());
                    }
                }
                stack.push(readDto);

                readDtos.add(readDto);
            }
        }

        for (ReadDto dto : readDtos) {
            System.out.println("---text:"+ dto.getText() + "---level:" + dto.getTitleLevel() + "---uuid:" + dto.getUuid() + "---parentid:" + dto.getParentUuid());

        }
    }
}
