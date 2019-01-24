package com.gorange.discount.engine.enums;

/**
 * 折扣计算结果类型枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum DiscountCalcResultTypeEnum {
    /**
     * 金额
     */
    AMOUNT,
    /**
     * 百分比
     */
    RATE,
    /**
     * 数量
     */
    QUANTITY,
    /**
     * 积点
     */
    POINT,
    /**
     * 印花
     */
    STAMP,
    /**
     * 积分
     */
    INTEGRAL;

    /**
     * 通过名称获取枚举类对象
     * @param name 枚举名称
     * @return  返回折扣计算结果类型枚举类
     */
    public static DiscountCalcResultTypeEnum getExpressionKeyEnum(String name){
        try {
            return DiscountCalcResultTypeEnum.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }
}
