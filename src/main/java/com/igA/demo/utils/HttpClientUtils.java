package com.igA.demo.utils;



import com.igA.demo.constant.kettleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.pentaho.di.core.logging.LogChannel;
import org.pentaho.di.core.logging.LogChannelFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class HttpClientUtils {


    public static kettleResponse doPost(String url, String token, String jsonStr) throws IOException {

        kettleResponse kettleResponse = new kettleResponse();

        HttpClient httpClient = new HttpClient();

        httpClient.getHttpConnectionManager().getParams()
                .setConnectionTimeout(120 * 1000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(120 * 1000);
        PostMethod postMethod = new PostMethod(url);
        postMethod.addRequestHeader("accept", "*/*");
        postMethod.addRequestHeader("connection", "Keep-Alive");
        postMethod.addRequestHeader("Content-Type", "application/json");
        postMethod.addRequestHeader("AIIT-ZHYL-PLATFORM", "13");  //修改

        if (token != null) {
            postMethod.addRequestHeader("AIIT-ZHYL-AUTH", token);
        }

        if (jsonStr != null) {
            RequestEntity requestEntity = new StringRequestEntity(jsonStr, "application/json", "UTF-8");

            postMethod.setRequestEntity(requestEntity);
        }

        int statusCode = httpClient.executeMethod(postMethod);
        kettleResponse.setCode(statusCode);

        InputStream inputStream = postMethod.getResponseBodyAsStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8),5*1024*1024);
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

}