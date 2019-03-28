package com.lwc.discount.model.discount.business.joincondition;

import java.io.Serializable;

/**
 * 折扣参与条件参数交易类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionTicket implements Serializable {

    /**
     * 当前交易参与折扣条件
     */
    private JoinConditionTicketForCurrent currentTicket;
    /**
     * 关联交易参与折扣条件
     */
    private JoinConditionTicketForAssociated associatedTicket;

    /**
     * 获取currentTicket属性值
     *
     * @return currentTicket属性值
     */
    public JoinConditionTicketForCurrent getCurrentTicket() {
        return currentTicket;
    }

    /**
     * 设置currentTicket属性值
     * 可以使用getCurrentTicket()获取currentTicket的属性值
     *
     * @param currentTicket currentTicket
     */
    public void setCurrentTicket(JoinConditionTicketForCurrent currentTicket) {
        this.currentTicket = currentTicket;
    }

    /**
     * 获取associatedTicket属性值
     *
     * @return associatedTicket属性值
     */
    public JoinConditionTicketForAssociated getAssociatedTicket() {
        return associatedTicket;
    }

    /**
     * 设置associatedTicket属性值
     * 可以使用getAssociatedTicket()获取associatedTicket的属性值
     *
     * @param associatedTicket associatedTicket
     */
    public void setAssociatedTicket(JoinConditionTicketForAssociated associatedTicket) {
        this.associatedTicket = associatedTicket;

    }
}
