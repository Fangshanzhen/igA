package com.igA.demo.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * 对象转化为MultipartFile格式
 */
public class FileTransformUtils {
    public static MultipartFile transform(String string) {
        MultipartFile multipartFile = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8));
            multipartFile = new MockMultipartFile("file", "file.json", "multipart/form-data", inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return multipartFile;
    }
}
