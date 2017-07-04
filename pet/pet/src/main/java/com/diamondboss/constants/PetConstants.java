package com.diamondboss.constants;

/**
 * Created by liuzifu on 2017/6/5.
 */
public class PetConstants {
	
	/**
	 * 合伙人订单表名称
	 */
    public static final String ORDER_PARTNER_TABLE_PREFIX = "order_partner_";

    /**
     * 用户订单表名称
     */
    public static final String ORDER_USER_TABLE_PREFIX = "order_user_";
    
    /**
     * 合伙人钱包表名称
     */
    public static final String PARTNER_WALLET_DETAIL = "partner_wallet_detail_";
    
    /**
     * 抢单信息表名称
     */
    public static final String GRAD_ORDER_INFO_TABLE_PREFIX = "grad_Order_info_";
    
    
    /**
     *  新建订单/未支付
     */
    public static final String ORDER_STATUS_UNPAID = "0";
    
    /**
     * 支付失败
     */
    public static final String ORDER_STATUS_PAY_FAILURE = "1";
    
    /**
     * 派单中/支付成功
     */
    public static final String ORDER_STATUS_PAY_SUCCESS = "2";
    
    /**
     * 异常处理
     */
    public static final String ORDER_STATUS_EXCEPTION = "3";
    
    /**
     * 已接单
     */
    public static final String ORDER_STATUS_RECEIVED = "4";

    /**
     * 已完成
     */
    public static final String ORDER_STATUS_FINISH = "5";

    
}
