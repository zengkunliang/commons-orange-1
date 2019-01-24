package com.gorange.discount.engine.model.discount.business;

import com.gorange.discount.engine.model.discount.business.joincondition.JoinCondition;

import java.io.Serializable;

/**
 * 折扣参与条件类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DiscountJoinCondition implements Serializable {
    /**
     * 是否有参与条件<br>
     * 不排除没有任何条件限制可以直接进行折扣计算<br>
     * 当未提供该栏位值时默认为false,将不会进行参与条件验证
     */
    private Boolean haveCondition;
    /**
     * 参与条件参数
     */
    private JoinCondition condition;

    /**
     * 获取haveCondition属性值
     *
     * @return haveCondition属性值
     */
    public Boolean getHaveCondition() {
        if(haveCondition==null){
            haveCondition = false;
        }
        return haveCondition;
    }

    /**
     * 设置haveCondition属性值
     * 可以使用getHaveCondition()获取haveCondition的属性值
     *
     * @param haveCondition haveCondition
     */
    public void setHaveCondition(Boolean haveCondition) {
        this.haveCondition = haveCondition;
    }

    /**
     * 获取condition属性值
     *
     * @return condition属性值
     */
    public JoinCondition getCondition() {
        return condition;
    }

    /**
     * 设置condition属性值
     * 可以使用getCondition()获取condition的属性值
     *
     * @param condition condition
     */
    public void setCondition(JoinCondition condition) {
        this.condition = condition;
    }
}
