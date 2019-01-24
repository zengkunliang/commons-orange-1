package com.gorange.discount.engine.model.discount.business;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 折扣日折扣时间区间类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DiscountTime implements Serializable{
    /**
     * 开始时间<br>
     * 折扣日单个时间区间的开始时间,数据格式为HH:mm:ss<br>
     * 为null 或 "" 或 " "时引擎自动设置为: 00:00:00
     */
    private String startTime;
    /**
     * 结束时间<br>
     * 折扣日单个时间区间的结束时间,数据格式为HH:mm:ss<br>
     * 为null 或 "" 或 " "时引擎自动设置为: 23:59:59
     */
    private String endTime;

    /**
     * 获取startTime属性值
     *
     * @return startTime属性值
     */
    public String getStartTime() {
        if(StringUtils.isBlank(startTime)){
            startTime = "00:00:00";
        }
        return startTime;
    }

    /**
     * 设置startTime属性值
     * 可以使用getStartTime()获取startTime的属性值
     *
     * @param startTime startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取endTime属性值
     *
     * @return endTime属性值
     */
    public String getEndTime() {
        if(StringUtils.isBlank(endTime)){
            endTime = "23:59:59";
        }
        return endTime;
    }

    /**
     * 设置endTime属性值
     * 可以使用getEndTime()获取endTime的属性值
     *
     * @param endTime endTime
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
