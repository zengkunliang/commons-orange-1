package com.lwc.discount.model.discount.business;

import com.lwc.discount.enums.BusinessMessageEnum;
import com.lwc.discount.model.discount.business.result.DiscountResultDetail;
import com.lwc.discount.utils.I18nUtils;

import java.io.Serializable;

/**
 * 折扣结果
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class DiscountResult implements Serializable {
    /**
     * 国际化code
     */
    private String languageCode;
    /**
     * 业务结果代码
     */
    private String resultCode;
    /**
     * 业务结果代码描述
     */
    private String resultMessage;
    /**
     * 折扣结果详情
     */
    private DiscountResultDetail discResultDetail;

    /**
     * 无参构造
     */
    private DiscountResult(){}

    /**
     * 有参构造
     * @param languageCode      国际化code
     */
    public DiscountResult(String languageCode){
        this.languageCode = languageCode;
        this.resultCode = BusinessMessageEnum.MESSAGE_0000.getResponseCode();
        this.resultMessage = I18nUtils.getI18NMessage(this.languageCode,BusinessMessageEnum.MESSAGE_0000);
    }

    /**
     * 设置结果信息
     * @param businessMessageEnum   业务处理消息枚举类
     * @return 返回折扣结果
     */
    public DiscountResult setResultInfo(BusinessMessageEnum businessMessageEnum){
        this.resultCode = businessMessageEnum.getResponseCode();
        this.resultMessage = I18nUtils.getI18NMessage(this.languageCode,businessMessageEnum);
        return this;
    }


    /**
     * 获取resultCode属性值
     *
     * @return resultCode属性值
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * 设置resultCode属性值
     * 可以使用getResultCode()获取resultCode的属性值
     *
     * @param resultCode resultCode
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 获取resultMessage属性值
     *
     * @return resultMessage属性值
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * 设置resultMessage属性值
     * 可以使用getResultMessage()获取resultMessage的属性值
     *
     * @param resultMessage resultMessage
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * 获取discResultDetail属性值
     *
     * @return discResultDetail属性值
     */
    public DiscountResultDetail getDiscResultDetail() {
        return discResultDetail;
    }

    /**
     * 设置discResultDetail属性值
     * 可以使用getDiscResultDetail()获取discResultDetail的属性值
     *
     * @param discResultDetail discResultDetail
     */
    public void setDiscResultDetail(DiscountResultDetail discResultDetail) {
        this.discResultDetail = discResultDetail;
    }

}
