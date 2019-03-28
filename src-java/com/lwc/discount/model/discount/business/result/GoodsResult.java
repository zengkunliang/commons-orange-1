package com.lwc.discount.model.discount.business.result;

import java.io.Serializable;
import java.util.List;

/**
 * 商品结果类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class GoodsResult implements Serializable {
    /**
     * 商品行号
     */
    private Integer lineNo;
    /**
     * 商品条码
     */
    private String barcode;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品图片
     */
    private String imageUrl;
    /**
     * 商品数量
     */
    private Double quantity;
    /**
     * 商品单价
     */
    private Double price;
    /**
     * 包装
     */
    private String pack;
    /**
     * 商品行原始金额
     */
    private Double originalAmount;
    /**
     * 商品行折扣抵扣金额
     */
    private Double discAmount;
    /**
     * 商品行折扣赠送积点
     */
    private Double discPoint;
    /**
     * 商品行折扣赠送印花
     */
    private Double discStamp;
    /**
     * 商品行折扣赠送积分
     */
    private Double discIntegral;
    /**
     * 商品行最终金额
     */
    private Double finalAmount;
    /**
     * 商品折扣结果
     */
    private List<GoodsDiscountResult> discResultList;

    /**
     * 获取lineNo属性值
     *
     * @return lineNo属性值
     */
    public Integer getLineNo() {
        return lineNo;
    }

    /**
     * 设置lineNo属性值
     * 可以使用getLineNo()获取lineNo的属性值
     *
     * @param lineNo lineNo
     */
    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    /**
     * 获取barcode属性值
     *
     * @return barcode属性值
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 设置barcode属性值
     * 可以使用getBarcode()获取barcode的属性值
     *
     * @param barcode barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * 获取name属性值
     *
     * @return name属性值
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性值
     * 可以使用getName()获取name的属性值
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取imageUrl属性值
     *
     * @return imageUrl属性值
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * 设置imageUrl属性值
     * 可以使用getImageUrl()获取imageUrl的属性值
     *
     * @param imageUrl imageUrl
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * 获取quantity属性值
     *
     * @return quantity属性值
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * 设置quantity属性值
     * 可以使用getQuantity()获取quantity的属性值
     *
     * @param quantity quantity
     */
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取price属性值
     *
     * @return price属性值
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置price属性值
     * 可以使用getPrice()获取price的属性值
     *
     * @param price price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取pack属性值
     *
     * @return pack属性值
     */
    public String getPack() {
        return pack;
    }

    /**
     * 设置pack属性值
     * 可以使用getPack()获取pack的属性值
     *
     * @param pack pack
     */
    public void setPack(String pack) {
        this.pack = pack;
    }

    /**
     * 获取originalAmount属性值
     *
     * @return originalAmount属性值
     */
    public Double getOriginalAmount() {
        return originalAmount;
    }

    /**
     * 设置originalAmount属性值
     * 可以使用getOriginalAmount()获取originalAmount的属性值
     *
     * @param originalAmount originalAmount
     */
    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
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
     * 获取discPoint属性值
     *
     * @return discPoint属性值
     */
    public Double getDiscPoint() {
        return discPoint;
    }

    /**
     * 设置discPoint属性值
     * 可以使用getDiscPoint()获取discPoint的属性值
     *
     * @param discPoint discPoint
     */
    public void setDiscPoint(Double discPoint) {
        this.discPoint = discPoint;
    }

    /**
     * 获取discStamp属性值
     *
     * @return discStamp属性值
     */
    public Double getDiscStamp() {
        return discStamp;
    }

    /**
     * 设置discStamp属性值
     * 可以使用getDiscStamp()获取discStamp的属性值
     *
     * @param discStamp discStamp
     */
    public void setDiscStamp(Double discStamp) {
        this.discStamp = discStamp;
    }

    /**
     * 获取discIntegral属性值
     *
     * @return discIntegral属性值
     */
    public Double getDiscIntegral() {
        return discIntegral;
    }

    /**
     * 设置discIntegral属性值
     * 可以使用getDiscIntegral()获取discIntegral的属性值
     *
     * @param discIntegral discIntegral
     */
    public void setDiscIntegral(Double discIntegral) {
        this.discIntegral = discIntegral;
    }

    /**
     * 获取finalAmount属性值
     *
     * @return finalAmount属性值
     */
    public Double getFinalAmount() {
        return finalAmount;
    }

    /**
     * 设置finalAmount属性值
     * 可以使用getFinalAmount()获取finalAmount的属性值
     *
     * @param finalAmount finalAmount
     */
    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }


    /**
     * 获取discResultList属性值
     *
     * @return discResultList属性值
     */
    public List<GoodsDiscountResult> getDiscResultList() {
        return discResultList;
    }

    /**
     * 设置discResultList属性值<br>
     * 可以使用getDiscResultList()获取discResultList的属性值
     *
     * @param discResultList discResultList
     */
    public void setDiscResultList(List<GoodsDiscountResult> discResultList) {
        this.discResultList = discResultList;
    }
}
