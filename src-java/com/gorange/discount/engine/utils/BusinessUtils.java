package com.gorange.discount.engine.utils;

import com.gorange.discount.engine.enums.*;
import com.gorange.discount.engine.model.discount.business.*;
import com.gorange.discount.engine.model.discount.business.calccondition.*;
import com.gorange.discount.engine.model.discount.business.joincondition.*;
import com.gorange.discount.engine.model.discount.business.result.DiscountResultDetail;
import com.gorange.discount.engine.model.discount.business.result.GoodsDiscountResult;
import com.gorange.discount.engine.model.discount.business.result.GoodsResult;
import com.gorange.discount.engine.model.discount.business.query.DiscountMemo;
import com.gorange.discount.engine.model.goods.Goods;
import com.gorange.discount.engine.model.member.Member;
import com.gorange.discount.engine.model.ticket.AssociatedTicket;
import com.gorange.discount.engine.model.ticket.CurrentTicket;
import com.gorange.discount.engine.utils.model.discount.business.DiscountUtils;
import com.gorange.discount.engine.utils.model.goods.GoodsUtils;
import com.gorange.discount.engine.utils.model.ticket.CurrentTicketUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 折扣业务处理工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class BusinessUtils {
    private static Logger log = LoggerFactory.getLogger(BusinessUtils.class);

    /**
     * 对折扣数据进行排序(根据计算优先级从高到低)
     * @param discountList  折扣数据集
     */
    public static void sortDiscountByCalcLevel(List<Discount> discountList){
        boolean desc = true;
        if(discountList!=null&&!discountList.isEmpty()){
            Collections.sort(discountList, (arg1, arg2) -> {
                int result = 0;
                try {
                    double arg1Value = arg1.getDiscountGroup().getCalcLevel();
                    double arg2Value = arg2.getDiscountGroup().getCalcLevel();
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
     * 对折扣进行过滤并计算
     * @param currentTicket     当前交易
     * @param associatedTicket  关联交易
     * @param discountList      折扣数据集
     */
    public static void filterAndCalc(CurrentTicket currentTicket, AssociatedTicket associatedTicket, List<Discount> discountList){
        if(discountList!=null&&!discountList.isEmpty()) {

            String companyNo        = currentTicket.getCompanyNo();
            Date businessTime       = currentTicket.getBusinessTime();
            String timeZone         = currentTicket.getTimeZone();

            for (Discount discount : discountList) {
                log.info("折扣【{}】根据基础条件进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByBase(companyNo,timeZone,businessTime,discount)){
                    continue;
                }

                log.info("折扣【{}】进行当前交易&关联交易克隆",discount.getUniqueNo());
                discount.setCurrentTicket(currentTicket);
                discount.setAssociatedTicket(associatedTicket);

                log.info("折扣【{}】根据会员进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByMember(discount)){
                    continue;
                }

                log.info("折扣【{}】根据商品进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByGoods(discount)){
                    continue;
                }

                log.info("折扣【{}】根据交易进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByTicket(discount)){
                    continue;
                }

                log.info("折扣【{}】数据验证通过,开始计算折扣",discount.getUniqueNo());
                if(!BusinessUtils.calcDiscountResult(discount)){
                    log.info("折扣【{}】整合当前交易数据完成，交易未能参与该折扣",discount.getUniqueNo());
                    continue;
                }

                BusinessUtils.reorganizeCurrentTicketInfo(currentTicket,discount.getCurrentTicket());
                List<Object> paramList = new ArrayList<>();
                paramList.add(discount.getUniqueNo());
                paramList.add(currentTicket.getOriginalAmount());
                paramList.add(currentTicket.getDiscAmount());
                paramList.add(currentTicket.getDiscPoint());
                paramList.add(currentTicket.getDiscIntegral());
                paramList.add(currentTicket.getDiscStamp());
                paramList.add(currentTicket.getFinalAmount());
                log.info("折扣【{}】整合当前交易数据完成,原始金额【{}】折扣金额【{}】赠送积点【{}】赠送积分【{}】赠送印花【{}】最终金额【{}】",paramList.toArray());
            }
        }
    }

    /**
     * 包装折扣结果
     * @param currentTicket     当前交易对象
     * @param discountResult    折扣结果对象
     */
    public static void packageDiscountResult(CurrentTicket currentTicket, DiscountResult discountResult){
        DiscountResultDetail resultDetail = new DiscountResultDetail();

        List<GoodsResult> goodsResultList = new ArrayList<>();
        for (Goods goods : currentTicket.getGoodsList()) {
            GoodsResult goodsResult = new GoodsResult();
            goodsResult.setLineNo(goods.getLineNo());
            goodsResult.setBarcode(goods.getBarcode());
            goodsResult.setName(goods.getName());
            goodsResult.setImageUrl(goods.getImageUrl());
            goodsResult.setQuantity(goods.getQuantity());
            goodsResult.setPrice(goods.getPrice());
            goodsResult.setOriginalAmount(goods.getOriginalAmount());
            goodsResult.setDiscAmount(goods.getDiscAmount());
            goodsResult.setDiscPoint(goods.getDiscPoint());
            goodsResult.setFinalAmount(goods.getFinalAmount());
            goodsResult.setDiscResultList(goods.getDiscResultList());
            goodsResultList.add(goodsResult);
        }

        resultDetail.setTicketOriginalAmount(currentTicket.getOriginalAmount());
        resultDetail.setTicketDiscAmount(currentTicket.getDiscAmount());
        resultDetail.setTicketDiscPoint(currentTicket.getDiscPoint());
        resultDetail.setTicketDiscStamp(currentTicket.getDiscStamp());
        resultDetail.setTicketDiscIntegral(currentTicket.getDiscIntegral());
        resultDetail.setTicketFinalAmount(currentTicket.getFinalAmount());
        resultDetail.setGoodsResultList(goodsResultList);

        discountResult.setDiscResultDetail(resultDetail);

        List<Object> paramList = new ArrayList<>();
        paramList.add(currentTicket.getOrderNo());
        paramList.add(currentTicket.getOriginalAmount());
        paramList.add(currentTicket.getDiscAmount());
        paramList.add(currentTicket.getDiscPoint());
        paramList.add(currentTicket.getDiscIntegral());
        paramList.add(currentTicket.getDiscStamp());
        paramList.add(currentTicket.getFinalAmount());
        log.info("包装交易【{}】折扣结果完成,原始金额【{}】折扣金额【{}】赠送积点【{}】赠送积分【{}】赠送印花【{}】最终金额【{}】",paramList.toArray());
    }

    /**
     * 筛选顾客可参与的折扣
     * @param companyNo     公司编号
     * @param timeZone      时区
     * @param businessTime  交易时间
     * @param member        顾客对象
     * @param discountList  折扣对象集
     * @return 返回顾客可参与的折扣集
     */
    public static List<DiscountMemo> filterTargetMemberDiscount(String companyNo, String timeZone, Date businessTime, Member member, List<Discount> discountList){
        List<DiscountMemo> joinDiscountList = new ArrayList<>();

        if(member!=null&&discountList!=null&&!discountList.isEmpty()){
            for (Discount discount : discountList) {
                log.info("折扣【{}】根据基础条件进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByBase(companyNo,timeZone,businessTime,discount)){
                    continue;
                }

                CurrentTicket currentTicket = new CurrentTicket();
                currentTicket.setMember(member);
                discount.setCurrentTicket(currentTicket);

                log.info("折扣【{}】根据会员进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByMember(discount)){
                    continue;
                }
                joinDiscountList.add(DiscountUtils.conventDiscountToDiscountMemo(timeZone,discount));
            }
        }

        return joinDiscountList;
    }

    /**
     * 筛选商品可参与的折扣
     * @param companyNo     公司编号
     * @param timeZone      时区
     * @param businessTime  交易时间
     * @param goodsList     商品对象集
     * @param discountList  折扣对象集
     * @return 返回商品可参与的折扣集
     */
    public static Map<String,List<DiscountMemo>> filterTargetGoodsDiscount(String companyNo,String timeZone,Date businessTime,List<Goods> goodsList,List<Discount> discountList){
        Map<String,List<DiscountMemo>> joinDiscInfo = new LinkedHashMap<>();

        if(goodsList!=null&&discountList!=null&&!goodsList.isEmpty()&&!discountList.isEmpty()){
            for (Discount discount : discountList) {
                log.info("折扣【{}】根据基础条件进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByBase(companyNo,timeZone,businessTime,discount)){
                    continue;
                }

                CurrentTicket currentTicket = new CurrentTicket();
                currentTicket.setGoodsList(goodsList);
                discount.setCurrentTicket(currentTicket);

                log.info("折扣【{}】根据商品进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByGoods(discount)){
                    continue;
                }

                List<String> joinGoodsBarcodeList = BusinessUtils.filterTargetGoodsDiscountExpression(goodsList,discount);
                BusinessUtils.packageGoodsJoinDiscInfo(timeZone,discount,joinDiscInfo,joinGoodsBarcodeList);
            }
        }

        return joinDiscInfo;
    }

    /**
     * 筛选顾客购买商品可参与的折扣
     * @param companyNo     公司编号
     * @param businessTime  交易时间
     * @param timeZone      时区
     * @param member        顾客对象
     * @param goodsList     商品对象集
     * @param discountList  折扣对象集
     * @return 返回顾客购买商品可参与的折扣集
     */
    public static Map<String,List<DiscountMemo>>  filterTargetMemberAndGoodsDiscount(String companyNo,Date businessTime,String timeZone,Member member,List<Goods> goodsList,List<Discount> discountList){
        Map<String,List<DiscountMemo>> joinDiscInfo = new LinkedHashMap<>();

        if(member!=null&&discountList!=null&&!discountList.isEmpty()){
            for (Discount discount : discountList) {
                log.info("折扣【{}】根据基础条件进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByBase(companyNo,timeZone,businessTime,discount)){
                    continue;
                }

                CurrentTicket currentTicket = new CurrentTicket();
                currentTicket.setMember(member);
                currentTicket.setGoodsList(goodsList);
                discount.setCurrentTicket(currentTicket);

                log.info("折扣【{}】根据会员进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByMember(discount)){
                    continue;
                }

                log.info("折扣【{}】根据商品进行折扣数据验证",discount.getUniqueNo());
                if(!BusinessUtils.filterDiscountByGoods(discount)){
                    continue;
                }

                List<String> joinGoodsBarcodeList = BusinessUtils.filterTargetGoodsDiscountExpression(goodsList,discount);
                BusinessUtils.packageGoodsJoinDiscInfo(timeZone,discount,joinDiscInfo,joinGoodsBarcodeList);
            }
        }

        return joinDiscInfo;
    }

    /**
     * 根据顾客进行折扣数据过滤
     * @param discount      折扣数据
     * @return 通过顾客条件验证返回true 否则返回false
     */
    private static boolean filterDiscountByMember(Discount discount){
        DiscountJoinCondition discountJoinCondition = discount.getDiscountJoinCondition();
        if(discountJoinCondition.getHaveCondition()){
            JoinCondition joinCondition = discountJoinCondition.getCondition();
            JoinConditionMember joinConditionMember = joinCondition.getConditionMember();
            if(joinConditionMember!=null){
                Member member = discount.getCurrentTicket().getMember();
                if(!CommonUtils.judgementExpression(member.getBarcode(),joinConditionMember.getBarcode())){
                    log.info("交易顾客条码【{}】不符合折扣可参于的顾客条码【{}】",member.getBarcode(),joinConditionMember.getBarcode());
                    return false;
                }
                if(!CommonUtils.judgementExpression(member.getType(),joinConditionMember.getType())
                        &&!CommonUtils.judgementExpression(member.getCustomType(),joinConditionMember.getType())){
                    log.info("交易顾客分类【{}】或交易顾客自定义分类【{}】不符合折扣可参于的顾客分类【{}】",member.getType(),member.getCustomType(),joinConditionMember.getType());
                    return false;
                }

                if(!CommonUtils.judgementExpression(member.getSex(),joinConditionMember.getSex())){
                    log.info("交易顾客性别【{}】不符合折扣可参于的顾客性别【{}】",member.getSex(),joinConditionMember.getSex());
                    return false;
                }
                if(!CommonUtils.judgementExpression(member.getAge(),joinConditionMember.getAge())){
                    log.info("交易顾客年龄【{}】不符合折扣可参于的顾客年龄【{}】",member.getAge(),joinConditionMember.getAge());
                    return false;
                }
                if(!CommonUtils.judgementExpression(member.getBirthday(),joinConditionMember.getBirthday())){
                    log.info("交易顾客生日【{}】不符合折扣可参于的顾客生日【{}】",member.getBirthday(),joinConditionMember.getBirthday());
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 根据基础条件进行折扣数据过滤
     * @param companyNo     公司编号
     * @param timeZone      时区
     * @param businessTime  交易时间
     * @param discount      折扣数据
     * @return 通过基础条件验证返回true 否则返回false
     */
    private static boolean filterDiscountByBase(String companyNo, String timeZone, Date businessTime, Discount discount){
        //判断公司
        if(!StringUtils.defaultString(companyNo).equals(discount.getCompanyNo())){
            log.info("非当前公司[{}]折扣[{}]",discount.getCompanyNo(),discount.getUniqueNo());
            return false;
        }
        //判断日期区间
        if(!(discount.getStartDate().getTime()<=businessTime.getTime()&&businessTime.getTime()<=discount.getEndDate().getTime())){
            log.info("当前日期非折扣【{}】日期区间内",discount.getUniqueNo());
            return false;
        }
        //判断星期区间
        if(discount.getWeekList()!=null){
            WeekTypeEnum weekTypeEnum = DateUtils.getWeekTypeEnum(timeZone,businessTime);
            if(!ArrayUtils.contains(discount.getWeekList().toArray(),weekTypeEnum.name())){
                log.info("当前星期非折扣【{}】星期区间内",discount.getUniqueNo());
                return false;
            }
        }
        //判断时间区间
        if(discount.getDiscountTimeList()!=null&&!discount.getDiscountTimeList().isEmpty()){
            boolean valid = false;
            for (DiscountTime discountTime : discount.getDiscountTimeList()) {
                int startTime = Integer.parseInt(discountTime.getStartTime().replace(":",""));
                int endTime = Integer.parseInt(discountTime.getEndTime().replace(":",""));
                int currentTime = Integer.parseInt(DateUtils.format(businessTime, DateFormatTypeEnum.TIME).replace(":",""));
                if(startTime<=currentTime&&currentTime<=endTime){
                    valid = true;
                }else{
                    valid = false;
                    break;
                }
            }
            if(!valid){
                log.info("当前时间非折扣【{}】时间区间内",discount.getUniqueNo());
                return false;
            }
        }
        return true;
    }

    /**
     * 根据商品进行折扣数据过滤
     * @param discount      折扣数据
     * @return 通过商品条件验证返回true 否则返回false
     */
    private static boolean filterDiscountByGoods(Discount discount){
        DiscountJoinCondition discountJoinCondition = discount.getDiscountJoinCondition();
        if(discountJoinCondition.getHaveCondition()){
            JoinCondition joinCondition = discountJoinCondition.getCondition();
            JoinConditionGoods conditionGoods = joinCondition.getConditionGoods();
            if(conditionGoods!=null){
                List<Goods> goodsList = discount.getCurrentTicket().getGoodsList();
                Iterator<Goods> iterator = goodsList.iterator();
                while (iterator.hasNext()) {
                    Goods goods = iterator.next();
                    if(!CommonUtils.judgementExpression(goods.getGroupNo(),conditionGoods.getGroupNo())
                            &&!CommonUtils.judgementExpression(goods.getCustomGroupNo(),conditionGoods.getGroupNo())){
                        log.debug("交易商品不符合折扣可参于的商品分类");
                        iterator.remove();
                        continue;
                    }
                    if(!CommonUtils.judgementExpression(goods.getBarcode(),conditionGoods.getBarcode())){
                        log.debug("交易商品不符合折扣可参于的商品条码");
                        iterator.remove();
                        continue;
                    }
                    if(!CommonUtils.judgementExpression(goods.getPrice(),conditionGoods.getMinPrice())){
                        log.debug("交易商品不符合折扣可参于的商品最小金额");
                        iterator.remove();
                        continue;
                    }
                    if(!CommonUtils.judgementExpression(goods.getPrice(),conditionGoods.getMaxPrice())){
                        log.debug("交易商品不符合折扣可参于的商品最大金额");
                        iterator.remove();
                        continue;
                    }
                }
                if(discount.getCurrentTicket().getGoodsList().size()<=0){
                    log.info("交易中没有商品符合折扣商品条件");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 根据交易进行折扣数据过滤
     * @param discount      折扣数据
     * @return 通过交易条件验证返回true 否则返回false
     */
    private static boolean filterDiscountByTicket(Discount discount){
        DiscountJoinCondition discountJoinCondition = discount.getDiscountJoinCondition();
        if(discountJoinCondition.getHaveCondition()){
            JoinCondition joinCondition = discountJoinCondition.getCondition();
            JoinConditionTicket conditionTicket = joinCondition.getConditionTicket();
            if(conditionTicket!=null){
                CurrentTicket currentTicket = discount.getCurrentTicket();
                AssociatedTicket associatedTicket = discount.getAssociatedTicket();
                JoinConditionTicketForCurrent conditionTicketForCurrent = conditionTicket.getCurrentTicket();
                JoinConditionTicketForAssociated conditionTicketForAssociated = conditionTicket.getAssociatedTicket();

                if(!BusinessUtils.filterDiscountByAssociatedTicket(associatedTicket,conditionTicketForAssociated)){
                    return false;
                }
                if(!BusinessUtils.filterDiscountByCurrentTicket(currentTicket,conditionTicketForCurrent)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 根据关联交易进行折扣数据过滤
     * @param associatedTicket              关联交易
     * @param conditionTicketForAssociated  关联交易条件
     * @return
     */
    private static boolean filterDiscountByAssociatedTicket(AssociatedTicket associatedTicket,JoinConditionTicketForAssociated conditionTicketForAssociated){
        if(conditionTicketForAssociated!=null){
            if(!CommonUtils.judgementExpression(associatedTicket.getCompanyNo(),conditionTicketForAssociated.getCompanyNo())){
                log.info("关联交易公司【{}】不符合折扣可参于的关联交易公司【{}】",associatedTicket.getCompanyNo(),conditionTicketForAssociated.getCompanyNo());
                return false;
            }
            if(!CommonUtils.judgementExpression(associatedTicket.getBusinessTime(),conditionTicketForAssociated.getTicketStartTime())){
                log.info("关联交易时间【{}】不符合折扣可参于的关联交易开始时间【{}】",DateUtils.format(associatedTicket.getBusinessTime(),DateFormatTypeEnum.DATE_TIME_UTC),conditionTicketForAssociated.getTicketStartTime());
                return false;
            }
            if(!CommonUtils.judgementExpression(associatedTicket.getBusinessTime(),conditionTicketForAssociated.getTicketEndTime())){
                log.info("关联交易时间【{}】不符合折扣可参于的关联交易结束时间【{}】",DateUtils.format(associatedTicket.getBusinessTime(),DateFormatTypeEnum.DATE_TIME_UTC),conditionTicketForAssociated.getTicketStartTime());
                return false;
            }
            if(!CommonUtils.judgementExpression(associatedTicket.getTicketTotalAmount(),conditionTicketForAssociated.getTicketTotalAmount())){
                log.info("关联交易总金额【{}】不符合折扣可参于的关联交易总金额【{}】",associatedTicket.getTicketTotalAmount(),conditionTicketForAssociated.getTicketTotalAmount());
                return false;
            }
        }
        return true;
    }

    /**
     * 根据当前交易进行折扣数据过滤
     * @param currentTicket                 当前交易
     * @param conditionTicketForCurrent     当前交易条件
     * @return
     */
    private static boolean filterDiscountByCurrentTicket(CurrentTicket currentTicket,JoinConditionTicketForCurrent conditionTicketForCurrent){
        if(conditionTicketForCurrent!=null){
            if(conditionTicketForCurrent.getIndex()!=0){
                String ticketCountKey = conditionTicketForCurrent.getType()+"-"+conditionTicketForCurrent.getJoinType();
                boolean join = false;
                int ticketCount = 0;
                int index = conditionTicketForCurrent.getIndex().intValue();
                if(currentTicket.getTicketCountMap()!=null&&currentTicket.getTicketCountMap().containsKey(ticketCountKey)){
                    ticketCount = currentTicket.getTicketCountMap().get(ticketCountKey).intValue();
                    if(conditionTicketForCurrent.getIteration()){
                        join = ticketCount%index==0;
                    }else{
                        join = ticketCount==index;
                    }
                }
                if(!join){
                    log.info("当前交易笔数【{}】不符合折扣可参于的当前交易笔数",ticketCount,index);
                    return false;
                }
            }
            if(!CommonUtils.judgementExpression(currentTicket.getFinalAmount(),conditionTicketForCurrent.getTicketTotalAmount())){
                log.info("当前交易总金额【{}】不符合折扣可参于的当前交易总金额【{}】",currentTicket.getFinalAmount(),conditionTicketForCurrent.getTicketTotalAmount());
                return false;
            }
        }
        return true;
    }

    /**
     * 计算折扣结果
     * @param discount          折扣数据
     * @return 有目标商品且计算过折扣则返回true 否则返回false
     */
    private static boolean calcDiscountResult(Discount discount){
        boolean joinCalc = false;
        CurrentTicket currentTicket = discount.getCurrentTicket();
        DiscountCalcCondition discountCalcCondition = discount.getDiscountCalcCondition();
        List<CalcConditionGoods> conditionGoodsList = discountCalcCondition.getCondition().getConditionGoods();

        Map<String,Double> conditionGoodsInfoMap = BusinessUtils.getTicketJoinDiscountCalcGoodsInfo(currentTicket,conditionGoodsList.get(0).getCalcGoods());

        for (CalcConditionGoods conditionGoods : conditionGoodsList) {
            CalcConditionGoodsCalcGoods calcConditionGoodsCalcGoods = conditionGoods.getCalcGoods();
            CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods = conditionGoods.getTargetGoods();

            List<Goods> targetGoodsList = GoodsUtils.getDiscTargetGoodsByTicket(currentTicket,discount,calcConditionGoodsTargetGoods);

            int joinDiscCount = BusinessUtils.getTicketJoinDiscountCount(conditionGoodsInfoMap,calcConditionGoodsCalcGoods);
            if(joinDiscCount>0){
                if(BusinessUtils.calcTicketJoinDiscountValue(joinDiscCount,currentTicket,targetGoodsList,discount,calcConditionGoodsTargetGoods)){
                    joinCalc = true;
                }
            }
        }
        return joinCalc;
    }

    /**
     * 获取计算商品表达式的交易中可参与交易计算的商品信息<br>
     * 目前只有根据数量和金额进行交易次数判别,所以只需要取到可以参与计算商品的这两个统计信息
     * @param currentTicket                 当前交易
     * @param calcConditionGoodsCalcGoods   计算商品表达式对象
     * @return  返回计算商品表达式的交易中可参与交易计算的商品信息
     */
    private static Map<String,Double> getTicketJoinDiscountCalcGoodsInfo(CurrentTicket currentTicket, CalcConditionGoodsCalcGoods calcConditionGoodsCalcGoods){
        Map<String,Double> conditionGoodsInfoMap = new LinkedHashMap<>();

        List<Goods> tempGoodsList = new ArrayList<>();
        tempGoodsList.addAll(CommonUtils.deepCopy(currentTicket.getGoodsList()));

        double joinGoodsQuantity = 0;
        double joinGoodsAmount = 0;
        for (CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition : calcConditionGoodsCalcGoods.getCondition()) {
            for (Goods goods : tempGoodsList) {
                if (!CommonUtils.judgementExpression(goods.getGroupNo(), calcConditionGoodsCalcGoodsCondition.getGroupNo())
                        &&!CommonUtils.judgementExpression(goods.getCustomGroupNo(), calcConditionGoodsCalcGoodsCondition.getGroupNo())) {
                    continue;
                }
                if (!CommonUtils.judgementExpression(goods.getBarcode(), calcConditionGoodsCalcGoodsCondition.getBarcode())) {
                    continue;
                }
                if (!CommonUtils.judgementExpression(goods.getPrice(), calcConditionGoodsCalcGoodsCondition.getPrice())) {
                    continue;
                }
                joinGoodsQuantity += goods.getCaclQuantity();
                joinGoodsAmount += goods.getFinalAmount();
            }
        }
        conditionGoodsInfoMap.put(DiscountCalcTypeEnum.AMOUNT.name(),joinGoodsAmount);
        conditionGoodsInfoMap.put(DiscountCalcTypeEnum.QUANTITY.name(),joinGoodsQuantity);

        return conditionGoodsInfoMap;
    }

    /**
     * 获取交易可参与折扣次数
     * @param conditionGoodsInfoMap         计算商品表达式的交易中可参与交易计算的商品信息
     * @param calcConditionGoodsCalcGoods   计算商品表达式对象
     * @return
     */
    private static int getTicketJoinDiscountCount(Map<String,Double> conditionGoodsInfoMap, CalcConditionGoodsCalcGoods calcConditionGoodsCalcGoods){
        boolean iteration = calcConditionGoodsCalcGoods.getIteration();
        String expression = calcConditionGoodsCalcGoods.getExpression();

        int joinDiscCount = 0;
        if(RelationshipKeyEnum.OR.name().equals(expression)){
            joinDiscCount = 0;
        }else if(RelationshipKeyEnum.AND.name().equals(expression)){
            joinDiscCount = Integer.MAX_VALUE;
        }

        String calcType = null;
        Map<String,Map<String,Number>> conditionInfoMap = new LinkedHashMap<>();
        for (CalcConditionGoodsCalcGoodsCondition calcConditionGoodsCalcGoodsCondition : calcConditionGoodsCalcGoods.getCondition()) {
            int amountJoinDiscCount = 0;
            int quantityJoinDiscCount = 0;
            Map<String,Number> infoMap = new LinkedHashMap<>();
            String goodsConditionNo = calcConditionGoodsCalcGoodsCondition.getGoodsConditionNo();
            double joinGoodsAmount = conditionGoodsInfoMap.get(DiscountCalcTypeEnum.AMOUNT.name());
            double joinGoodsQuantity = conditionGoodsInfoMap.get(DiscountCalcTypeEnum.QUANTITY.name());

            if(calcConditionGoodsCalcGoodsCondition.getAmount()!=null&&!calcConditionGoodsCalcGoodsCondition.getAmount().isEmpty()){
                double conditionAmount = calcConditionGoodsCalcGoodsCondition.getAmount().get(ExpressionKeyEnum.EQ.name());
                if(joinGoodsAmount>=conditionAmount){
                    if(iteration){
                        amountJoinDiscCount = ((Double)NumberUtils.div(joinGoodsAmount,conditionAmount)).intValue();
                    }else{
                        amountJoinDiscCount = 1;
                    }
                }
                calcType = DiscountCalcTypeEnum.AMOUNT.name();
                infoMap.put("conditionValue",conditionAmount);
            }else if(calcConditionGoodsCalcGoodsCondition.getQuantity()!=null&&!calcConditionGoodsCalcGoodsCondition.getQuantity().isEmpty()){
                double conditionQuantity = calcConditionGoodsCalcGoodsCondition.getQuantity().get(ExpressionKeyEnum.EQ.name());
                if(joinGoodsQuantity>=conditionQuantity){
                    if(iteration){
                        quantityJoinDiscCount = ((Double)NumberUtils.div(joinGoodsQuantity,conditionQuantity)).intValue();
                    }else{
                        quantityJoinDiscCount = 1;
                    }
                }
                calcType = DiscountCalcTypeEnum.QUANTITY.name();
                infoMap.put("conditionValue",conditionQuantity);
            }

            if(RelationshipKeyEnum.OR.name().equals(expression)){
                if(DiscountCalcTypeEnum.AMOUNT.name().equals(calcType)){
                    joinDiscCount += amountJoinDiscCount;
                }else if(DiscountCalcTypeEnum.QUANTITY.name().equals(calcType)){
                    joinDiscCount += quantityJoinDiscCount;
                }
                infoMap.put("joinDiscCount",joinDiscCount);
            }else if(RelationshipKeyEnum.AND.name().equals(expression)){
                joinDiscCount = Math.min(joinDiscCount,Math.max(amountJoinDiscCount,quantityJoinDiscCount));
            }
            conditionInfoMap.put(goodsConditionNo,infoMap);
        }

        if(joinDiscCount>0){
            Iterator<Map.Entry<String,Map<String,Number>>> iterator = conditionInfoMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String,Map<String,Number>> item = iterator.next();
                Map<String,Number> infoMap = item.getValue();
                double conditionValue = infoMap.get("conditionValue").doubleValue();
                int detailJoinDiscount = 0;
                if(RelationshipKeyEnum.OR.name().equals(expression)){
                    //根据每个条件参与的次数进行相应额度扣减
                    detailJoinDiscount = infoMap.get("joinDiscCount").intValue();
                }else if(RelationshipKeyEnum.AND.name().equals(expression)){
                    //因为是所有条件都必须满足,所以每个条件参与的次数都一样。根据次数进行相应额度扣减
                    detailJoinDiscount = joinDiscCount;
                }
                if(DiscountCalcTypeEnum.AMOUNT.name().equals(calcType)){
                    double joinGoodsAmount = conditionGoodsInfoMap.get(DiscountCalcTypeEnum.AMOUNT.name());
                    joinGoodsAmount = NumberUtils.sub(joinGoodsAmount,NumberUtils.mul(detailJoinDiscount,conditionValue));
                    conditionGoodsInfoMap.put(DiscountCalcTypeEnum.AMOUNT.name(),joinGoodsAmount);
                }else if(DiscountCalcTypeEnum.QUANTITY.name().equals(calcType)){
                    double joinGoodsQuantity = conditionGoodsInfoMap.get(DiscountCalcTypeEnum.QUANTITY.name());
                    joinGoodsQuantity = NumberUtils.sub(joinGoodsQuantity,NumberUtils.mul(detailJoinDiscount,conditionValue));
                    conditionGoodsInfoMap.put(DiscountCalcTypeEnum.QUANTITY.name(),joinGoodsQuantity);
                }
            }
        }
        return joinDiscCount;
    }

    /**
     * 计算交易参与折扣值
     * @param joinDiscCount                 可参与折扣次数
     * @param currentTicket                 当前交易
     * @param targetGoodsList               目标商品集
     * @param discount                      折扣数据
     * @param calcConditionGoodsTargetGoods 目标商品表达式对象
     * @return 有目标商品且计算过折扣则返回true 否则返回false
     */
    private static boolean calcTicketJoinDiscountValue(int joinDiscCount, CurrentTicket currentTicket, List<Goods> targetGoodsList, Discount discount, CalcConditionGoodsTargetGoods calcConditionGoodsTargetGoods){
        boolean joinCalc = false;
        //合计目标商品总金额
        double totalJoinCalGoodsAmount = 0D;
        for (Goods goods : targetGoodsList) {
            totalJoinCalGoodsAmount += goods.getFinalAmount();
        }

        if(targetGoodsList!=null&&!targetGoodsList.isEmpty()){
            //商品排序
            if(CalcGoodsSortTypeEnum.DESC.name().equals(calcConditionGoodsTargetGoods.getSortType())){
                GoodsUtils.sortGoodsByPrice(targetGoodsList,true);
            }else if(CalcGoodsSortTypeEnum.ASC.name().equals(calcConditionGoodsTargetGoods.getSortType())){
                GoodsUtils.sortGoodsByPrice(targetGoodsList,false);
            }

            //获取计算数据
            double discValue = calcConditionGoodsTargetGoods.getDiscValue();
            int discGoodsCount = calcConditionGoodsTargetGoods.getDiscGoodsCount();
            String calcResultType = discount.getDiscountCalcCondition().getCalcResultType();

            //获取该折扣最大可折扣值
            double singleDiscountMaxValue = BusinessUtils.getSingleDiscountMaxValue(discount);

            if(discGoodsCount<0){
                joinCalc = BusinessUtils.calcTicketJoinDiscountValueByAll(discGoodsCount,joinDiscCount,discValue,calcResultType,singleDiscountMaxValue,currentTicket,discount,targetGoodsList);
            }else if(discGoodsCount==0){
                joinCalc = BusinessUtils.calcTicketJoinDiscountValueByApportion(discGoodsCount,joinDiscCount,discValue,calcResultType,singleDiscountMaxValue,totalJoinCalGoodsAmount,currentTicket,discount,targetGoodsList);
            }else if(discGoodsCount>0){
                joinCalc = BusinessUtils.calcTicketJoinDiscountValueByAppointCount(discGoodsCount,joinDiscCount,discValue,calcResultType,singleDiscountMaxValue,currentTicket,discount,targetGoodsList);
            }
        }
        return joinCalc;
    }

    /**
     * 计算交易参与折扣值(目标商品都参与折扣)
     * @param discGoodsCount                折扣设定的一共几件商品发生折扣
     * @param joinDiscCount                 可参与折扣次数
     * @param discValue                     折扣值
     * @param calcResultType                计算结果类型
     * @param singleDiscountMaxValue        单个折扣最大可折扣金额
     * @param currentTicket                 当前交易
     * @param discount                      折扣数据
     * @param targetGoodsList               目标商品集
     * @return 有目标商品且计算过折扣则返回true 否则返回false
     */
    private static boolean calcTicketJoinDiscountValueByAll(int discGoodsCount,int joinDiscCount, double discValue, String calcResultType, double singleDiscountMaxValue, CurrentTicket currentTicket, Discount discount, List<Goods> targetGoodsList){
        boolean joinCalc = false;
        if(discGoodsCount<0&&!targetGoodsList.isEmpty()){
            double sumDiscountValue = 0;
            int rounding = currentTicket.getRounding();
            double singleDiscSumAmount = BusinessUtils.getTicketDiscountValueByDiscUniqueNo(rounding,discount.getUniqueNo(),currentTicket);
            for (Goods goods : targetGoodsList) {
                int goodsDiscCount = 1;
                int quantity = goods.getCaclQuantity();
                int goodsDiscCalcCount;
                if(joinDiscCount>=quantity){
                    goodsDiscCalcCount = quantity;
                }else{
                    goodsDiscCalcCount = joinDiscCount;
                }

                GoodsDiscountResult goodsDiscountResult = null;
                if(DiscountCalcResultTypeEnum.QUANTITY.name().equals(calcResultType)){
                    double goodsDiscValue = goods.getFinalAmount();
                    double goodsDiscAmount = goodsDiscValue;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                }else if(DiscountCalcResultTypeEnum.AMOUNT.name().equals(calcResultType)){
                    double goodsDiscValue = Math.min(goods.getFinalAmount(),NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(goodsDiscCalcCount,discValue)));
                    double goodsDiscAmount = goodsDiscValue;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                }else if(DiscountCalcResultTypeEnum.RATE.name().equals(calcResultType)){
                    double goodsDiscValue = Math.min(goods.getFinalAmount(),NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(goodsDiscCalcCount,NumberUtils.mul(goods.getFinalAmount(),discValue))));
                    double goodsDiscAmount = goodsDiscValue;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                }else if(DiscountCalcResultTypeEnum.POINT.name().equals(calcResultType)
                        ||DiscountCalcResultTypeEnum.STAMP.name().equals(calcResultType)
                        ||DiscountCalcResultTypeEnum.INTEGRAL.name().equals(calcResultType)){
                    double goodsDiscValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(joinDiscCount,discValue));
                    double goodsDiscAmount = 0;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                }
                if(goodsDiscountResult!=null){
                    if(singleDiscSumAmount>=singleDiscountMaxValue){
                        break;
                    }
                    sumDiscountValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.add(NumberUtils.add(sumDiscountValue,singleDiscSumAmount),goodsDiscountResult.getDiscValue()));
                    if(sumDiscountValue>singleDiscountMaxValue){
                        double goodsDiscValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.sub(singleDiscountMaxValue,singleDiscSumAmount));
                        goodsDiscountResult.setDiscValue(goodsDiscValue);
                        if(goodsDiscountResult.getDiscAmount()!=0){
                            goodsDiscountResult.setDiscAmount(goodsDiscValue);
                        }
                    }
                    CurrentTicketUtils.getGoodsByLineNo(goods.getLineNo(),currentTicket).getDiscResultList().add(goodsDiscountResult);
                    joinCalc = true;
                }
            }

            if(joinCalc){
                //重新定义当前交易金额信息
                currentTicket.resetTicketAmountInfo();
            }
        }
        return joinCalc;
    }

    /**
     * 计算交易参与折扣值(折扣分摊到目标商品)
     * @param discGoodsCount                折扣设定的一共几件商品发生折扣
     * @param joinDiscCount                 可参与折扣次数
     * @param discValue                     折扣值
     * @param calcResultType                计算结果类型
     * @param singleDiscountMaxValue        单个折扣最大可折扣金额
     * @param totalJoinCalGoodsAmount       目标商品中金额
     * @param currentTicket                 当前交易
     * @param discount                      折扣数据
     * @param targetGoodsList               目标商品集
     * @return 有目标商品且计算过折扣则返回true 否则返回false
     */
    private static boolean calcTicketJoinDiscountValueByApportion(int discGoodsCount,int joinDiscCount, double discValue, String calcResultType, double singleDiscountMaxValue, double totalJoinCalGoodsAmount, CurrentTicket currentTicket, Discount discount, List<Goods> targetGoodsList){
        boolean joinCalc = false;
        if(discGoodsCount==0&&!targetGoodsList.isEmpty()){
            int rounding = currentTicket.getRounding();

            //获取总折扣值
            double totalDiscValue = 0;
            if(DiscountCalcResultTypeEnum.QUANTITY.name().equals(calcResultType)
                    ||DiscountCalcResultTypeEnum.RATE.name().equals(calcResultType)){
                totalDiscValue = NumberUtils.mul(joinDiscCount,NumberUtils.mul(discValue,targetGoodsList.get(0).getFinalPrice(rounding)));
            }else if(DiscountCalcResultTypeEnum.AMOUNT.name().equals(calcResultType)
                    ||DiscountCalcResultTypeEnum.POINT.name().equals(calcResultType)
                    ||DiscountCalcResultTypeEnum.STAMP.name().equals(calcResultType)
                    ||DiscountCalcResultTypeEnum.INTEGRAL.name().equals(calcResultType)){
                totalDiscValue = NumberUtils.mul(joinDiscCount,discValue);
            }
            double singDiscSumAmount = BusinessUtils.getTicketDiscountValueByDiscUniqueNo(rounding,discount.getUniqueNo(),currentTicket);
            if(NumberUtils.add(totalDiscValue,singDiscSumAmount)>singleDiscountMaxValue){
                if(singDiscSumAmount<singleDiscountMaxValue){
                    totalDiscValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.sub(singleDiscountMaxValue,singDiscSumAmount));
                }else{
                    return joinCalc;
                }
            }

            //计算折扣
            double sumDiscValue = 0D;
            for (Goods goods : targetGoodsList) {
                int goodsDiscCount = 1;
                double finalAmount = goods.getFinalAmount();
                GoodsDiscountResult goodsDiscountResult = null;
                double amountRate = NumberUtils.div(finalAmount,totalJoinCalGoodsAmount);
                if(DiscountCalcResultTypeEnum.AMOUNT.name().equals(calcResultType)
                        ||DiscountCalcResultTypeEnum.QUANTITY.name().equals(calcResultType)
                        ||DiscountCalcResultTypeEnum.RATE.name().equals(calcResultType)){
                    double singleDiscGoodsSumAmount = BusinessUtils.getGoodsDiscountAmountByDiscUniqueNo(rounding,discount.getUniqueNo(),goods);
                    double goodsAmount = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.add(goods.getFinalAmount(),singleDiscGoodsSumAmount));
                    double goodsDiscValue = Math.min(goodsAmount,NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(amountRate,totalDiscValue)));
                    double goodsDiscAmount = goodsDiscValue;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                    sumDiscValue += goodsDiscValue;
                }else if(DiscountCalcResultTypeEnum.POINT.name().equals(calcResultType)
                        ||DiscountCalcResultTypeEnum.STAMP.name().equals(calcResultType)
                        ||DiscountCalcResultTypeEnum.INTEGRAL.name().equals(calcResultType)){
                    double goodsDiscValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(amountRate,totalDiscValue));
                    double goodsDiscAmount = 0D;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                    sumDiscValue += goodsDiscountResult.getDiscValue();
                }
                if(goodsDiscountResult!=null){
                    CurrentTicketUtils.getGoodsByLineNo(goods.getLineNo(),currentTicket).getDiscResultList().add(goodsDiscountResult);
                    joinCalc = true;
                }
            }

            if(joinCalc){
                //重新定义当前交易金额信息
                currentTicket.resetTicketAmountInfo();

                //补入差额的折扣值
                if(sumDiscValue<totalDiscValue){
                    int goodsDiscCount = 0;
                    for(Goods goods:targetGoodsList){
                        GoodsDiscountResult goodsDiscountResult = null;
                        Goods ticketGoods = CurrentTicketUtils.getGoodsByLineNo(goods.getLineNo(),currentTicket);
                        if(DiscountCalcResultTypeEnum.AMOUNT.name().equals(calcResultType)
                                ||DiscountCalcResultTypeEnum.QUANTITY.name().equals(calcResultType)
                                ||DiscountCalcResultTypeEnum.RATE.name().equals(calcResultType)){
                            double singleDiscGoodsSumAmount = BusinessUtils.getGoodsDiscountAmountByDiscUniqueNo(rounding,discount.getUniqueNo(),goods);
                            double goodsAmount = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.add(ticketGoods.getFinalAmount(),singleDiscGoodsSumAmount));
                            double goodsDiscValue = Math.min(goodsAmount,NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.sub(totalDiscValue,sumDiscValue)));
                            double goodsDiscAmount = goodsDiscValue;
                            goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                        }else if(DiscountCalcResultTypeEnum.POINT.name().equals(calcResultType)
                                ||DiscountCalcResultTypeEnum.STAMP.name().equals(calcResultType)
                                ||DiscountCalcResultTypeEnum.INTEGRAL.name().equals(calcResultType)){
                            double goodsDiscValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.sub(totalDiscValue,sumDiscValue));
                            double goodsDiscAmount = 0;
                            goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                        }
                        if(goodsDiscountResult!=null){
                            ticketGoods.getDiscResultList().add(goodsDiscountResult);
                            break;
                        }
                    }
                }
            }
        }
        return joinCalc;
    }

    /**
     * 计算交易参与折扣值(指定折扣几件目标商品)
     * @param discGoodsCount                折扣设定的一共几件商品发生折扣
     * @param joinDiscCount                 可参与折扣次数
     * @param discValue                     折扣值
     * @param calcResultType                计算结果类型
     * @param singleDiscountMaxValue        单个折扣最大可折扣金额
     * @param currentTicket                 当前交易
     * @param discount                      折扣数据
     * @param targetGoodsList               目标商品集
     * @return 有目标商品且计算过折扣则返回true 否则返回false
     */
    private static boolean calcTicketJoinDiscountValueByAppointCount(int discGoodsCount,int joinDiscCount, double discValue, String calcResultType, double singleDiscountMaxValue, CurrentTicket currentTicket, Discount discount, List<Goods> targetGoodsList){
        boolean joinCalc = false;
        if(discGoodsCount>0&&!targetGoodsList.isEmpty()){
            double sumDiscountValue = 0;
            int rounding = currentTicket.getRounding();
            double singleDiscSumAmount = BusinessUtils.getTicketDiscountValueByDiscUniqueNo(rounding,discount.getUniqueNo(),currentTicket);

            joinDiscCount = joinDiscCount*discGoodsCount;

            for (Goods goods : targetGoodsList) {
                int goodsDiscCount = 1;
                int quantity = goods.getCaclQuantity();
                int goodsDiscCaclCount;
                if(joinDiscCount>=quantity){
                    joinDiscCount = joinDiscCount-quantity;
                    goodsDiscCaclCount = quantity;
                }else{
                    joinDiscCount = 0;
                    goodsDiscCaclCount = joinDiscCount;
                }
                GoodsDiscountResult goodsDiscountResult = null;
                if(DiscountCalcResultTypeEnum.QUANTITY.name().equals(calcResultType)){
                    double goodsDiscValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(goods.getFinalPrice(rounding),goodsDiscCaclCount));
                    double goodsDiscAmount = goodsDiscValue;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                }else if(DiscountCalcResultTypeEnum.AMOUNT.name().equals(calcResultType)){
                    double goodsDiscValue = Math.min(goods.getFinalAmount(),NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(goodsDiscCaclCount,discValue)));
                    double goodsDiscAmount = goodsDiscValue;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                }else if(DiscountCalcResultTypeEnum.RATE.name().equals(calcResultType)){
                    double goodsDiscValue = Math.min(goods.getFinalAmount(),NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(goodsDiscCaclCount,NumberUtils.mul(goods.getFinalAmount(),discValue))));
                    double goodsDiscAmount = goodsDiscValue;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                }else if(DiscountCalcResultTypeEnum.POINT.name().equals(calcResultType)
                        ||DiscountCalcResultTypeEnum.STAMP.name().equals(calcResultType)
                        ||DiscountCalcResultTypeEnum.INTEGRAL.name().equals(calcResultType)){
                    double goodsDiscValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.mul(goodsDiscCaclCount,discValue));
                    double goodsDiscAmount = 0;
                    goodsDiscountResult = new GoodsDiscountResult(discount,goodsDiscValue,goodsDiscAmount,goodsDiscCount);
                }
                if(goodsDiscountResult!=null){
                    if(singleDiscSumAmount>=singleDiscountMaxValue){
                        break;
                    }
                    sumDiscountValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.add(NumberUtils.add(sumDiscountValue,singleDiscSumAmount),goodsDiscountResult.getDiscValue()));
                    if(sumDiscountValue>singleDiscountMaxValue){
                        double goodsDiscValue = NumberUtils.FormatDouble.formatValue(rounding,NumberUtils.sub(singleDiscountMaxValue,singleDiscSumAmount));
                        goodsDiscountResult.setDiscValue(goodsDiscValue);
                        if(goodsDiscountResult.getDiscAmount()!=0){
                            goodsDiscountResult.setDiscAmount(goodsDiscValue);
                        }
                    }
                    CurrentTicketUtils.getGoodsByLineNo(goods.getLineNo(),currentTicket).getDiscResultList().add(goodsDiscountResult);
                    joinCalc = true;
                }
                if(joinDiscCount==0){
                    break;
                }
            }

            if(joinCalc){
                //重新定义当前交易金额信息
                currentTicket.resetTicketAmountInfo();
            }
        }
        return joinCalc;
    }

    /**
     * 整合当前交易信息
     * @param currentTicket         当前交易
     * @param tempCurrentTicket     折扣对象中临时当前交易
     */
    private static void reorganizeCurrentTicketInfo(CurrentTicket currentTicket,CurrentTicket tempCurrentTicket){
        for(Goods tempGoods:tempCurrentTicket.getGoodsList()){
            Goods goods = CurrentTicketUtils.getGoodsByLineNo(tempGoods.getLineNo(),currentTicket);
            goods.getDiscResultList().clear();

            List<GoodsDiscountResult> discResultList = tempGoods.getDiscResultList();
            if(discResultList!=null&&!discResultList.isEmpty()){
                goods.getDiscResultList().addAll(discResultList);
            }
        }
        currentTicket.resetTicketAmountInfo();
    }

    /**
     * 过滤目标商品条件表达式
     * @param goodsList     商品对象集
     * @param discount      折扣对象
     * @return 返回为该折扣目标商品的商品条码集合
     */
    private static List<String> filterTargetGoodsDiscountExpression(List<Goods> goodsList,Discount discount){
        List<String> joinGoodsBarcodeList = new ArrayList<>();

        if(goodsList!=null&&discount!=null&&!goodsList.isEmpty()){
            DiscountCalcCondition discountCalcCondition = discount.getDiscountCalcCondition();
            List<CalcConditionGoods> conditionGoodsList = discountCalcCondition.getCondition().getConditionGoods();

            for (Goods goods : goodsList) {
                for (CalcConditionGoods calcConditionGoods : conditionGoodsList) {
                    CalcConditionGoodsTargetGoods targetGoods = calcConditionGoods.getTargetGoods();
                    if(!CommonUtils.judgementExpression(goods.getGroupNo(),targetGoods.getGroupNo())
                            &&!CommonUtils.judgementExpression(goods.getCustomGroupNo(),targetGoods.getGroupNo())){
                        continue;
                    }
                    if(!CommonUtils.judgementExpression(goods.getBarcode(),targetGoods.getBarcode())){
                        continue;
                    }

                    if(!joinGoodsBarcodeList.contains(goods.getBarcode())){
                        joinGoodsBarcodeList.add(goods.getBarcode());
                    }
                }
            }
        }

        return joinGoodsBarcodeList;
    }

    /**
     * 获取商品指定折扣金额
     * @param rounding  保留精度
     * @param uniqueNo  折扣唯一标识
     * @param goods     商品对象
     * @return
     */
    private static double getGoodsDiscountAmountByDiscUniqueNo(int rounding,String uniqueNo,Goods goods){
        double singleDiscSumAmount = 0;
        for(GoodsDiscountResult discountResult:goods.getDiscResultList()){
            if(discountResult.getDiscUniqueNo().equals(uniqueNo)){
                singleDiscSumAmount += discountResult.getDiscAmount();
            }
        }
        return NumberUtils.FormatDouble.formatValue(rounding,singleDiscSumAmount);
    }

    /**
     * 获取交易指定折扣值
     * @param rounding          保留精度
     * @param uniqueNo          折扣唯一标识
     * @param currentTicket     当前交易对象
     * @return
     */
    private static double getTicketDiscountValueByDiscUniqueNo(int rounding, String uniqueNo, CurrentTicket currentTicket){
        double singDiscSumValue = 0;
        for(Goods goods:currentTicket.getGoodsList()){
            for(GoodsDiscountResult discountResult:goods.getDiscResultList()){
                if(discountResult.getDiscUniqueNo().equals(uniqueNo)){
                    singDiscSumValue += discountResult.getDiscAmount();
                }
            }
        }
        return NumberUtils.FormatDouble.formatValue(rounding,singDiscSumValue);
    }

    /**
     * 获取该折扣最大可折扣值
     * @param discount      折扣对象
     * @return
     */
    private static double getSingleDiscountMaxValue(Discount discount){
        double singleDiscountMaxValue = Double.MAX_VALUE;
        CalcConditionValue conditionAmount = discount.getDiscountCalcCondition().getCondition().getConditionValue();
        if(conditionAmount!=null){
            //获取交易最大折扣金额
            double ticketMaxDiscValue = Double.MAX_VALUE;
            if(conditionAmount.getTicketMaxDiscValue()!=null&&!conditionAmount.getTicketMaxDiscValue().isEmpty()){
                ticketMaxDiscValue = conditionAmount.getTicketMaxDiscValue().get(ExpressionKeyEnum.EQ.name());
            }
            //获取会员最大折扣金额
            double memberMaxDiscValue = Double.MAX_VALUE;
            if(conditionAmount.getMemberMaxDiscValue()!=null&&!conditionAmount.getMemberMaxDiscValue().isEmpty()){
                memberMaxDiscValue = conditionAmount.getMemberMaxDiscValue().get(ExpressionKeyEnum.EQ.name());
            }
            memberMaxDiscValue -= discount.getMemberHistoryDiscValue();
            //获取门店最大折扣金额
            double companyMaxDiscValue = Double.MAX_VALUE;
            if(conditionAmount.getCompanyMaxDiscValue()!=null&&!conditionAmount.getCompanyMaxDiscValue().isEmpty()){
                companyMaxDiscValue = conditionAmount.getCompanyMaxDiscValue().get(ExpressionKeyEnum.EQ.name());
            }
            companyMaxDiscValue -= discount.getCompanyHistoryDiscValue();

            singleDiscountMaxValue = Math.min(ticketMaxDiscValue,Math.min(memberMaxDiscValue,companyMaxDiscValue));
        }
        return singleDiscountMaxValue;
    }

    /**
     * 包装商品参与折扣信息结果
     * @param timeZone              时区
     * @param discount              折扣对象
     * @param joinDiscInfo          参与折扣信息
     * @param joinGoodsBarcodeList  参与商品条码集
     */
    private static void packageGoodsJoinDiscInfo(String timeZone,Discount discount,Map<String,List<DiscountMemo>> joinDiscInfo,List<String> joinGoodsBarcodeList){
        if(joinGoodsBarcodeList!=null&&!joinGoodsBarcodeList.isEmpty()){
            joinGoodsBarcodeList.forEach(barcode -> {
                if(joinDiscInfo.containsKey(barcode)){
                    joinDiscInfo.get(barcode).add(DiscountUtils.conventDiscountToDiscountMemo(timeZone,discount));
                }else{
                    List<DiscountMemo> joinDiscountList = new ArrayList<>();
                    joinDiscountList.add(DiscountUtils.conventDiscountToDiscountMemo(timeZone,discount));
                    joinDiscInfo.put(barcode,joinDiscountList);
                }
            });
        }
    }
}
