package com.gorange.discount.engine.utils.model.discount.business.calccondition;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.enums.RelationshipKeyEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcConditionGoodsCalcGoods;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcConditionGoodsCalcGoodsCondition;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 计算商品工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionGoodsCalcGoodsUtils {
    private static Logger log = LoggerFactory.getLogger(CalcConditionGoodsCalcGoodsUtils.class);

    /**
     * 计算商品属性验证
     * <pre>
     * 验证条件:
     *      验证所有计算商品属性的条件限制
     * </pre>
     * @param calcConditionGoodsCalcGoods   计算商品类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(CalcConditionGoodsCalcGoods calcConditionGoodsCalcGoods, BusinessMessage businessMessage){
        if(!CalcConditionGoodsCalcGoodsUtils.validParamForExpression(calcConditionGoodsCalcGoods, businessMessage)){
            log.debug("计算商品类(计算商品条件对象关联关系)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsCalcGoodsUtils.validParamForCondition(calcConditionGoodsCalcGoods, businessMessage)){
            log.debug("计算商品类(计算商品条件)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 计算商品类(计算商品条件对象关联关系)验证
     * <pre>
     * 验证条件:
     *      不可为blank
     *      匹配RelationshipKeyEnum中的数据
     * </pre>
     * @param calcConditionGoodsCalcGoods   计算商品类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForExpression(CalcConditionGoodsCalcGoods calcConditionGoodsCalcGoods, BusinessMessage businessMessage){
        if(StringUtils.isBlank(calcConditionGoodsCalcGoods.getExpression())){
            log.info("计算商品类(计算商品条件对象关联关系)验证 计算商品条件对象关联关系不可为Blank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1600);
            return false;
        }
        if(RelationshipKeyEnum.getExpressionKeyEnum(calcConditionGoodsCalcGoods.getExpression())==null){
            log.info("计算商品类(计算商品条件对象关联关系)验证 计算商品条件对象关联关系数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1601);
            return false;
        }
        return true;
    }

    /**
     * 计算商品类(计算商品条件)验证
     * <pre>
     * 验证条件:
     *      不可为null
     *      不可为empty
     * </pre>
     * @param calcConditionGoodsCalcGoods   计算商品类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCondition(CalcConditionGoodsCalcGoods calcConditionGoodsCalcGoods, BusinessMessage businessMessage){
        if(calcConditionGoodsCalcGoods.getCondition()==null){
            log.info("计算商品类(计算商品条件)验证 计算商品条件不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1602);
            return false;
        }
        if(calcConditionGoodsCalcGoods.getCondition().isEmpty()){
            log.info("计算商品类(计算商品条件)验证 计算商品条件数据不可为Empty");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1603);
            return false;
        }
        Map<String,Double> price = null;
        Map<String,List<String>> groupNo = null;
        Map<String,List<String>> barcode = null;
        Map<String,Double> amount = null;
        Map<String,Double> quantity = null;
        for(CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition:calcConditionGoodsCalcGoods.getCondition()){
            if(groupNo!=null&&!groupNo.equals(calcConditionGoodsCalcGoodsCondition.getGroupNo())){
                log.info("计算商品类(计算商品条件)验证 计算商品所有分类数据必须一致");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1604);
                return false;
            }
            if(barcode!=null&&!barcode.equals(calcConditionGoodsCalcGoodsCondition.getBarcode())){
                log.info("计算商品类(计算商品条件)验证 计算商品所有条码数据必须一致");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1605);
                return false;
            }
            if(price!=null&&!price.equals(calcConditionGoodsCalcGoodsCondition.getPrice())){
                log.info("计算商品类(计算商品条件)验证 计算商品所有单价数据必须一致");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1606);
                return false;
            }
            if(amount!=null&&!amount.equals(calcConditionGoodsCalcGoodsCondition.getAmount())){
                log.info("计算商品类(计算商品条件)验证 计算商品所有金额数据必须一致");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1607);
                return false;
            }
            if(quantity!=null&&!quantity.equals(calcConditionGoodsCalcGoodsCondition.getQuantity())){
                log.info("计算商品类(计算商品条件)验证 计算商品所有数量数据必须一致");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1608);
                return false;
            }
            price = calcConditionGoodsCalcGoodsCondition.getPrice();
            groupNo = calcConditionGoodsCalcGoodsCondition.getGroupNo();
            barcode = calcConditionGoodsCalcGoodsCondition.getBarcode();
            quantity = calcConditionGoodsCalcGoodsCondition.getQuantity();
            amount = calcConditionGoodsCalcGoodsCondition.getAmount();
            log.debug("开始计算商品条件属性验证");
            boolean valid = CalcConditionGoodsCalcGoodsConditionUtils.validAllParam(calcConditionGoodsCalcGoodsCondition, businessMessage);
            log.debug("完成计算商品条件属性验证{}",valid);
            if(!valid){
                return valid;
            }
        }
        return true;
    }

}
