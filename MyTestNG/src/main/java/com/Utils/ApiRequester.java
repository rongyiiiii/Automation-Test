package com.Utils;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import com.Base.ApiResponse;
import com.alibaba.fastjson2.JSONObject;
import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.impl.routing.SystemDefaultRoutePlanner;

public class ApiRequester {

    public String response;

    public Object apiReqObject;

    @SneakyThrows
    public String handle(){
        //Set headers
        HashMap<String, String> headers = new HashMap<>();

        //Set params
        String req = new Gson().toJson(apiReqObject);

        //Build request URL
        String url = "https://tenapi.cn/v2/toutiaohotnew";

        //Send request
        Map<String, Object> body = new HashMap<>();
        body.put("args", req);

        response = HttpUtil.get(url, body, headers);
        JSONObject jsonObject = null;
        String resultText = String.format("\n\n[url] %s \n\n[args] %s\n\n[headers] %s \n\n[response] %s\n\n",
                URLDecoder.decode(url, "utf-8"), req, headers, response);
        System.out.println("response" + response);
        try {
            jsonObject = JSONObject.parseObject(response);
        } catch (Exception e) {
            throw new RuntimeException(resultText);
        }

        //response
        return response;
    }

    public static void main(String[] args) {
        ApiRequester request = new ApiRequester();
        request.handle();
    }
}
