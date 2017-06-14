package com.diamondboss.util.pojo;

/**
 * Created by liuzifu on 2017/6/14.
 */
public class SmsReturnInfo {
    private int code;
    private String sessionId;
    private Boolean success;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
