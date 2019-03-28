package com.lwc.discount.model.member;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;

/**
 * 顾客类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public final class Member implements Serializable {
    /**
     * 条码<br>
     * 在公司下是顾客的唯一标识<br>
     * 长度区间为【1-50】
     */
    private String barcode;
    /**
     * 类型<br>
     * 手动设定时不可以设定为空字符串(""," "),否则引擎将默认为未设置顾客分类<br>
     * 当顾客并未进行分类时,该栏位可以不进行数据设定,未进行分类设定的顾客都将被划分为同一分类
     */
    private String type;
    /**
     * 自定义类型<br>
     * 手动设定时不可以设定为空字符串(""," "),否则引擎将默认为未设置顾客分类<br>
     * 当顾客并未进行分类时,该栏位可以不进行数据设定,未进行分类设定的顾客都将被划分为同一分类
     */
    private String customType;
    /**
     * 姓名
     */
    private String name;
    /**
     * 性别<br>
     * 0:女 1:男 2:其他
     */
    private String sex;
    /**
     * 年龄<br>
     * 年龄范围从[5-120]
     */
    private Integer age;
    /**
     * 生日<br>
     * 数据格式为【yyyy-MM-dd'T'HH:mm:ss Z】
     */
    private String birthday;

    /**
     * 获取barcode属性值
     *
     * @return barcode属性值
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 设置barcode属性值
     * 可以使用getBarcode()获取barcode的属性值
     *
     * @param barcode barcode
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    /**
     * 获取type属性值
     *
     * @return type属性值
     */
    public String getType() {
        if(StringUtils.isBlank(type)){
            type = "";
        }
        return type;
    }

    /**
     * 设置type属性值
     * 可以使用getType()获取type的属性值
     *
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getCustomType() {
        if(StringUtils.isBlank(customType)){
            customType = "";
        }
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
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
     * 获取sex属性值
     *
     * @return sex属性值
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置sex属性值<br>
     * 可以使用getSex()获取sex的属性值
     *
     * @param sex sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取age属性值
     *
     * @return age属性值
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置age属性值<br>
     * 可以使用getAge()获取age的属性值
     *
     * @param age age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取birthday属性值<br>
     * 数据格式为【yyyy-MM-dd'T'HH:mm:ss Z】<br>
     * 不得大于当前时间
     *
     * @return birthday属性值
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 设置birthday属性值
     * 可以使用getBirthday()获取birthday的属性值
     *
     * @param birthday birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
