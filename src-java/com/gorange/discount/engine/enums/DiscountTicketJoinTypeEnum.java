package com.gorange.discount.engine.enums;

/**
 * 折扣交易参与类型枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DiscountTicketJoinTypeEnum {
    /**
     * 所有
     */
    ALL,
    /**
     * 当天
     */
    DAY,
    /**
     * 当月
     */
    MONTH,
    /**
     * 当季
     */
    QUARTER,
    /**
     * 当年
     */
    YEAR;

    /**
     * 通过名称获取枚举类对象
     * @param name 枚举名称
     * @return 返回折扣交易参与类型枚举类
     */
    public static DiscountTicketJoinTypeEnum getExpressionKeyEnum(String name){
        try {
            return DiscountTicketJoinTypeEnum.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }
}
