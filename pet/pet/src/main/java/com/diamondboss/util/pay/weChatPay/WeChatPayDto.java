package com.diamondboss.util.pay.weChatPay;


public class WeChatPayDto {

    /**
     * 内部订单号
     */
    private String orderId;
    /**
     * 订单金额，单位分
     */
    private Integer fee;
    /**
     * ip
     */
    private String ip;
    /**
     * 用户标识
     */
    private String openid;
    /**
     * 附加数据，可用作校验
     */
    private String attach;
    /**
     * 商品描述
     */
    private String body;
    /**
     * 微信回调接口地址
     */
    private String notifyUrl;

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getFee() {
        return fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }
}
