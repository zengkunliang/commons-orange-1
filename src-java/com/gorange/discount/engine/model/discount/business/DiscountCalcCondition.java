package com.gorange.discount.engine.model.discount.business;

import com.gorange.discount.engine.model.discount.business.calccondition.CalcCondition;

import java.io.Serializable;

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
     * 计算条件参数
     */
    private CalcCondition condition;

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
     * 获取condition属性值
     *
     * @return condition属性值
     */
    public CalcCondition getCondition() {
        return condition;
    }

    /**
     * 设置condition属性值
     * 可以使用getCondition()获取condition的属性值
     *
     * @param condition condition
     */
    public void setCondition(CalcCondition condition) {
        this.condition = condition;
    }
}
