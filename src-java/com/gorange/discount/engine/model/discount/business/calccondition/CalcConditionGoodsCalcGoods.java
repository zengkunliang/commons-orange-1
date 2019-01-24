package com.gorange.discount.engine.model.discount.business.calccondition;

import com.gorange.discount.engine.enums.RelationshipKeyEnum;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 计算商品类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionGoodsCalcGoods implements Serializable {
    /**
     * 是否迭代,默认false
     */
    private Boolean iteration;
    /**
     * 计算商品条件对象关联关系,默认为AND<br>
     * RelationshipKeyEnum中的数据
     */
    private String expression;
    /**
     * 计算商品条件
     */
    private List<CalcConditionGoodsCalcGoodsCondition> condition;

    /**
     * 获取iteration属性值
     *
     * @return iteration属性值
     */
    public Boolean getIteration() {
        if(iteration==null){
            iteration = false;
        }
        return iteration;
    }

    /**
     * 设置iteration属性值
     * 可以使用getIteration()获取iteration的属性值
     *
     * @param iteration iteration
     */
    public void setIteration(Boolean iteration) {
        this.iteration = iteration;
    }

    /**
     * 获取expression属性值
     *
     * @return expression属性值
     */
    public String getExpression() {
        if(StringUtils.isBlank(expression)){
            expression = RelationshipKeyEnum.AND.name();
        }
        return expression;
    }

    /**
     * 设置experssion属性值
     * 可以使用getExperssion()获取experssion的属性值
     *
     * @param expression expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * 获取condition属性值
     *
     * @return condition属性值
     */
    public List<CalcConditionGoodsCalcGoodsCondition> getCondition() {
        return condition;
    }

    /**
     * 设置condition属性值
     * 可以使用getCondition()获取condition的属性值
     *
     * @param condition condition
     */
    public void setCondition(List<CalcConditionGoodsCalcGoodsCondition> condition) {
        this.condition = condition;
    }
}
