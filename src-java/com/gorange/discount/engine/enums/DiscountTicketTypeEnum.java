package com.gorange.discount.engine.enums;

/**
 * 折扣交易类型枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DiscountTicketTypeEnum {
    /**
     * 商户交易
     */
    MERCHANT_TICKET,
    /**
     * 商户下的机台交易
     */
    MERCHANT_POS_TICKET,
    /**
     * 商户下的会员交易
     */
    MERCHANT_MEMBER_TICKET;

    /**
     * 通过名称获取枚举类对象
     * @param name 枚举名称
     * @return 返回折扣交易类型枚举类
     */
    public static DiscountTicketTypeEnum getExpressionKeyEnum(String name){
        try {
            return DiscountTicketTypeEnum.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }
}
