package com.lwc.discount.model.discount.business.result;

import com.lwc.discount.model.discount.business.Discount;

import java.io.Serializable;

/**
 * 商品结果类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class GoodsDiscountResult implements Serializable {
    /**
     * 折扣唯一编号
     */
    private String discUniqueNo;
    /**
     * 折扣分组
     */
    private String discGroupNo;
    /**
     * 折扣分组名称
     */
    private String discGroupName;
    /**
     * 折扣名称
     */
    private String discName;
    /**
     * 折扣描述
     */
    private String discDescription;
    /**
     * 折扣计算结果类型
     */
    private String discCalcResultType;
    /**
     * 折扣值
     */
    private Double discValue;
    /**
     * 折扣金额
     */
    private Double discAmount;
    /**
     * 折扣次数
     */
    private Integer discCount;

    /**
     * 无参构造
     */
    public GoodsDiscountResult(){}

    /**
     * 有参构造
     * @param discount      折扣
     * @param discValue     折扣值
     * @param discAmount    折扣金额
     * @param discCount     折扣次数
     */
    public GoodsDiscountResult(Discount discount,double discValue,double discAmount,int discCount){
        this.discUniqueNo = discount.getUniqueNo();
        this.discGroupNo = discount.getDiscountGroup().getGroupNo();
        this.discGroupName = discount.getDiscountGroup().getGroupName();
        this.discName = discount.getName();
        this.discDescription = discount.getDescription();
        this.discCalcResultType = discount.getDiscountCalcCondition().getCalcResultType();
        this.discValue = discValue;
        this.discAmount = discAmount;
        this.discCount = discCount;
    }

    /**
     * 获取discUniqueNo属性值
     *
     * @return discUniqueNo属性值
     */
    public String getDiscUniqueNo() {
        return discUniqueNo;
    }

    /**
     * 设置discUniqueNo属性值
     * 可以使用getDiscUniqueNo()获取discUniqueNo的属性值
     *
     * @param discUniqueNo discUniqueNo
     */
    public void setDiscUniqueNo(String discUniqueNo) {
        this.discUniqueNo = discUniqueNo;
    }

    /**
     * 获取discGroupNo属性值
     *
     * @return discGroupNo属性值
     */
    public String getDiscGroupNo() {
        return discGroupNo;
    }

    /**
     * 设置discGroupNo属性值
     * 可以使用getDiscGroupNo()获取discGroupNo的属性值
     *
     * @param discGroupNo discGroupNo
     */
    public void setDiscGroupNo(String discGroupNo) {
        this.discGroupNo = discGroupNo;
    }

    /**
     * 获取discGroupName属性值
     *
     * @return discGroupName属性值
     */
    public String getDiscGroupName() {
        return discGroupName;
    }

    /**
     * 设置discGroupName属性值<br>
     * 可以使用getDiscGroupName()获取discGroupName的属性值
     *
     * @param discGroupName discGroupName
     */
    public void setDiscGroupName(String discGroupName) {
        this.discGroupName = discGroupName;
    }

    /**
     * 获取discName属性值
     *
     * @return discName属性值
     */
    public String getDiscName() {
        return discName;
    }

    /**
     * 设置discName属性值
     * 可以使用getDiscName()获取discName的属性值
     *
     * @param discName discName
     */
    public void setDiscName(String discName) {
        this.discName = discName;
    }

    /**
     * 获取discDescription属性值
     *
     * @return discDescription属性值
     */
    public String getDiscDescription() {
        return discDescription;
    }

    /**
     * 设置discDescription属性值
     * 可以使用getDiscDescription()获取discDescription的属性值
     *
     * @param discDescription discDescription
     */
    public void setDiscDescription(String discDescription) {
        this.discDescription = discDescription;
    }

    /**
     * 获取discCalcResultType属性值
     *
     * @return discCalcResultType属性值
     */
    public String getDiscCalcResultType() {
        return discCalcResultType;
    }

    /**
     * 设置discCalcResultType属性值
     * 可以使用getDiscCalcResultType()获取discCalcResultType的属性值
     *
     * @param discCalcResultType discCalcResultType
     */
    public void setDiscCalcResultType(String discCalcResultType) {
        this.discCalcResultType = discCalcResultType;
    }

    /**
     * 获取discValue属性值
     *
     * @return discValue属性值
     */
    public Double getDiscValue() {
        return discValue;
    }

    /**
     * 设置discValue属性值
     * 可以使用getDiscValue()获取discValue的属性值
     *
     * @param discValue discValue
     */
    public void setDiscValue(Double discValue) {
        this.discValue = discValue;
    }

    /**
     * 获取discAmount属性值
     *
     * @return discAmount属性值
     */
    public Double getDiscAmount() {
        return discAmount;
    }

    /**
     * 设置discAmount属性值
     * 可以使用getDiscAmount()获取discAmount的属性值
     *
     * @param discAmount discAmount
     */
    public void setDiscAmount(Double discAmount) {
        this.discAmount = discAmount;
    }

    /**
     * 获取discCount属性值
     *
     * @return discCount属性值
     */
    public Integer getDiscCount() {
        return discCount;
    }

    /**
     * 设置discCount属性值
     * 可以使用getDiscCount()获取discCount的属性值
     *
     * @param discCount discCount
     */
    public void setDiscCount(Integer discCount) {
        this.discCount = discCount;
    }
}
