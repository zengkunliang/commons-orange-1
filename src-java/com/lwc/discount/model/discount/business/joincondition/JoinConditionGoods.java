package com.lwc.discount.model.discount.business.joincondition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 折扣参与条件参数商品类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionGoods implements Serializable {
    /**
     * 分类编号<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     */
    private Map<String,List<String>> groupNo;
    /**
     * 条码<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     */
    private Map<String,List<String>> barcode;
    /**
     * 最小单价<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * 最小金额的key只能是GR 或 GR_AND_EQ<br>
     * key有正确有效的值时,value不可为null
     * 最小金额必须大于0
     */
    private Map<String,Double> minPrice;
    /**
     * 最大单价<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * 最大金额的key只能是LE 或 LE_AND_EQ<br>
     * key有正确有效的值时,value不可为null
     * 最大金额必须大于0
     */
    private Map<String,Double> maxPrice;

    /**
     * 获取groupNo属性值
     *
     * @return groupNo属性值
     */
    public Map<String, List<String>> getGroupNo() {
        return groupNo;
    }

    /**
     * 设置groupNo属性值
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
     * 设置barcode属性值
     * 可以使用getBarcode()获取barcode的属性值
     *
     * @param barcode barcode
     */
    public void setBarcode(Map<String, List<String>> barcode) {
        this.barcode = barcode;
    }

    /**
     * 获取minPrice属性值
     *
     * @return minPrice属性值
     */
    public Map<String, Double> getMinPrice() {
        return minPrice;
    }

    /**
     * 设置minPrice属性值
     * 可以使用getMinPrice()获取minPrice的属性值
     *
     * @param minPrice minPrice
     */
    public void setMinPrice(Map<String, Double> minPrice) {
        this.minPrice = minPrice;
    }

    /**
     * 获取maxPrice属性值
     *
     * @return maxPrice属性值
     */
    public Map<String, Double> getMaxPrice() {
        return maxPrice;
    }

    /**
     * 设置maxPrice属性值
     * 可以使用getMaxPrice()获取maxPrice的属性值
     *
     * @param maxPrice maxPrice
     */
    public void setMaxPrice(Map<String, Double> maxPrice) {
        this.maxPrice = maxPrice;
    }
}
