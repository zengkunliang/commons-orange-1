package com.gorange.discount.engine.model.discount.query;

import com.gorange.discount.engine.model.discount.business.DiscountTime;

import java.util.List;

/**
 * 折扣备忘录类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountMemo {
    /**
     * 折扣唯一标识<br>
     * 公司下唯一,未进行设置时引擎自动进行设置<br>
     * 不为Blank时长度区间为【1-50】
     */
    private String uniqueNo;
    /**
     * 折扣分组编号<br>
     * 不得为null 或 "" 或 " "<br>
     * 长度区间为【1-10】
     */
    private String groupNo;
    /**
     * 折扣分组名称<br>
     * 不为Blank时长度区间为【1-50】
     */
    private String groupName;
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
     * 折扣开始日期<br>
     * 日期不得为null<br>
     * 开始日期必须小于或等于结束日期<br>
     * 将根据该属性值进行折扣生效日期区间过滤
     */
    private String startDate;
    /**
     * 折扣结束日期<br>
     * 日期不得为null<br>
     * 结束日期不得小于当前日期<br>
     * 将根据该属性值进行折扣生效日期区间过滤
     */
    private String endDate;
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
     * 获取uniqueNo属性值
     *
     * @return uniqueNo属性值
     */
    public String getUniqueNo() {
        return uniqueNo;
    }

    /**
     * 设置uniqueNo属性值<br>
     * 可以使用getUniqueNo()获取uniqueNo的属性值
     *
     * @param uniqueNo uniqueNo
     */
    public void setUniqueNo(String uniqueNo) {
        this.uniqueNo = uniqueNo;
    }

    /**
     * 获取groupNo属性值
     *
     * @return groupNo属性值
     */
    public String getGroupNo() {
        return groupNo;
    }

    /**
     * 设置groupNo属性值<br>
     * 可以使用getGroupNo()获取groupNo的属性值
     *
     * @param groupNo groupNo
     */
    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    /**
     * 获取groupName属性值
     *
     * @return groupName属性值
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * 设置groupName属性值<br>
     * 可以使用getGroupName()获取groupName的属性值
     *
     * @param groupName groupName
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
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
     * 设置name属性值<br>
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
     * 设置description属性值<br>
     * 可以使用getDescription()获取description的属性值
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取startDate属性值
     *
     * @return startDate属性值
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * 设置startDate属性值<br>
     * 可以使用getStartDate()获取startDate的属性值
     *
     * @param startDate startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取endDate属性值
     *
     * @return endDate属性值
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * 设置endDate属性值<br>
     * 可以使用getEndDate()获取endDate的属性值
     *
     * @param endDate endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
     * 设置weekList属性值<br>
     * 可以使用getWeekList()获取weekList的属性值
     *
     * @param weekList weekList
     */
    public void setWeekList(List<String> weekList) {
        this.weekList = weekList;
    }

    /**
     * 获取discountTimeList属性值
     *
     * @return discountTimeList属性值
     */
    public List<DiscountTime> getDiscountTimeList() {
        return discountTimeList;
    }

    /**
     * 设置discountTimeList属性值<br>
     * 可以使用getDiscountTimeList()获取discountTimeList的属性值
     *
     * @param discountTimeList discountTimeList
     */
    public void setDiscountTimeList(List<DiscountTime> discountTimeList) {
        this.discountTimeList = discountTimeList;
    }
}
