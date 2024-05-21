package com.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HttpUtil {

    public static JSONObject doGet(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String responseText = EntityUtils.toString(entity);
            JSONObject responseJson = JSON.parseObject(responseText);
            response.close();
            httpClient.close();
            return responseJson;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject doPost(String url, JSONObject requestBody) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity requestEntity = new StringEntity(requestBody.toString(), StandardCharsets.UTF_8);
        httpPost.setEntity(requestEntity);
        httpPost.setHeader("Content-Type", "application/json");

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            String responseText = EntityUtils.toString(httpEntity);
            JSONObject responseJson = JSON.parseObject(responseText);
            response.close();
            httpClient.close();
            return responseJson;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}