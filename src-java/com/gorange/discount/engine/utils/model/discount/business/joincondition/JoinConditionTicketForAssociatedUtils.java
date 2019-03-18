package com.gorange.discount.engine.utils.model.discount.business.joincondition;

import com.gorange.discount.engine.enums.ExpressionKeyEnum;
import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.joincondition.JoinConditionTicketForAssociated;
import com.gorange.discount.engine.utils.DateUtils;
import com.gorange.discount.engine.utils.model.discount.business.DiscountUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 折扣参与条件交易参数关联交易工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionTicketForAssociatedUtils {
    private static Logger log = LoggerFactory.getLogger(JoinConditionTicketForAssociatedUtils.class);
    /**
     * 折扣参与条件交易参数关联交易属性验证
     * <pre>
     * 验证条件:
     *      验证所折扣参与条件交易参数关联交易属性的条件限制
     * </pre>
     * @param joinConditionTicketForAssociated  折扣参与条件交易参数关联交易类
     * @param businessMessage                           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(JoinConditionTicketForAssociated joinConditionTicketForAssociated, BusinessMessage businessMessage){
        if(!JoinConditionTicketForAssociatedUtils.validParamForCompanyNo(joinConditionTicketForAssociated, businessMessage)){
            log.debug("折扣参与条件交易参数关联交易属性(公司号)验证未通过");
            return false;
        }
        if(!JoinConditionTicketForAssociatedUtils.validParamForTime(joinConditionTicketForAssociated, businessMessage)){
            log.debug("折扣参与条件交易参数关联交易属性(交易参与类型)验证未通过");
            return false;
        }
        if(!JoinConditionTicketForAssociatedUtils.validParamForTicketTotalAmount(joinConditionTicketForAssociated, businessMessage)){
            log.debug("折扣参与条件交易参数关联交易属性(交易总金额)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣参与条件交易参数关联交易属性(公司号)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     *      value长度区间【1-50】
     * </pre>
     * @param joinConditionTicketForAssociated  折扣参与条件交易参数关联交易类
     * @param businessMessage                           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCompanyNo(JoinConditionTicketForAssociated joinConditionTicketForAssociated, BusinessMessage businessMessage){
        if(joinConditionTicketForAssociated.getCompanyNo()!=null&&!joinConditionTicketForAssociated.getCompanyNo().isEmpty()){
            Iterator<Map.Entry<String,List<String>>> iterator = joinConditionTicketForAssociated.getCompanyNo().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                List<String> companyNoList = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件交易参数关联交易属性(公司号)验证 公司号表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1000);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.debug("折扣参与条件交易参数关联交易属性(公司号)验证 公司号表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1000);
                    return false;
                }
                if(companyNoList==null){
                    log.info("折扣参与条件交易参数关联交易属性(公司号)验证 公司号表达式key对应的公司号数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1001);
                    return false;
                }
                for(String companyNo:companyNoList){
                    int companyNoLength = StringUtils.defaultString(companyNo).length();
                    if(companyNoLength>DiscountUtils.COMPANY_NO_MAX_LENGTH){
                        log.info("折扣参与条件交易参数关联交易属性(公司号)验证 公司号表达式key对应的公司号数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1002);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 折扣参与条件交易参数关联交易属性(交易时间)验证
     * <pre>
     * 验证条件:
     *      不可为null
     *      且必须匹配DiscountTicketJoinTypeEnum中的数据
     * </pre>
     * @param joinConditionTicketForAssociated  折扣参与条件交易参数关联交易类
     * @param businessMessage                           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForTime(JoinConditionTicketForAssociated joinConditionTicketForAssociated, BusinessMessage businessMessage){
        Date startTime = null,endTime = null;
        if(joinConditionTicketForAssociated.getTicketStartTime()!=null&&!joinConditionTicketForAssociated.getTicketStartTime().isEmpty()){
            for(String expression:joinConditionTicketForAssociated.getTicketStartTime().keySet()){
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件交易参数关联交易属性(交易时间)验证 交易开始时间表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1010);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.GR_AND_EQ){
                    log.info("折扣参与条件交易参数关联交易属性(交易时间)验证  交易开始时间表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1010);
                    return false;
                }
                String startTimeStr = joinConditionTicketForAssociated.getTicketStartTime().get(expression);
                if(StringUtils.isBlank(startTimeStr)){
                    log.info("折扣参与条件交易参数关联交易属性(交易时间)验证 交易开始时间表达式key对应的开始时间数据不可为Blank");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1011);
                    return false;
                }
                startTime = DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),startTimeStr);
                if(startTime==null){
                    log.info("折扣参与条件交易参数关联交易属性(交易时间)验证 交易开始时间表达式key对应的开始时间数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1012);
                    return false;
                }
            }
        }
        if(joinConditionTicketForAssociated.getTicketEndTime()!=null&&!joinConditionTicketForAssociated.getTicketEndTime().isEmpty()){
            Iterator<Map.Entry<String,String>> iterator = joinConditionTicketForAssociated.getTicketEndTime().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                String endTimeStr = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件交易参数关联交易属性(交易时间)验证 交易结束时间表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1020);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.LE_AND_EQ){
                    log.info("折扣参与条件交易参数关联交易属性(交易时间)验证  交易结束时间表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1020);
                    return false;
                }
                if(StringUtils.isBlank(endTimeStr)){
                    log.info("折扣参与条件交易参数关联交易属性(交易时间)验证 交易结束时间表达式key对应的结束时间数据不可为Blank");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1021);
                    return false;
                }
                endTime = DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),endTimeStr);
                if(endTime==null){
                    log.info("折扣参与条件交易参数关联交易属性(交易时间)验证 交易结束时间表达式key对应的结束时间数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1022);
                    return false;
                }
            }
        }
        if(startTime!=null&&endTime!=null&&startTime.getTime()>=endTime.getTime()){
            log.info("折扣参与条件交易参数关联交易属性(交易时间)验证 交易开始时间不得大于交易结束时间");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1023);
            return false;
        }
        return true;
    }


    /**
     * 折扣参与条件交易参数关联交易属性(交易总金额)验证
     * <pre>
     * 验证条件:
     *      key只能是GR 或 GR_AND_EQ
     *      key有正确有效的值时,value不可为null
     *      交易总金额必须大于0
     * </pre>
     * @param joinConditionTicketForAssociated  折扣参与条件交易参数关联交易类
     * @param businessMessage                           消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForTicketTotalAmount(JoinConditionTicketForAssociated joinConditionTicketForAssociated, BusinessMessage businessMessage){
        Map<String, Double> ticketTotalAmountMap = joinConditionTicketForAssociated.getTicketTotalAmount();
        if(ticketTotalAmountMap!=null&&!ticketTotalAmountMap.isEmpty()){
            Iterator<Map.Entry<String, Double>> iterator = ticketTotalAmountMap.entrySet().iterator();
            if(iterator.hasNext()){
                Map.Entry<String,Double> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                Double ticketTotalAmount = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件交易参数关联交易属性(交易总金额)验证 交易总金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1024);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.EQ
                        &&expressionKeyEnum!=ExpressionKeyEnum.GR_AND_EQ
                        &&expressionKeyEnum!=ExpressionKeyEnum.LE_AND_EQ){
                    log.info("折扣参与条件交易参数关联交易属性(交易总金额)验证 交易总金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1024);
                    return false;
                }
                if(ticketTotalAmount==null){
                    log.info("折扣参与条件交易参数关联交易属性(交易总金额)验证 交易总金额key对应的金额数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1025);
                    return false;
                }
                if(ticketTotalAmount<=0){
                    log.info("折扣参与条件交易参数关联交易属性(交易总金额)验证 交易总金额表达式key对应的金额数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1026);
                    return false;
                }
            }
        }
        return true;
    }
}
