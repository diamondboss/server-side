package com.diamondboss.util.pay.aliPay;

import java.util.Objects;

/**
 * Created by liuzifu on 2017/7/1.
 */
public enum EnumAlipayResult {
    SUCCESS("9000", "订单支付成功"),
    PROCESS("8000", "正在处理中"),
    FAILURE("4000", "订单支付失败"),
    REPEAT("5000", "重复请求"),
    CANCEL("6001", "用户中途取消"),
    CONNECT_ERROR("6002", "网络连接出错"),
    RESULT_UNKNOW("6004", "支付结果未知"),
    OTHER("其它", "其它支付错误");

    public final String status;
    public final String info;

    EnumAlipayResult(String status, String info) {
        this.status = status;
        this.info = info;
    }

    public static EnumAlipayResult parse(Integer status) {
        if (status == null) return PROCESS;

        for (EnumAlipayResult enumBikeStatus : EnumAlipayResult.values()) {
            if (Objects.equals(enumBikeStatus.status, status)) {
                return enumBikeStatus;
            }
        }
        return PROCESS;
    }

}
