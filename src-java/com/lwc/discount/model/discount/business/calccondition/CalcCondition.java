package com.lwc.discount.model.discount.business.calccondition;

import java.io.Serializable;

/**
 * 折扣计算条件参数类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcCondition implements Serializable {
    /**
     * 计算商品
     */
    private CalcConditionGoodsCalcGoods calcGoods;
    /**
     * 目标商品
     */
    private CalcConditionGoodsTargetGoods targetGoods;

    /**
     * 获取calcGoods属性值
     *
     * @return calcGoods属性值
     */
    public CalcConditionGoodsCalcGoods getCalcGoods() {
        return calcGoods;
    }

    /**
     * 设置calcGoods属性值
     * 可以使用getCalcGoods()获取calcGoods的属性值
     *
     * @param calcGoods calcGoods
     */
    public void setCalcGoods(CalcConditionGoodsCalcGoods calcGoods) {
        this.calcGoods = calcGoods;
    }

    /**
     * 获取targetGoods属性值
     *
     * @return targetGoods属性值
     */
    public CalcConditionGoodsTargetGoods getTargetGoods() {
        return targetGoods;
    }

    /**
     * 设置targetGoods属性值
     * 可以使用getTargetGoods()获取targetGoods的属性值
     *
     * @param targetGoods targetGoods
     */
    public void setTargetGoods(CalcConditionGoodsTargetGoods targetGoods) {
        this.targetGoods = targetGoods;
    }
}
