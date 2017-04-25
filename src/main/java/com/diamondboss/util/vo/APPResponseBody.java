package com.diamondboss.util.vo;

/**
 * Created by hjm on 2017/4/9 09:03.
 *
 * the bean that interac with server, rule: the server returned response body
 */

public class APPResponseBody {
	
    /**
     * 0: indicate success; 1: indicate fail
     */
    private int retnCode;
    
    /**
     * descripte the retnCode
     */
    private String retnDesc = "";
   
    /**
     * the data server returned
     */
    private Object data;

    public int getRetnCode() {
        return retnCode;
    }

    public void setRetnCode(int retnCode) {
        this.retnCode = retnCode;
    }

    public String getRetnDesc() {
        return retnDesc;
    }

    public void setRetnDesc(String retnDesc) {
        this.retnDesc = retnDesc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
