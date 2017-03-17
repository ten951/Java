package com.wyt.sprboot.spinitdemo.uitls;

import java.util.Date;

/**
 * @author Darcy
 *         Created by Administrator on 2016/5/12.
 */
public class GenericResponse {
    /*状态码*/
    private String code;
    /*状态信息*/
    private String message;
    /*返回数据*/
    private Object result;
    /*返回数据*/
    private Object obj;
    /*服务器本地时间*/
    private Date dateTime;

    public GenericResponse() {
    }

    public GenericResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.dateTime = new Date();
    }

    public GenericResponse(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.dateTime = new Date();
    }

    public GenericResponse(String code, String message, Object result, Object obj) {
        this.code = code;
        this.message = message;
        this.result = result;
        this.obj = obj;
        this.dateTime = new Date();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
