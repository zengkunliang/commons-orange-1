package com.gorange.discount.engine.enums;

/**
 * 业务处理消息枚举类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public enum BusinessMessageEnum {
    /** 处理成功 **/
    MESSAGE_0000("business_message_0000", "0000", "Successful!"),

    /** 顾客校验 年龄不可为null **/
    MESSAGE_0001("business_message_0001", "0001", "顾客年龄不可为null"),
    /** 顾客校验 年龄数据不合规范 **/
    MESSAGE_0002("business_message_0002", "0002", "顾客年龄数据不合规范"),
    /** 顾客校验 年龄数据不合规范 **/
    MESSAGE_0003("business_message_0003", "0003", "顾客性别数据不合规范"),
    /** 顾客校验 性别不可为null **/
    MESSAGE_0004("business_message_0004", "0004", "顾客性别不可为null"),
    /** 顾客校验 条码数据不合规范 **/
    MESSAGE_0005("business_message_0005", "0005", "顾客条码数据不合规范"),
    /** 顾客校验 条码不可为Blank **/
    MESSAGE_0006("business_message_0006", "0006", "顾客条码不可为Blank"),
    /** 顾客校验 生日数据不合规范 **/
    MESSAGE_0007("business_message_0007", "0007", "顾客生日数据不合规范"),
    /** 顾客校验 分类长度不合规范 **/
    MESSAGE_0008("business_message_0008", "0008", "顾客分类长度不合规范"),

    /** 商品校验 数量不得小于且等于0 **/
    MESSAGE_0100("business_message_0100", "0100", "商品数量不得小于且等于0"),
    /** 商品校验 单价不得小于0 **/
    MESSAGE_0102("business_message_0102", "0102", "商品单价不得小于0"),
    /** 商品校验 单价不可为null **/
    MESSAGE_0103("business_message_0103", "0103", "商品单价不可为null"),
    /** 商品校验 条码长度不合规范 **/
    MESSAGE_0104("business_message_0104", "0104", "商品条码长度不合规范"),
    /** 商品校验 条码不可为Blank **/
    MESSAGE_0105("business_message_0105", "0105", "商品条码不可为Blank"),
    /** 商品校验 分类编号长度不合规范 **/
    MESSAGE_0106("business_message_0106", "0106", "商品分类编号长度不合规范"),
    /** 商品校验 单位长度不合规范 **/
    MESSAGE_0107("business_message_0107", "0107", "商品单位长度不合规范"),
    /** 商品校验 商品不可为null **/
    MESSAGE_0108("business_message_0108", "0108", "商品不可为null"),
    /** 商品校验 商品行号不可为null **/
    MESSAGE_0109("business_message_0109", "0109", "商品行号不可为null"),
    /** 商品校验 商品行号不合规范 **/
    MESSAGE_0110("business_message_0110", "0110", "商品行号不合规范"),
    /** 商品校验 商品行号不可重复 **/
    MESSAGE_0111("business_message_0111", "0111", "商品行号不可重复"),
    /** 商品校验 商品集不可为null **/
    MESSAGE_0112("business_message_0112", "0112", "商品集不可为null"),
    /** 商品校验 商品集不可为Empty **/
    MESSAGE_0113("business_message_0113", "0113", "商品集不可为Empty"),

    /** 折扣校验 数据不可为null **/
    MESSAGE_0200("business_message_0200", "0200", "折扣数据不可为null"),
    /** 折扣校验 折扣条件表达式不可为null **/
    MESSAGE_0201("business_message_0201", "0201", "折扣计算条件不可为null"),
    /** 折扣校验 折扣参与条件不可为null **/
    MESSAGE_0202("business_message_0202", "0202", "折扣参与条件不可为null"),
    /** 折扣校验 计算优先级数据不合规范 **/
    MESSAGE_0203("business_message_0203", "0203", "折扣计算优先级数据不合规范"),
    /** 折扣校验 商户编码数据不可为Bank **/
    MESSAGE_0204("business_message_0204", "0204", "折扣商户编码数据不可为Bank"),
    /** 折扣校验 商户编码数据不合规范 **/
    MESSAGE_0205("business_message_0205", "0205", "折扣商户编码数据不合规范"),
    /** 折扣校验 分组数据不可为null **/
    MESSAGE_0206("business_message_0206", "0206", "折扣分组数据不可为null"),
    /** 折扣校验 唯一标识数据不合规范 **/
    MESSAGE_0208("business_message_0208", "0208", "折扣唯一标识数据不合规范"),
    /** 折扣校验 名称数据不合规范 **/
    MESSAGE_0209("business_message_0209", "0209", "折扣名称数据不合规范"),
    /** 折扣校验 开始日期不可null **/
    MESSAGE_0210("business_message_0210", "0210", "折扣开始日期不可null"),
    /** 折扣校验 结束日期不可null **/
    MESSAGE_0211("business_message_0211", "0211", "折扣结束日期不可null"),
    /** 折扣校验 开始日期不得大于结束日期 **/
    MESSAGE_0212("business_message_0212", "0211", "折扣开始日期不得大于结束日期"),
    /** 折扣校验 结束日期不得小于当前日期 **/
    MESSAGE_0213("business_message_0213", "0213", "折扣结束日期不得小于当前日期"),
    /** 折扣校验 星期区间不可null **/
    MESSAGE_0214("business_message_0214", "0214", "折扣星期区间不可null"),
    /** 折扣校验 星期区间数据不合规范 **/
    MESSAGE_0215("business_message_0215", "0215", "折扣星期区间数据不合规范"),
    /** 折扣校验 时间区间未设置 **/
    MESSAGE_0216("business_message_0216", "0216", "折扣时间区间未设置"),

    /** 折扣时间区间校验 结束时间数据不合逻辑 **/
    MESSAGE_0300("business_message_0300", "0300", "折扣时间区间中结束时间数据不合逻辑"),
    /** 折扣时间区间校验 结束时间格式错误 **/
    MESSAGE_0301("business_message_0301", "0301", "折扣时间区间中结束时间格式错误"),
    /** 折扣时间区间校验 结束时间不可为Blank **/
    MESSAGE_0302("business_message_0302", "0302", "折扣时间区间中结束时间不可为Blank"),
    /** 折扣时间区间校验 开始时间数据不合逻辑 **/
    MESSAGE_0303("business_message_0303", "0303", "折扣时间区间中开始时间数据不合逻辑"),
    /** 折扣时间区间校验 开始时间格式错误 **/
    MESSAGE_0304("business_message_0304", "0304", "折扣时间区间中开始时间格式错误"),
    /** 折扣时间区间校验 开始时间不可为Blank **/
    MESSAGE_0305("business_message_0305", "0305", "折扣时间区间中开始时间不可为Blank"),
    /** 折扣时间区间校验 开始时间与结束时间区间小于1秒 **/
    MESSAGE_0306("business_message_0306", "0306", "折扣时间区间中开始时间与结束时间区间小于1秒"),
    /** 折扣时间区间校验 开始时间大于结束时间 **/
    MESSAGE_0307("business_message_0307", "0307", "折扣时间区间中开始时间大于结束时间"),

    /** 折扣计算条件验证 计算条件参数不可为null **/
    MESSAGE_0400("business_message_0400", "0400", "计算条件参数不可为null"),
    /** 折扣计算条件验证 折扣计算结果类型不可为Blank **/
    MESSAGE_0401("business_message_0401", "0401", "折扣计算条件中折扣计算结果类型不可为Blank"),
    /** 折扣计算条件验证 折扣计算结果类型不合规范 **/
    MESSAGE_0402("business_message_0402", "0402", "折扣计算条件中折扣计算结果类型不合规范"),
    /** 折扣计算条件验证 商品数据不可为null **/
    MESSAGE_0403("business_message_0403", "0403", "折扣计算条件中商品数据不可为null"),
    /** 折扣计算条件验证 商品数据不可为Empty **/
    MESSAGE_0404("business_message_0404", "0404", "折扣计算条件中商品数据不可为Empty"),
    /** 折扣计算条件验证 计算商品数据不可为null **/
    MESSAGE_0405("business_message_0405", "0405", "折扣计算条件中计算商品数据不可为null"),
    /** 折扣计算条件验证 目标商品数据不可为null **/
    MESSAGE_0406("business_message_0406", "0406", "折扣计算条件中目标商品数据不可为null"),


    /** 折扣参与条件验证 明确表示有参与条件时,参与条件参数不可为null **/
    MESSAGE_0500("business_message_0600", "0600", "折扣参与条件中明确表示有参与条件时,参与条件参数不可为null"),

    /** 折扣结果验证  **/
    MESSAGE_0600("business_message_0400", "0400", "折扣计算条件中计算条件参数不可为null"),

    /** 折扣参与条件参数顾客验证 条码表达式key数据不合规范 **/
    MESSAGE_0700("business_message_0700", "0700", "折扣参与条件参数顾客属性中条码表达式key数据不合规范"),
    /** 折扣参与条件参数顾客验证 条码表达式key对应的条码数据不可为null **/
    MESSAGE_0701("business_message_0701", "0701", "折扣参与条件参数顾客属性中条码表达式key对应的条码数据不可为null"),
    /** 折扣参与条件参数顾客验证 条码表达式key对应的条码数据不合规范 **/
    MESSAGE_0702("business_message_0702", "0702", "折扣参与条件参数顾客属性中条码表达式key对应的条码数据不合规范"),
    /** 折扣参与条件参数顾客验证 分类表达式key数据不合规范 **/
    MESSAGE_0710("business_message_0710", "0710", "折扣参与条件参数顾客属性中分类表达式key数据不合规范"),
    /** 折扣参与条件参数顾客验证 分类表达式key对应的条码数据不可为null **/
    MESSAGE_0711("business_message_0711", "0711", "折扣参与条件参数顾客属性中分类表达式key对应的分类数据不可为null"),
    /** 折扣参与条件参数顾客验证 分类表达式key对应的分类数据不合规范 **/
    MESSAGE_0712("business_message_0712", "0712", "折扣参与条件参数顾客属性中分类表达式key对应的分类数据不合规范"),
    /** 折扣参与条件参数顾客验证 性别表达式key数据不合规范 **/
    MESSAGE_0721("business_message_0721", "0721", "折扣参与条件参数顾客属性中性别表达式key数据不合规范"),
    /** 折扣参与条件参数顾客验证 性别表达式key对应的条码数据不可为null **/
    MESSAGE_0722("business_message_0722", "0722", "折扣参与条件参数顾客属性中性别表达式key对应的性别数据不可为null"),
    /** 折扣参与条件参数顾客验证 性别表达式key对应的条码数据不可为null **/
    MESSAGE_0723("business_message_0723", "0723", "折扣参与条件参数顾客属性中生日表达式key对应的性别数据不合规范"),
    /** 折扣参与条件参数顾客验证 年龄表达式key数据不合规范 **/
    MESSAGE_0730("business_message_0730", "0730", "折扣参与条件参数顾客属性中年龄表达式key数据不合规范"),
    /** 折扣参与条件参数顾客验证 年龄表达式key对应的条码数据不可为null **/
    MESSAGE_0731("business_message_0731", "0731", "折扣参与条件参数顾客属性中年龄表达式key对应的年龄数据不可为null"),
    /** 折扣参与条件参数顾客验证 年龄表达式key对应的年龄数据不合规范 **/
    MESSAGE_0732("business_message_0732", "0732", "折扣参与条件参数顾客属性中年龄表达式key对应的年龄数据不合规范"),
    /** 折扣参与条件参数顾客验证 生日表达式key数据不合规范 **/
    MESSAGE_0740("business_message_0740", "0740", "折扣参与条件参数顾客属性中生日表达式key数据不合规范"),
    /** 折扣参与条件参数顾客验证 生日表达式key对应的条码数据不可为null **/
    MESSAGE_0741("business_message_0741", "0741", "折扣参与条件参数顾客属性中生日表达式key对应的生日数据不可为null"),
    /** 折扣参与条件参数顾客验证 生日表达式key对应的生日数据不合规范 **/
    MESSAGE_0742("business_message_0742", "0742", "折扣参与条件参数顾客属性中生日表达式key对应的生日数据不合规范"),

    /** 折扣参与条件参数商品验证 分组表达式key数据不合规范 **/
    MESSAGE_0800("business_message_0800", "0800", "折扣参与条件参数商品属性中分组表达式key数据不合规范"),
    /** 折扣参与条件参数商品验证 分组表达式key对应的条码数据不可为null **/
    MESSAGE_0801("business_message_0801", "0801", "折扣参与条件参数商品属性中分组表达式key对应的分组数据不可为null"),
    /** 折扣参与条件参数商品验证 分组表达式key对应的分组数据不合规范 **/
    MESSAGE_0802("business_message_0802", "0802", "折扣参与条件参数商品属性中分组表达式key对应的分组数据不合规范"),
    /** 折扣参与条件参数商品验证 条码表达式key数据不合规范 **/
    MESSAGE_0810("business_message_0810", "0810", "折扣参与条件参数商品属性中条码表达式key数据不合规范"),
    /** 折扣参与条件参数商品验证 条码表达式key对应的条码数据不可为null **/
    MESSAGE_0811("business_message_0811", "0811", "折扣参与条件参数商品属性中条码表达式key对应的条码数据不可为null"),
    /** 折扣参与条件参数商品验证 条码表达式key对应的条码数据不合规范 **/
    MESSAGE_0812("business_message_0812", "0812", "折扣参与条件参数商品属性中条码表达式key对应的条码数据不合规范"),
    /** 折扣参与条件参数商品验证 最小金额表达式key数据不合规范 **/
    MESSAGE_0820("business_message_0820", "0820", "折扣参与条件参数商品属性中最小金额表达式key数据不合规范"),
    /** 折扣参与条件参数商品验证 最小金额表达式key对应的金额数据不可为null **/
    MESSAGE_0821("business_message_0821", "0821", "折扣参与条件参数商品属性中最小金额表达式key对应的金额数据不可为null"),
    /** 折扣参与条件参数商品验证 最小金额表达式key对应的金额数据不合规范 **/
    MESSAGE_0822("business_message_0822", "0822", "折扣参与条件参数商品属性中最小金额表达式key对应的金额数据不合规范"),
    /** 折扣参与条件参数商品验证 最大金额表达式key数据不合规范 **/
    MESSAGE_0830("business_message_0830", "0830", "折扣参与条件参数商品属性中最大金额表达式key数据不合规范"),
    /** 折扣参与条件参数商品验证 最大金额表达式key对应的金额数据不可为null **/
    MESSAGE_0831("business_message_0831", "0831", "折扣参与条件参数商品属性中最大金额表达式key对应的金额数据不可为null"),
    /** 折扣参与条件参数商品验证 最大金额表达式key对应的金额数据不合规范 **/
    MESSAGE_0832("business_message_0832", "0832", "折扣参与条件参数商品属性中最大金额表达式key对应的金额数据不合规范"),
    /** 折扣参与条件参数商品验证 最小金额不得大于最大金额 **/
    MESSAGE_0833("business_message_0833", "0833", "折扣参与条件参数商品属性中最小金额不得大于最大金额"),


    /** 折扣参与条件交易参数当前交易验证 交易类型不可为null **/
    MESSAGE_0900("business_message_0900", "0900", "折扣参与条件交易参数当前交易属性中交易类型不可为null"),
    /** 折扣参与条件交易参数当前交易验证 交易类型数据不合规范 **/
    MESSAGE_0901("business_message_0901", "0901", "折扣参与条件交易参数当前交易属性中交易类型数据不合规范"),
    /** 折扣参与条件交易参数当前交易验证 交易参与类型不可为null **/
    MESSAGE_0910("business_message_0910", "0910", "折扣参与条件交易参数当前交易属性中交易参与类型不可为null"),
    /** 折扣参与条件交易参数当前交易验证 交易参与类型数据不合规范 **/
    MESSAGE_0911("business_message_0911", "0911", "折扣参与条件交易参数当前交易属性中交易参与类型数据不合规范"),
    /** 折扣参与条件交易参数当前交易验证 交易总金额表达式key数据不合规范 **/
    MESSAGE_0920("business_message_0920", "0920", "折扣参与条件交易参数当前交易属性中交易总金额表达式key数据不合规范"),
    /** 折扣参与条件交易参数当前交易验证 交易总金额key对应的金额数据不可为null **/
    MESSAGE_0921("business_message_0920", "0921", "折扣参与条件交易参数当前交易属性中交易总金额key对应的金额数据不可为null"),
    /** 折扣参与条件交易参数当前交易验证 交易总金额表达式key对应的金额数据不合规范 **/
    MESSAGE_0922("business_message_0922", "0922", "折扣参与条件交易参数当前交易属性中交易总金额表达式key对应的金额数据不合规范"),

    /** 折扣参与条件交易参数关联交易验证 商户号表达式key数据不合规范 **/
    MESSAGE_1000("business_message_1000", "1000", "折扣参与条件交易参数关联交易属性中商户号表达式key数据不合规范"),
    /** 折扣参与条件交易参数关联交易验证 商户号表达式key对应的商户号数据不可为null **/
    MESSAGE_1001("business_message_1001", "1001", "折扣参与条件交易参数关联交易属性中商户号表达式key对应的商户号数据不可为null"),
    /** 折扣参与条件交易参数关联交易验证 商户号表达式key对应的商户号数据不合规范 **/
    MESSAGE_1002("business_message_1002", "1002", "折扣参与条件交易参数关联交易属性中商户号表达式key对应的商户号数据不合规范"),
    /** 折扣参与条件交易参数关联交易验证 交易开始时间表达式key数据不合规范 **/
    MESSAGE_1010("business_message_1010", "1010", "折扣参与条件交易参数关联交易属性中交易开始时间表达式key数据不合规范"),
    /** 折扣参与条件交易参数关联交易验证 交易开始时间表达式key对应的开始时间数据不可为Blank **/
    MESSAGE_1011("business_message_1011", "1011", "折扣参与条件交易参数关联交易属性中交易开始时间表达式key对应的开始时间数据不可为Blank"),
    /** 折扣参与条件交易参数关联交易验证 交易开始时间表达式key对应的开始时间数据不合规范 **/
    MESSAGE_1012("business_message_1012", "1012", "折扣参与条件交易参数关联交易属性中交易开始时间表达式key对应的开始时间数据不合规范"),
    /** 折扣参与条件交易参数关联交易验证 交易结束时间表达式key数据不合规范 **/
    MESSAGE_1020("business_message_1020", "1020", "折扣参与条件交易参数关联交易属性中交易结束时间表达式key数据不合规范"),
    /** 折扣参与条件交易参数关联交易验证 交易结束时间表达式key对应的结束时间数据不可为Blank **/
    MESSAGE_1021("business_message_1021", "1021", "折扣参与条件交易参数关联交易属性中交易结束时间表达式key对应的结束时间数据不可为Blank"),
    /** 折扣参与条件交易参数关联交易验证 交易结束时间表达式key对应的结束时间数据不合规范 **/
    MESSAGE_1022("business_message_1022", "1022", "折扣参与条件交易参数关联交易属性中交易结束时间表达式key对应的结束时间数据不合规范"),
    /** 折扣参与条件交易参数关联交易验证 交易开始时间不得大于交易结束时间 **/
    MESSAGE_1023("business_message_1023", "1023", "折扣参与条件交易参数关联交易属性中交易开始时间不得大于交易结束时间"),
    /** 折扣参与条件交易参数关联交易验证 交易总金额表达式key数据不合规范 **/
    MESSAGE_1024("business_message_1024", "1024", "折扣参与条件交易参数关联交易属性中交易总金额表达式key数据不合规范"),
    /** 折扣参与条件交易参数关联交易验证 交易总金额key对应的金额数据不可为null **/
    MESSAGE_1025("business_message_1025", "1025", "折扣参与条件交易参数关联交易属性中交易总金额key对应的金额数据不可为null"),
    /** 折扣参与条件交易参数关联交易验证 交易总金额表达式key对应的金额数据不合规范 **/
    MESSAGE_1026("business_message_1026", "1026", "折扣参与条件交易参数关联交易属性中交易总金额表达式key对应的金额数据不合规范"),

    /** 折扣计算条件参数最大值验证 交易最大折扣值表达式key数据不合规范 **/
    MESSAGE_1100("business_message_1100", "1100", "折扣计算条件参数最大值属性中交易最大折扣值表达式key数据不合规范"),
    /** 折扣计算条件参数最大值验证 交易最大折扣值表达式key对应的最大值数据不可为null **/
    MESSAGE_1101("business_message_1101", "1101", "折扣计算条件参数最大值属性中交易最大折扣值表达式key对应的最大值数据不可为null"),
    /** 折扣计算条件参数最大值验证 交易最大折扣值表达式key对应的最大值数据不合规范 **/
    MESSAGE_1102("business_message_1102", "1102", "折扣计算条件参数最大值属性中交易最大折扣值表达式key对应的最大值数据不合规范"),
    /** 折扣计算条件参数最大值验证 会员最大折扣值表达式key数据不合规范 **/
    MESSAGE_1103("business_message_1103", "1103", "折扣计算条件参数最大值属性中会员最大折扣值表达式key数据不合规范"),
    /** 折扣计算条件参数最大值验证 会员最大折扣值表达式key对应的最大值数据不可为null **/
    MESSAGE_1104("business_message_1104", "1104", "折扣计算条件参数最大值属性中会员最大折扣值表达式key对应的最大值数据不可为null"),
    /** 折扣计算条件参数最大值验证 会员最大折扣值表达式key对应的最大值数据不合规范 **/
    MESSAGE_1105("business_message_1105", "1105", "折扣计算条件参数最大值属性中会员最大折扣值表达式key对应的最大值数据不合规范"),
    /** 折扣计算条件参数最大值验证 商户最大折扣值表达式key数据不合规范 **/
    MESSAGE_1106("business_message_1106", "1106", "折扣计算条件参数最大值属性中商户最大折扣值表达式key数据不合规范"),
    /** 折扣计算条件参数最大值验证 商户最大折扣值表达式key对应的最大值数据不可为null **/
    MESSAGE_1107("business_message_1107", "1107", "折扣计算条件参数最大值属性中商户最大折扣值表达式key对应的最大值数据不可为null"),
    /** 折扣计算条件参数最大值验证 商户最大折扣值表达式key对应的最大值数据不合规范 **/
    MESSAGE_1108("business_message_1108", "1108", "折扣计算条件参数最大值属性中商户最大折扣值表达式key对应的最大值数据不合规范"),
    /** 折扣计算条件参数最大值验证 交易最大折扣值不得小于或等于0 **/
    MESSAGE_1109("business_message_1109", "1109", "折扣计算条件参数最大值属性中交易最大折扣值不得小于或等于0"),
    /** 折扣计算条件参数最大值验证 会员最大折扣值不得小于或等于0 **/
    MESSAGE_1110("business_message_1110", "1110", "折扣计算条件参数最大值属性中会员最大折扣值不得小于或等于0"),
    /** 折扣计算条件参数最大值验证 商户最大折扣值不得小于或等于0 **/
    MESSAGE_1111("business_message_1111", "1111", "折扣计算条件参数最大值属性中商户最大折扣值不得小于或等于0"),
    /** 折扣计算条件参数最大值验证 交易最大折扣值不得大于会员最大折扣最大值 **/
    MESSAGE_1112("business_message_1112", "1112", "折扣计算条件参数最大值属性中交易折扣最大值不得大于会员折扣最大值"),
    /** 折扣计算条件参数最大值验证 会员最大折扣值不得大于商户最大折扣最大值 **/
    MESSAGE_1113("business_message_1113", "1113", "折扣计算条件参数最大值属性中会员折扣最大值不得大于商户折扣最大值"),

    /** 目标商品验证 分类编号表达式key数据不合规范 **/
    MESSAGE_1200("business_message_1200", "1200", "目标商品属性中分类编号表达式key数据不合规范"),
    /** 目标商品验证 分类编号表达式key对应的分类数据不可为null **/
    MESSAGE_1201("business_message_1201", "1201", "目标商品属性中分类编号表达式key对应的分类数据不可为null"),
    /** 目标商品验证 分类编号表达式key对应的分类数据不合规范 **/
    MESSAGE_1202("business_message_1202", "1202", "目标商品属性中分类编号表达式key对应的分类数据不合规范"),
    /** 目标商品验证 条码表达式key数据不合规范 **/
    MESSAGE_1203("business_message_1203", "1203", "目标商品属性中条码表达式key数据不合规范"),
    /** 目标商品验证 条码表达式key对应的条码数据不可为null **/
    MESSAGE_1204("business_message_1204", "1204", "目标商品属性中条码表达式key对应的条码数据不可为null"),
    /** 目标商品验证 条码表达式key对应的条码数据不合规范 **/
    MESSAGE_1205("business_message_1205", "1205", "目标商品属性中条码表达式key对应的条码数据不合规范"),
    /** 目标商品验证 排序类型不可为Blank **/
    MESSAGE_1206("business_message_1206", "1206", "目标商品属性中排序类型不可为Blank"),
    /** 目标商品验证 排序类型数据不合规范 **/
    MESSAGE_1207("business_message_1207", "1207", "目标商品属性中排序类型数据不合规范"),
    /** 目标商品验证 折扣值不可为null **/
    MESSAGE_1208("business_message_1208", "1208", "目标商品属性中折扣值不可为null"),
    /** 目标商品验证 折扣值不合逻辑 **/
    MESSAGE_1209("business_message_1209", "1209", "目标商品属性中折扣值不合逻辑"),

    /** 计算商品条件验证 分类编号表达式key数据不合规范 **/
    MESSAGE_1300("business_message_1300", "1300", "计算商品条件属性中分类编号表达式key数据不合规范"),
    /** 计算商品条件验证 分类编号表达式key对应的分类数据不可为null **/
    MESSAGE_1301("business_message_1301", "1301", "计算商品条件属性中分类编号表达式key对应的分类数据不可为null"),
    /** 计算商品条件验证 分类编号表达式key对应的分类数据不合规范 **/
    MESSAGE_1302("business_message_1302", "1302", "计算商品条件属性中分类编号表达式key对应的分类数据不合规范"),
    /** 计算商品条件验证 条码表达式key数据不合规范 **/
    MESSAGE_1303("business_message_1303", "1303", "计算商品条件属性中条码表达式key数据不合规范"),
    /** 计算商品条件验证 条码表达式key对应的条码数据不可为null **/
    MESSAGE_1304("business_message_1304", "1304", "计算商品条件属性中条码表达式key对应的条码数据不可为null"),
    /** 计算商品条件验证 条码表达式key对应的条码数据不合规范 **/
    MESSAGE_1305("business_message_1305", "1305", "计算商品条件属性中条码表达式key对应的条码数据不合规范"),
    /** 计算商品条件验证 单价表达式key数据不合规范 **/
    MESSAGE_1306("business_message_1306", "1306", "计算商品条件属性中单价表达式key数据不合规范"),
    /** 计算商品条件验证 单价表达式key对应的单价数据不可为null **/
    MESSAGE_1307("business_message_1307", "1307", "计算商品条件属性中单价表达式key对应的单价数据不可为null"),
    /** 计算商品条件验证 单价达式key对应的单价数据不合规范 **/
    MESSAGE_1308("business_message_1308", "1308", "计算商品条件属性中单价达式key对应的单价数据不合规范"),
    /** 计算商品条件验证 数量表达式key数据不合规范 **/
    MESSAGE_1309("business_message_1309", "1309", "计算商品条件属性中数量表达式key数据不合规范"),
    /** 计算商品条件验证 数量表达式key对应的数量数据不可为null **/
    MESSAGE_1310("business_message_1310", "1310", "计算商品条件属性中数量表达式key对应的数量数据不可为null"),
    /** 计算商品条件验证 数量表达式key对应的数量数据不合规范 **/
    MESSAGE_1311("business_message_1311", "1311", "计算商品条件属性中数量表达式key对应的数量数据不合规范"),
    /** 计算商品条件验证 数量/金额表达式必须设置一种 **/
    MESSAGE_1312("business_message_1312", "1312", "计算商品条件属性中数量/金额表达式必须设置一种"),
    /** 计算商品条件验证 金额表达式key数据不合规范 **/
    MESSAGE_1313("business_message_1313", "1313", "计算商品条件属性中金额表达式key数据不合规范"),
    /** 计算商品条件验证 金额表达式key对应的金额数据不可为null **/
    MESSAGE_1314("business_message_1314", "1314", "计算商品条件属性中金额表达式key对应的金额数据不可为null"),
    /** 计算商品条件验证 金额表达式key对应的金额数据不合规范 **/
    MESSAGE_1315("business_message_1315", "1315", "计算商品条件属性中金额表达式key对应的金额数据不合规范"),

    /** 当前交易校验 商户编码数据不可为Bank **/
    MESSAGE_1400("business_message_1400", "1400", "当前交易商户编码数据不可为Bank"),
    /** 当前交易校验 商户编码数据不合规范 **/
    MESSAGE_1401("business_message_1401", "1401", "当前交易商户编码数据不合规范"),
    /** 当前交易校验 交易单号数据不可为Bank **/
    MESSAGE_1402("business_message_1402", "1402", "当前交易交易单号数据不可为Bank"),
    /** 当前交易校验 交易单号数据不合规范 **/
    MESSAGE_1403("business_message_1403", "1403", "当前交易交易单号数据不合规范"),

    /** 关联交易校验 商户编码数据不可为Bank **/
    MESSAGE_1500("business_message_1500", "1500", "关联交易商户编码数据不可为Bank"),
    /** 关联前交易校验 商户编码数据不合规范 **/
    MESSAGE_1501("business_message_1501", "1501", "关联交易商户编码数据不合规范"),
    /** 关联前交易校验 交易时间不可为null **/
    MESSAGE_1502("business_message_1502", "1502", "关联交易交易时间不可为null"),
    /** 关联前交易校验 交易总金额数据不可为null **/
    MESSAGE_1503("business_message_1503", "1503", "关联交易交易总金额数据不可为null"),
    /** 关联前交易校验 交易总金额数据不合规范 **/
    MESSAGE_1504("business_message_1504", "1504", "关联交易交易总金额数据不合规范"),

    /** 计算商品验证 计算商品条件对象关联关系不可为Blank **/
    MESSAGE_1600("business_message_1600", "1600", "计算商品属性中计算商品条件对象关联关系不可为Blank"),
    /** 计算商品验证 计算商品条件对象关联关系数据不合规范 **/
    MESSAGE_1601("business_message_1601", "1601", "计算商品属性中计算商品条件对象关联关系数据不合规范"),
    /** 计算商品验证 计算商品条件不可为null **/
    MESSAGE_1602("business_message_1602", "1602", "计算商品属性中计算商品条件不可为null"),
    /** 计算商品验证 计算商品条件数据不可为Empty **/
    MESSAGE_1603("business_message_1603", "1603", "计算商品属性中计算商品条件数据不可为Empty"),

    /** 折扣分组校验 折扣分组编号数据不可为Bank **/
    MESSAGE_1700("business_message_1700", "1700", "折扣分组编号数据不可为Bank"),
    /** 折扣分组校验 折扣分组编号数据不合规范 **/
    MESSAGE_1701("business_message_1701", "1701", "折扣分组编号数据不合规范"),
    /** 折扣分组校验 折扣分组名称数据不合规范 **/
    MESSAGE_1702("business_message_1702", "1702", "折扣分组名称数据不合规范"),
    /** 折扣分组校验 折扣分组计算优先级数据不合规范 **/
    MESSAGE_1703("business_message_1703", "1703", "折扣分组计算优先级数据不合规范"),


    /** 服务器发生错误 **/
    MESSAGE_9999("business_message_9999", "9999", "Service error!");

    /**
     * 国际化code
     */
    private String languageCode;
    /**
     * 返回代碼
     */
    private String responseCode;
    /**
     * 默认消息
     */
    private String defaultMsg;

    /**
     * 无参构造
     */
    private BusinessMessageEnum() {}

    /**
     * 有参构造
     * @param languageCode          国际化语言代码
     * @param responseCode          国际化消息code
     * @param defaultMsg            国际化默认消息
     */
    private BusinessMessageEnum(String languageCode, String responseCode, String defaultMsg) {
        this.languageCode = languageCode;
        this.responseCode = responseCode;
        this.defaultMsg = defaultMsg;
    }

    @Override
    public String toString(){
        return "{\"languageCode\":"+languageCode+",\"responseCode\":"+responseCode+",\"defaultMsg\":"+defaultMsg+"}";
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDefaultMsg() {
        return defaultMsg;
    }

    public void setDefaultMsg(String defaultMsg) {
        this.defaultMsg = defaultMsg;
    }
}
