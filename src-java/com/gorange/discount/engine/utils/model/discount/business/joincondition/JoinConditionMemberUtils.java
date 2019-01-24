package com.gorange.discount.engine.utils.model.discount.business.joincondition;

import com.gorange.discount.engine.enums.ExpressionKeyEnum;
import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.joincondition.JoinConditionMember;
import com.gorange.discount.engine.utils.DateUtils;
import com.gorange.discount.engine.utils.model.member.MemberUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 折扣参与条件参数顾客工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionMemberUtils {
    private static Logger log = LoggerFactory.getLogger(JoinConditionMemberUtils.class);

    /**
     * 折扣参与条件参数顾客属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣参与条件参数顾客属性的条件限制
     * </pre>
     * @param joinConditionMember   折扣参与条件参数顾客类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(JoinConditionMember joinConditionMember, BusinessMessage businessMessage){
        if(!JoinConditionMemberUtils.validParamForBarcode(joinConditionMember, businessMessage)){
            log.debug("折扣参与条件参数顾客属性(条码)验证未通过");
            return false;
        }
        if(!JoinConditionMemberUtils.validParamForType(joinConditionMember, businessMessage)){
            log.debug("折扣参与条件参数顾客属性(分类)验证未通过");
            return false;
        }
        if(!JoinConditionMemberUtils.validParamForSex(joinConditionMember, businessMessage)){
            log.debug("折扣参与条件参数顾客属性(性别)验证未通过");
            return false;
        }
        if(!JoinConditionMemberUtils.validParamForAge(joinConditionMember, businessMessage)){
            log.debug("折扣参与条件参数顾客属性(年龄)验证未通过");
            return false;
        }
        if(!JoinConditionMemberUtils.validParamForBirthday(joinConditionMember, businessMessage)){
            log.debug("折扣参与条件参数顾客属性(生日)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣参与条件参数顾客属性(条码)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     *      value长度区间【1-50】
     * </pre>
     * @param joinConditionMember   折扣参与条件参数顾客类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBarcode(JoinConditionMember joinConditionMember, BusinessMessage businessMessage){
        if(joinConditionMember.getBarcode()!=null&&!joinConditionMember.getBarcode().isEmpty()){
            Iterator<Map.Entry<String,List<String>>> iterator = joinConditionMember.getBarcode().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                List<String> barcodeList = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数顾客属性(条码)验证 条码表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0700);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("折扣参与条件参数顾客属性(条码)验证 条码表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0700);
                    return false;
                }
                if(barcodeList==null){
                    log.info("折扣参与条件参数顾客属性(条码)验证 条码表达式key对应的条码数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0701);
                    return false;
                }
                for(String barcode:barcodeList){
                    int barcodeLength = StringUtils.defaultString(barcode).length();
                    if(MemberUtils.BARCODE_MIN_LENGTH>barcodeLength||barcodeLength>MemberUtils.BARCODE_MAX_LENGTH){
                        log.info("折扣参与条件参数顾客属性(条码)验证 条码表达式key对应的条码数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0702);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 折扣参与条件参数顾客属性(分类)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     *      value长度区间【1-50】
     * </pre>
     * @param joinConditionMember   折扣参与条件参数顾客类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForType(JoinConditionMember joinConditionMember, BusinessMessage businessMessage){
        if(joinConditionMember.getType()!=null&&!joinConditionMember.getType().isEmpty()){
            for(String expression:joinConditionMember.getType().keySet()){
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数顾客属性(分类)验证 分类表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0710);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("折扣参与条件参数顾客属性(分类)验证 分类表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0710);
                    return false;
                }
                List<String> typeList = joinConditionMember.getType().get(expression);
                if(typeList==null){
                    log.info("折扣参与条件参数顾客属性(分类)验证 分类表达式key对应的分类数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0711);
                    return false;
                }
                for(String type:typeList){
                    int barcodeLength = StringUtils.defaultString(type).length();
                    if(barcodeLength>MemberUtils.TYPE_MAX_LENGTH){
                        log.info("折扣参与条件参数顾客属性(分类)验证 分类表达式key对应的分类数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0712);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 折扣参与条件参数顾客属性(性别)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     *      value数据必须是 "0" | "1" | "2"
     * </pre>
     * @param joinConditionMember   折扣参与条件参数顾客类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForSex(JoinConditionMember joinConditionMember, BusinessMessage businessMessage){
        if(joinConditionMember.getSex()!=null&&!joinConditionMember.getSex().isEmpty()){
            Iterator<Map.Entry<String,List<String>>> iterator = joinConditionMember.getSex().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                List<String> sexList = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数顾客属性(性别)验证 性别表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0721);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("折扣参与条件参数顾客属性(性别)验证 性别表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0721);
                    return false;
                }
                if(sexList==null){
                    log.info("折扣参与条件参数顾客属性(性别)验证 性别表达式key对应的性别数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0722);
                    return false;
                }
                for(String sex:sexList){
                    if(!MemberUtils.MEMBER_SEX_WOMAN.equals(sex)&&!MemberUtils.MEMBER_SEX_MAN.equals(sex)&&!MemberUtils.MEMBER_SEX_OTHER.equals(sex)){
                        log.info("折扣参与条件参数顾客属性(生日)验证 生日表达式key对应的生日数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0723);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 折扣参与条件参数顾客属性(年龄)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     *      value数据必须大于且等于3 小于且等于120
     * </pre>
     * @param joinConditionMember   折扣参与条件参数顾客类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForAge(JoinConditionMember joinConditionMember, BusinessMessage businessMessage){
        if(joinConditionMember.getAge()!=null&&!joinConditionMember.getAge().isEmpty()){
            for(String expression:joinConditionMember.getAge().keySet()){
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数顾客属性(年龄)验证 年龄表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0730);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("折扣参与条件参数顾客属性(年龄)验证 年龄表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0730);
                    return false;
                }
                List<Integer> ageList = joinConditionMember.getAge().get(expression);
                if(ageList==null){
                    log.info("折扣参与条件参数顾客属性(年龄)验证 年龄表达式key对应的年龄数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0731);
                    return false;
                }
                for(Integer age:ageList){
                    if(MemberUtils.MEMBER_AGE_MIN.intValue()>age.intValue()||age.intValue()>MemberUtils.MEMBER_AGE_MAX){
                        log.info("折扣参与条件参数顾客属性(年龄)验证 年龄表达式key对应的年龄数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0732);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 折扣参与条件参数顾客属性(生日)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     *      value数据必须时UTC格式字串
     * </pre>
     * @param joinConditionMember   折扣参与条件参数顾客类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBirthday(JoinConditionMember joinConditionMember, BusinessMessage businessMessage){
        if(joinConditionMember.getBirthday()!=null&&!joinConditionMember.getBirthday().isEmpty()){
            Iterator<Map.Entry<String,List<String>>> iterator = joinConditionMember.getBirthday().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                List<String> birthdayList = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数顾客属性(生日)验证 生日表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0740);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("折扣参与条件参数顾客属性(生日)验证 生日表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0740);
                    return false;
                }
                if(birthdayList==null){
                    log.info("折扣参与条件参数顾客属性(生日)验证 生日表达式key对应的条码数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0741);
                    return false;
                }
                for(String birthday:birthdayList){
                    if(DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),birthday)==null){
                        log.info("折扣参与条件参数顾客属性(生日)验证 生日表达式key对应的生日数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0742);
                        return false;
                    }
                }
            }
        }
        return true;
    }

}
