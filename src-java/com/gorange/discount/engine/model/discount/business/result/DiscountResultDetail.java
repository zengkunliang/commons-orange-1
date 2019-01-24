package com.gorange.discount.engine.model.discount.business.result;

import java.io.Serializable;
import java.util.List;

/**
 * 折扣结果详情类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountResultDetail implements Serializable {
    /**
     * 交易原始金额
     */
    private Double ticketOriginalAmount;
    /**
     * 交易折扣抵扣金额
     */
    private Double ticketDiscAmount;
    /**
     * 交易折扣赠送积点
     */
    private Double ticketDiscPoint;
    /**
     * 交易折扣赠送印花
     */
    private Double ticketDiscStamp;
    /**
     * 交易折扣赠送积分
     */
    private Double ticketDiscIntegral;
    /**
     * 交易最终金额
     */
    private Double ticketFinalAmount;
    /**
     * 交易商品结果集
     */
    private List<GoodsResult> goodsResultList;

    /**
     * 获取ticketOriginalAmount属性值
     *
     * @return ticketOriginalAmount属性值
     */
    public Double getTicketOriginalAmount() {
        return ticketOriginalAmount;
    }

    /**
     * 设置ticketOriginalAmount属性值
     * 可以使用getTicketOriginalAmount()获取ticketOriginalAmount的属性值
     *
     * @param ticketOriginalAmount ticketOriginalAmount
     */
    public void setTicketOriginalAmount(Double ticketOriginalAmount) {
        this.ticketOriginalAmount = ticketOriginalAmount;
    }

    /**
     * 获取ticketDiscAmount属性值
     *
     * @return ticketDiscAmount属性值
     */
    public Double getTicketDiscAmount() {
        return ticketDiscAmount;
    }

    /**
     * 设置ticketDiscAmount属性值
     * 可以使用getTicketDiscAmount()获取ticketDiscAmount的属性值
     *
     * @param ticketDiscAmount ticketDiscAmount
     */
    public void setTicketDiscAmount(Double ticketDiscAmount) {
        this.ticketDiscAmount = ticketDiscAmount;
    }

    /**
     * 获取ticketDiscPoint属性值
     *
     * @return ticketDiscPoint属性值
     */
    public Double getTicketDiscPoint() {
        return ticketDiscPoint;
    }

    /**
     * 设置ticketDiscPoint属性值
     * 可以使用getTicketDiscPoint()获取ticketDiscPoint的属性值
     *
     * @param ticketDiscPoint ticketDiscPoint
     */
    public void setTicketDiscPoint(Double ticketDiscPoint) {
        this.ticketDiscPoint = ticketDiscPoint;
    }

    /**
     * 获取ticketDiscStamp属性值
     *
     * @return ticketDiscStamp属性值
     */
    public Double getTicketDiscStamp() {
        return ticketDiscStamp;
    }

    /**
     * 设置ticketDiscStamp属性值
     * 可以使用getTicketDiscStamp()获取ticketDiscStamp的属性值
     *
     * @param ticketDiscStamp ticketDiscStamp
     */
    public void setTicketDiscStamp(Double ticketDiscStamp) {
        this.ticketDiscStamp = ticketDiscStamp;
    }

    /**
     * 获取ticketDiscIntegral属性值
     *
     * @return ticketDiscIntegral属性值
     */
    public Double getTicketDiscIntegral() {
        return ticketDiscIntegral;
    }

    /**
     * 设置ticketDiscIntegral属性值
     * 可以使用getTicketDiscIntegral()获取ticketDiscIntegral的属性值
     *
     * @param ticketDiscIntegral ticketDiscIntegral
     */
    public void setTicketDiscIntegral(Double ticketDiscIntegral) {
        this.ticketDiscIntegral = ticketDiscIntegral;
    }

    /**
     * 获取ticketFinalAmount属性值
     *
     * @return ticketFinalAmount属性值
     */
    public Double getTicketFinalAmount() {
        return ticketFinalAmount;
    }

    /**
     * 设置ticketFinalAmount属性值
     * 可以使用getTicketFinalAmount()获取ticketFinalAmount的属性值
     *
     * @param ticketFinalAmount ticketFinalAmount
     */
    public void setTicketFinalAmount(Double ticketFinalAmount) {
        this.ticketFinalAmount = ticketFinalAmount;
    }

    /**
     * 获取goodsResultList属性值
     *
     * @return goodsResultList属性值
     */
    public List<GoodsResult> getGoodsResultList() {
        return goodsResultList;
    }

    /**
     * 设置goodsResultList属性值
     * 可以使用getGoodsResultList()获取goodsResultList的属性值
     *
     * @param goodsResultList goodsResultList
     */
    public void setGoodsResultList(List<GoodsResult> goodsResultList) {
        this.goodsResultList = goodsResultList;
    }
}
