package com.gorange.discount.engine.model.common;

import com.gorange.discount.engine.enums.BusinessMessageEnum;

/**
 * 业务处理消息类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class BusinessMessage {
    /**
     * 业务处理消息枚举类
     */
    private BusinessMessageEnum businessMessageEnum;

    /**
     * 无参构造<br>
     * 默认设置业务处理消息为成功
     */
    public BusinessMessage(){
        this.businessMessageEnum = BusinessMessageEnum.MESSAGE_0000;
    }

    /**
     * 有参构造<br>
     * 自定义设置业务处理消息枚举
     * @param businessMessageEnum   业务处理消息枚举类
     */
    public BusinessMessage(BusinessMessageEnum businessMessageEnum){
        this.businessMessageEnum = businessMessageEnum;
    }

    /**
     * 获取messageEnum属性值
     *
     * @return messageEnum属性值
     */
    public BusinessMessageEnum getBusinessMessageEnum() {
        return businessMessageEnum;
    }

    /**
     * 设置businessMessageEnum属性值<br>
     * 可以使用getBusinessMessageEnum()获取businessMessageEnum的属性值
     *
     * @param businessMessageEnum businessMessageEnum
     */
    public void setBusinessMessageEnum(BusinessMessageEnum businessMessageEnum) {
        this.businessMessageEnum = businessMessageEnum;
    }
}
