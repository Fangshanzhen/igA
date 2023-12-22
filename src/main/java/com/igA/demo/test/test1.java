package com.igA.demo.test;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.HashMap;

public class test1 {


    public static void main(String[] args) {
        // 示例数据
        Map<String, Object> bingliMap = new HashMap<>();
        bingliMap.put("symptom", "cough");
        bingliMap.put("diagnosis", null); // 这个字段为空

        // 使用Fastjson创建JSONObject并添加bingliMap
        // Fastjson会自动处理并保留Map中的null值
        JSONObject jsonObject = new JSONObject(bingliMap);

        // 打印JSONObject
        System.out.println(jsonObject.toString());
    }

}
