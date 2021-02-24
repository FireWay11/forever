package com.lovezc.forever.util.word;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;


public class GetWord {

    public static void main(String[] args) {
        List<String> wordTitles = null;
        try {
            wordTitles = getWordTitles("D:\\测试文档.docx");
        } catch (IOException e) {
            e.printStackTrace();
        }
        wordTitles.forEach(System.out::println);
    }

    public static List<String> getWordTitles(String path) throws IOException {
        InputStream is = null;
        is = new FileInputStream(path);
        List<String> list = new ArrayList<String>();
        XWPFDocument doc = null;
        doc = new XWPFDocument(is);
        List<XWPFParagraph> paras = doc.getParagraphs();
        for (XWPFParagraph graph : paras) {
            String text = graph.getParagraphText();
            String style = graph.getStyle();
            if ("1".equals(style)) {
                System.out.println(text+"--["+style+"]");
            }else if ("2".equals(style)) {
                System.out.println(text+"--["+style+"]");
            }else if ("3".equals(style)) {
                System.out.println(text+"--["+style+"]");
            }else{
                continue;
            }
            list.add(text);
        }
        return list;
    }


}