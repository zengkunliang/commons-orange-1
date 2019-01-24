package com.gorange.discount.engine.utils.model.discount.business;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.discount.business.DiscountGroup;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 折扣分组工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DiscountGroupUtils {
    private static Logger log = LoggerFactory.getLogger(DiscountGroupUtils.class);
    /**
     * 折扣分组编号最大长度
     */
    public static final Integer GROUP_NO_MAX_LENGTH         = 10;
    /**
     * 折扣分组名称最大长度
     */
    public static final Integer NAME_NO_MAX_LENGTH          = 50;
    /**
     * 折扣计算优先级最小数值
     */
    public static final Integer MIN_CALC_LEVEL              = 0;
    /**
     * 折扣计算优先级最大数值
     */
    public static final Integer MAX_CALC_LEVEL              = 999999;

    /**
     * 折扣属性验证
     * <pre>
     * 验证条件:
     *      验证所有折扣属性的条件限制是否符合要求
     * </pre>
     * @param discountGroup     折扣分组对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(DiscountGroup discountGroup, BusinessMessage businessMessage){
        if(!DiscountGroupUtils.validParamForGroupNo(discountGroup, businessMessage)){
            log.debug("折扣分组属性(分组编号)验证未通过");
            return false;
        }
        if(!DiscountGroupUtils.validParamForName(discountGroup, businessMessage)){
            log.debug("折扣分组属性(名称)验证未通过");
            return false;
        }
        if(!DiscountGroupUtils.validParamForCalcLevel(discountGroup, businessMessage)){
            log.debug("折扣属性(计算优先级)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 折扣属性(分组编号)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 "" 或 " "
     *      长度区间为【1-10】
     * </pre>
     * @param discountGroup     折扣分组对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForGroupNo(DiscountGroup discountGroup, BusinessMessage businessMessage){
        if(StringUtils.isBlank(discountGroup.getGroupNo())){
            log.info("折扣分组属性(分组编号)验证 分组编号数据不可为Bank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1700);
            return false;
        }
        int groupNoLength = discountGroup.getGroupNo().length();
        if(groupNoLength> DiscountGroupUtils.GROUP_NO_MAX_LENGTH){
            log.info("折扣分组属性(分组编号)验证 分组编号数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1701);
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
     * @param discountGroup     折扣分组对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForName(DiscountGroup discountGroup, BusinessMessage businessMessage){
        if(!StringUtils.isBlank(discountGroup.getGroupName())&&discountGroup.getGroupName().length()>DiscountGroupUtils.NAME_NO_MAX_LENGTH){
            log.info("折扣分组属性(名称)验证 名称数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0209);
            return false;
        }
        return true;
    }

    /**
     * 折扣属性(计算优先级)验证
     * <pre>
     * 验证条件:
     *      不得小于0
     *      不得大于999999
     * </pre>
     * @param discountGroup     折扣分组对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForCalcLevel(DiscountGroup discountGroup, BusinessMessage businessMessage){
        if(DiscountGroupUtils.MIN_CALC_LEVEL >discountGroup.getCalcLevel()||discountGroup.getCalcLevel()> DiscountGroupUtils.MAX_CALC_LEVEL){
            log.info("折扣属性(计算优先级)验证 计算优先级数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_1703);
            return false;
        }
        return true;
    }

}
