package com.lwc.discount.model.discount.business.joincondition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 折扣参与条件参数顾客类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class JoinConditionMember implements Serializable {
    /**
     * 顾客条码<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     * value长度区间【1-50】
     */
    private Map<String,List<String>> barcode;
    /**
     * 顾客分类
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     * value长度区间【1-50】
     */
    private Map<String,List<String>> type;
    /**
     * 顾客性别
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     * value数据必须是 "0" | "1" | "2"
     */
    private Map<String,List<String>> sex;
    /**
     * 顾客年龄
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null
     * value数据必须大于且等于3 小于且等于120
     */
    private Map<String,List<Integer>> age;
    /**
     * 顾客生日<br>
     * key必须匹配ExpressionKeyEnum中的数据<br>
     * key只能是IN 或 NOT_IN<br>
     * key有正确有效的值时,value不可为null<br>
     * value数据格式为【yyyy-MM-dd'T'HH:mm:ss Z】
     */
    private Map<String,List<String>> birthday;

    /**
     * 获取barcode属性值
     *
     * @return barcode属性值
     */
    public Map<String, List<String>> getBarcode() {
        return barcode;
    }

    /**
     * 设置barcode属性值
     * 可以使用getBarcode()获取barcode的属性值
     *
     * @param barcode barcode
     */
    public void setBarcode(Map<String, List<String>> barcode) {
        this.barcode = barcode;
    }

    /**
     * 获取type属性值
     *
     * @return type属性值
     */
    public Map<String, List<String>> getType() {
        return type;
    }

    /**
     * 设置type属性值
     * 可以使用getType()获取type的属性值
     *
     * @param type type
     */
    public void setType(Map<String, List<String>> type) {
        this.type = type;
    }

    /**
     * 获取sex属性值
     *
     * @return sex属性值
     */
    public Map<String, List<String>> getSex() {
        return sex;
    }

    /**
     * 设置sex属性值
     * 可以使用getSex()获取sex的属性值
     *
     * @param sex sex
     */
    public void setSex(Map<String, List<String>> sex) {
        this.sex = sex;
    }

    /**
     * 获取age属性值
     *
     * @return age属性值
     */
    public Map<String, List<Integer>> getAge() {
        return age;
    }

    /**
     * 设置age属性值
     * 可以使用getAge()获取age的属性值
     *
     * @param age age
     */
    public void setAge(Map<String, List<Integer>> age) {
        this.age = age;
    }

    /**
     * 获取birthday属性值
     *
     * @return birthday属性值
     */
    public Map<String, List<String>> getBirthday() {
        return birthday;
    }

    /**
     * 设置birthday属性值
     * 可以使用getBirthday()获取birthday的属性值
     *
     * @param birthday birthday
     */
    public void setBirthday(Map<String, List<String>> birthday) {
        this.birthday = birthday;
    }
}
