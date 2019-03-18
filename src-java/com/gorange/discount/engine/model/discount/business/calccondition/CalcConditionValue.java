package com.gorange.discount.engine.model.discount.business.calccondition;

import java.io.Serializable;
import java.util.Map;

/**
 * 折扣参与条件当前交易类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionValue implements Serializable {
    /**
     * 交易最大折扣值
     * key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     * key有正确有效的值时,value不可为null
     * value数据必须大于0
     */
    private Map<String,Double> ticketMaxDiscValue;
    /**
     * 会员最大折扣值
     * key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     * key有正确有效的值时,value不可为null
     * value数据必须大于0
     * value数据必须大于或等于ticketMaxDiscAmount value数据
     */
    private Map<String,Double> memberMaxDiscValue;
    /**
     * 公司最大折扣值
     * key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     * key有正确有效的值时,value不可为null
     * value数据必须大于0
     * value数据必须大于或等于memberMaxDiscAmount value数据
     */
    private Map<String,Double> companyMaxDiscValue;

    /**
     * 获取ticketMaxDiscValue属性值
     *
     * @return ticketMaxDiscValue属性值
     */
    public Map<String, Double> getTicketMaxDiscValue() {
        return ticketMaxDiscValue;
    }

    /**
     * 设置ticketMaxDiscValue属性值<br>
     * 可以使用getTicketMaxDiscValue()获取ticketMaxDiscValue的属性值
     *
     * @param ticketMaxDiscValue ticketMaxDiscValue
     */
    public void setTicketMaxDiscValue(Map<String, Double> ticketMaxDiscValue) {
        this.ticketMaxDiscValue = ticketMaxDiscValue;
    }

    /**
     * 获取memberMaxDiscValue属性值
     *
     * @return memberMaxDiscValue属性值
     */
    public Map<String, Double> getMemberMaxDiscValue() {
        return memberMaxDiscValue;
    }

    /**
     * 设置memberMaxDiscValue属性值<br>
     * 可以使用getMemberMaxDiscValue()获取memberMaxDiscValue的属性值
     *
     * @param memberMaxDiscValue memberMaxDiscValue
     */
    public void setMemberMaxDiscValue(Map<String, Double> memberMaxDiscValue) {
        this.memberMaxDiscValue = memberMaxDiscValue;
    }

    /**
     * 获取companyMaxDiscValue属性值
     *
     * @return companyMaxDiscValue属性值
     */
    public Map<String, Double> getCompanyMaxDiscValue() {
        return companyMaxDiscValue;
    }

    /**
     * 设置companyMaxDiscValue属性值<br>
     * 可以使用getCompanyMaxDiscValue()获取companyMaxDiscValue的属性值
     *
     * @param companyMaxDiscValue companyMaxDiscValue
     */
    public void setCompanyMaxDiscValue(Map<String, Double> companyMaxDiscValue) {
        this.companyMaxDiscValue = companyMaxDiscValue;
    }
}
