package com.gorange.discount.engine.model.discount.business.joincondition;

import java.io.Serializable;

/**
 * 折扣参与条件参数类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinCondition implements Serializable {
    /**
     * 折扣参与条件顾客类
     */
    private JoinConditionMember conditionMember;
    /**
     * 折扣参与条件商品类
     */
    private JoinConditionGoods conditionGoods;
    /**
     * 折扣参与条件交易类
     */
    private JoinConditionTicket conditionTicket;

    /**
     * 获取conditionMember属性值
     *
     * @return conditionMember属性值
     */
    public JoinConditionMember getConditionMember() {
        return conditionMember;
    }

    /**
     * 设置conditionMember属性值
     * 可以使用getConditionMember()获取conditionMember的属性值
     *
     * @param conditionMember conditionMember
     */
    public void setConditionMember(JoinConditionMember conditionMember) {
        this.conditionMember = conditionMember;
    }

    /**
     * 获取conditionGoods属性值
     *
     * @return conditionGoods属性值
     */
    public JoinConditionGoods getConditionGoods() {
        return conditionGoods;
    }

    /**
     * 设置conditionGoods属性值
     * 可以使用getConditionGoods()获取conditionGoods的属性值
     *
     * @param conditionGoods conditionGoods
     */
    public void setConditionGoods(JoinConditionGoods conditionGoods) {
        this.conditionGoods = conditionGoods;
    }

    /**
     * 获取conditionTicket属性值
     *
     * @return conditionTicket属性值
     */
    public JoinConditionTicket getConditionTicket() {
        return conditionTicket;
    }

    /**
     * 设置conditionTicket属性值
     * 可以使用getConditionTicket()获取conditionTicket的属性值
     *
     * @param conditionTicket conditionTicket
     */
    public void setConditionTicket(JoinConditionTicket conditionTicket) {
        this.conditionTicket = conditionTicket;
    }
}
