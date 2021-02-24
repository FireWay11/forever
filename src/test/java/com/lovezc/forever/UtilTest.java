package com.lovezc.forever;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.List;

@SpringBootTest
public class UtilTest {

    @Test
    public void test1(){
        XWPFDocument docx = null;
//        String path = "D:\\测试文档.docx";
        String path = "D:\\评估报告.docx";
        try {
            OPCPackage pack = POIXMLDocument.openPackage(path);
            docx = new XWPFDocument(pack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert docx != null;
        List<XWPFParagraph> list = docx.getParagraphs();
        int i = 0;
        String nowPath = "D:\\1";
        File file = new File(nowPath);
        for(XWPFParagraph li:list){
            // System.out.print(li.getText().replaceAll(" “,”").indexOf(“─”));
            String runStr = li.getText();
            int j = runStr.replaceAll(" ", "").indexOf("-");
                    String name = runStr.substring(runStr.indexOf("-")+1);
            if (j <= i) {
                int k = i - j + 1;
                for (int l = 0; l < k; l++) {
                    file = file.getParentFile();
                }
            }
            file = new File(file.getAbsolutePath() + "\\" + name);
            if(!file.exists()){
                file.mkdirs();
            }
            i=j;
            nowPath = file.getAbsolutePath();
            // System.out.println(li.getText());
            System.out.println(nowPath);
        }
    }

}
