package com.lwc.discount.enums;

/**
 * 星期枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum WeekTypeEnum {
    /**
     * 星期一
     */
    MON,
    /**
     * 星期二
     */
    TUE,
    /**
     * 星期三
     */
    WED,
    /**
     * 星期四
     */
    THU,
    /**
     * 星期五
     */
    FRI,
    /**
     * 星期六
     */
    SAT,
    /**
     * 星期日
     */
    SUN;

    /**
     * 通过名称获取枚举类对象
     * @param name 枚举名称
     * @return 返回星期枚举类
     */
    public static WeekTypeEnum getWeekTypeEnum(String name){
        try {
            return WeekTypeEnum.valueOf(name);
        }catch (Exception e){
            return null;
        }
    }
}
