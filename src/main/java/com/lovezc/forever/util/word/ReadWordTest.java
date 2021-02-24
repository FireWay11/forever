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
//        File file = new File("D:\\测试文档.docx");
        File file = new File("D:\\评估报告.docx");
        FileInputStream fis = new FileInputStream(file);
        XWPFDocument xdoc = new XWPFDocument(fis);
        List<XWPFParagraph> paragraphs = xdoc.getParagraphs();
        List<ReadDto> readDtos = new ArrayList<>();
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
            String titleLvl = getTitleLvl(xdoc, paragraph);
            if (StringUtils.isNotEmpty(titleLvl)) {
                int level = Integer.parseInt(titleLvl);
//                System.out.println("text: " + text + ", titleLvl: " + titleLvl);
                ReadDto readDto = new ReadDto();
                readDto.setText(text);
                readDto.setTitleLevel(level);
                readDtos.add(readDto);
            }
        }
        int zeroCount = 0;//0出现的次数
        int oneCount = 0;//1出现的次数
        int twoCount = 0;//2出现的次数
        int threeCount = 0;//3出现的次数
        int curPoint = 0;//当前指针值
        for (int i = 0; i < readDtos.size(); i++) {
            int curLevel = readDtos.get(i).getTitleLevel();
            if (curLevel > 4) {
                throw new RuntimeException("暂不支持目录层级超过4层!!!");
            }
            if (curPoint == 0) {
                zeroCount++;
                curPoint = 1;
                readDtos.get(i).setPrefix(zeroCount + ".");
            } else if (curPoint == 1) {
                if (curLevel == 0) {
                    zeroCount++;
                    oneCount = 0;
                    twoCount = 0;
                    threeCount = 0;
                    curPoint = 1;
                    readDtos.get(i).setPrefix(zeroCount + ".");
                }
                if (curLevel == 1) {
                    curPoint++;
                    oneCount++;
                    readDtos.get(i).setPrefix(zeroCount + "." + "1.");
                }
            } else if (curPoint == 2) {
                if (curLevel == 0) {
                    zeroCount++;
                    oneCount = 0;
                    twoCount = 0;
                    threeCount = 0;
                    curPoint = 1;
                    readDtos.get(i).setPrefix(zeroCount + ".");
                } else if (curLevel == 1) {
                    oneCount++;
                    twoCount = 0;
                    curPoint = 2;
                    readDtos.get(i).setPrefix(zeroCount + "." + oneCount + ".");
                } else if (curLevel == 2) {
                    curPoint = 3;
                    twoCount++;
                    threeCount = 0;
                    readDtos.get(i).setPrefix(zeroCount + "." + oneCount + "." + twoCount + ".");
                }
            } else if (curPoint == 3) {
                if (curLevel == 0) {
                    zeroCount++;
                    oneCount = 0;
                    twoCount = 0;
                    threeCount = 0;
                    curPoint = 1;
                    readDtos.get(i).setPrefix(zeroCount + ".");
                } else if (curLevel == 1) {
                    oneCount++;
                    curPoint = 2;
                    twoCount = 0;
                    readDtos.get(i).setPrefix(zeroCount + "." + oneCount + ".");
                } else if (curLevel == 2) {
                    curPoint = 3;
                    twoCount++;
                    threeCount = 0;
                    readDtos.get(i).setPrefix(zeroCount + "." + oneCount + "." + twoCount + ".");
                } else if (curLevel == 3) {
                    threeCount++;
                    if (i < readDtos.size() - 1) {
                        int nextLevel = readDtos.get(i + 1).getTitleLevel();
                        if (nextLevel > 3) {
                            throw new RuntimeException("暂不支持目录层级超过4层!!!");
                        }
                        if (nextLevel == 3) {
                            curPoint = 3;
                        } else if (nextLevel < 3) {
                            curPoint = nextLevel + 1;
                        }
                    }
                    readDtos.get(i).setPrefix(zeroCount + "." + oneCount + "." + twoCount + "." + threeCount + ".");
                }
            }
        }
        for (ReadDto dto : readDtos) {
            System.out.println("text:" + dto.getPrefix() + dto.getText() + ",level:" + dto.getTitleLevel());

        }
    }
}
