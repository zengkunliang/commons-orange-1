package com.lwc.discount.utils.model.discount.business.joincondition;

import com.lwc.discount.model.common.BusinessMessage;
import com.lwc.discount.model.discount.business.joincondition.JoinCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 折扣参与条件参数工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionUtils {
    private static Logger log = LoggerFactory.getLogger(JoinConditionUtils.class);

    /**
     * 折扣参与条件参数属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣参与条件参数属性的条件限制
     * </pre>
     * @param joinCondition 折扣参与条件参数类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(JoinCondition joinCondition, BusinessMessage businessMessage){
        if(!JoinConditionUtils.validParamForConditionMember(joinCondition, businessMessage)){
            log.debug("折扣参与条件参数属性(顾客参数)验证未通过");
            return false;
        }
        if(!JoinConditionUtils.validParamForConditionGoods(joinCondition, businessMessage)){
            log.debug("折扣参与条件参数属性(商品参数)验证未通过");
            return false;
        }
        if(!JoinConditionUtils.validParamForConditionTicket(joinCondition, businessMessage)){
            log.debug("折扣参与条件参数属性(交易参数)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣参与条件属性(顾客参数)验证
     * @param joinCondition 折扣参与条件参数对象
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForConditionMember(JoinCondition joinCondition, BusinessMessage businessMessage){
        if(joinCondition.getConditionMember()!=null){
            log.debug("开始折扣参与条件参数顾客属性验证");
            boolean valid = JoinConditionMemberUtils.validAllParam(joinCondition.getConditionMember(), businessMessage);
            log.debug("完成折扣参与条件参数顾客属性验证{}",valid);
            return valid;
        }
        return true;
    }

    /**
     * 折扣参与条件属性(商品参数)验证
     * @param joinCondition 折扣参与条件参数对象
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForConditionGoods(JoinCondition joinCondition, BusinessMessage businessMessage){
        if(joinCondition.getConditionGoods()!=null){
            log.debug("开始折扣参与条件参数商品属性验证");
            boolean valid = JoinConditionGoodsUtils.validAllParam(joinCondition.getConditionGoods(), businessMessage);
            log.debug("完成折扣参与条件参数商品属性验证{}",valid);
            return valid;
        }
        return true;
    }

    /**
     * 折扣参与条件属性(交易参数)验证
     * @param joinCondition 折扣参与条件参数对象
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForConditionTicket(JoinCondition joinCondition, BusinessMessage businessMessage){
        if(joinCondition.getConditionGoods()!=null){
            log.debug("开始折扣参与条件参数交易属性验证");
            boolean valid = JoinConditionTicketUtils.validAllParam(joinCondition.getConditionTicket(), businessMessage);
            log.debug("完成折扣参与条件参数交易属性验证{}",valid);
            return valid;
        }
        return true;
    }

}
