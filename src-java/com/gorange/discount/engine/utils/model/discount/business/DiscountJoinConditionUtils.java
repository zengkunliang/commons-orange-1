package com.gorange.discount.engine.utils.model.discount.business;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.DiscountJoinCondition;
import com.gorange.discount.engine.utils.model.discount.business.joincondition.JoinConditionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 折扣参与条件工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountJoinConditionUtils {
    private static Logger log = LoggerFactory.getLogger(DiscountJoinConditionUtils.class);

    /**
     * 折扣参与条件属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣参与条件属性的条件限制
     * </pre>
     * @param discountJoinCondition 折扣参与条件对象
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(DiscountJoinCondition discountJoinCondition, BusinessMessage businessMessage){
        if(!DiscountJoinConditionUtils.validParamForCondition(discountJoinCondition, businessMessage)){
            log.debug("折扣参与条件属性(参与条件参数)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣参与条件属性(参与条件参数)验证
     * <pre>
     * 验证条件:
     *      明确表示有参与条件时,参与条件参数不可为null
     * </pre>
     * @param discountJoinCondition 折扣参与条件对象
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCondition(DiscountJoinCondition discountJoinCondition, BusinessMessage businessMessage){
        if(discountJoinCondition.getHaveCondition()){
            if(discountJoinCondition.getCondition()==null){
                log.info("折扣参与条件属性(参与条件参数)验证 明确表示有参与条件时,参与条件参数不可为null");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0500);
                return false;
            }
            log.debug("开始折扣参与条件参数属性验证");
            boolean valid = JoinConditionUtils.validAllParam(discountJoinCondition.getCondition(), businessMessage);
            log.debug("完成折扣参与条件参数属性验证{}",valid);
            return valid;
        }
        return true;
    }
}
