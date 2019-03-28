package com.gorange.discount.engine.utils.model.discount.business.calccondition;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 折扣计算条件参数商品工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionUtils {
    private static Logger log = LoggerFactory.getLogger(CalcConditionUtils.class);

    /**
     * 折扣计算条件参数商品属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣计算条件参数商品属性的条件限制
     * </pre>
     * @param calcCondition     折扣计算条件参数类
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(CalcCondition calcCondition, BusinessMessage businessMessage){
        if(!CalcConditionUtils.validParamForCalcGoods(calcCondition, businessMessage)){
            log.debug("折扣计算条件参数商品属性(商品)验证未通过");
            return false;
        }
        if(!CalcConditionUtils.validParamForTargetGoods(calcCondition, businessMessage)){
            log.debug("折扣参与条件参数属性(金额)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣计算条件参数商品属性(计算商品)验证
     * @param calcCondition     折扣计算条件参数类
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCalcGoods(CalcCondition calcCondition, BusinessMessage businessMessage){
        if(calcCondition.getCalcGoods()==null){
            log.info("折扣计算条件参数商品属性(计算商品)验证 计算商品数据不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0403);
            return false;
        }
        log.debug("开始计算商品属性验证");
        boolean valid = CalcConditionGoodsCalcGoodsUtils.validAllParam(calcCondition.getCalcGoods(), businessMessage);
        log.debug("完成计算商品属性验证{}",valid);
        return valid;
    }

    /**
     * 折扣计算条件参数商品属性(目标商品)验证
     * @param calcCondition     折扣计算条件参数类
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForTargetGoods(CalcCondition calcCondition, BusinessMessage businessMessage){
        if(calcCondition.getTargetGoods()==null){
            log.info("折扣计算条件参数商品属性(目标商品)验证 目标商品数据不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0404);
            return false;
        }
        log.debug("开始目标商品属性验证");
        boolean valid = CalcConditionGoodsTargetGoodsUtils.validAllParam(calcCondition.getTargetGoods(), businessMessage);
        log.debug("完成目标商品属性验证{}",valid);
        return valid;
    }
}
