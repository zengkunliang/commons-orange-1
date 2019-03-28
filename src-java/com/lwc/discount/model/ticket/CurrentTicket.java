package com.lwc.discount.model.ticket;

import com.lwc.discount.model.goods.Goods;
import com.lwc.discount.model.member.Member;
import com.lwc.discount.utils.DateUtils;
import com.lwc.discount.utils.NumberUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 当前交易类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class CurrentTicket implements Serializable {

    /**
     * 交易公司<br>
     * 公司的唯一标识<br>
     * 不得为null 或 "" 或 " "<br>
     * 长度区间为【1-50】
     */
    private String companyNo;
    /**
     * 交易时间<br>
     * 默认引擎当前时间
     */
    private Date businessTime;
    /**
     * 交易时区<br>
     * 默认北京时区(GMT+08:00)
     */
    private String timeZone;
    /**
     * 交易单号<br>
     * 不得为null 或 "" 或 " "
     * 长度区间为【1-50】
     */
    private String orderNo;
    /**
     * 金额保留精度<br>
     * 默认保留2位
     */
    private Integer rounding;
    /**
     * 交易顾客
     */
    private Member member;
    /**
     * 交易商品<br>
     * 不得为null
     */
    private List<Goods> goodsList;
    /**
     * 交易笔数信息
     * <pre>
     * 提供交易笔数信息
     * 根据DiscountTicketTypeEnum,DiscountTicketJoinTypeEnum两个枚举类进行数据组装,传入需要的数据
     *   //该交易为公司第1笔交易
     *   COMPANY_TICKET-ALL:1
     *
     *   //该交易为公司当天第1笔交易
     *   COMPANY_TICKET-DAY:1
     *
     *   //该交易为公司当月第1笔交易
     *   COMPANY_TICKET-MONTH:1
     *
     *   //该交易为公司当季第1笔交易
     *   COMPANY_TICKET-QUARTER:1
     *
     *   //该交易为公司当年第1笔交易
     *   COMPANY_TICKET-YEAR:1
     *
     * </pre>
     */
    private Map<String,Integer> ticketCountMap;


    /**
     * 原始金额(处理中自定义属性)
     */
    private Double originalAmount;
    /**
     * 折扣金额(处理中自定义属性)
     */
    private Double discAmount;
    /**
     * 折扣赠送积点(处理中自定义属性)
     */
    private Double discPoint;
    /**
     * 折扣赠送印花(处理中自定义属性)
     */
    private Double discStamp;
    /**
     * 折扣赠送积分(处理中自定义属性)
     */
    private Double discIntegral;
    /**
     * 最终金额(处理中自定义属性)
     */
    private Double finalAmount;


    /**
     * 重新定义当前交易金额信息<br>
     * 在每个折扣处理完成后都需要调用该方法设定交易金额
     */
    public void resetTicketAmountInfo(){
        this.originalAmount = 0D;
        this.discAmount = 0D;
        this.discPoint = 0D;
        this.discStamp = 0D;
        this.discIntegral = 0D;
        this.finalAmount = 0D;

        if(this.goodsList!=null&&!this.goodsList.isEmpty()){
            for(Goods goods:this.goodsList){
                goods.resetGoodsDiscInfo(this.rounding);

                double goodsOriginalAmount = NumberUtils.mul(goods.getQuantity(),goods.getPrice());

                this.originalAmount += goodsOriginalAmount;
                this.discAmount += goods.getDiscAmount();
                this.discPoint += goods.getDiscPoint();
                this.discStamp += goods.getDiscStamp();
                this.discIntegral += goods.getDiscIntegral();
            }
            this.originalAmount = NumberUtils.FormatDouble.formatValue(this.getRounding(),this.originalAmount);
            this.discAmount = NumberUtils.FormatDouble.formatValue(this.getRounding(),this.discAmount);
            this.discPoint = NumberUtils.FormatDouble.formatValue(this.getRounding(),this.discPoint);
            this.discStamp = NumberUtils.FormatDouble.formatValue(this.getRounding(),this.discStamp);
            this.discIntegral = NumberUtils.FormatDouble.formatValue(this.getRounding(),this.discIntegral);
            this.finalAmount = NumberUtils.FormatDouble.formatValue(this.getRounding(),NumberUtils.sub(this.originalAmount,this.discAmount));
        }
    }

    /**
     * 获取companyNo属性值
     *
     * @return companyNo属性值
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * 设置companyNo属性值<br>
     * 可以使用getCompanyNo()获取companyNo的属性值
     *
     * @param companyNo companyNo
     */
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    /**
     * 获取businessTime属性值
     *
     * @return businessTime属性值
     */
    public Date getBusinessTime() {
        if(businessTime==null){
            businessTime = new Date();
        }
        return (Date) businessTime.clone();
    }

    /**
     * 设置businessTime属性值
     * 可以使用getBusinessTime()获取businessTime的属性值
     *
     * @param businessTime businessTime
     */
    public void setBusinessTime(Object businessTime) {
        Date tempBusinessTime;
        if(businessTime instanceof String){
            tempBusinessTime = DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),(String)businessTime);
        }else if(businessTime instanceof Number){
            tempBusinessTime = new Date(((Number) businessTime).longValue());
        }else if(businessTime instanceof Date){
            tempBusinessTime = (Date) ((Date)businessTime).clone();
        }else{
            tempBusinessTime = null;
        }
        this.businessTime = tempBusinessTime;
    }

    /**
     * 获取timeZone属性值
     *
     * @return timeZone属性值
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * 设置timeZone属性值
     * 可以使用getTimeZone()获取timeZone的属性值
     *
     * @param timeZone timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * 获取orderNo属性值
     *
     * @return orderNo属性值
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置orderNo属性值
     * 可以使用getOrderNo()获取orderNo的属性值
     *
     * @param orderNo orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取rounding属性值
     *
     * @return rounding属性值
     */
    public Integer getRounding() {
        if(rounding==null){
            rounding = 2;
        }
        return rounding;
    }

    /**
     * 设置rounding属性值<br>
     * 可以使用getRounding()获取rounding的属性值
     *
     * @param rounding rounding
     */
    public void setRounding(Integer rounding) {
        this.rounding = rounding;
    }

    /**
     * 获取member属性值
     *
     * @return member属性值
     */
    public Member getMember() {
        if(member==null){
            member = new Member();
        }
        return member;
    }

    /**
     * 设置member属性值
     * 可以使用getMember()获取member的属性值
     *
     * @param member member
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * 获取goodsList属性值
     *
     * @return goodsList属性值
     */
    public List<Goods> getGoodsList() {
        return goodsList;
    }

    /**
     * 设置goodsList属性值
     * 可以使用getGoodsList()获取goodsList的属性值
     *
     * @param goodsList goodsList
     */
    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    /**
     * 设置businessTime属性值
     * 可以使用getBusinessTime()获取businessTime的属性值
     *
     * @param businessTime businessTime
     */
    public void setBusinessTime(Date businessTime) {
        this.businessTime = (Date) businessTime.clone();
    }

    /**
     * 获取ticketCountMap属性值
     *
     * @return ticketCountMap属性值
     */
    public Map<String, Integer> getTicketCountMap() {
        return ticketCountMap;
    }

    /**
     * 设置ticketCountMap属性值
     * 可以使用getTicketCountMap()获取ticketCountMap的属性值
     *
     * @param ticketCountMap ticketCountMap
     */
    public void setTicketCountMap(Map<String, Integer> ticketCountMap) {
        this.ticketCountMap = ticketCountMap;
    }

    /**
     * 获取originalAmount属性值
     *
     * @return originalAmount属性值
     */
    public Double getOriginalAmount() {
        if(originalAmount==null){
            originalAmount = 0D;
        }
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
        if(discAmount==null){
            discAmount = 0D;
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
     * 设置finalAmount属性值
     * 可以使用getFinalAmount()获取finalAmount的属性值
     *
     * @param finalAmount finalAmount
     */
    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }
}
