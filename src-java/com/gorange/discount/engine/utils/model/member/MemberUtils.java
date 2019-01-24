package com.gorange.discount.engine.utils.model.member;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.common.BusinessMessage;
import com.gorange.discount.engine.model.member.Member;
import com.gorange.discount.engine.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 顾客工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class MemberUtils {
    private static Logger log = LoggerFactory.getLogger(MemberUtils.class);

    /**
     * 条码最小长度
     */
    public static final Integer BARCODE_MIN_LENGTH  = 1;
    /**
     * 条码最大长度
     */
    public static final Integer BARCODE_MAX_LENGTH  = 50;
    /**
     * 类型最大长度
     */
    public static final Integer TYPE_MAX_LENGTH     = 50;
    /**
     * 女
     */
    public static final String MEMBER_SEX_WOMAN     = "0";
    /**
     * 男
     */
    public static final String MEMBER_SEX_MAN       = "1";
    /**
     * 其他
     */
    public static final String MEMBER_SEX_OTHER     = "2";
    /**
     * 顾客最小年龄
     */
    public static final Integer MEMBER_AGE_MIN      = 3;
    /**
     * 顾客最大年龄
     */
    public static final Integer MEMBER_AGE_MAX      = 120;

    /**
     * 顾客属性验证
     * <pre>
     * 验证条件:
     *      验证所有顾客属性的条件限制
     * </pre>
     * @param member            顾客对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validAllParam(Member member, BusinessMessage businessMessage){
        if(!MemberUtils.validParamForBarcode(member, businessMessage)){
            log.debug("顾客属性(条码)验证未通过");
            return false;
        }
        if(!MemberUtils.validParamForType(member, businessMessage)){
            log.debug("顾客属性(类型)验证未通过");
            return false;
        }
        if(!MemberUtils.validParamForSex(member, businessMessage)){
            log.debug("顾客属性(性别)验证未通过");
            return false;
        }
        if(!MemberUtils.validParamForAge(member, businessMessage)){
            log.debug("顾客属性(年龄)验证未通过");
            return false;
        }
        if(!MemberUtils.validParamForBirthday(member, businessMessage)){
            log.debug("顾客属性(生日)验证未通过");
            return false;
        }
        return true;
    }

    /**
     * 顾客属性(条码)验证
     * <pre>
     * 验证条件:
     *      不得为null 或 "" 或 " "
     *      长度区间为【1-50】
     * </pre>
     * @param member            顾客对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBarcode(Member member, BusinessMessage businessMessage){
        if(StringUtils.isBlank(member.getBarcode())){
            log.info("顾客属性(条码)验证 条码不可为Blank");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0006);
            return false;
        }
        int barcodeLength = member.getBarcode().length();
        if(barcodeLength<MemberUtils.BARCODE_MIN_LENGTH||barcodeLength>MemberUtils.BARCODE_MAX_LENGTH){
            log.info("顾客属性(条码)验证 条码数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0005);
            return false;
        }
        return true;
    }

    /**
     * 顾客属性(类型)验证
     * <pre>
     * 验证条件:
     *      长度区间为【1-50】
     * </pre>
     * @param member            顾客对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForType(Member member, BusinessMessage businessMessage){
        String memberType = member.getType();
        int typeLength = memberType.length();
        if(typeLength>MemberUtils.TYPE_MAX_LENGTH){
            log.info("顾客属性(类型)验证 分类长度不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0008);
            return false;
        }
        return true;
    }

    /**
     * 顾客属性(性别)验证
     * <pre>
     * 验证条件:
     *      不得为null
     *      只能是0:女 或 1:男 或 2:其他
     * </pre>
     * @param member            顾客对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForSex(Member member, BusinessMessage businessMessage){
        if(member.getSex()==null){
            log.info("顾客属性(性别)验证 性别不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0004);
            return false;
        }
        if(!(member.getSex().equals(MemberUtils.MEMBER_SEX_WOMAN)
                ||member.getSex().equals(MemberUtils.MEMBER_SEX_MAN)
                ||member.getSex().equals(MemberUtils.MEMBER_SEX_OTHER))){
            log.info("顾客属性(性别)验证 性别数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0003);
            return false;
        }
        return true;
    }

    /**
     * 顾客属性(年龄)验证
     * <pre>
     * 验证条件:
     *      不得为null
     *      范围从[3-120]
     * </pre>
     * @param member            顾客对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForAge(Member member, BusinessMessage businessMessage){
        if(member.getAge()==null){
            log.info("顾客属性(年龄)验证 年龄不可为null");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0001);
            return false;
        }
        if(member.getAge()<MemberUtils.MEMBER_AGE_MIN||member.getAge()>MemberUtils.MEMBER_AGE_MAX){
            log.info("顾客属性(年龄)验证 年龄数据不合规范");
            businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0002);
            return false;
        }
        return true;
    }

    /**
     * 顾客属性(生日)验证
     * <pre>
     * 验证条件:
     *      当数据不为null时,数据格式为【yyyy-MM-dd'T'HH:mm:ss Z】
     *      不得大于当前时间
     * </pre>
     * @param member            顾客对象
     * @param businessMessage   消息对象
     * @return 验证通过返回true 否则false
     */
    public static boolean validParamForBirthday(Member member, BusinessMessage businessMessage){
        if(member.getBirthday()!=null){
            Date birthday = DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),member.getBirthday());
            if(birthday==null||birthday.getTime()>System.currentTimeMillis()){
                log.info("顾客属性(生日)验证 生日数据不合规范");
                businessMessage.setBusinessMessageEnum(BusinessMessageEnum.MESSAGE_0007);
                return false;
            }
        }
        return true;
    }
}
