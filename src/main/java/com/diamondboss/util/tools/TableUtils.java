package com.diamondboss.util.tools;


import com.diamondboss.constants.PetConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by liuzifu on 2017/6/5.
 */
public class TableUtils {

    /**
     * 根据合伙人或用户id确定订单信息表名
     * @param partnerId
     * @return
     */
    public static String getOrderTableName(Long partnerId, String tablePrefix){
        if (partnerId == null || partnerId == 0){
            return "";
        }

        int orderPartnerSuffix =  partnerId.intValue() / 100 + 1;

        return tablePrefix + orderPartnerSuffix;
    }

    /**
     * 根据合伙人或用户id确定订单信息表名
     * @param partnerId
     * @return
     */
    public static String getOrderTableName(String partnerId, String tablePrefix){
    	return getOrderTableName(Long.valueOf(partnerId), tablePrefix);
    }

    public static void main(String[] args){
        System.out.println(getOrderTableName(new Long(1534), PetConstants.ORDER_PARTNER_TABLE_PREFIX));
        System.out.println(LocalDate.now());
        List<Long> partnerList = new ArrayList<>();
        partnerList.add(new Long(1));
        Random random = new Random();
        int i = random.nextInt(partnerList.size());
        System.out.println(partnerList.get(i));
    }
}
