package com.gorange.discount.engine.model.discount.business.query;

import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.utils.I18nUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 折扣查询结果
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class QueryMemberDiscountResult implements Serializable {
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
     * 可参与折扣集
     */
    private List<DiscountMemo> discountList;

    /**
     * 无参构造
     */
    private QueryMemberDiscountResult(){}

    /**
     * 有参构造
     * @param languageCode      国际化code
     */
    public QueryMemberDiscountResult(String languageCode){
        this.languageCode = languageCode;
        this.resultCode = BusinessMessageEnum.MESSAGE_0000.getResponseCode();
        this.resultMessage = I18nUtils.getI18NMessage(languageCode,BusinessMessageEnum.MESSAGE_0000);
    }

    /**
     * 设置结果信息
     * @param businessMessageEnum   业务处理消息枚举类
     * @return 返回折扣结果
     */
    public QueryMemberDiscountResult setResultInfo(BusinessMessageEnum businessMessageEnum){
        this.setResultCode(businessMessageEnum.getResponseCode());
        this.setResultMessage(I18nUtils.getI18NMessage(this.languageCode,businessMessageEnum));
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
     * 获取discountList属性值
     *
     * @return discountList属性值
     */
    public List<DiscountMemo> getDiscountList() {
        return discountList;
    }

    /**
     * 设置discountList属性值<br>
     * 可以使用getDiscountList()获取discountList的属性值
     *
     * @param discountList discountList
     */
    public void setDiscountList(List<DiscountMemo> discountList) {
        this.discountList = discountList;
    }

}
