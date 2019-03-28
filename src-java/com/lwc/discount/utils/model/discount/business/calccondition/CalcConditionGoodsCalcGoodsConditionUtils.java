package com.lwc.discount.utils.model.discount.business.calccondition;

import com.lwc.discount.enums.ExpressionKeyEnum;
import com.lwc.discount.enums.BusinessMessageEnum;
import com.lwc.discount.model.common.BusinessMessage;
import com.lwc.discount.model.discount.business.calccondition.CalcConditionGoodsCalcGoodsCondition;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.lwc.discount.utils.model.goods.GoodsUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 计算商品条件工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionGoodsCalcGoodsConditionUtils {
    private static Logger log = LoggerFactory.getLogger(CalcConditionGoodsCalcGoodsConditionUtils.class);

    /**
     * 计算商品条件属性验证
     * <pre>
     * 验证条件:
     *      验证所有计算商品条件属性的条件限制
     * </pre>
     * @param calcConditionGoodsCalcGoodsCondition  计算商品条件类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition, BusinessMessage businessMessage){
        if(!CalcConditionGoodsCalcGoodsConditionUtils.validParamForGroupNo(calcConditionGoodsCalcGoodsCondition, businessMessage)){
            log.debug("计算商品条件属性(分类编号)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsCalcGoodsConditionUtils.validParamForBarcode(calcConditionGoodsCalcGoodsCondition, businessMessage)){
            log.debug("计算商品条件属性(条码)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsCalcGoodsConditionUtils.validParamForPrice(calcConditionGoodsCalcGoodsCondition, businessMessage)){
            log.debug("计算商品条件属性(单价)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsCalcGoodsConditionUtils.validParamForQuantity(calcConditionGoodsCalcGoodsCondition, businessMessage)){
            log.debug("计算商品条件属性(数量)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsCalcGoodsConditionUtils.validParamForAmount(calcConditionGoodsCalcGoodsCondition, businessMessage)){
            log.debug("计算商品条件属性(金额)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsCalcGoodsConditionUtils.validQuantityAndAmountLogic(calcConditionGoodsCalcGoodsCondition, businessMessage)){
            log.debug("计算商品条件属性(数量&金额逻辑)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 计算商品条件属性(分类编号)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     * </pre>
     * @param calcConditionGoodsCalcGoodsCondition  计算商品条件类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForGroupNo(CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition, BusinessMessage businessMessage){
        if(calcConditionGoodsCalcGoodsCondition.getGroupNo()!=null&&!calcConditionGoodsCalcGoodsCondition.getGroupNo().isEmpty()){
            Iterator<Map.Entry<String,List<String>>> iterator = calcConditionGoodsCalcGoodsCondition.getGroupNo().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                List<String> groupNoList = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("计算商品条件属性(分类编号)验证 分类编号表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1300);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("计算商品条件属性(分类编号)验证 分类编号表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1300);
                    return false;
                }
                if(groupNoList==null){
                    log.info("计算商品条件属性(分类编号)验证 分类编号表达式key对应的分类数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1301);
                    return false;
                }
                for(String groupNo:groupNoList){
                    int groupNoLength = StringUtils.defaultString(groupNo).length();
                    if(groupNoLength>GoodsUtils.GROUP_NO_MAX_LENGTH){
                        log.info("计算商品条件属性(分类编号)验证 分类编号表达式key对应的分类数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1302);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 计算商品条件属性(条码)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     * </pre>
     * @param calcConditionGoodsCalcGoodsCondition  计算商品条件类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBarcode(CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition, BusinessMessage businessMessage){
        if(calcConditionGoodsCalcGoodsCondition.getBarcode()!=null&&!calcConditionGoodsCalcGoodsCondition.getBarcode().isEmpty()){
            for(String expression:calcConditionGoodsCalcGoodsCondition.getBarcode().keySet()){
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("计算商品条件属性(条码)验证 条码表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1303);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("计算商品条件属性(条码)验证 条码表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1303);
                    return false;
                }
                List<String> barcodeList = calcConditionGoodsCalcGoodsCondition.getBarcode().get(expression);
                if(barcodeList==null){
                    log.info("计算商品条件属性(条码)验证 条码表达式key对应的条码数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1304);
                    return false;
                }
                for(String barcode:barcodeList){
                    int barcodeLength = StringUtils.defaultString(barcode).length();
                    if(GoodsUtils.BARCODE_MIN_LENGTH>barcodeLength||barcodeLength>GoodsUtils.BARCODE_MAX_LENGTH){
                        log.info("计算商品条件属性(条码)验证 条码表达式key对应的条码数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1305);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 计算商品条件属性(单价)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ 或 GR_AND_EQ 或 LE_AND_EQ
     *      key有正确有效的值时,value不可为null,value必须大于0
     * </pre>
     * @param calcConditionGoodsCalcGoodsCondition  计算商品条件类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForPrice(CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition, BusinessMessage businessMessage){
        if(calcConditionGoodsCalcGoodsCondition.getPrice()!=null&&!calcConditionGoodsCalcGoodsCondition.getPrice().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = calcConditionGoodsCalcGoodsCondition.getPrice().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                Double price = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("计算商品条件属性(单价)验证 单价表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1306);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.EQ
                        &&expressionKeyEnum!=ExpressionKeyEnum.GR_AND_EQ
                        &&expressionKeyEnum!=ExpressionKeyEnum.LE_AND_EQ){
                    log.info("计算商品条件属性(单价)验证 单价表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1306);
                    return false;
                }
                if(price==null){
                    log.info("计算商品条件属性(单价)验证 单价表达式key对应的单价数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1307);
                    return false;
                }
                if(price<=0){
                    log.info("计算商品条件属性(单价)验证 单价达式key对应的单价数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1308);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 计算商品条件属性(金额)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     *      key有正确有效的值时,value不可为null,value必须大于0
     * </pre>
     * @param calcConditionGoodsCalcGoodsCondition  计算商品条件类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForAmount(CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition, BusinessMessage businessMessage){
        if(calcConditionGoodsCalcGoodsCondition.getAmount()!=null&&!calcConditionGoodsCalcGoodsCondition.getAmount().isEmpty()) {
            for (String expression : calcConditionGoodsCalcGoodsCondition.getAmount().keySet()) {
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if (expressionKeyEnum == null) {
                    log.info("计算商品条件属性(金额)验证 金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1313);
                    return false;
                }
                if (expressionKeyEnum != ExpressionKeyEnum.EQ) {
                    log.info("计算商品条件属性(金额)验证 金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1313);
                    return false;
                }
                Double amount = calcConditionGoodsCalcGoodsCondition.getAmount().get(expression);
                if (amount == null) {
                    log.info("计算商品条件属性(金额)验证 金额表达式key对应的金额数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1314);
                    return false;
                }
                if (amount <= 0) {
                    log.info("计算商品条件属性(金额)验证 金额表达式key对应的金额数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1315);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 计算商品条件属性(数量)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是EQ
     *      key有正确有效的值时,value不可为null,value必须大于0
     * </pre>
     * @param calcConditionGoodsCalcGoodsCondition  计算商品条件类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForQuantity(CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition, BusinessMessage businessMessage){
        if(calcConditionGoodsCalcGoodsCondition.getQuantity()!=null&&!calcConditionGoodsCalcGoodsCondition.getQuantity().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = calcConditionGoodsCalcGoodsCondition.getQuantity().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                Double quantity = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("计算商品条件属性(数量)验证 数量表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1309);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.EQ){
                    log.info("计算商品条件属性(数量)验证 数量表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1309);
                    return false;
                }
                if(quantity==null){
                    log.info("计算商品条件属性(数量)验证 数量表达式key对应的数量数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1310);
                    return false;
                }
                if(quantity<=0){
                    log.info("计算商品条件属性(数量)验证 数量表达式key对应的数量数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1311);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 计算商品条件属性(数量和金额逻辑)验证
     * @param calcConditionGoodsCalcGoodsCondition  计算商品条件类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validQuantityAndAmountLogic(CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition, BusinessMessage businessMessage){
        boolean quantityEmpty = false;
        boolean amountEmpty = false;
        if(calcConditionGoodsCalcGoodsCondition.getQuantity()==null||calcConditionGoodsCalcGoodsCondition.getQuantity().isEmpty()){
            quantityEmpty = true;
        }
        if(calcConditionGoodsCalcGoodsCondition.getAmount()==null||calcConditionGoodsCalcGoodsCondition.getAmount().isEmpty()){
            amountEmpty = true;
        }
        if(quantityEmpty&&amountEmpty){
            log.info("计算商品条件属性(数量和金额逻辑)验证 数量/金额表达式必须设置一种");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1312);
            return false;
        }
        if(!quantityEmpty&&!amountEmpty){
            log.info("计算商品条件属性(数量和金额逻辑)验证 数量/金额表达式只能设置一种");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1312);
            return false;
        }
        return true;
    }
}
