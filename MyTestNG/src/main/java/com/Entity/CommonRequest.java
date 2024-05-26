package com.Entity;

import java.util.HashMap;

public class CommonRequest {

    /**
     * 请求域名url
     */
    private String url;

    /**
     * 请求header头
     */
    private HashMap<String, String> headers;

    /**
     * 组装完成的url
     */
    private String finalUrl;
}
