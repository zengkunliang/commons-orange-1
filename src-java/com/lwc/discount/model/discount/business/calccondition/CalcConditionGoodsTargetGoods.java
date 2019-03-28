package com.lwc.discount.model.discount.business.calccondition;

import com.lwc.discount.enums.CalcGoodsSortTypeEnum;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 目标商品
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionGoodsTargetGoods implements Serializable {
    /**
     * 分类编号<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     */
    private Map<String,List<String>> groupNo;
    /**
     * 条码
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     */
    private Map<String,List<String>> barcode;
    /**
     * 目标商品排序(单价排序),默认无序<br>
     * 匹配CalcGoodsSortTypeEnum中的数据
     */
    private String sortType;
    /**
     * 一共几件商品发生折扣 默认:0<br>
     * 小于0:所有可参与折扣商品全部发生折扣 等于0:且将折扣分摊到所有可参与折扣的商品上 大于0:在几件商品上发生折扣<br>
     * 上面描述的折扣解释为:折扣值*可以参与折扣次数
     */
    private Integer discGoodsCount;
    /**
     * 折扣值<br>
     * 折扣值必须大于0
     */
    private Double discValue;

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
     * 获取sortType属性值
     *
     * @return sortType属性值
     */
    public String getSortType() {
        if(StringUtils.isBlank(sortType)){
            sortType = CalcGoodsSortTypeEnum.NONE.name();
        }
        return sortType;
    }

    /**
     * 设置sortType属性值
     * 可以使用getSortType()获取sortType的属性值
     *
     * @param sortType sortType
     */
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    /**
     * 获取discGoodsCount属性值
     *
     * @return discGoodsCount属性值
     */
    public Integer getDiscGoodsCount() {
        if(discGoodsCount==null){
            discGoodsCount = 0;
        }
        return discGoodsCount;
    }

    /**
     * 设置discGoodsCount属性值
     * 可以使用getDiscGoodsCount()获取discGoodsCount的属性值
     *
     * @param discGoodsCount discGoodsCount
     */
    public void setDiscGoodsCount(Integer discGoodsCount) {
        this.discGoodsCount = discGoodsCount;
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
}
