package com.gorange.discount.engine.utils.model.discount.business.joincondition;

import com.gorange.discount.engine.enums.ExpressionKeyEnum;
import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.joincondition.JoinConditionGoods;
import com.gorange.discount.engine.utils.model.goods.GoodsUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 折扣参与条件参数商品工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionGoodsUtils {
    private static Logger log = LoggerFactory.getLogger(JoinConditionGoodsUtils.class);

    /**
     * 折扣参与条件参数商品属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣参与条件参数商品属性的条件限制
     * </pre>
     * @param joinConditionGoods    折扣参与条件参数商品类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(JoinConditionGoods joinConditionGoods, BusinessMessage businessMessage){
        if(!JoinConditionGoodsUtils.validParamForGroupNo(joinConditionGoods, businessMessage)){
            log.debug("折扣参与条件参数商品属性(分组)验证未通过");
            return false;
        }
        if(!JoinConditionGoodsUtils.validParamForBarcode(joinConditionGoods, businessMessage)){
            log.debug("折扣参与条件参数商品属性(条码)验证未通过");
            return false;
        }
        if(!JoinConditionGoodsUtils.validParamForPrice(joinConditionGoods, businessMessage)){
            log.debug("折扣参与条件参数商品属性(金额)验证未通过");
            return false;
        }

        return true;
    }

    /**
     * 折扣参与条件参数商品属性(分组)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     * </pre>
     * @param joinConditionGoods    折扣参与条件参数商品类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForGroupNo(JoinConditionGoods joinConditionGoods, BusinessMessage businessMessage){
        if(joinConditionGoods.getBarcode()!=null&&!joinConditionGoods.getGroupNo().isEmpty()){
            Iterator<Map.Entry<String,List<String>>> iterator = joinConditionGoods.getGroupNo().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                List<String> groupNoList = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数商品属性(分组)验证 分组表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0800);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("折扣参与条件参数商品属性(分组)验证 分组表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0800);
                    return false;
                }
                if(groupNoList==null){
                    log.info("折扣参与条件参数商品属性(分组)验证 分组表达式key对应的条码数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0801);
                    return false;
                }
                for(String groupNo:groupNoList){
                    int groupNoLength = StringUtils.defaultString(groupNo).length();
                    if(groupNoLength>GoodsUtils.GROUP_NO_MAX_LENGTH){
                        log.info("折扣参与条件参数商品属性(分组)验证 分组表达式key对应的分组数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0802);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 折扣参与条件参数商品属性(条码)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     * </pre>
     * @param joinConditionGoods    折扣参与条件参数商品类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBarcode(JoinConditionGoods joinConditionGoods, BusinessMessage businessMessage){
        if(joinConditionGoods.getBarcode()!=null&&!joinConditionGoods.getBarcode().isEmpty()){
            for(String expression:joinConditionGoods.getBarcode().keySet()){
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数商品属性(条码)验证 条码表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0810);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("折扣参与条件参数商品属性(条码)验证 条码表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0810);
                    return false;
                }
                List<String> barcodeList = joinConditionGoods.getBarcode().get(expression);
                if(barcodeList==null){
                    log.info("折扣参与条件参数商品属性(条码)验证 条码表达式key对应的条码数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0811);
                    return false;
                }
                for(String barcode:barcodeList){
                    int barcodeLength = StringUtils.defaultString(barcode).length();
                    if(GoodsUtils.BARCODE_MIN_LENGTH>barcodeLength||barcodeLength>GoodsUtils.BARCODE_MAX_LENGTH){
                        log.info("折扣参与条件参数商品属性(条码)验证 条码表达式key对应的条码数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0812);
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * 折扣参与条件参数商品属性(金额)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      最小金额的key只能是GR 或 GR_AND_EQ
     *      最大金额的key只能是LE 或 LE_AND_EQ
     *      key有正确有效的值时,value不可为null
     *      最小金额必须大于0
     *      最大金额必须大于0
     * </pre>
     * @param joinConditionGoods    折扣参与条件参数商品类
     * @param businessMessage               消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForPrice(JoinConditionGoods joinConditionGoods, BusinessMessage businessMessage){
        Double minPrice = null,maxPrice = null;
        if(joinConditionGoods.getMinPrice()!=null&&!joinConditionGoods.getMinPrice().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = joinConditionGoods.getMinPrice().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                minPrice = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数商品属性(金额)验证 最小金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0820);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.GR&&expressionKeyEnum!=ExpressionKeyEnum.GR_AND_EQ){
                    log.info("折扣参与条件参数商品属性(金额)验证 最小金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0820);
                    return false;
                }
                if(minPrice==null){
                    log.info("折扣参与条件参数商品属性(金额)验证 最小金额表达式key对应的金额数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0821);
                    return false;
                }
                if(minPrice<=0){
                    log.info("折扣参与条件参数商品属性(金额)验证 最小金额表达式key对应的金额数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0822);
                    return false;
                }
            }
        }
        if(joinConditionGoods.getMaxPrice()!=null&&!joinConditionGoods.getMaxPrice().isEmpty()){
            Iterator<Map.Entry<String,Double>> iterator = joinConditionGoods.getMaxPrice().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Double> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                maxPrice = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("折扣参与条件参数商品属性(金额)验证 最大金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0830);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.LE&&expressionKeyEnum!=ExpressionKeyEnum.LE_AND_EQ){
                    log.info("折扣参与条件参数商品属性(金额)验证 最大金额表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0830);
                    return false;
                }
                if(maxPrice==null){
                    log.info("折扣参与条件参数商品属性(金额)验证 最大金额表达式key对应的金额数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0831);
                    return false;
                }
                if(maxPrice<=0){
                    log.info("折扣参与条件参数商品属性(金额)验证 最小金额表达式key对应的金额数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0832);
                    return false;
                }
            }
        }
        if(minPrice!=null&&maxPrice!=null&&minPrice>maxPrice){
            log.info("折扣参与条件参数商品属性(金额)验证 最小金额不得大于最大金额");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0833);
            return false;
        }
        return true;
    }
}
