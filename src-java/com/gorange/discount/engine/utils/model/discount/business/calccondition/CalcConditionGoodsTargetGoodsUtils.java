package com.gorange.discount.engine.utils.model.discount.business.calccondition;

import com.gorange.discount.engine.enums.CalcGoodsSortTypeEnum;
import com.gorange.discount.engine.enums.ExpressionKeyEnum;
import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcConditionGoodsTargetGoods;
import com.gorange.discount.engine.utils.model.goods.GoodsUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 目标商品工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CalcConditionGoodsTargetGoodsUtils {
    private static Logger log = LoggerFactory.getLogger(CalcConditionGoodsTargetGoodsUtils.class);

    /**
     * 目标商品属性验证
     * <pre>
     * 验证条件:
     *      验证所有目标商品属性的条件限制
     * </pre>
     * @param calcConditionGoodsTargetGoods 目标商品类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods, BusinessMessage businessMessage){
        if(!CalcConditionGoodsTargetGoodsUtils.validParamForGroupNo(calcConditionGoodsTargetGoods, businessMessage)){
            log.debug("目标商品属性(分类编号)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsTargetGoodsUtils.validParamForBarcode(calcConditionGoodsTargetGoods, businessMessage)){
            log.debug("目标商品属性(条码)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsTargetGoodsUtils.validParamForSortType(calcConditionGoodsTargetGoods, businessMessage)){
            log.debug("目标商品属性(排序类型)验证未通过");
            return false;
        }
        if(!CalcConditionGoodsTargetGoodsUtils.validParamForDiscValue(calcConditionGoodsTargetGoods, businessMessage)){
            log.debug("目标商品属性(折扣值)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 目标商品属性(分类编号)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     * </pre>
     * @param calcConditionGoodsTargetGoods 目标商品类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForGroupNo(CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods, BusinessMessage businessMessage){
        if(calcConditionGoodsTargetGoods.getGroupNo()!=null&&!calcConditionGoodsTargetGoods.getGroupNo().isEmpty()){
            Iterator<Map.Entry<String,List<String>>> iterator = calcConditionGoodsTargetGoods.getGroupNo().entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> expressionMapEntry = iterator.next();
                String expression = expressionMapEntry.getKey();
                List<String> groupNoList = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("目标商品属性(分类编号)验证 分类编号表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1200);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("目标商品属性(分类编号)验证 分类编号表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1200);
                    return false;
                }
                if(groupNoList==null){
                    log.info("目标商品属性(分类编号)验证 分类编号表达式key对应的分类数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1201);
                    return false;
                }
                for(String groupNo:groupNoList){
                    int groupNoLength = StringUtils.defaultString(groupNo).length();
                    if(groupNoLength>GoodsUtils.GROUP_NO_MAX_LENGTH){
                        log.info("目标商品属性(分类编号)验证 分类编号表达式key对应的分类数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1202);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 目标商品属性(条码)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     * </pre>
     * @param calcConditionGoodsTargetGoods 目标商品类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBarcode(CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods, BusinessMessage businessMessage){
        if(calcConditionGoodsTargetGoods.getBarcode()!=null&&!calcConditionGoodsTargetGoods.getBarcode().isEmpty()){
            for(String expression:calcConditionGoodsTargetGoods.getBarcode().keySet()){
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression);
                if(expressionKeyEnum==null){
                    log.info("目标商品属性(条码)验证 条码表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1203);
                    return false;
                }
                if(expressionKeyEnum!=ExpressionKeyEnum.IN&&expressionKeyEnum!=ExpressionKeyEnum.NOT_IN){
                    log.info("目标商品属性(条码)验证 条码表达式key数据不合规范");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1203);
                    return false;
                }
                List<String> barcodeList = calcConditionGoodsTargetGoods.getBarcode().get(expression);
                if(barcodeList==null){
                    log.info("目标商品属性(条码)验证 条码表达式key对应的条码数据不可为null");
                    businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1204);
                    return false;
                }
                for(String barcode:barcodeList){
                    int barcodeLength = StringUtils.defaultString(barcode).length();
                    if(GoodsUtils.BARCODE_MIN_LENGTH>barcodeLength||barcodeLength>GoodsUtils.BARCODE_MAX_LENGTH){
                        log.info("目标商品属性(条码)验证 条码表达式key对应的条码数据不合规范");
                        businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1205);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 目标商品属性(排序类型)验证
     * <pre>
     * 验证条件:
     *      key必须匹配ExpressionKeyEnum中的数据
     *      key只能是IN 或 NOT_IN
     *      key有正确有效的值时,value不可为null
     * </pre>
     * @param calcConditionGoodsTargetGoods 目标商品类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForSortType(CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods, BusinessMessage businessMessage){
        if(StringUtils.isBlank(calcConditionGoodsTargetGoods.getSortType())){
            log.info("目标商品属性(排序类型)验证 排序类型不可为Blank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1206);
            return false;
        }
        if(CalcGoodsSortTypeEnum.getExpressionKeyEnum(calcConditionGoodsTargetGoods.getSortType())==null){
            log.info("目标商品属性(排序类型)验证 排序类型数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1207);
            return false;
        }
        return true;
    }

    /**
     * 目标商品属性(折扣值)验证
     * <pre>
     * 验证条件:
     *      不可为null
     *      不可为0
     * </pre>
     * @param calcConditionGoodsTargetGoods 目标商品类
     * @param businessMessage                       消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForDiscValue(CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods, BusinessMessage businessMessage){
        if(calcConditionGoodsTargetGoods.getDiscValue()==null){
            log.info("目标商品属性(折扣值)验证 折扣值不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1208);
            return false;
        }
        if(calcConditionGoodsTargetGoods.getDiscValue()<=0){
            log.info("目标商品属性(折扣值)验证 折扣值不合逻辑");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1209);
            return false;
        }
        return true;
    }
}
