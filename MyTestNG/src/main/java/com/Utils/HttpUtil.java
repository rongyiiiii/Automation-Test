package com.Utils;

import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    /**
     * httpGet
     * @param url
     * @param headers
     * @return
     */
    @SneakyThrows
    public static String get(String url, Map<String, Object> map, HashMap<String, String> headers) {
        return HttpUtil.doGetRequest(url, map, headers);
    }

    /**
     * httpPost
     * @param url
     * @param headers
     * @param requestBody
     * @return
     */
    @SneakyThrows
    public static String post(String url, HashMap<String, String> headers, String requestBody) {
        return HttpUtil.doPostRequest(url, headers, requestBody);
    }

    /**
     * httpGet
     * @param url
     * @param headers
     * @return
     */
    @SneakyThrows
    public static String doGetRequest(String url, Map<String, Object> map, HashMap<String, String> headers) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGetRequest = new HttpGet(url);
        httpGetRequest.setHeader("Content-Type", "application/json");

        //headers
        if (headers != null && headers.size() >0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getValue() != null) {
                    httpGetRequest.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }

        try {
            CloseableHttpResponse response = httpClient.execute(httpGetRequest);
            String resp = EntityUtils.toString(response.getEntity());
            response.close();
            httpClient.close();
            return resp;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * httpPost
     * @param url
     * @param headers
     * @param requestBody
     * @return
     */
    @SneakyThrows
    public static String doPostRequest(String url, HashMap<String, String> headers, String requestBody) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPostRequest = new HttpPost(url);
        httpPostRequest.setHeader("Content-Type", "application/json");
        //headers
        if (headers != null && headers.size() >0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry.getValue() != null) {
                    httpPostRequest.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }
        StringEntity requestEntity = new StringEntity(requestBody, StandardCharsets.UTF_8);
        httpPostRequest.setEntity(requestEntity);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPostRequest);
            String resp = EntityUtils.toString(response.getEntity());
            response.close();
            httpClient.close();
            return resp;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}