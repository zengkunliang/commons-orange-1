package com.gorange.discount.engine.enums;

/**
 * 关联关系key枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum RelationshipKeyEnum {
    /**
     * 并且
     */
    AND,
    /**
     * 或者
     */
    OR;

    /**
     * 通过名称获取枚举类对象
     * @param name 枚举名称
     * @return 返回关联关系key枚举类
     */
    public static RelationshipKeyEnum getExpressionKeyEnum(String name){
        try {
            return RelationshipKeyEnum.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }
}
