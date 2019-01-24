package com.gorange.discount.engine.enums;

/**
 * 表达式key枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum ExpressionKeyEnum {
    /**
     * 在当中
     */
    IN,
    /**
     * 不在当中
     */
    NOT_IN,
    /**
     * 等于
     */
    EQ,
    /**
     * 不等于
     */
    NOT_EQ,
    /**
     * 大于
     */
    GR,
    /**
     * 大于并等于
     */
    GR_AND_EQ,
    /**
     * 小于
     */
    LE,
    /**
     * 小于并等于
     */
    LE_AND_EQ;

    /**
     * 通过名称获取枚举类对象
     * @param name 枚举名称
     * @return 返回表达式key枚举类
     */
    public static ExpressionKeyEnum getExpressionKeyEnum(String name){
        try {
            return ExpressionKeyEnum.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }
}
