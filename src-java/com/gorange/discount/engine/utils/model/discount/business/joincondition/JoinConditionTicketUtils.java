package com.gorange.discount.engine.utils.model.discount.business.joincondition;

import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.joincondition.JoinConditionTicket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 交易工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionTicketUtils {
    private static Logger log = LoggerFactory.getLogger(JoinConditionTicketUtils.class);

    /**
     * 折扣参与条件参数交易属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣参与条件参数交易属性的条件限制
     * </pre>
     * @param joinConditionTicket   折扣参与条件参数交易类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(JoinConditionTicket joinConditionTicket, BusinessMessage businessMessage){
        if(!JoinConditionTicketUtils.validParamForCurrentTicket(joinConditionTicket, businessMessage)){
            log.debug("折扣参与条件参数交易属性(当前交易)验证未通过");
            return false;
        }
        if(!JoinConditionTicketUtils.validParamForAssociatedTicket(joinConditionTicket, businessMessage)){
            log.debug("折扣参与条件参数交易属性(关联交易)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣参与条件参数交易属性(当前交易)验证
     * @param joinConditionTicket   折扣参与条件参数交易类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCurrentTicket(JoinConditionTicket joinConditionTicket, BusinessMessage businessMessage){
        if(joinConditionTicket.getCurrentTicket()!=null){
            log.debug("开始折扣参与条件交易参数当前交易属性验证");
            boolean valid = JoinConditionTicketForCurrentUtils.validAllParam(joinConditionTicket.getCurrentTicket(), businessMessage);
            log.debug("完成折扣参与条件交易参数当前交易属性验证{}",valid);
            return valid;
        }
        return true;
    }

    /**
     * 折扣参与条件参数交易属性(关联交易)验证
     * @param joinConditionTicket   折扣参与条件参数交易类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForAssociatedTicket(JoinConditionTicket joinConditionTicket, BusinessMessage businessMessage){
        if(joinConditionTicket.getAssociatedTicket()!=null){
            log.debug("开始折扣参与条件交易参数关联交易属性验证");
            boolean valid = JoinConditionTicketForAssociatedUtils.validAllParam(joinConditionTicket.getAssociatedTicket(), businessMessage);
            log.debug("完成折扣参与条件交易参数关联交易属性验证{}",valid);
            return valid;
        }
        return true;
    }
}
