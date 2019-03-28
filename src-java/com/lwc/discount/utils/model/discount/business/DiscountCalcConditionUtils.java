package com.lwc.discount.utils.model.discount.business;

import com.lwc.discount.enums.DiscountCalcResultTypeEnum;
import com.lwc.discount.enums.BusinessMessageEnum;
import com.lwc.discount.enums.ExpressionKeyEnum;
import com.lwc.discount.model.common.BusinessMessage;
import com.lwc.discount.model.discount.business.DiscountCalcCondition;
import com.lwc.discount.model.discount.business.calccondition.CalcCondition;
import com.lwc.discount.utils.model.discount.business.calccondition.CalcConditionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * 折扣计算条件工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountCalcConditionUtils {
    private static Logger log = LoggerFactory.getLogger(DiscountCalcConditionUtils.class);

    /**
     * 折扣计算条件属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣计算条件属性的条件限制
     * </pre>
     * @param discountCalcCondition 折扣计算条件对象
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(DiscountCalcCondition discountCalcCondition, BusinessMessage businessMessage){
        if(!DiscountCalcConditionUtils.validParamForCalcResultType(discountCalcCondition, businessMessage)){
            log.debug("折扣计算条件属性(折扣计算结果类型)验证未通过");
            return false;
        }
        if(!DiscountCalcConditionUtils.validParamForTicketMaxDiscAmount(discountCalcCondition, businessMessage)){
            log.debug("折扣计算条件参数金额属性(交易折扣金额)验证未通过");
            return false;
        }
        if(!DiscountCalcConditionUtils.validParamForMemberMaxDiscAmount(discountCalcCondition, businessMessage)){
            log.debug("折扣计算条件参数金额属性(会员折扣金额)验证未通过");
            return false;
        }
        if(!DiscountCalcConditionUtils.validParamForCompanyMaxDiscAmount(discountCalcCondition, businessMessage)){
            log.debug("折扣计算条件参数金额属性(公司折扣金额)验证未通过");
            return false;
        }
        if(!DiscountCalcConditionUtils.validParamForMaxDiscAmountLogic(discountCalcCondition, businessMessage)){
            log.debug("折扣计算条件参数金额属性(折扣金额逻辑)验证未通过");
            return false;
        }
        if(!DiscountCalcConditionUtils.validParamForCondition(discountCalcCondition, businessMessage)){
            log.debug("折扣计算条件属性(计算条件参数)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣计算条件属性(折扣计算结果类型)验证
     * <pre>
     * 验证条件:
     *      不得为null
     *      必须匹配DiscountCalcResultTypeEnum中的数据
     * </pre>
     * @param discountCalcCondition 折扣计算条件对象
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCalcResultType(DiscountCalcCondition discountCalcCondition, BusinessMessage businessMessage){
        if(StringUtils.isBlank(discountCalcCondition.getCalcResultType())){
            log.info("折扣计算条件属性(折扣计算结果类型)验证 折扣计算结果类型不可为Blank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0401);
            return false;
        }
        if(DiscountCalcResultTypeEnum.getExpressionKeyEnum(discountCalcCondition.getCalcResultType())==null){
            log.info("折扣计算条件属性(折扣计算结果类型)验证 折扣计算结果类型不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0402);
            return false;
        }
        return true;
    }

    /**
     * 折扣计算条件参数金额属性(交易最大折扣值)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     *      key有正确有效的值时,value不可为null
     *      value数据必须大于0
     * </pre>
     * @param discountCalcCondition     折扣计算条件对象
     * @param businessMessage           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForTicketMaxDiscAmount(DiscountCalcCondition discountCalcCondition, BusinessMessage businessMessage){
        if(discountCalcCondition.getTicketMaxDiscValue()!=null&&!discountCalcCondition.getTicketMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = discountCalcCondition.getTicketMaxDiscValue().entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Double> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                Double ticketMaxDiscountValue = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣计算条件参数金额属性(交易最大折扣值)验证 交易最大折扣值表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1100);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.EQ){
                    log.info("折扣计算条件参数金额属性(交易最大折扣值)验证 交易最大折扣值表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1100);
                    return false;
                }
                if(ticketMaxDiscountValue==null){
                    log.info("折扣计算条件参数金额属性(交易最大折扣值)验证 交易最大折扣值表达式key对应的最大值数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1101);
                    return false;
                }
                if(ticketMaxDiscountValue<=0){
                    log.info("折扣计算条件参数金额属性(交易最大折扣值)验证 交易最大折扣值表达式key对应的最大值数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1102);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 折扣计算条件参数金额属性(会员最大折扣值)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     *      key有正确有效的值时,value不可为null
     *      value数据必须大于0
     * </pre>
     * @param discountCalcCondition     折扣计算条件对象
     * @param businessMessage           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForMemberMaxDiscAmount(DiscountCalcCondition discountCalcCondition, BusinessMessage businessMessage){
        if(discountCalcCondition.getMemberMaxDiscValue()!=null&&!discountCalcCondition.getMemberMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = discountCalcCondition.getMemberMaxDiscValue().entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Double> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                Double memberMaxDiscountValue = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣计算条件参数最大值属性(会员最大折扣值)验证 会员最大折扣值表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1103);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.EQ){
                    log.info("折扣计算条件参数最大值属性(会员最大折扣值)验证 会员最大折扣值金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1103);
                    return false;
                }
                if(memberMaxDiscountValue==null){
                    log.info("折扣计算条件参数最大值金额属性(会员最大折扣值)验证 会员最大折扣值金额表达式key对应的最大值数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1104);
                    return false;
                }
                if(memberMaxDiscountValue<=0){
                    log.info("折扣计算条件参数最大值属性(会员最大折扣值)验证 会员最大折扣值金额表达式key对应的最大值数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1105);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 折扣计算条件参数金额属性(公司最大折扣值)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     *      key有正确有效的值时,value不可为null
     *      value数据必须大于0
     * </pre>
     * @param discountCalcCondition     折扣计算条件对象
     * @param businessMessage           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCompanyMaxDiscAmount(DiscountCalcCondition discountCalcCondition, BusinessMessage businessMessage){
        if(discountCalcCondition.getCompanyMaxDiscValue()!=null&&!discountCalcCondition.getCompanyMaxDiscValue().isEmpty()){
            for(String expression: discountCalcCondition.getCompanyMaxDiscValue().keySet()){
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣计算条件参数最大值属性(公司最大折扣值)验证 公司最大折扣值表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1106);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.EQ){
                    log.info("折扣计算条件参数最大值属性(公司最大折扣值)验证 公司最大折扣值表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1106);
                    return false;
                }
                Double companyMaxDiscountValue = discountCalcCondition.getCompanyMaxDiscValue().get(expression);
                if(companyMaxDiscountValue==null){
                    log.info("折扣计算条件参数最大值属性(公司最大折扣值)验证 公司最大折扣值表达式key对应的最大值数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1107);
                    return false;
                }
                if(companyMaxDiscountValue<=0){
                    log.info("折扣计算条件参数最大值属性(公司最大折扣值)验证 公司最大折扣值表达式key对应的最大值数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1108);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 折扣计算条件参数金额属性(最大折扣值逻辑)验证
     * @param discountCalcCondition     折扣计算条件对象
     * @param businessMessage           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForMaxDiscAmountLogic(DiscountCalcCondition discountCalcCondition, BusinessMessage businessMessage){
        Double ticketMaxDiscountValue = null,memberMaxDiscountValue = null,companyMaxDiscountValue = null;
        if(discountCalcCondition.getTicketMaxDiscValue()!=null&&!discountCalcCondition.getTicketMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = discountCalcCondition.getTicketMaxDiscValue().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                ticketMaxDiscountValue = expressionMapEntry.getValue();
            }
        }
        if(discountCalcCondition.getMemberMaxDiscValue()!=null&&!discountCalcCondition.getMemberMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = discountCalcCondition.getMemberMaxDiscValue().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                memberMaxDiscountValue = expressionMapEntry.getValue();
            }
        }
        if(discountCalcCondition.getCompanyMaxDiscValue()!=null&&!discountCalcCondition.getCompanyMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = discountCalcCondition.getCompanyMaxDiscValue().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                companyMaxDiscountValue = expressionMapEntry.getValue();
            }
        }
        return DiscountCalcConditionUtils.validParamForMaxDiscAmountLogic(ticketMaxDiscountValue,memberMaxDiscountValue,companyMaxDiscountValue,businessMessage);
    }

    /**
     * 折扣计算条件参数金额属性(最大折扣值逻辑)验证
     * @param ticketMaxDiscountValue       交易最大折扣金额
     * @param memberMaxDiscountValue       会员最大折扣金额
     * @param companyMaxDiscountValue      公司最大折扣金额
     * @param businessMessage              消息对象
     * @return 验证通过返回true 否则false
     */
    private static boolean validParamForMaxDiscAmountLogic(Double ticketMaxDiscountValue, Double memberMaxDiscountValue, Double companyMaxDiscountValue, BusinessMessage businessMessage){
        if(ticketMaxDiscountValue!=null&&memberMaxDiscountValue!=null&&companyMaxDiscountValue!=null){
            if(ticketMaxDiscountValue<=0){
                log.info("折扣计算条件参数最大值属性(最大折扣值逻辑)验证 交易最大折扣值不得小于或等于0");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1109);
                return false;
            }
            if(memberMaxDiscountValue<=0){
                log.info("折扣计算条件参数最大值属性(最大折扣值逻辑)验证 会员最大折扣值不得小于或等于0");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1110);
                return false;
            }
            if(companyMaxDiscountValue<=0){
                log.info("折扣计算条件参数最大值属性(最大折扣值逻辑)验证 公司最大折扣值不得小于或等于0");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1111);
                return false;
            }
            if(ticketMaxDiscountValue>memberMaxDiscountValue){
                log.info("折扣计算条件参数最大值属性(最大折扣值逻辑)验证 交易最大折扣值不得大于会员最大折扣最大值");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1112);
                return false;
            }
            if(memberMaxDiscountValue>companyMaxDiscountValue){
                log.info("折扣计算条件参数最大值属性(最大折扣值逻辑)验证 会员最大折扣值不得大于公司最大折扣最大值");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1113);
                return false;
            }
        }
        return true;
    }

    /**
     * 折扣计算条件属性(计算条件参数)验证
     * <pre>
     * 验证条件:
     *      不得为null
     * </pre>
     * @param discountCalcCondition 折扣计算条件对象
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCondition(DiscountCalcCondition discountCalcCondition, BusinessMessage businessMessage){
        if(discountCalcCondition.getCondition()==null){
            log.info("折扣计算条件属性(计算条件参数)验证 计算条件参数不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0400);
            return false;
        }
        log.debug("开始折扣计算条件参数属性验证");
        for(CalcCondition calcCondition:discountCalcCondition.getCondition()){
            boolean valid = CalcConditionUtils.validAllParam(calcCondition, businessMessage);
            log.debug("完成折扣计算条件参数属性验证{}",valid);
            if(!valid){
                return valid;
            }
        }
        return true;
    }
}
