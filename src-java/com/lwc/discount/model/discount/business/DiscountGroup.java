package com.lwc.discount.model.discount.business;

import java.io.Serializable;
import java.util.List;

/**
 * 折扣分组类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountGroup implements Serializable {
    /**
     * 折扣分组编号<br>
     * 不得为null 或 "" 或 " "<br>
     * 长度区间为【1-10】
     */
    private String groupNo;
    /**
     * 折扣分组名称
     * 不为Blank时长度区间为【1-50】
     */
    private String groupName;
    /**
     * 折扣计算优先级 默认:0<br>
     * 将根据该栏位值从高到底进行排序计算<br>
     * 数据不得小于0<br>
     * 数据不得大于999999<br>
     * 当未提供该栏位值时默认为0,将随机的根据数据传入顺序由引擎自定义进行排序
     */
    private Integer calcLevel;
    /**
     * 互斥的折扣分组编号集
     */
    private List<String> exclusionGroupNoList;


    /**
     * 获取groupNo属性值
     *
     * @return groupNo属性值
     */
    public String getGroupNo() {
        return groupNo;
    }

    /**
     * 设置groupNo属性值<br>
     * 可以使用getGroupNo()获取groupNo的属性值
     *
     * @param groupNo groupNo
     */
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    /**
     * 获取groupName属性值
     *
     * @return groupName属性值
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置groupName属性值<br>
     * 可以使用getGroupName()获取groupName的属性值
     *
     * @param groupName groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * 获取calcLevel属性值
     *
     * @return calcLevel属性值
     */
    public Integer getCalcLevel() {
        if(calcLevel==null){
            calcLevel = 0;
        }
        return calcLevel;
    }

    /**
     * 设置calcLevel属性值<br>
     * 可以使用getCalcLevel()获取calcLevel的属性值
     *
     * @param calcLevel calcLevel
     */
    public void setCalcLevel(Integer calcLevel) {
        this.calcLevel = calcLevel;
    }

    /**
     * 获取exclusionGroupNoList属性值
     *
     * @return exclusionGroupNoList属性值
     */
    public List<String> getExclusionGroupNoList() {
        return exclusionGroupNoList;
    }

    /**
     * 设置exclusionGroupNoList属性值<br>
     * 可以使用getExclusionGroupNoList()获取exclusionGroupNoList的属性值
     *
     * @param exclusionGroupNoList exclusionGroupNoList
     */
    public void setExclusionGroupNoList(List<String> exclusionGroupNoList) {
        this.exclusionGroupNoList = exclusionGroupNoList;
    }
}
