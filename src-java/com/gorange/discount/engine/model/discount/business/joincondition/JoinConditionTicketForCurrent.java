package com.gorange.discount.engine.model.discount.business.joincondition;

import com.gorange.discount.engine.enums.DiscountTicketJoinTypeEnum;
import com.gorange.discount.engine.enums.DiscountTicketTypeEnum;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Map;

/**
 * 折扣参与条件交易参数当前交易类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionTicketForCurrent implements Serializable {
    /**
     * 参与的交易笔数序号(第几笔交易可以参与)<br>
     * 引擎默认为0(当前交易),不得大于Integer.MAX_VALUE<br>
     * 当数据为0时则没有任何逻辑检查,表示所有交易都可以。非0时则根据【type + joinType + iteration】进行交易笔数判别
     */
    private Integer index;
    /**
     * 折扣交易类型
     * <pre>
     *  COMPANY_TICKET:        公司交易
     *  COMPANY_POS_TICKET:    公司下的机台交易
     *  COMPANY_MEMBER_TICKET: 公司下的会员交易
     * </pre>
     * 引擎默认为【COMPANY_TICKET】
     */
    private String type;
    /**
     * 交易参与类型
     * <pre>
     *  ALL:        所有
     *  DAY:        当天
     *  MONTH:      当月
     *  QUARTER:    当季
     *  EAR:        当年
     * </pre>
     * 引擎默认为【ALL】
     */
    private String joinType;
    /**
     * 是否迭代<br>
     * 引擎默认为false
     */
    private Boolean iteration;
    /**
     * 交易总金额<br>
     * key只能是GR 或 GR_AND_EQ<br>
     * key有正确有效的值时,value不可为null<br>
     * value数据必须大于0
     */
    private Map<String,Double> ticketTotalAmount;

    /**
     * 获取index属性值
     *
     * @return index属性值
     */
    public Integer getIndex() {
        if(index==null){
            index = 0;
        }
        return index;
    }

    /**
     * 设置index属性值
     * 可以使用getIndex()获取index的属性值
     *
     * @param index index
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 获取type属性值
     *
     * @return type属性值
     */
    public String getType() {
        if(StringUtils.isBlank(type)){
            type = DiscountTicketTypeEnum.COMPANY_TICKET.name();
        }
        return type;
    }

    /**
     * 设置type属性值
     * 可以使用getType()获取type的属性值
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取joinType属性值
     *
     * @return joinType属性值
     */
    public String getJoinType() {
        if(StringUtils.isBlank(joinType)){
            joinType = DiscountTicketJoinTypeEnum.ALL.name();
        }
        return joinType;
    }

    /**
     * 设置joinType属性值
     * 可以使用getJoinType()获取joinType的属性值
     *
     * @param joinType joinType
     */
    public void setJoinType(String joinType) {
        this.joinType = joinType;
    }

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
