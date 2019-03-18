package com.gorange.discount.engine.model.discount.business;

import com.gorange.discount.engine.model.ticket.AssociatedTicket;
import com.gorange.discount.engine.model.ticket.CurrentTicket;
import com.gorange.discount.engine.utils.CommonUtils;
import com.gorange.discount.engine.utils.DateUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 折扣类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Discount implements Serializable {
    /**
     * 折扣唯一标识<br>
     * 公司下唯一,未进行设置时引擎自动进行设置<br>
     * 不为Blank时长度区间为【1-50】
     */
    private String uniqueNo;
    /**
     * 折扣所属公司<br>
     * 公司的唯一标识<br>
     * 不得为null 或 "" 或 " "<br>
     * 长度区间为【1-50】
     */
    private String companyNo;
    /**
     * 折扣名称<br>
     * 不为Blank时长度区间为【1-50】
     */
    private String name;
    /**
     * 折扣描述<br>
     * 描述折扣应用场景
     */
    private String description;
    /**
     * 折扣分组
     */
    private DiscountGroup discountGroup;
    /**
     * 折扣开始日期<br>
     * 日期不得为null<br>
     * 开始日期必须小于或等于结束日期<br>
     * 将根据该属性值进行折扣生效日期区间过滤
     */
    private Date startDate;
    /**
     * 折扣结束日期<br>
     * 日期不得为null<br>
     * 结束日期不得小于当前日期<br>
     * 将根据该属性值进行折扣生效日期区间过滤
     */
    private Date endDate;
    /**
     * 星期区间<br>
     * 不得为空<br>
     * 星期区间内容之必须符合WeekTypeEnum<br>
     * 单个折扣日期区间有可能存在多个星期区间
     */
    private List<String> weekList;
    /**
     * 折扣日时间区间<br>
     * 单个折扣日有可能存在多个时间区间<br>
     * 当未设置时间区间时,引擎自动分配一组时间区间[00:00:00-23:59:59]
     */
    private List<DiscountTime> discountTimeList;
    /**
     * 折扣参与条件<br>
     * 折扣参与条件会根据不同的场景,公司需求条件各不相同
     */
    private DiscountJoinCondition discountJoinCondition;
    /**
     * 折扣计算条件<br>
     * 折扣计算条件根据不同的场景,公司需求条件各不相同
     */
    private DiscountCalcCondition discountCalcCondition;


    /**
     * 当前交易(处理中自定义属性)
     */
    private CurrentTicket currentTicket;
    /**
     * 关联交易(处理中自定义属性)
     */
    private AssociatedTicket associatedTicket;


    /**
     * 获取uniqueNo属性值
     *
     * @return uniqueNo属性值
     */
    public String getUniqueNo() {
        return uniqueNo;
    }

    /**
     * 设置uniqueNo属性值
     * 可以使用getUniqueNo()获取uniqueNo的属性值
     *
     * @param uniqueNo uniqueNo
     */
    public void setUniqueNo(String uniqueNo) {
        this.uniqueNo = uniqueNo;
    }


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
     * 获取name属性值
     *
     * @return name属性值
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性值
     * 可以使用getName()获取name的属性值
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取description属性值
     *
     * @return description属性值
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置description属性值
     * 可以使用getDescription()获取description的属性值
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取discountGroup属性值
     *
     * @return discountGroup属性值
     */
    public DiscountGroup getDiscountGroup() {
        return discountGroup;
    }

    /**
     * 设置discountGroup属性值<br>
     * 可以使用getDiscountGroup()获取discountGroup的属性值
     *
     * @param discountGroup discountGroup
     */
    public void setDiscountGroup(DiscountGroup discountGroup) {
        this.discountGroup = discountGroup;
    }

    /**
     * 获取startDate属性值
     *
     * @return startDate属性值
     */
    public Date getStartDate() {
        if(startDate==null){
            return null;
        }
        return (Date) startDate.clone();
    }

    /**
     * 设置startDate属性值<br>
     * 可以使用getStartDate()获取startDate的属性值
     *
     * @param startDate startDate
     */
    public void setStartDate(Object startDate) {
        Date tempStartDate;
        if(startDate instanceof String){
            tempStartDate = DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),(String)startDate);
        }else if(startDate instanceof Number){
            tempStartDate = new Date(((Number) startDate).longValue());
        }else if(startDate instanceof Date){
            tempStartDate = (Date)startDate;
        }else{
            tempStartDate = null;
        }
        this.startDate = tempStartDate;
    }

    /**
     * 获取endDate属性值
     *
     * @return endDate属性值
     */
    public Date getEndDate() {
        if(endDate==null){
            return null;
        }
        return (Date) endDate.clone();
    }

    /**
     * 设置endDate属性值<br>
     * 可以使用getEndDate()获取endDate的属性值
     *
     * @param endDate endDate
     */
    public void setEndDate(Object endDate) {
        Date tempEndDate;
        if(endDate instanceof String){
            tempEndDate = DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),(String)endDate);
        }else if(endDate instanceof Number){
            tempEndDate = new Date(((Number) endDate).longValue());
        }else if(endDate instanceof Date){
            tempEndDate = (Date)endDate;
        }else{
            tempEndDate = null;
        }
        this.endDate = tempEndDate;
    }

    /**
     * 获取weekList属性值
     *
     * @return weekList属性值
     */
    public List<String> getWeekList() {
        return weekList;
    }

    /**
     * 设置weekList属性值
     * 可以使用getWeekList()获取weekList的属性值
     *
     * @param weekList weekList
     */
    public void setWeekList(List<String> weekList) {
        this.weekList = weekList;
    }

    /**
     * 获取discountTimeList属性值<br>
     * 当未设置时间区间时,引擎自动分配一组时间区间[00:00:00-23:59:59]
     * @return discountTimeList属性值
     */
    public List<DiscountTime> getDiscountTimeList() {
        if(discountTimeList==null){
            discountTimeList = new ArrayList<>();
            DiscountTime discountTime = new DiscountTime();
            discountTime.setStartTime("00:00:00");
            discountTime.setEndTime("23:59:59");
            discountTimeList.add(discountTime);
        }
        return discountTimeList;
    }

    /**
     * 设置discountTimeList属性值
     * 可以使用getDiscountTimeList()获取discountTimeList的属性值
     *
     * @param discountTimeList discountTimeList
     */
    public void setDiscountTimeList(List<DiscountTime> discountTimeList) {
        this.discountTimeList = discountTimeList;
    }

    /**
     * 获取discountJoinCondition属性值
     *
     * @return discountJoinCondition属性值
     */
    public DiscountJoinCondition getDiscountJoinCondition() {
        return discountJoinCondition;
    }

    /**
     * 设置discountJoinCondition属性值
     * 可以使用getDiscountJoinCondition()获取discountJoinCondition的属性值
     *
     * @param discountJoinCondition discountJoinCondition
     */
    public void setDiscountJoinCondition(DiscountJoinCondition discountJoinCondition) {
        this.discountJoinCondition = discountJoinCondition;
    }

    /**
     * 获取discountCalcCondition属性值
     *
     * @return discountCalcCondition属性值
     */
    public DiscountCalcCondition getDiscountCalcCondition() {
        return discountCalcCondition;
    }

    /**
     * 设置discountCalcCondition属性值
     * 可以使用getDiscountCalcCondition()获取discountCalcCondition的属性值
     *
     * @param discountCalcCondition discountCalcCondition
     */
    public void setDiscountCalcCondition(DiscountCalcCondition discountCalcCondition) {
        this.discountCalcCondition = discountCalcCondition;
    }


    /**
     * 获取currentTicket属性值
     *
     * @return currentTicket属性值
     */
    public CurrentTicket getCurrentTicket() {
        if(currentTicket==null){
            currentTicket = new CurrentTicket();
        }
        return currentTicket;
    }

    /**
     * 设置currentTicket属性值
     * 可以使用getCurrentTicket()获取currentTicket的属性值
     *
     * @param currentTicket currentTicket
     */
    public void setCurrentTicket(CurrentTicket currentTicket) {
        this.currentTicket = CommonUtils.deepCopy(currentTicket);
    }

    /**
     * 获取associatedTicket属性值
     *
     * @return associatedTicket属性值
     */
    public AssociatedTicket getAssociatedTicket() {
        if(associatedTicket==null){
            associatedTicket = new AssociatedTicket();
        }
        return associatedTicket;
    }

    /**
     * 设置associatedTicket属性值
     * 可以使用getAssociatedTicket()获取associatedTicket的属性值
     *
     * @param associatedTicket associatedTicket
     */
    public void setAssociatedTicket(AssociatedTicket associatedTicket) {
        this.associatedTicket = CommonUtils.deepCopy(associatedTicket);
    }
}
