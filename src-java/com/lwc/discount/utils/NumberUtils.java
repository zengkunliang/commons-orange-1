package com.lwc.discount.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数值工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class NumberUtils {
    /** 默认计算保留精度 **/
    private static final Integer DEFAULT_CALC_SAVE_RATE = 10;

    /**
     * 数值相加
     * @param v1    参与计算值
     * @param v2    参与计算值
     * @return 返回计算值相加的结果
     */
    public static double add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 数值相减
     * @param v1    参与计算值
     * @param v2    参与计算值
     * @return 返回计算值相减的结果
     */
    public static double sub(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 数值相乘
     * @param v1    参与计算值
     * @param v2    参与计算值
     * @return 返回计算值相乘的结果
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 数值相除(保留精度10)
     * @param v1    参与计算值
     * @param v2    参与计算值
     * @return 返回计算值相除的结果
     */
    public static double div(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,NumberUtils.DEFAULT_CALC_SAVE_RATE,BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 格式化小数
     * @param value 参与计算值
     * @return 返回计算值格式化的结果
     */
    private static double fixDecimals(Number value) {
        return Math.rint((value).doubleValue() * 1000000.0) / 1000000.0;
    }

    /**
     * 四舍五入格式化整数
     */
    public static final class FormatInteger extends NumberUtils {
        /**
         * 四舍五入格式化整数
         * @param value     格式化值
         * @return 返回int类型格式化值
         */
        public static Integer formatValue(Number value) {
            String resultStr = FormatInteger.formatValueToString(value);
            if(resultStr!=null){
                return Integer.valueOf(resultStr);
            }
            return null;
        }
        /**
         * 四舍五入格式化整数
         * @param value     格式化值
         * @return 返回string类型格式化值
         */
        public static String formatValueToString(Number value) {
            String pattern = "#0";
            NumberFormat integerFormat = new DecimalFormat(pattern);
            if (value != null) {
                return integerFormat.format(NumberUtils.fixDecimals(value));
            }
            return null;
        }
    }

    /**
     * 四舍五入格式化小数并保留指定精度
     */
    public static final class FormatDouble extends NumberUtils {
        /**
         * 四舍五入格式化小数并保留指定精度
         * @param rounding  保留精度
         * @param value     格式化值
         * @return 返回double类型格式化值
         */
        public static Double formatValue(int rounding,Number value) {
            String resultStr = FormatDouble.formatValueToString(rounding,value);
            if(resultStr!=null){
                return new Double(resultStr);
            }
            return null;
        }

        /**
         * 四舍五入格式化小数并保留指定精度
         * @param rounding  保留精度
         * @param value     格式化值
         * @return 返回string类型格式化值
         */
        public static String formatValueToString(int rounding,Number value) {
            String pattern = CommonUtils.paddingAssignStr("",rounding,"0",false);
            if(!StringUtils.isBlank(pattern)){
                pattern = "#0."+pattern;
            }else{
                pattern = "#0";
            }
            NumberFormat doubleFormat = new DecimalFormat(pattern);
            doubleFormat.setRoundingMode(RoundingMode.HALF_UP);
            if (value != null) {
                return doubleFormat.format(NumberUtils.fixDecimals(value));
            }
            return null;
        }

    }
}
