package com.gorange.discount.engine.utils.model.discount.business;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.DiscountTime;
import com.gorange.discount.engine.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 折扣时间区间工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountTimeUtils {
    private static Logger log = LoggerFactory.getLogger(DiscountTimeUtils.class);

    /**
     * 折扣日折扣时间区间属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣日折扣时间区间属性的条件限制
     * </pre>
     * @param discountTime      折扣时间区间对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(DiscountTime discountTime, BusinessMessage businessMessage){
        if(!DiscountTimeUtils.validParamForStartTime(discountTime, businessMessage)){
            log.debug("折扣日折扣时间区间属性(开始时间)验证未通过");
            return false;
        }
        if(!DiscountTimeUtils.validParamForEndTime(discountTime, businessMessage)){
            log.debug("折扣日折扣时间区间属性(结束时间)验证未通过");
            return false;
        }
        if(!DiscountTimeUtils.validTimeLogic(discountTime, businessMessage)){
            log.debug("折扣日折扣时间区间逻辑验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣日折扣时间区间逻辑验证
     * <pre>
     * 验证条件:
     *      开始时间不得大于或等于结束时间
     *      时间间隔区间必须大于或等于1秒钟
     * </pre>
     * @param discountTime      折扣时间区间对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validTimeLogic(DiscountTime discountTime, BusinessMessage businessMessage){
        int startTime = Integer.parseInt(discountTime.getStartTime().replace(":",""));
        int endTime = Integer.parseInt(discountTime.getEndTime().replace(":",""));
        if(startTime>=endTime){
            log.info("折扣日折扣时间区间逻辑验证 开始时间大于结束时间");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0307);
            return false;
        }
        if(1>endTime-startTime){
            log.info("折扣日折扣时间区间逻辑验证 开始时间与结束时间区间小于1秒");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0306);
            return false;
        }
        return true;
    }

    /**
     * 折扣日折扣时间区间属性(开始时间)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 "" 或 " "
     *      必须为hh:mm:ss规则
     * </pre>
     * @param discountTime      折扣时间区间对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForStartTime(DiscountTime discountTime, BusinessMessage businessMessage){
        //验证不为空
        if(StringUtils.isBlank(discountTime.getStartTime())){
            log.info("折扣日折扣时间区间属性(开始时间)验证 开始时间不可为Blank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0305);
            return false;
        }
        //验证数据规则
        String regex = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
        if(!discountTime.getStartTime().matches(regex)){
            log.info("折扣日折扣时间区间属性(开始时间)验证 开始时间格式错误");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0304);
            return false;
        }
        //验证数据有效性
        int startTime = Integer.parseInt(discountTime.getStartTime().replace(":",""));
        if(DateUtils.START_TIME_FOR_DAY>startTime||startTime>DateUtils.END_TIME_FOR_DAY){
            log.info("折扣日折扣时间区间属性(开始时间)验证 开始时间数据不合逻辑");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0303);
            return false;
        }
        return true;
    }

    /**
     * 折扣日折扣时间区间属性(结束时间)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 "" 或 " "
     *      必须为hh:mm:ss规则
     * </pre>
     * @param discountTime      折扣时间区间对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForEndTime(DiscountTime discountTime, BusinessMessage businessMessage){
        //验证不为空
        if(StringUtils.isBlank(discountTime.getEndTime())){
            log.info("折扣日折扣时间区间属性(结束时间)验证 结束时间不可为Blank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0302);
            return false;
        }
        //验证数据规则
        String regex = "[0-9]{2}:[0-9]{2}:[0-9]{2}";
        if(!discountTime.getEndTime().matches(regex)){
            log.info("折扣日折扣时间区间属性(结束时间)验证 结束时间格式错误");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0301);
            return false;
        }
        //验证数据有效性
        int endTime = Integer.parseInt(discountTime.getEndTime().replace(":",""));
        if(DateUtils.START_TIME_FOR_DAY>endTime||endTime>DateUtils.END_TIME_FOR_DAY){
            log.info("折扣日折扣时间区间属性(结束时间)验证 结束时间数据不合逻辑");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0300);
            return false;
        }
        return true;
    }
}
