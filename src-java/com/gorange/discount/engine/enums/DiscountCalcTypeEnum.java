package com.gorange.discount.engine.enums;

/**
 * 折扣计算类型枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DiscountCalcTypeEnum {
    /**
     * 金额
     */
    AMOUNT,
    /**
     * 数量
     */
    QUANTITY;

    /**
     * 通过名称获取枚举类对象
     * @param name 枚举名称
     * @return  返回折扣计算结果类型枚举类
     */
    public static DiscountCalcTypeEnum getExpressionKeyEnum(String name){
        try {
            return DiscountCalcTypeEnum.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }
}
