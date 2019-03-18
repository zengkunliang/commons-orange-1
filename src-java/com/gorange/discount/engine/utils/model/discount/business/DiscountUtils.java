package com.gorange.discount.engine.utils.model.discount.business;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.enums.DateFormatTypeEnum;
import com.gorange.discount.engine.enums.WeekTypeEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.Discount;
import com.gorange.discount.engine.model.discount.business.DiscountTime;
import com.gorange.discount.engine.model.discount.query.DiscountMemo;
import com.gorange.discount.engine.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * 折扣工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountUtils {
    private static Logger log = LoggerFactory.getLogger(DiscountUtils.class);
    /**
     * 公司编号最大长度
     */
    public static final Integer COMPANY_NO_MAX_LENGTH       = 50;
    /**
     * 折扣唯一编号最大长度
     */
    public static final Integer UNIQUE_NO_MAX_LENGTH        = 50;
    /**
     * 折扣名称最大长度
     */
    public static final Integer NAME_NO_MAX_LENGTH          = 50;

    /**
     * 折扣数据验证
     * @param discountList      折扣对象集
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validDiscountData(List<Discount> discountList, BusinessMessage businessMessage){
        boolean valid = true;
        if(discountList!=null&&!discountList.isEmpty()){
            for(Discount discount:discountList){
                if(!DiscountUtils.validDiscountData(discount, businessMessage)){
                    log.info("折扣【{}】未通过验证:{} ",discount.getUniqueNo(),businessMessage.getBusinessMessageEnum().getDefaultMsg());
                    return false;
                }
            }
        }
        return valid;
    }

    /**
     * 折扣数据验证
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validDiscountData(Discount discount, BusinessMessage businessMessage){
        if(discount==null){
            log.debug("折扣数据不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0200);
            return false;
        }
        log.debug("开始折扣属性验证");
        boolean valid = DiscountUtils.validAllParam(discount, businessMessage);
        log.debug("完成折扣属性验证{}",valid);
        return valid;
    }

    /**
     * 折扣属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣属性的条件限制是否符合要求
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(Discount discount, BusinessMessage businessMessage){
        if(!DiscountUtils.validParamForUniqueNo(discount, businessMessage)){
            log.debug("折扣属性(唯一标识)验证未通过");
            return false;
        }
        if(!DiscountUtils.validParamForGroupNo(discount, businessMessage)){
            log.debug("折扣属性(分组)验证未通过");
            return false;
        }
        if(!DiscountUtils.validParamForCompanyNo(discount, businessMessage)){
            log.debug("折扣属性(公司编码)验证未通过");
            return false;
        }
        if(!DiscountUtils.validParamForName(discount, businessMessage)){
            log.debug("折扣属性(名称)验证未通过");
            return false;
        }
        if(!DiscountUtils.validParamForDate(discount, businessMessage)){
            log.debug("折扣属性(日期)验证未通过");
            return false;
        }
        if(!DiscountUtils.validParamForWeekList(discount, businessMessage)){
            log.debug("折扣属性(星期区间)验证未通过");
            return false;
        }
        if(!DiscountUtils.validParamForDiscountTimeList(discount, businessMessage)){
            log.debug("折扣属性(时间区间)验证未通过");
            return false;
        }
        if(!DiscountUtils.validParamForDiscountJoinCondition(discount, businessMessage)){
            log.debug("折扣属性(折扣参与条件)验证未通过");
            return false;
        }
        if(!DiscountUtils.validParamForDiscountCalcCondition(discount, businessMessage)){
            log.debug("折扣属性(折扣计算条件)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣属性(唯一标识)验证
     * <pre>
     * 验证条件:
     *      不为Blank时长度区间为【1-50】
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForUniqueNo(Discount discount, BusinessMessage businessMessage){
        if(!StringUtils.isBlank(discount.getUniqueNo())&&discount.getUniqueNo().length()>DiscountUtils.UNIQUE_NO_MAX_LENGTH){
            log.info("折扣属性(唯一标识)验证 唯一标识数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0208);
            return false;
        }
        return true;
    }

    /**
     * 折扣属性(分组)验证
     * <pre>
     * 验证条件:
     *      不得为null
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForGroupNo(Discount discount, BusinessMessage businessMessage){
        if(discount.getDiscountGroup()==null){
            log.info("折扣属性(分组)验证 分组数据不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0206);
            return false;
        }
        return DiscountGroupUtils.validAllParam(discount.getDiscountGroup(),businessMessage);
    }

    /**
     * 折扣属性(公司编码)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 "" 或 " "
     *      长度区间为【1-50】
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCompanyNo(Discount discount, BusinessMessage businessMessage){
        if(StringUtils.isBlank(discount.getCompanyNo())){
            log.info("折扣属性(公司编码)验证 公司编码数据不可为Bank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0204);
            return false;
        }
        int companyNoLength = discount.getCompanyNo().length();
        if(companyNoLength>DiscountUtils.COMPANY_NO_MAX_LENGTH){
            log.info("折扣属性(公司编码)验证 公司编码数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0205);
            return false;
        }
        return true;
    }

    /**
     * 折扣属性(名称)验证
     * <pre>
     * 验证条件:
     *      不为Blank时长度区间为【1-50】
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForName(Discount discount, BusinessMessage businessMessage){
        if(!StringUtils.isBlank(discount.getName())&&discount.getName().length()>DiscountUtils.NAME_NO_MAX_LENGTH){
            log.info("折扣属性(名称)验证 名称数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0209);
            return false;
        }
        return true;
    }

    /**
     * 折扣属性(日期)验证
     * <pre>
     * 验证条件:
     *      日期不得为null
     *      开始日期必须小于或等于结束日期
     *      结束日期不得小于当前日期
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForDate(Discount discount, BusinessMessage businessMessage){
        if(discount.getStartDate()==null){
            log.info("折扣属性(日期)验证 开始日期不可null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0210);
            return false;
        }
        if(discount.getEndDate()==null){
            log.info("折扣属性(日期)验证 结束日期不可null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0211);
            return false;
        }
        if(discount.getStartDate().getTime()>discount.getEndDate().getTime()){
            log.info("折扣属性(日期)验证 开始日期不得大于结束日期");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0212);
            return false;
        }
        if(discount.getEndDate().getTime()< DateUtils.setDateTime(new Date(),0,0,0,0).getTimeInMillis()){
            log.info("折扣属性(日期)验证 折扣结束日期不得小于当前日期");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0213);
            return false;
        }

        return true;
    }

    /**
     * 折扣属性(星期区间)验证
     * <pre>
     * 验证条件:
     *      星期区间内容之必须符合WeekTypeEnum
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForWeekList(Discount discount, BusinessMessage businessMessage){
        if(discount.getWeekList()==null){
            log.info("折扣属性(星期区间)验证 星期区间不可null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0214);
            return false;
        }
        for(String week:discount.getWeekList()){
            if(WeekTypeEnum.getWeekTypeEnum(week)==null){
                log.info("折扣属性(日期)验证 星期区间数据不合规范");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0215);
                return false;
            }
        }
        return true;
    }

    /**
     * 折扣属性(时间区间)验证
     * <pre>
     * 验证条件:
     *      星期区间内容之必须符合WeekTypeEnum
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForDiscountTimeList(Discount discount, BusinessMessage businessMessage){
        if(discount.getDiscountTimeList().isEmpty()){
            log.info("折扣属性(时间区间)验证 时间区间未设置");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0216);
            return false;
        }
        for (DiscountTime discountTime : discount.getDiscountTimeList()) {
            log.debug("开始折扣日折扣时间区间属性验证");
            boolean valid = DiscountTimeUtils.validAllParam(discountTime, businessMessage);
            log.debug("完成折扣日折扣时间区间属性验证{}",valid);
            if(!valid){
                return valid;
            }
        }
        return true;
    }

    /**
     * 折扣属性(折扣参与条件)验证
     * <pre>
     * 验证条件:
     *      不得为null
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForDiscountJoinCondition(Discount discount, BusinessMessage businessMessage){
        if(discount.getDiscountJoinCondition()==null){
            log.info("折扣属性(折扣参与条件)验证 折扣参与条件不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0202);
            return false;
        }
        log.debug("开始折扣参与条件属性验证");
        boolean valid = DiscountJoinConditionUtils.validAllParam(discount.getDiscountJoinCondition(), businessMessage);
        log.debug("完成折扣参与条件属性验证{}",valid);
        return valid;
    }

    /**
     * 折扣属性(折扣计算条件)验证
     * <pre>
     * 验证条件:
     *      不得为null
     * </pre>
     * @param discount          折扣对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForDiscountCalcCondition(Discount discount, BusinessMessage businessMessage){
        if(discount.getDiscountCalcCondition()==null){
            log.info("折扣属性(折扣计算条件)验证 折扣计算条件不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0201);
            return false;
        }
        log.debug("开始折扣计算条件属性验证");
        boolean valid = DiscountCalcConditionUtils.validAllParam(discount.getDiscountCalcCondition(), businessMessage);
        log.debug("完成折扣计算条件属性验证{}",valid);
        return valid;
    }

    /**
     * 转换折扣对象为折扣备忘录对象
     * @param timeZone  时区
     * @param discount  折扣对象
     * @return 返回折扣备忘录对象
     */
    public static DiscountMemo conventDiscountToDiscountMemo(String timeZone,Discount discount){
        if(discount!=null){
            DiscountMemo discountMemo = new DiscountMemo();
            discountMemo.setUniqueNo(discount.getUniqueNo());
            discountMemo.setCompanyNo(discount.getCompanyNo());
            discountMemo.setGroupNo(discount.getDiscountGroup().getGroupNo());
            discountMemo.setGroupName(discount.getDiscountGroup().getGroupName());
            discountMemo.setName(discount.getName());
            discountMemo.setDescription(discount.getDescription());
            discountMemo.setStartDate(DateUtils.getAppointTimeZoneDateStr(timeZone, discount.getStartDate(), DateFormatTypeEnum.DATE_TIME));
            discountMemo.setEndDate(DateUtils.getAppointTimeZoneDateStr(timeZone, discount.getEndDate(),DateFormatTypeEnum.DATE_TIME));
            discountMemo.setWeekList(discount.getWeekList());
            discountMemo.setDiscountTimeList(discount.getDiscountTimeList());
            return discountMemo;
        }
        return null;
    }
}
