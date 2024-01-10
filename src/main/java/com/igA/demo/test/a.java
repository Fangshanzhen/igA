package com.igA.demo.test;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class a {


    public static void main(String[] args) throws Exception {

        String filepath="C:\\Users\\fsz\\Desktop\\a.txt";
        FileInputStream fis=null;  //节点类
        InputStreamReader isr=null; //转换类
        BufferedReader br=null;  //装饰类
        fis= new FileInputStream(filepath);  //填写读取文件所需要的路径
        isr= new InputStreamReader(fis,"UTF-8");  //传入fis 并且设置字符编码 ，需要与写入文件时候的编码相同
        br=new BufferedReader(isr);
        String text;
        try {
            while ((text=br.readLine())!=null)
            {
                System.out.println(text);  //输出每次读取到的行数据
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            br.close();
        }
    }


}


