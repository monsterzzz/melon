package com.monster.melon.serializer;

import com.fasterxml.jackson.annotation.JsonInclude;

public class Response {
    private Integer code = 200;
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
