package com.gorange.discount.engine.utils.model.discount.business;

import com.gorange.discount.engine.enums.DiscountCalcResultTypeEnum;
import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.DiscountCalcCondition;
import com.gorange.discount.engine.utils.model.discount.business.calccondition.CalcConditionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        if(!DiscountCalcConditionUtils.validParamForCondition(discountCalcCondition, businessMessage)){
            log.debug("折扣计算条件属性(计算条件参数)验证未通过");
            return false;
        }
        if(!DiscountCalcConditionUtils.validParamForCalcResultType(discountCalcCondition, businessMessage)){
            log.debug("折扣计算条件属性(折扣计算结果类型)验证未通过");
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
        boolean valid = CalcConditionUtils.validAllParam(discountCalcCondition.getCondition(), businessMessage);
        log.debug("完成折扣计算条件参数属性验证{}",valid);
        return valid;
    }
}
