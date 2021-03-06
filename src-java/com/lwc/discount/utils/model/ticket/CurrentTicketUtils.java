package com.lwc.discount.utils.model.ticket;

import com.lwc.discount.enums.BusinessMessageEnum;
import com.lwc.discount.enums.DateFormatTypeEnum;
import com.lwc.discount.model.common.BusinessMessage;
import com.lwc.discount.model.goods.Goods;
import com.lwc.discount.model.ticket.CurrentTicket;
import com.lwc.discount.utils.DateUtils;
import com.lwc.discount.utils.model.discount.business.DiscountUtils;
import com.lwc.discount.utils.model.goods.GoodsUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 当前交易工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CurrentTicketUtils {
    private static Logger log = LoggerFactory.getLogger(CurrentTicketUtils.class);
    /**
     * 交易单号最大长度
     */
    public static final Integer ORDER_NO_MAX_LENGTH      = 50;

    /**
     * 当前交易属性验证
     * <pre>
     * 验证条件:
     *      验证所有顾客属性的条件限制
     * </pre>
     * @param currentTicket     当前交易对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(CurrentTicket currentTicket, BusinessMessage businessMessage){
        currentTicket.resetTicketAmountInfo();
        log.info("当前交易公司: {}",currentTicket.getCompanyNo());
        log.info("当前交易时间: {}", DateUtils.format(currentTicket.getBusinessTime(), DateFormatTypeEnum.DATE_TIME));
        log.info("当前交易时区: {}",currentTicket.getTimeZone());
        log.info("当前交易顾客: {}", currentTicket.getMember().getBarcode());
        log.info("当前交易金额保留精度: {}",currentTicket.getRounding());
        log.info("当前交易商品集数据条数: {}",currentTicket.getGoodsList().size());
        if(!CurrentTicketUtils.validParamForCompanyNo(currentTicket, businessMessage)){
            log.debug("当前交易属性(公司编码)验证未通过");
            return false;
        }
        if(!CurrentTicketUtils.validParamForOrderNo(currentTicket, businessMessage)){
            log.debug("当前交易属性(交易单号)验证未通过");
            return false;
        }
        if(!CurrentTicketUtils.validParamForGoods(currentTicket, businessMessage)){
            log.debug("当前交易属性(商品集)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 当前交易属性(公司编码)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 "" 或 " "
     *      长度区间为【1-50】
     * </pre>
     * @param currentTicket     当前交易对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCompanyNo(CurrentTicket currentTicket, BusinessMessage businessMessage){
        if(StringUtils.isBlank(currentTicket.getCompanyNo())){
            log.info("当前交易属性(公司编码)验证 公司编码数据不可为Bank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1400);
            return false;
        }
        int companyNoLength = currentTicket.getCompanyNo().length();
        if(companyNoLength>DiscountUtils.COMPANY_NO_MAX_LENGTH){
            log.info("当前交易属性(公司编码)验证 公司编码数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1401);
            return false;
        }
        return true;
    }

    /**
     * 当前交易属性(交易单号)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 "" 或 " "
     *      长度区间为【1-50】
     * </pre>
     * @param currentTicket     当前交易对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForOrderNo(CurrentTicket currentTicket, BusinessMessage businessMessage){
        if(StringUtils.isBlank(currentTicket.getOrderNo())){
            log.info("当前交易属性(交易单号)验证 交易单号数据不可为Bank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1402);
            return false;
        }
        int orderNoLength = currentTicket.getOrderNo().length();
        if(orderNoLength>CurrentTicketUtils.ORDER_NO_MAX_LENGTH){
            log.info("当前交易属性(交易单号)验证 交易单号数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1403);
            return false;
        }
        return true;
    }


    /**
     * 当前交易属性(商品集)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 Empty
     * </pre>
     * @param currentTicket     当前交易对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForGoods(CurrentTicket currentTicket, BusinessMessage businessMessage){
        return GoodsUtils.validAllParam(currentTicket.getGoodsList(),businessMessage);
    }

    /**
     * 根据行号从当前交易对象中获取商品对象
     * @param lineNo        商品行号
     * @param currentTicket 当前交易对象
     * @return 返回对应商品行对象 未找到则返回null
     */
    public static Goods getGoodsByLineNo(int lineNo,CurrentTicket currentTicket){
        if(currentTicket!=null&&currentTicket.getGoodsList()!=null&&!currentTicket.getGoodsList().isEmpty()){
            for (Goods goods : currentTicket.getGoodsList()) {
                if(goods.getLineNo().intValue()==lineNo){
                    return goods;
                }
            }
        }
        return null;
    }
}
