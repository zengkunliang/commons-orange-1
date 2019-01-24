package com.gorange.discount.engine.utils.model.discount.business.calccondition;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcCondition;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcConditionGoods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 折扣计算条件参数工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionUtils{
    private static Logger log = LoggerFactory.getLogger(CalcConditionUtils.class);

    /**
     * 折扣计算条件参数属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣计算条件参数属性的条件限制
     * </pre>
     * @param calcCondition 折扣计算条件参数类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(CalcCondition calcCondition, BusinessMessage businessMessage){
        if(!CalcConditionUtils.validParamForConditionGoods(calcCondition, businessMessage)){
            log.debug("折扣参与条件参数属性(商品)验证未通过");
            return false;
        }
        if(!CalcConditionUtils.validParamForConditionAmount(calcCondition, businessMessage)){
            log.debug("折扣参与条件参数属性(金额)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣计算条件属性(商品)验证
     * @param calcCondition 折扣计算条件参数类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForConditionGoods(CalcCondition calcCondition, BusinessMessage businessMessage){
        if(calcCondition.getConditionGoods()==null){
            log.info("折扣计算条件属性(商品)验证 商品数据不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0403);
            return false;
        }
        if(calcCondition.getConditionGoods().isEmpty()){
            log.info("折扣计算条件属性(商品)验证 商品数据不可为Empty");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0404);
            return false;
        }
        for(CalcConditionGoods calcConditionGoods:calcCondition.getConditionGoods()){
            log.debug("开始折扣计算条件参数商品属性验证");
            boolean valid = CalcConditionGoodsUtils.validAllParam(calcConditionGoods, businessMessage);
            log.debug("完成折扣计算条件参数商品属性验证{}",valid);
            if(!valid){
                return valid;
            }
        }
        return true;
    }

    /**
     * 折扣计算条件属性(折扣值)验证
     * @param calcCondition 折扣计算条件参数类
     * @param businessMessage       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForConditionAmount(CalcCondition calcCondition, BusinessMessage businessMessage){
        if(calcCondition.getConditionValue()!=null){
            log.debug("开始折扣计算条件参数折扣值属性验证");
            boolean valid = CalcConditionAmountUtils.validAllParam(calcCondition.getConditionValue(), businessMessage);
            log.debug("完成折扣计算条件参数折扣值属性验证{}",valid);
            return valid;
        }
        return true;
    }
}
