package com.gorange.discount.engine.utils.model.discount.business.calccondition;

import com.gorange.discount.engine.enums.ExpressionKeyEnum;
import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcConditionValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

/**
 * 折扣计算条件参数商品工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionAmountUtils {
    private static Logger log = LoggerFactory.getLogger(CalcConditionAmountUtils.class);

    /**
     * 折扣计算条件参数金额属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣计算条件参数金额属性的条件限制
     * </pre>
     * @param calcConditionValue   折扣计算条件金额类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(CalcConditionValue calcConditionValue, BusinessMessage businessMessage){
        if(!CalcConditionAmountUtils.validParamForTicketMaxDiscAmount(calcConditionValue, businessMessage)){
            log.debug("折扣计算条件参数金额属性(交易折扣金额)验证未通过");
            return false;
        }
        if(!CalcConditionAmountUtils.validParamForMemberMaxDiscAmount(calcConditionValue, businessMessage)){
            log.debug("折扣计算条件参数金额属性(交易折扣金额)验证未通过");
            return false;
        }
        if(!CalcConditionAmountUtils.validParamForMerchantMaxDiscAmount(calcConditionValue, businessMessage)){
            log.debug("折扣计算条件参数金额属性(交易折扣金额)验证未通过");
            return false;
        }
        if(!CalcConditionAmountUtils.validParamForMaxDiscAmountLogic(calcConditionValue, businessMessage)){
            log.debug("折扣计算条件参数金额属性(折扣金额逻辑)验证未通过");
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
     * @param calcConditionValue   折扣计算条件金额类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForTicketMaxDiscAmount(CalcConditionValue calcConditionValue, BusinessMessage businessMessage){
        if(calcConditionValue.getTicketMaxDiscValue()!=null&&!calcConditionValue.getTicketMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = calcConditionValue.getTicketMaxDiscValue().entrySet().iterator();
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
     * @param calcConditionValue   折扣计算条件金额类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForMemberMaxDiscAmount(CalcConditionValue calcConditionValue, BusinessMessage businessMessage){
        if(calcConditionValue.getMemberMaxDiscValue()!=null&&!calcConditionValue.getMemberMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = calcConditionValue.getMemberMaxDiscValue().entrySet().iterator();
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
     * 折扣计算条件参数金额属性(商户最大折扣值)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     *      key有正确有效的值时,value不可为null
     *      value数据必须大于0
     * </pre>
     * @param calcConditionValue   折扣计算条件金额类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForMerchantMaxDiscAmount(CalcConditionValue calcConditionValue, BusinessMessage businessMessage){
        if(calcConditionValue.getMerchantMaxDiscValue()!=null&&!calcConditionValue.getMerchantMaxDiscValue().isEmpty()){
            for(String expression: calcConditionValue.getMerchantMaxDiscValue().keySet()){
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣计算条件参数最大值属性(商户最大折扣值)验证 商户最大折扣值表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1106);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.EQ){
                    log.info("折扣计算条件参数最大值属性(商户最大折扣值)验证 商户最大折扣值表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1106);
                    return false;
                }
                Double merchantMaxDiscountValue = calcConditionValue.getMerchantMaxDiscValue().get(expression);
                if(merchantMaxDiscountValue==null){
                    log.info("折扣计算条件参数最大值属性(商户最大折扣值)验证 商户最大折扣值表达式key对应的最大值数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1107);
                    return false;
                }
                if(merchantMaxDiscountValue<=0){
                    log.info("折扣计算条件参数最大值属性(商户最大折扣值)验证 商户最大折扣值表达式key对应的最大值数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1108);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 折扣计算条件参数金额属性(最大折扣值逻辑)验证
     * @param calcConditionValue   折扣计算条件金额类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForMaxDiscAmountLogic(CalcConditionValue calcConditionValue, BusinessMessage businessMessage){
        Double ticketMaxDiscountValue = null,memberMaxDiscountValue = null,merchantMaxDiscountValue = null;
        if(calcConditionValue.getTicketMaxDiscValue()!=null&&!calcConditionValue.getTicketMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = calcConditionValue.getTicketMaxDiscValue().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                ticketMaxDiscountValue = expressionMapEntry.getValue();
            }
        }
        if(calcConditionValue.getMemberMaxDiscValue()!=null&&!calcConditionValue.getMemberMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = calcConditionValue.getMemberMaxDiscValue().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                memberMaxDiscountValue = expressionMapEntry.getValue();
            }
        }
        if(calcConditionValue.getMerchantMaxDiscValue()!=null&&!calcConditionValue.getMerchantMaxDiscValue().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = calcConditionValue.getMerchantMaxDiscValue().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                merchantMaxDiscountValue = expressionMapEntry.getValue();
            }
        }
        return CalcConditionAmountUtils.validParamForMaxDiscAmountLogic(ticketMaxDiscountValue,memberMaxDiscountValue,merchantMaxDiscountValue,businessMessage);
    }

    /**
     * 折扣计算条件参数金额属性(最大折扣值逻辑)验证
     * @param ticketMaxDiscountValue       交易最大折扣金额
     * @param memberMaxDiscountValue       会员最大折扣金额
     * @param merchantMaxDiscountValue     商户最大折扣金额
     * @param businessMessage              消息对象
     * @return
     */
    private static boolean validParamForMaxDiscAmountLogic(Double ticketMaxDiscountValue, Double memberMaxDiscountValue, Double merchantMaxDiscountValue, BusinessMessage businessMessage){
        if(ticketMaxDiscountValue!=null&&memberMaxDiscountValue!=null&&merchantMaxDiscountValue!=null){
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
            if(merchantMaxDiscountValue<=0){
                log.info("折扣计算条件参数最大值属性(最大折扣值逻辑)验证 商户最大折扣值不得小于或等于0");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1111);
                return false;
            }
            if(ticketMaxDiscountValue>memberMaxDiscountValue){
                log.info("折扣计算条件参数最大值属性(最大折扣值逻辑)验证 交易最大折扣值不得大于会员最大折扣最大值");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1112);
                return false;
            }
            if(memberMaxDiscountValue>merchantMaxDiscountValue){
                log.info("折扣计算条件参数最大值属性(最大折扣值逻辑)验证 会员最大折扣值不得大于商户最大折扣最大值");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1113);
                return false;
            }
        }
        return true;
    }
}
