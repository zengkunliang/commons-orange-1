package com.gorange.discount.engine.model.discount.business.calccondition;

import java.io.Serializable;
import java.util.List;

/**
 * 折扣计算条件参数类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcCondition implements Serializable {
    /**
     * 折扣计算条件商品类
     */
    private List<CalcConditionGoods> conditionGoods;
    /**
     * 折扣计算条件值类
     */
    private CalcConditionValue conditionValue;

    /**
     * 获取conditionGoods属性值
     *
     * @return conditionGoods属性值
     */
    public List<CalcConditionGoods> getConditionGoods() {
        return conditionGoods;
    }

    /**
     * 设置conditionGoods属性值
     * 可以使用getConditionGoods()获取conditionGoods的属性值
     *
     * @param conditionGoods conditionGoods
     */
    public void setConditionGoods(List<CalcConditionGoods> conditionGoods) {
        this.conditionGoods = conditionGoods;
    }

    /**
     * 获取conditionValue属性值
     *
     * @return conditionValue属性值
     */
    public CalcConditionValue getConditionValue() {
        return conditionValue;
    }

    /**
     * 设置conditionValue属性值<br>
     * 可以使用getConditionValue()获取conditionValue的属性值
     *
     * @param conditionValue conditionValue
     */
    public void setConditionValue(CalcConditionValue conditionValue) {
        this.conditionValue = conditionValue;
    }
}
