package com.lwc.discount.model.discount.business;

import com.lwc.discount.model.discount.business.calccondition.CalcCondition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 折扣计算条件类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DiscountCalcCondition implements Serializable {
    /**
     * 折扣计算结果类型<br>
     * 匹配DiscountCalcResultTypeEnum中的数据
     */
    private String calcResultType;
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
     * 折扣计算条件参数类
     */
    private List<CalcCondition> condition;

    /**
     * 获取calcResultType属性值
     *
     * @return calcResultType属性值
     */
    public String getCalcResultType() {
        return calcResultType;
    }

    /**
     * 设置calcResultType属性值
     * 可以使用getCalcResultType()获取calcResultType的属性值
     *
     * @param calcResultType calcResultType
     */
    public void setCalcResultType(String calcResultType) {
        this.calcResultType = calcResultType;
    }

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

    /**
     * 获取condition属性值
     *
     * @return condition属性值
     */
    public List<CalcCondition> getCondition() {
        return condition;
    }

    /**
     * 设置condition属性值<br>
     * 可以使用getCondition()获取condition的属性值
     *
     * @param condition condition
     */
    public void setCondition(List<CalcCondition> condition) {
        this.condition = condition;
    }
}
