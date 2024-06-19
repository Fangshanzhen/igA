package com.igA.demo.utils;


import com.igA.demo.constant.kettleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;
import org.springframework.web.multipart.MultipartFile;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.MediaType;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpClientUtils {


    public static kettleResponse doPost(String url, String token, Object jsonStr, String type, String xiangmuid) throws IOException {

        kettleResponse kettleResponse = new kettleResponse();

        HttpClient httpClient = new HttpClient();

        httpClient.getHttpConnectionManager().getParams()
                .setConnectionTimeout(120 * 1000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(120 * 1000);
        PostMethod postMethod = new PostMethod(url);
        postMethod.addRequestHeader("accept", "*/*");
        postMethod.addRequestHeader("connection", "Keep-Alive");
        if (type.equals("1")) {
            postMethod.addRequestHeader("Content-Type", MediaType.APPLICATION_JSON);
        } else {
//            postMethod.addRequestHeader("Content-Type", MediaType.MULTIPART_FORM_DATA);
        }
        if (xiangmuid.equals("iga")) {
            postMethod.addRequestHeader("AIIT-ZHYL-PLATFORM", "25");
        }// iga是25, cgkd是27
        if (xiangmuid.equals("cgkd")) {
            postMethod.addRequestHeader("AIIT-ZHYL-PLATFORM", "27");
        }
        if (token != null) {
            postMethod.addRequestHeader("AIIT-ZHYL-AUTH", token);
        }

        if (jsonStr != null) {
            if (type.equals("1")) {
                RequestEntity requestEntity = new StringRequestEntity((String) jsonStr, "application/json", "UTF-8");
                postMethod.setRequestEntity(requestEntity);
            } else {
                RequestEntity requestEntity = new ByteArrayRequestEntity((byte[]) jsonStr);
                postMethod.setRequestEntity(requestEntity);
            }
        }

        int statusCode = httpClient.executeMethod(postMethod);
        kettleResponse.setCode(statusCode);

        InputStream inputStream = postMethod.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8), 5 * 1024 * 1024);
        StringBuilder stringBuilder = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            stringBuilder.append(str);
        }
        br.close();
        String log1 = stringBuilder.toString();

        if (log1.contains("accessToken")) {
            log.info("accessToken: " + log1);
        }

        kettleResponse.setData(log1);
        return kettleResponse;
    }


    public static kettleResponse uploadFile(String url, MultipartFile multipartFile, String token) throws IOException {
        kettleResponse kettleResponse = new kettleResponse();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(url);
        uploadFile.addHeader("AIIT-ZHYL-PLATFORM", "25");

        if (token != null) {
            uploadFile.addHeader("AIIT-ZHYL-AUTH", token);
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody(
                "file",
                multipartFile.getInputStream(),
                ContentType.create("multipart/form-data", StandardCharsets.UTF_8),
                "Hospital-1_0.json"
        );

        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);

        try {
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            String responseContent = EntityUtils.toString(responseEntity, "UTF-8");
            kettleResponse.setCode(response.getStatusLine().getStatusCode());
            kettleResponse.setData(responseContent);

        } finally {

            httpClient.close();
        }

        httpClient.close();
        return kettleResponse;
    }


    public static kettleResponse uploadFile1(String url, MultipartFile multipartFile, String token, String type) throws IOException {
        kettleResponse kettleResponse = new kettleResponse();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost(url);
        uploadFile.addHeader("AIIT-ZHYL-PLATFORM", "27");

        if (token != null) {
            uploadFile.addHeader("AIIT-ZHYL-AUTH", token);
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (type.equals("2")) { //蛋白尿
            builder.addBinaryBody(
                    "file",
                    multipartFile.getInputStream(),
                    ContentType.create("multipart/form-data", StandardCharsets.UTF_8),
                    "Hospital-9_0.json"
            );
        }
        if (type.equals("1")) { //Alport 综合征
            builder.addBinaryBody(
                    "file",
                    multipartFile.getInputStream(),
                    ContentType.create("multipart/form-data", StandardCharsets.UTF_8),
                    "Hospital-1_0.json"
            );
        }
        if (type.equals("3")) { //肾小管疾病
            builder.addBinaryBody(
                    "file",
                    multipartFile.getInputStream(),
                    ContentType.create("multipart/form-data", StandardCharsets.UTF_8),
                    "Hospital-3_0.json"
            );
        }
        if (type.equals("4")) { //肾脏囊性疾病
            builder.addBinaryBody(
                    "file",
                    multipartFile.getInputStream(),
                    ContentType.create("multipart/form-data", StandardCharsets.UTF_8),
                    "Hospital-5_0.json"
            );
        }
        if (type.equals("5")) { //先天性肾脏尿路畸形
            builder.addBinaryBody(
                    "file",
                    multipartFile.getInputStream(),
                    ContentType.create("multipart/form-data", StandardCharsets.UTF_8),
                    "Hospital-7_0.json"
            );
        }


        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);

        try {
            CloseableHttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();
            String responseContent = EntityUtils.toString(responseEntity, "UTF-8");
            kettleResponse.setCode(response.getStatusLine().getStatusCode());
            kettleResponse.setData(responseContent);

        } finally {
            httpClient.close();
        }

        httpClient.close();
        return kettleResponse;
    }


}