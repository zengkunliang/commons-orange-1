package com.gorange.discount.engine.utils.model.goods;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.Discount;
import com.gorange.discount.engine.model.discount.business.DiscountGroup;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcConditionGoods;
import com.gorange.discount.engine.model.discount.business.calccondition.CalcConditionGoodsTargetGoods;
import com.gorange.discount.engine.model.discount.business.result.GoodsDiscountResult;
import com.gorange.discount.engine.model.goods.Goods;
import com.gorange.discount.engine.model.ticket.CurrentTicket;
import com.gorange.discount.engine.utils.CommonUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 商品工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class GoodsUtils {
    private static Logger log = LoggerFactory.getLogger(GoodsUtils.class);

    /**
     * 条码最小长度
     */
    public static final Integer BARCODE_MIN_LENGTH      = 6;
    /**
     * 条码最大长度
     */
    public static final Integer BARCODE_MAX_LENGTH      = 50;
    /**
     * 分类编号最大长度
     */
    public static final Integer GROUP_NO_MAX_LENGTH     = 150;
    /**
     * 单位最大长度
     */
    public static final Integer PACK_MAX_LENGTH         = 10;

    /**
     * 商品属性验证
     * <pre>
     * 验证条件:
     *      验证所有商品属性的条件限制是否符合要求
     * </pre>
     * @param goodsList         商品对象集
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(List<Goods> goodsList, BusinessMessage businessMessage){
        List<Integer> lineNoList = new ArrayList<>();
        if(goodsList==null){
            log.info("商品属性验证 商品集不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0112);
            return false;
        }
        if(goodsList.isEmpty()){
            log.info("商品属性验证 商品集不可为Empty");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0113);
            return false;
        }
        for(Goods goods:goodsList){
            if(goods.getLineNo()==null){
                log.info("商品属性验证 商品行号不可为null");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0109);
                return false;
            }
            if(goods.getLineNo()<1){
                log.info("商品属性验证 商品行号不合规范");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0110);
                return false;
            }
            if(lineNoList.contains(goods.getLineNo())){
                log.info("商品属性验证 商品行号不可重复");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0111);
                return false;
            }
            lineNoList.add(goods.getLineNo());
            boolean valid = GoodsUtils.validAllParam(goods,businessMessage);
            if(!valid){
                return valid;
            }
        }
        return true;
    }

    /**
     * 商品属性验证
     * <pre>
     * 验证条件:
     *      验证所有商品属性的条件限制是否符合要求
     * </pre>
     * @param goods             商品对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(Goods goods,BusinessMessage businessMessage){
        if(goods==null){
            log.info("商品属性验证 商品不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0108);
            return false;
        }
        if(!GoodsUtils.validParamForGroupNo(goods, businessMessage)){
            log.debug("商品属性(分类编号)验证未通过");
            return false;
        }
        if(!GoodsUtils.validParamForBarcode(goods, businessMessage)){
            log.debug("商品属性(条码)验证未通过");
            return false;
        }
        if(!GoodsUtils.validParamForPrice(goods, businessMessage)){
            log.debug("商品属性(单价)验证未通过");
            return false;
        }
        if(!GoodsUtils.validParamForQuantity(goods, businessMessage)){
            log.debug("商品属性(数量)验证未通过");
            return false;
        }
        if(!GoodsUtils.validParamForPack(goods, businessMessage)){
            log.debug("商品属性(单位)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 商品属性(分类编号)验证
     * <pre>
     * 验证条件:
     *      长度区间为【0-150】
     * </pre>
     * @param goods             商品对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForGroupNo(Goods goods, BusinessMessage businessMessage){
        String goodsGroupNo = goods.getGroupNo();
        int groupNoLength = goodsGroupNo.length();
        if(groupNoLength>GoodsUtils.GROUP_NO_MAX_LENGTH){
            log.info("商品属性(分类编号)验证 分类编号长度不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0106);
            return false;
        }
        return true;
    }

    /**
     * 商品属性(条码)验证
     * <pre>
     * 验证条件:
     *      不得为null
     *      长度区间为【6-50】
     * </pre>
     * @param goods             商品对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBarcode(Goods goods, BusinessMessage businessMessage){
        String goodsBarcode = goods.getBarcode();
        if(StringUtils.isBlank(goodsBarcode)){
            log.info("商品属性(条码)验证 条码不可为Blank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0105);
            return false;
        }
        int barcodeLength = goodsBarcode.length();
        if(GoodsUtils.BARCODE_MIN_LENGTH>barcodeLength||barcodeLength>GoodsUtils.BARCODE_MAX_LENGTH){
            log.info("商品属性(条码)验证 条码长度不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0104);
            return false;
        }
        return true;
    }

    /**
     * 商品属性(单价)验证
     * <pre>
     * 验证条件:
     *      不得为null
     *      不得小于0
     * </pre>
     * @param goods             商品对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForPrice(Goods goods, BusinessMessage businessMessage){
        if(goods.getPrice()==null){
            log.info("商品属性(单价)验证 单价不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0103);
            return false;
        }
        if(goods.getPrice()<0){
            log.info("商品属性(单价)验证 单价不得小于0");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0102);
            return false;
        }
        return true;
    }

    /**
     * 商品属性(数量)验证
     * <pre>
     * 验证条件:
     *      不得为null
     *      不得小于且等于0
     * </pre>
     * @param goods             商品对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForQuantity(Goods goods, BusinessMessage businessMessage){
        if(goods.getQuantity()<=0){
            log.info("商品属性(数量)验证 数量不得小于且等于0");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0100);
            return false;
        }
        return true;
    }

    /**
     * 商品属性(单位)验证
     * <pre>
     * 验证条件:
     *      长度区间为【0-10】
     * </pre>
     * @param goods             商品对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForPack(Goods goods, BusinessMessage businessMessage){
        String goodsPack = goods.getPack();
        int packLength = goodsPack.length();
        if(packLength>GoodsUtils.PACK_MAX_LENGTH){
            log.info("商品属性(单位)验证 单位长度不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0107);
            return false;
        }
        return true;
    }

    /**
     * 对商品进行排序(根据单价)
     * @param goodsList 需要排序的商品集
     * @param desc      是否根据单价进行倒序
     */
    public static void sortGoodsByPrice(List<Goods> goodsList,boolean desc){
        if(goodsList!=null&&!goodsList.isEmpty()){
            Collections.sort(goodsList, (arg1, arg2) -> {
                int result = 0;
                try {
                    double arg1Value = arg1.getPrice();
                    double arg2Value = arg2.getPrice();
                    if(arg1Value>arg2Value){
                        result = 1;
                    }else{
                        result = -1;
                    }
                    if (desc) {
                        result = -result;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return result;
            });
        }
    }

    /**
     * 获取交易中单个折扣目标商品
     * @param currentTicket                 当前交易对象
     * @param discount                      折扣对象
     * @param calcConditionGoodsTargetGoods 目标商品表达式对象
     * @return 返回单个折扣目标商品集
     */
    public static List<Goods> getDiscTargetGoodsByTicket(CurrentTicket currentTicket, Discount discount,CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods){
        List<Goods> targetGoodsList = new ArrayList<>();

        //克隆交易商品
        List<Goods> tempGoodsList = new ArrayList<>();
        tempGoodsList.addAll(CommonUtils.deepCopy(currentTicket.getGoodsList()));

        //筛选目标商品
        for (Goods goods : tempGoodsList) {
            if(GoodsUtils.validTargetGoods(goods,discount.getDiscountGroup(),calcConditionGoodsTargetGoods)){
                targetGoodsList.add(goods);
            }
        }
        return targetGoodsList;
    }

    /**
     * 验证是否是目标商品
     * @param goods                         商品对象
     * @param discountGroup                 折扣分组对象
     * @param calcConditionGoodsTargetGoods 目标商品表达式对象
     * @return 验证是否是折扣目标商品
     */
    public static boolean validTargetGoods(Goods goods, DiscountGroup discountGroup, CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods){
        if(!CommonUtils.judgementExpression(goods.getGroupNo(),calcConditionGoodsTargetGoods.getGroupNo())){
            return false;
        }
        if(!CommonUtils.judgementExpression(goods.getBarcode(),calcConditionGoodsTargetGoods.getBarcode())){
            return false;
        }
        if(discountGroup.getExclusionGroupNoList()!=null&&!discountGroup.getExclusionGroupNoList().isEmpty()){
            for(GoodsDiscountResult goodsDiscountResult:goods.getDiscResultList()){
                if(ArrayUtils.contains(discountGroup.getExclusionGroupNoList().toArray(),goodsDiscountResult.getDiscGroupNo())){
                    return false;
                }
            }
        }
        return true;
    }
}
