package com.lwc.discount.model.discount.business.calccondition;

import com.lwc.discount.utils.CommonUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 计算商品条件类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionGoodsCalcGoodsCondition implements Serializable {
    /**
     * 分类编号<br>
     * 参与商品分类编号必须匹配该条件<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     */
    private Map<String,List<String>> groupNo;
    /**
     * 条码<br>
     * 参与商品条码必须匹配该条件<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     */
    private Map<String,List<String>> barcode;
    /**
     * 单价<br>
     * 参与商品单价必须匹配该条件<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是EQ 或 GR_AND_EQ 或 LE_AND_EQ
     * key有正确有效的值时,value不可为null,value必须大于0
     */
    private Map<String,Double> price;
    /**
     * 金额<br>
     * 可参与商品总金额必须匹配该条件<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是EQ
     * key有正确有效的值时,value不可为null,value必须大于0<br>
     * 当满足同时满足groupNo barcode price时且这些满足条件的商品符合数量则算是一次可以参与的折扣
     */
    private Map<String,Double> amount;
    /**
     * 数量<br>
     * 可参与商品总数量必须匹配该条件<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是EQ
     * key有正确有效的值时,value不可为null,value必须大于0<br>
     * 当满足同时满足groupNo barcode price时且这些满足条件的商品符合数量则算是一次可以参与的折扣
     */
    private Map<String,Double> quantity;


    /**
     * 参与计算商品表达式ID(处理中自定义属性)
     */
    private String goodsConditionNo;

    /**
     * 获取groupNo属性值
     *
     * @return groupNo属性值
     */
    public Map<String, List<String>> getGroupNo() {
        return groupNo;
    }

    /**
     * 设置groupNo属性值<br>
     * 可以使用getGroupNo()获取groupNo的属性值
     *
     * @param groupNo groupNo
     */
    public void setGroupNo(Map<String, List<String>> groupNo) {
        this.groupNo = groupNo;
    }

    /**
     * 获取barcode属性值
     *
     * @return barcode属性值
     */
    public Map<String, List<String>> getBarcode() {
        return barcode;
    }

    /**
     * 设置barcode属性值<br>
     * 可以使用getBarcode()获取barcode的属性值
     *
     * @param barcode barcode
     */
    public void setBarcode(Map<String, List<String>> barcode) {
        this.barcode = barcode;
    }

    /**
     * 获取price属性值
     *
     * @return price属性值
     */
    public Map<String, Double> getPrice() {
        return price;
    }

    /**
     * 设置price属性值<br>
     * 可以使用getPrice()获取price的属性值
     *
     * @param price price
     */
    public void setPrice(Map<String, Double> price) {
        this.price = price;
    }

    /**
     * 获取amount属性值
     *
     * @return amount属性值
     */
    public Map<String, Double> getAmount() {
        return amount;
    }

    /**
     * 设置amount属性值<br>
     * 可以使用getAmount()获取amount的属性值
     *
     * @param amount amount
     */
    public void setAmount(Map<String, Double> amount) {
        this.amount = amount;
    }

    /**
     * 获取quantity属性值
     *
     * @return quantity属性值
     */
    public Map<String, Double> getQuantity() {
        return quantity;
    }

    /**
     * 设置quantity属性值<br>
     * 可以使用getQuantity()获取quantity的属性值
     *
     * @param quantity quantity
     */
    public void setQuantity(Map<String, Double> quantity) {
        this.quantity = quantity;
    }

    /**
     * 获取goodsConditionNo属性值
     *
     * @return goodsConditionNo属性值
     */
    public String getGoodsConditionNo() {
        if(goodsConditionNo==null){
            goodsConditionNo = CommonUtils.getRandomString32();
        }
        return goodsConditionNo;
    }

    /**
     * 设置goodsConditionNo属性值<br>
     * 可以使用getGoodsConditionNo()获取goodsConditionNo的属性值
     *
     * @param goodsConditionNo goodsConditionNo
     */
    private void setGoodsConditionNo(String goodsConditionNo) {
        this.goodsConditionNo = goodsConditionNo;
    }
}
