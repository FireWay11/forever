package com.lovezc.forever.util.word;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

import java.io.*;
import java.util.List;

public class AddTOC {

    public static void main(String[] args) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream("D:\\测试文档1.docx");
        FileInputStream fileInputStream = new FileInputStream("D:\\评估报告.docx");
        XWPFDocument doc = new XWPFDocument(fileInputStream);
        generateTOC(doc);
//        OutputStream out = new FileOutputStream("D:\\测试文档1.docx");
        OutputStream out = new FileOutputStream("D:\\评估报告.docx");
        doc.write(out);
        out.close();
    }

    public static void generateTOC(XWPFDocument document) {
        String findText = "toc";
        String replaceText = "";
        for (XWPFParagraph p : document.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                int pos = r.getTextPosition();
                String text = r.getText(pos);
                System.out.println(text);
                if (text != null && text.contains(findText)) {
                    text = text.replace(findText, replaceText);
                    r.setText(text, 0);
                    addField(p, "TOC \\o \"1-3\" \\h \\z \\u");
//                    addField(p, "TOC \\h");
                    break;
                }
            }
        }
    }

    private static void addField(XWPFParagraph paragraph, String fieldName) {
        CTSimpleField ctSimpleField = paragraph.getCTP().addNewFldSimple();
        ctSimpleField.setInstr(fieldName);
        ctSimpleField.setDirty(STOnOff.TRUE);
        ctSimpleField.addNewR().addNewT().setStringValue("<fieldName>");
    }

    public static void addToc(String path) throws IOException {
        //读取word文件
        InputStream is = new FileInputStream(path);
        XWPFDocument doc = new XWPFDocument(is);
        //获取所有元素(段落和表格)
        List<IBodyElement> elements = doc.getBodyElements();
        int pIndex = 0;
        int tIndex = 0;
        for (int i = 0; i < elements.size(); i++) {
            IBodyElement e = elements.get(i);
            //判断元素类型：段落/ 表格
            if (BodyElementType.PARAGRAPH.equals(e.getElementType())) {
                //获取段落
                XWPFParagraph pa = e.getBody().getParagraphArray(pIndex);
                //获取段落文本
                List<XWPFRun> runs = pa.getRuns();
                for (XWPFRun run : runs) {
                    // todo ...替换文本
                    String text = run.getText(0);
                    System.out.println(text);
                }
            }
            //表格
            else if (BodyElementType.TABLE.equals(e.getElementType())) {
                XWPFTable ta = e.getBody().getTableArray(tIndex);
                //获取所有行
                List<XWPFTableRow> rows = ta.getRows();
                for (XWPFTableRow row : rows) {
                    //遍历每行 获取每行单元格
                    List<XWPFTableCell> cells = row.getTableCells();
                    //遍历单元格
                    for (XWPFTableCell cell : cells) {
                        //获取单元格段落
                        List<XWPFParagraph> paragraphs = cell.getParagraphs();
                        for (XWPFParagraph paragraph : paragraphs) {
                            //获取单元格文本
                            List<XWPFRun> runs = paragraph.getRuns();
                            for (XWPFRun run : runs) {
                                // todo ...替换文本
                            }
                        }
                    }
                }
            }
        }
    }


}