package com.lovezc.forever.util.word;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.policy.HackLoopTableRenderPolicy;
import com.google.common.collect.Lists;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Tarzan Liu
 * @Date: 2020/4/2 14:54
 */
public class test {

    public static void test() {
        HackLoopTableRenderPolicy policy = new HackLoopTableRenderPolicy();

        Configure config = Configure.newBuilder().bind("list", policy).build();

        List<Map<String, Object>> list = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            //通过map存放要填充的数据
            Map<String, Object> data = new HashMap<>();
            data.put("year", "201" + i);
            data.put("num", 33 + i);
            list.add(data);
        }
        //通过map存放要填充的数据
        Map<String, Object> data = new HashMap<>();
        data.put("list", list);
        //调用模板，填充数据
        XWPFTemplate template = XWPFTemplate.compile("D:/test.docx", config).render(data);
        try {
            //要导出的文件名
            FileOutputStream out = new FileOutputStream("D:/test.docx");
            template.write(out);
            out.flush();
            out.close();
            template.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test.test();
    }

}