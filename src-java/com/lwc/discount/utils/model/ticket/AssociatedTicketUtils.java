package com.lwc.discount.utils.model.ticket;

import com.lwc.discount.enums.BusinessMessageEnum;
import com.lwc.discount.enums.DateFormatTypeEnum;
import com.lwc.discount.model.common.BusinessMessage;
import com.lwc.discount.model.ticket.AssociatedTicket;
import com.lwc.discount.utils.DateUtils;
import com.lwc.discount.utils.model.discount.business.DiscountUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 关联交易工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class AssociatedTicketUtils {
    private static Logger log = LoggerFactory.getLogger(AssociatedTicketUtils.class);

    /**
     * 关联交易属性验证
     * <pre>
     * 验证条件:
     *      验证所有顾客属性的条件限制
     * </pre>
     * @param associatedTicket  当前交易对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(AssociatedTicket associatedTicket, BusinessMessage businessMessage){
        if(associatedTicket!=null){
            log.info("关联交易公司: {}",associatedTicket.getCompanyNo());
            log.info("关联交易时间: {}", DateUtils.format(associatedTicket.getBusinessTime(), DateFormatTypeEnum.DATE_TIME));
            log.info("关联交易总金额: {}",associatedTicket.getTicketTotalAmount());
            if(!AssociatedTicketUtils.validParamForCompanyNo(associatedTicket, businessMessage)){
                log.debug("关联交易属性(公司编码)验证未通过");
                return false;
            }
            if(!AssociatedTicketUtils.validParamForBusinessTime(associatedTicket, businessMessage)){
                log.debug("关联交易属性(交易单号)验证未通过");
                return false;
            }
            if(!AssociatedTicketUtils.validParamForTicketTotalAmount(associatedTicket, businessMessage)){
                log.debug("关联交易属性(交易总金额)验证未通过");
                return false;
            }
        }
        return true;
    }

    /**
     * 关联交易属性(公司编码)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 "" 或 " "
     *      长度区间为【1-50】
     * </pre>
     * @param associatedTicket  当前交易对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCompanyNo(AssociatedTicket associatedTicket, BusinessMessage businessMessage){
        if(StringUtils.isBlank(associatedTicket.getCompanyNo())){
            log.info("关联交易属性(公司编码)验证 公司编码数据不可为Bank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1500);
            return false;
        }
        int companyNoLength = associatedTicket.getCompanyNo().length();
        if(companyNoLength>DiscountUtils.COMPANY_NO_MAX_LENGTH){
            log.info("关联交易属性(公司编码)验证 公司编码数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1501);
            return false;
        }
        return true;
    }

    /**
     * 关联交易属性(交易时间)验证
     * <pre>
     * 验证条件:
     *      不得为null
     * </pre>
     * @param associatedTicket  当前交易对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBusinessTime(AssociatedTicket associatedTicket, BusinessMessage businessMessage){
        if(associatedTicket.getBusinessTime()==null){
            log.info("关联交易属性(交易时间)验证 交易时间数据不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1502);
            return false;
        }
        return true;
    }

    /**
     * 关联交易属性(交易总金额)验证
     * <pre>
     * 验证条件:
     *      不得为null
     *      必须大于0
     * </pre>
     * @param associatedTicket  当前交易对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForTicketTotalAmount(AssociatedTicket associatedTicket, BusinessMessage businessMessage){
        if(associatedTicket.getTicketTotalAmount()==null){
            log.info("关联交易属性(交易总金额)验证 交易总金额数据不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1503);
            return false;
        }
        if(associatedTicket.getTicketTotalAmount()<=0){
            log.info("关联交易属性(交易总金额)验证 交易总金额数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1504);
            return false;
        }
        return true;
    }

}
