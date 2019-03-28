package com.lwc.discount.model.goods;

import com.lwc.discount.enums.DiscountCalcResultTypeEnum;
import com.lwc.discount.model.discount.business.result.GoodsDiscountResult;
import com.lwc.discount.utils.NumberUtils;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * 商品类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Goods implements Serializable {
    /**
     * 交易中商品行号<br>
     * 在单笔交易中的唯一商品行标识<br>
     * 行号必须大于0
     */
    private Integer lineNo;
    /**
     * 分类编号<br>
     * 长度区间为【0-150】
     * 手动设定时不可以设定为空字符串(""," "),否则引擎将默认为未设置商品分类<br>
     * 当商品并未进行分类时,该栏位可以不进行数据设定,未进行分类设定的商品都将被划分为同一分类
     */
    private String groupNo;
    /**
     * 自定义分类编号<br>
     * 长度区间为【0-150】
     * 手动设定时不可以设定为空字符串(""," "),否则引擎将默认为未设置商品分类<br>
     * 当商品并未进行分类时,该栏位可以不进行数据设定,未进行分类设定的商品都将被划分为同一分类
     */
    private String customGroupNo;
    /**
     * 条码<br>
     * 不得为null 且 长度区间为【6-50】
     * 在公司下是商品的唯一标识
     */
    private String barcode;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 单价<br>
     * 不得为null 且 不得小于0
     */
    private Double price;
    /**
     * 数量<br>
     * 不得为null 且 不得小于且等于0<br>
     */
    private Double quantity;
    /**
     * 包装<br>
     * 长度区间为【0-10】
     */
    private String pack;


    /**
     * 原始金额(处理中自定义属性)
     */
    private Double originalAmount;
    /**
     * 折扣金额(处理中自定义属性)
     */
    private Double discAmount;
    /**
     * 商品行折扣赠送积点(处理中自定义属性)
     */
    private Double discPoint;
    /**
     * 商品行折扣赠送印花(处理中自定义属性)
     */
    private Double discStamp;
    /**
     * 商品行折扣赠送积分(处理中自定义属性)
     */
    private Double discIntegral;
    /**
     * 最终金额(处理中自定义属性)
     */
    private Double finalAmount;
    /**
     * 商品折扣结果(处理中自定义属性)
     */
    private List<GoodsDiscountResult> discResultList;


    /**
     * 重新定义商品折扣信息<br>
     * 在每个折扣处理完成后都需要调用该方法设定折扣信息
     * @param rounding  金额保留精度
     */
    public void resetGoodsDiscInfo(int rounding){
        this.discAmount = 0D;
        this.discPoint = 0D;
        this.discStamp = 0D;
        this.discIntegral = 0D;

        if(this.discResultList!=null&&!this.discResultList.isEmpty()){
            Map<String,GoodsDiscountResult> reorganizeDiscResultMap = new LinkedHashMap<>();

            for(GoodsDiscountResult discountResult:this.discResultList){
                if(DiscountCalcResultTypeEnum.AMOUNT.name().equals(discountResult.getDiscCalcResultType())){
                    this.discAmount += discountResult.getDiscAmount();
                }else if(DiscountCalcResultTypeEnum.QUANTITY.name().equals(discountResult.getDiscCalcResultType())){
                    this.discAmount += discountResult.getDiscAmount();
                }else if(DiscountCalcResultTypeEnum.POINT.name().equals(discountResult.getDiscCalcResultType())){
                    this.discPoint += discountResult.getDiscValue();
                }else if(DiscountCalcResultTypeEnum.STAMP.name().equals(discountResult.getDiscCalcResultType())){
                    this.discStamp += discountResult.getDiscValue();
                }else if(DiscountCalcResultTypeEnum.INTEGRAL.name().equals(discountResult.getDiscCalcResultType())){
                    this.discIntegral += discountResult.getDiscValue();
                }

                if(reorganizeDiscResultMap.containsKey(discountResult.getDiscUniqueNo())){
                    GoodsDiscountResult tempDiscountResult = reorganizeDiscResultMap.get(discountResult.getDiscUniqueNo());
                    tempDiscountResult.setDiscValue(NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.add(tempDiscountResult.getDiscValue(),discountResult.getDiscValue())));
                    tempDiscountResult.setDiscAmount(NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.add(tempDiscountResult.getDiscAmount(),discountResult.getDiscAmount())));
                    tempDiscountResult.setDiscCount(NumberUtils.FormatInteger.formatValue(NumberUtils.add(tempDiscountResult.getDiscCount(),discountResult.getDiscCount())));
                    reorganizeDiscResultMap.put(discountResult.getDiscUniqueNo(),tempDiscountResult);
                }else{
                    reorganizeDiscResultMap.put(discountResult.getDiscUniqueNo(),discountResult);
                }
            }

            this.discResultList.clear();
            Iterator<Map.Entry<String,GoodsDiscountResult>> iterator = reorganizeDiscResultMap.entrySet().iterator();
            while (iterator.hasNext()) {
                this.discResultList.add(iterator.next().getValue());
            }

            this.discAmount = NumberUtils.FormatDouble.formatValue(rounding,this.discAmount);
            this.discPoint = NumberUtils.FormatDouble.formatValue(rounding,this.discPoint);
            this.discStamp = NumberUtils.FormatDouble.formatValue(rounding,this.discStamp);
            this.discIntegral = NumberUtils.FormatDouble.formatValue(rounding,this.discIntegral);
        }

        this.originalAmount = NumberUtils.FormatDouble.formatValue(rounding, NumberUtils.mul(this.getCaclQuantity(),this.getPrice()));
        this.finalAmount = NumberUtils.FormatDouble.formatValue(rounding, NumberUtils.sub(this.getOriginalAmount(),this.getDiscAmount()));
    }

    /**
     * 获取商品最终单价
     * @param rounding  金额保留精度
     * @return 返回商品最终单价
     */
    public double getFinalPrice(int rounding){
        return NumberUtils.FormatDouble.formatValue(rounding, NumberUtils.div(this.getFinalAmount(),this.getCaclQuantity()));
    }

    /**
     * 获取商品最终单价
     * @param rounding      金额保留精度
     * @param appendAmount  增加的金额
     * @return 返回商品最终单价
     */
    public double getFinalPrice(int rounding,double appendAmount){
        return NumberUtils.FormatDouble.formatValue(rounding, NumberUtils.div(NumberUtils.add(this.getFinalAmount(),appendAmount),this.getCaclQuantity()));
    }

    /**
     * 获取quantity属性值(逻辑计算)
     *
     * @return quantity属性值
     */
    public int getCaclQuantity() {
        if(quantity==null){
            quantity = 1D;
        }
        if(this.getQuantity()*10/10!=this.getQuantity()){
            return 1;
        }else{
            return quantity.intValue();
        }
    }

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
     * 获取groupNo属性值
     *
     * @return groupNo属性值
     */
    public String getGroupNo() {
        if(StringUtils.isBlank(groupNo)){
            groupNo = "";
        }
        return groupNo;
    }

    /**
     * 设置groupNo属性值
     * 可以使用getGroupNo()获取groupNo的属性值
     *
     * @param groupNo groupNo
     */
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getCustomGroupNo() {
        if(StringUtils.isBlank(customGroupNo)){
            customGroupNo = "";
        }
        return customGroupNo;
    }

    public void setCustomGroupNo(String customGroupNo) {
        this.customGroupNo = customGroupNo;
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
     * 获取quantity属性值
     *
     * @return quantity属性值
     */
    public Double getQuantity() {
        if(quantity==null){
            quantity = 1D;
        }
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
     * 获取pack属性值
     *
     * @return pack属性值
     */
    public String getPack() {
        if(pack==null){
            pack = "";
        }
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
     * 设置originalAmount属性值<br>
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
        if(discAmount==null){
            discAmount = 0D;
        }
        if(discResultList!=null){

        }
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
        if(discPoint==null){
            discPoint = 0D;
        }
        return discPoint;
    }

    /**
     * 设置discPoint属性值<br>
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
        if(discStamp==null){
            discStamp = 0D;
        }
        return discStamp;
    }

    /**
     * 设置discStamp属性值<br>
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
        if(discIntegral==null){
            discIntegral = 0D;
        }
        return discIntegral;
    }

    /**
     * 设置discIntegral属性值<br>
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
        if(finalAmount==null){
            finalAmount = 0D;
        }
        return finalAmount;
    }

    /**
     * 设置finalAmount属性值<br>
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
        if (discResultList==null){
            discResultList = new ArrayList<>();
        }
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
