package com.Base;

import java.io.Serializable;

public class ApiResponse implements Serializable {

    private Integer code;
    private String msg;
    private Object data;

    public ApiResponse (int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
