package com.lwc.discount.enums;

/**
 * 计算商品排序类型枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum CalcGoodsSortTypeEnum {
    /**
     * 降序
     */
    DESC,
    /**
     * 升序
     */
    ASC,
    /**
     * 无序
     */
    NONE;

    /**
     * 通过名称获取枚举类对象
     * @param name 枚举名称
     * @return  返回计算商品排序类型枚举类
     */
    public static CalcGoodsSortTypeEnum getExpressionKeyEnum(String name){
        try {
            return CalcGoodsSortTypeEnum.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }
}
