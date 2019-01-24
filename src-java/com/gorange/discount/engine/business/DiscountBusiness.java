package com.gorange.discount.engine.business;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.Discount;
import com.gorange.discount.engine.model.discount.query.DiscountMemo;
import com.gorange.discount.engine.model.discount.query.QueryGoodsDiscountResult;
import com.gorange.discount.engine.model.discount.query.QueryMemberDiscountResult;
import com.gorange.discount.engine.model.discount.business.DiscountResult;
import com.gorange.discount.engine.model.goods.Goods;
import com.gorange.discount.engine.model.member.Member;
import com.gorange.discount.engine.model.ticket.AssociatedTicket;
import com.gorange.discount.engine.model.ticket.CurrentTicket;
import com.gorange.discount.engine.utils.BusinessUtils;
import com.gorange.discount.engine.utils.I18nUtils;
import com.gorange.discount.engine.utils.model.discount.business.DiscountUtils;
import com.gorange.discount.engine.utils.model.goods.GoodsUtils;
import com.gorange.discount.engine.utils.model.member.MemberUtils;
import com.gorange.discount.engine.utils.model.ticket.AssociatedTicketUtils;
import com.gorange.discount.engine.utils.model.ticket.CurrentTicketUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 折扣业务处理类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DiscountBusiness {
    private static Logger log = LoggerFactory.getLogger(DiscountBusiness.class);

    /**
     * 商品折扣计算
     * @param languageCode      国际化语言代码
     * @param currentTicket     当前交易
     * @param associatedTicket  关联交易
     * @param discountList      折扣数据集
     * @return 返回折扣计算结果
     */
    public DiscountResult calcGoodsDiscount(String languageCode, CurrentTicket currentTicket, AssociatedTicket associatedTicket, List<Discount> discountList){
        synchronized (this){
            String active = UUID.randomUUID().toString();
            log.info("============================================Start Calc【"+active+"】==========================================================");
            DiscountResult discountResult = new DiscountResult(StringUtils.defaultString(languageCode, I18nUtils.DEFAULT_LANGUAGE_CODE));
            try {
                BusinessMessage businessMessage = new BusinessMessage();

                if(!CurrentTicketUtils.validAllParam(currentTicket,businessMessage)){
                    return discountResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                if(!AssociatedTicketUtils.validAllParam(associatedTicket,businessMessage)){
                    return discountResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                if(!DiscountUtils.validDiscountData(discountList,businessMessage)){
                    return discountResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                BusinessUtils.sortDiscountByCalcLevel(discountList);

                BusinessUtils.filterAndCalc(currentTicket,associatedTicket,discountList);

                BusinessUtils.packageDiscountResult(currentTicket,discountResult);

            }catch (Exception e){
                log.error(e.getMessage(),e);
                discountResult.setResultInfo(BusinessMessageEnum.MESSAGE_9999);
            }
            log.info("============================================End Calc【"+active+"】==========================================================");
            return discountResult;
        }
    }

    /**
     * 查询顾客可参与折扣
     * @param languageCode  国际化语言代码
     * @param merchantNo    商户号
     * @param timeZone      时区
     * @param businessTime  交易时间
     * @param member        顾客对象
     * @param discountList  折扣数据集
     * @return 返回顾客可参与的折扣
     */
    public QueryMemberDiscountResult queryMemberJoinDiscount(String languageCode, String merchantNo, String timeZone, Date businessTime, Member member, List<Discount> discountList){
        synchronized (this){
            String active = UUID.randomUUID().toString();
            log.info("============================================Start Query Member Discount【"+active+"】==========================================================");
            QueryMemberDiscountResult discountQueryResult = new QueryMemberDiscountResult(StringUtils.defaultString(languageCode, I18nUtils.DEFAULT_LANGUAGE_CODE));
            try {
                BusinessMessage businessMessage = new BusinessMessage();

                if(!DiscountUtils.validDiscountData(discountList,businessMessage)){
                    return discountQueryResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                if(!MemberUtils.validAllParam(member,businessMessage)){
                    return discountQueryResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                List<DiscountMemo> joinDiscList = BusinessUtils.filterTargetMemberDiscount(merchantNo,timeZone,businessTime,member,discountList);

                discountQueryResult.setDiscountList(joinDiscList);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                discountQueryResult.setResultInfo(BusinessMessageEnum.MESSAGE_9999);
            }
            log.info("============================================End Query Member Discount【"+active+"】==========================================================");
            return discountQueryResult;
        }
    }

    /**
     * 查询商品可参与折扣
     * @param languageCode  国际化语言代码
     * @param merchantNo    商户号
     * @param timeZone      时区
     * @param businessTime  交易时间
     * @param goodsList     商品对象集
     * @param discountList  折扣数据集
     * @return 返回商品可参与的折扣
     */
    public QueryGoodsDiscountResult queryGoodsJoinDiscount(String languageCode, String merchantNo, String timeZone, Date businessTime, List<Goods> goodsList, List<Discount> discountList){
        synchronized (this){
            String active = UUID.randomUUID().toString();
            log.info("============================================Start Query Goods Discount【"+active+"】==========================================================");
            QueryGoodsDiscountResult discountQueryResult = new QueryGoodsDiscountResult(StringUtils.defaultString(languageCode, I18nUtils.DEFAULT_LANGUAGE_CODE));
            try {
                BusinessMessage businessMessage = new BusinessMessage();

                if(!DiscountUtils.validDiscountData(discountList,businessMessage)){
                    return discountQueryResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                if(!GoodsUtils.validAllParam(goodsList,businessMessage)){
                    return discountQueryResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                Map<String,List<DiscountMemo>> joinDiscInfo = BusinessUtils.filterTargetGoodsDiscount(merchantNo,timeZone,businessTime,goodsList,discountList);

                discountQueryResult.setDiscountInfo(joinDiscInfo);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                discountQueryResult.setResultInfo(BusinessMessageEnum.MESSAGE_9999);
            }
            log.info("============================================End Query Goods Discount【"+active+"】==========================================================");
            return discountQueryResult;
        }
    }

    /**
     * 查询顾客购买的商品可参与折扣
     * @param languageCode  国际化语言代码
     * @param merchantNo    商户号
     * @param timeZone      时区
     * @param businessTime  交易时间
     * @param member        顾客对象
     * @param goodsList     商品对象集
     * @param discountList  折扣数据集
     * @return 返回顾客可参与的折扣
     */
    public QueryGoodsDiscountResult queryMemberAndGoodsJoinDiscount(String languageCode, String merchantNo, String timeZone, Date businessTime, Member member, List<Goods> goodsList, List<Discount> discountList){
        synchronized (this){
            String active = UUID.randomUUID().toString();
            log.info("============================================Start Query Member's Goods Discount【"+active+"】==========================================================");
            QueryGoodsDiscountResult discountQueryResult = new QueryGoodsDiscountResult(StringUtils.defaultString(languageCode, I18nUtils.DEFAULT_LANGUAGE_CODE));
            try {
                BusinessMessage businessMessage = new BusinessMessage();

                if(!DiscountUtils.validDiscountData(discountList,businessMessage)){
                    return discountQueryResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                if(!MemberUtils.validAllParam(member,businessMessage)){
                    return discountQueryResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }
                if(!GoodsUtils.validAllParam(goodsList,businessMessage)){
                    return discountQueryResult.setResultInfo(businessMessage.getBusinessMessageEnum());
                }

                Map<String,List<DiscountMemo>> joinDiscInfo = BusinessUtils.filterTargetMemberAndGoodsDiscount(merchantNo,businessTime,timeZone,member,goodsList,discountList);

                discountQueryResult.setDiscountInfo(joinDiscInfo);
            }catch (Exception e){
                log.error(e.getMessage(),e);
                discountQueryResult.setResultInfo(BusinessMessageEnum.MESSAGE_9999);
            }
            log.info("============================================End Query Member's Goods Discount【"+active+"】==========================================================");
            return discountQueryResult;
        }
    }
}
