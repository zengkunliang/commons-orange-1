package com.lwc.discount.model.ticket;

import com.lwc.discount.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 关联交易类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class AssociatedTicket implements Serializable {
    /**
     * 交易公司<br>
     * 公司的唯一标识<br>
     * 不得为null 或 "" 或 " "<br>
     * 长度区间为【1-50】
     */
    private String companyNo;
    /**
     * 交易时间<br>
     * 不得为null
     */
    private Date businessTime;
    /**
     * 交易总金额<br>
     * 不得为null<br>
     * 必须大于0
     */
    private Double ticketTotalAmount;

    /**
     * 获取companyNo属性值
     *
     * @return companyNo属性值
     */
    public String getCompanyNo() {
        return companyNo;
    }

    /**
     * 设置companyNo属性值<br>
     * 可以使用getCompanyNo()获取companyNo的属性值
     *
     * @param companyNo companyNo
     */
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    /**
     * 获取businessTime属性值
     *
     * @return businessTime属性值
     */
    public Date getBusinessTime() {
        if(businessTime==null){
            return null;
        }
        return (Date) businessTime.clone();
    }

    /**
     * 设置businessTime属性值
     * 可以使用getBusinessTime()获取businessTime的属性值
     *
     * @param businessTime businessTime
     */
    public void setBusinessTime(Object businessTime) {
        Date tempBusinessTime;
        if(businessTime instanceof String){
            tempBusinessTime = DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),(String)businessTime);
        }else if(businessTime instanceof Number){
            tempBusinessTime = new Date(((Number) businessTime).longValue());
        }else if(businessTime instanceof Date){
            tempBusinessTime = (Date) ((Date)businessTime).clone();
        }else{
            tempBusinessTime = null;
        }
        this.businessTime = tempBusinessTime;
    }

    /**
     * 获取ticketTotalAmount属性值
     *
     * @return ticketTotalAmount属性值
     */
    public Double getTicketTotalAmount() {
        return ticketTotalAmount;
    }

    /**
     * 设置ticketTotalAmount属性值
     * 可以使用getTicketTotalAmount()获取ticketTotalAmount的属性值
     *
     * @param ticketTotalAmount ticketTotalAmount
     */
    public void setTicketTotalAmount(Double ticketTotalAmount) {
        this.ticketTotalAmount = ticketTotalAmount;
    }
}
