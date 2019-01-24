package com.gorange.discount.engine.model.discount.business.joincondition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 折扣参与条件交易参数关联交易类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionTicketForAssociated implements Serializable {
    /**
     * 交易所属商户
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null<br>
     * value长度区间【1-50】
     */
    private Map<String,List<String>> merchantNo;
    /**
     * 参与交易发生的时间区间(开始时间)<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是GR_AND_EQ<br>
     * key有正确有效的值时,value不可为null<br>
     * 数据格式为【yyyy-MM-dd'T'HH:mm:ss Z】
     */
    private Map<String,String>  ticketStartTime;
    /**
     * 参与交易发生的时间区间(结束时间)<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是LE_AND_EQ
     * key有正确有效的值时,value不可为null<br>
     * 数据格式为【yyyy-MM-dd'T'HH:mm:ss Z】
     */
    private Map<String,String>  ticketEndTime;
    /**
     * 交易总金额<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是EQ 或 GR_AND_EQ 或 LE_AND_EQ
     * key有正确有效的值时,value不可为null
     */
    private Map<String,Double> ticketTotalAmount;

    /**
     * 获取merchantNo属性值
     *
     * @return merchantNo属性值
     */
    public Map<String, List<String>> getMerchantNo() {
        return merchantNo;
    }

    /**
     * 设置merchantNo属性值
     * 可以使用getMerchantNo()获取merchantNo的属性值
     *
     * @param merchantNo merchantNo
     */
    public void setMerchantNo(Map<String, List<String>> merchantNo) {
        this.merchantNo = merchantNo;
    }

    /**
     * 获取ticketStartTime属性值
     *
     * @return ticketStartTime属性值
     */
    public Map<String, String> getTicketStartTime() {
        return ticketStartTime;
    }

    /**
     * 设置ticketStartTime属性值
     * 可以使用getTicketStartTime()获取ticketStartTime的属性值
     *
     * @param ticketStartTime ticketStartTime
     */
    public void setTicketStartTime(Map<String, String> ticketStartTime) {
        this.ticketStartTime = ticketStartTime;
    }

    /**
     * 获取ticketEndTime属性值
     *
     * @return ticketEndTime属性值
     */
    public Map<String, String> getTicketEndTime() {
        return ticketEndTime;
    }

    /**
     * 设置ticketEndTime属性值
     * 可以使用getTicketEndTime()获取ticketEndTime的属性值
     *
     * @param ticketEndTime ticketEndTime
     */
    public void setTicketEndTime(Map<String, String> ticketEndTime) {
        this.ticketEndTime = ticketEndTime;
    }

    /**
     * 获取ticketTotalAmount属性值
     *
     * @return ticketTotalAmount属性值
     */
    public Map<String, Double> getTicketTotalAmount() {
        return ticketTotalAmount;
    }

    /**
     * 设置ticketTotalAmount属性值
     * 可以使用getTicketTotalAmount()获取ticketTotalAmount的属性值
     *
     * @param ticketTotalAmount ticketTotalAmount
     */
    public void setTicketTotalAmount(Map<String, Double> ticketTotalAmount) {
        this.ticketTotalAmount = ticketTotalAmount;
    }
}
