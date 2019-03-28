package com.lwc.discount.utils.model.discount.business.joincondition;

import com.lwc.discount.enums.DiscountTicketJoinTypeEnum;
import com.lwc.discount.enums.DiscountTicketTypeEnum;
import com.lwc.discount.enums.ExpressionKeyEnum;
import com.lwc.discount.enums.BusinessMessageEnum;
import com.lwc.discount.model.common.BusinessMessage;
import com.lwc.discount.model.discount.business.joincondition.JoinConditionTicketForCurrent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * 折扣参与条件交易参数当前交易工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionTicketForCurrentUtils {
    private static Logger log = LoggerFactory.getLogger(JoinConditionTicketForCurrentUtils.class);

    /**
     * 折扣参与条件交易参数当前交易属性验证
     * <pre>
     * 验证条件:
     *      验证所折扣参与条件交易参数当前交易属性的条件限制
     * </pre>
     * @param joinConditionTicketForCurrent     折扣参与条件交易参数当前交易类
     * @param businessMessage                           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(JoinConditionTicketForCurrent joinConditionTicketForCurrent, BusinessMessage businessMessage){
        if(!JoinConditionTicketForCurrentUtils.validParamForType(joinConditionTicketForCurrent, businessMessage)){
            log.debug("折扣参与条件交易参数当前交易属性(交易类型)验证未通过");
            return false;
        }
        if(!JoinConditionTicketForCurrentUtils.validParamForJoinType(joinConditionTicketForCurrent, businessMessage)){
            log.debug("折扣参与条件交易参数当前交易属性(交易参与类型)验证未通过");
            return false;
        }
        if(!JoinConditionTicketForCurrentUtils.validParamForTicketTotalAmount(joinConditionTicketForCurrent, businessMessage)){
            log.debug("折扣参与条件交易参数当前交易属性(交易总金额)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣参与条件交易参数当前交易属性(交易类型)验证
     * <pre>
     * 验证条件:
     *      不可为null
     *      且必须匹配DiscountTicketTypeEnum中的数据
     * </pre>
     * @param joinConditionTicketForCurrent     折扣参与条件交易参数当前交易类
     * @param businessMessage                           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForType(JoinConditionTicketForCurrent joinConditionTicketForCurrent, BusinessMessage businessMessage){
        String type = joinConditionTicketForCurrent.getType();
        if(type==null){
            log.info("折扣参与条件交易参数当前交易属性(交易类型)验证 交易类型不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0900);
            return false;
        }
        if(DiscountTicketTypeEnum.getExpressionKeyEnum(type)==null){
            log.info("折扣参与条件交易参数当前交易属性(交易类型)验证 交易类型数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0901);
            return false;
        }
        return true;
    }

    /**
     * 折扣参与条件交易参数当前交易属性(交易参与类型)验证
     * <pre>
     * 验证条件:
     *      不可为null
     *      且必须匹配DiscountTicketJoinTypeEnum中的数据
     * </pre>
     * @param joinConditionTicketForCurrent     折扣参与条件交易参数当前交易类
     * @param businessMessage                           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForJoinType(JoinConditionTicketForCurrent joinConditionTicketForCurrent, BusinessMessage businessMessage){
        String joinType = joinConditionTicketForCurrent.getJoinType();
        if(joinType==null){
            log.info("折扣参与条件交易参数当前交易属性(交易参与类型)验证 交易参与类型不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0902);
            return false;
        }
        if(DiscountTicketJoinTypeEnum.getExpressionKeyEnum(joinType)==null){
            log.info("折扣参与条件交易参数当前交易属性(交易参与类型)验证 交易参与类型数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0903);
            return false;
        }
        return true;
    }


    /**
     * 折扣参与条件交易参数当前交易属性(交易总金额)验证
     * <pre>
     * 验证条件:
     *      key只能是GR 或 GR_AND_EQ
     *      key有正确有效的值时,value不可为null
     *      交易总金额必须大于0
     * </pre>
     * @param joinConditionTicketForCurrent     折扣参与条件交易参数当前交易类
     * @param businessMessage                           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForTicketTotalAmount(JoinConditionTicketForCurrent joinConditionTicketForCurrent, BusinessMessage businessMessage){
        Map<String, Double> ticketTotalAmountMap = joinConditionTicketForCurrent.getTicketTotalAmount();
        if(ticketTotalAmountMap!=null&&!ticketTotalAmountMap.isEmpty()){
            Iterator<Map.Entry<String, Double>> iterator = ticketTotalAmountMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Double> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                Double ticketTotalAmount = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件交易参数当前交易属性(交易总金额)验证 交易总金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0904);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.GR&&expressionKeyEnum!=ExpressionKeyEnum.GR_AND_EQ){
                    log.info("折扣参与条件交易参数当前交易属性(交易总金额)验证 交易总金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0904);
                    return false;
                }
                if(ticketTotalAmount==null){
                    log.info("折扣参与条件交易参数当前交易属性(交易总金额)验证 交易总金额key对应的金额数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0905);
                    return false;
                }
                if(ticketTotalAmount<=0){
                    log.info("折扣参与条件交易参数当前交易属性(交易总金额)验证 交易总金额表达式key对应的金额数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0906);
                    return false;
                }
            }
        }
        return true;
    }
}
