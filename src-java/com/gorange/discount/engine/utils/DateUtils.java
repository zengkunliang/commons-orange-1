package com.gorange.discount.engine.utils;

import com.gorange.discount.engine.enums.DateFormatTypeEnum;
import com.gorange.discount.engine.enums.WeekTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 时间处理工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class DateUtils {
    private static Logger log = LoggerFactory.getLogger(DateUtils.class);
    /**
     * 北京时区
     */
    public static final String CN_TIME_ZONE = "GMT+08:00";
    /**
     * UTC格式字串常量 Z
     */
    public static final String UTC_CONSTANTS_Z = "Z";
    /**
     * UTC格式字串常量 "UTC"
     */
    public static final String UTC_CONSTANTS_UTC = " UTC";
    /**
     * 毫秒
     */
    public static final Long MILLISECONDS = 1000L;
    /**
     * 一分钟毫秒数
     */
    public static final Long MILLISECONDS_OF_MINUTE = 60 * MILLISECONDS;
    /**
     * 一小时毫秒数
     */
    public static final Long MILLISECONDS_OF_HOUR = 60 * MILLISECONDS_OF_MINUTE;
    /**
     * 一天毫秒数
     */
    public static final Long MILLISECONDS_OF_DAY = 24 * MILLISECONDS_OF_HOUR;
    /**
     * 日期开始时间
     */
    public static final Integer START_TIME_FOR_DAY = 000000;
    /**
     * 日期结束时间
     */
    public static final Integer END_TIME_FOR_DAY = 235959;

    /**
     * 获取日期格式化对象<br>
     * 格式规则【yyyy-MM-dd】
     * @return 返回日期格式化对象
     */
    public static DateFormat dateFmt() { return new SimpleDateFormat("yyyy-MM-dd");}

    /**
     * 获取日期格式化对象<br>
     * 格式规则【yyyy-MM】
     * @return 返回日期格式化对象
     */
    public static DateFormat date2Fmt() { return new SimpleDateFormat("yyyy-MM");}

    /**
     * 获取日期格式化对象<br>
     * 格式规则【MM-dd】
     * @return 返回日期格式化对象
     */
    public static DateFormat date3Fmt() { return new SimpleDateFormat("MM-dd");}

    /**
     * 获取日期格式化对象<br>
     * 格式规则【yyyy-MM-dd E】
     * @return 返回日期格式化对象
     */
    public static DateFormat dateWeekFmt() {
        return new SimpleDateFormat("yyyy-MM-dd E");
    }

    /**
     * 获取日期格式化对象<br>
     * 格式规则【yyyy-MM-dd HH:mm:ss】
     * @return 返回日期格式化对象
     */
    public static DateFormat datetimeFmt() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取日期格式化对象<br>
     * 格式规则【yyyy-MM-dd HH:mm】
     * @return 返回日期格式化对象
     */
    public static DateFormat datetime2Fmt() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    /**
     * 获取日期格式化对象<br>
     * 格式规则【yyyyMMddHHmmss】
     * @return 返回日期格式化对象
     */
    public static DateFormat datetime3Fmt() { return new SimpleDateFormat("yyyyMMddHHmmss"); }

    /**
     * 获取日期格式化对象<br>
     * 格式规则【yyyy-MM-dd HH:mm:ss:SSS】
     * @return 返回日期格式化对象
     */
    public static DateFormat datetime4Fmt() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
    }

    /**
     * 获取日期格式化对象<br>
     * 格式规则【yyyy-MM-dd'T'HH:mm:ss Z】
     * @return 返回日期格式化对象
     */
    public static DateFormat datetimeUtcFmt() { return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss Z");}

    /**
     * 获取时间格式化对象<br>
     * 格式规则【HH:mm:ss】
     * @return 返回时间格式化对象
     */
    public static DateFormat timeFmt() { return new SimpleDateFormat("HH:mm:ss"); }

    /**
     * 获取时间格式化对象<br>
     * 格式规则【HH:mm】
     * @return 返回时间格式化对象
     */
    public static DateFormat time2Fmt() { return new SimpleDateFormat("HH:mm"); }

    /**
     * 转换日期为字符串
     * @param date 需要转换的日期
     * @param type 转换日期类型
     * @return 返回转换后的字符串
     */
    public static String format(Date date, DateFormatTypeEnum type) {
        if(date!=null){
            if (DateFormatTypeEnum.DATE.equals(type)) {
                return dateFmt().format(date);
            }
            if (DateFormatTypeEnum.DATE3.equals(type)) {
                return date3Fmt().format(date);
            }
            if (DateFormatTypeEnum.DATE2.equals(type)) {
                return date2Fmt().format(date);
            }
            if (DateFormatTypeEnum.DATE_WEEK.equals(type)) {
                return dateWeekFmt().format(date);
            }
            if (DateFormatTypeEnum.DATE_TIME.equals(type)) {
                return datetimeFmt().format(date);
            }
            if (DateFormatTypeEnum.DATE_TIME2.equals(type)) {
                return datetime2Fmt().format(date);
            }
            if (DateFormatTypeEnum.DATE_TIME3.equals(type)) {
                return datetime3Fmt().format(date);
            }
            if (DateFormatTypeEnum.DATE_TIME4.equals(type)) {
                return datetime4Fmt().format(date);
            }
            if (DateFormatTypeEnum.DATE_TIME_UTC.equals(type)) {
                DateFormat simpleDateFormat = datetimeUtcFmt();
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
                return simpleDateFormat.format(date).replace("+0000","Z");
            }
            if (DateFormatTypeEnum.TIME.equals(type)) {
                return timeFmt().format(date);
            }
            if (DateFormatTypeEnum.TIME2.equals(type)) {
                return time2Fmt().format(date);
            }
        }
        return null;
    }

    /**
     * 将时间转换成指定时区时间字串(指定格式)
     * @param timeZone  时区代码
     * @param date      指定时间
     * @param type      格式化类型
     * @return  返回转换后的字符串
     */
    public static String getAppointTimeZoneDateStr(String timeZone,Date date,DateFormatTypeEnum type) {
        DateFormat simpleDateFormat = null;
        if(date!=null){
            if (DateFormatTypeEnum.DATE.equals(type)) {
                simpleDateFormat = dateFmt();
            }
            if (DateFormatTypeEnum.DATE3.equals(type)) {
                simpleDateFormat = date3Fmt();
            }
            if (DateFormatTypeEnum.DATE2.equals(type)) {
                simpleDateFormat = date2Fmt();
            }
            if (DateFormatTypeEnum.DATE_WEEK.equals(type)) {
                simpleDateFormat = dateWeekFmt();
            }
            if (DateFormatTypeEnum.DATE_TIME.equals(type)) {
                simpleDateFormat = datetimeFmt();
            }
            if (DateFormatTypeEnum.DATE_TIME2.equals(type)) {
                simpleDateFormat = datetime2Fmt();
            }
            if (DateFormatTypeEnum.DATE_TIME3.equals(type)) {
                simpleDateFormat = datetime3Fmt();
            }
            if (DateFormatTypeEnum.DATE_TIME4.equals(type)) {
                simpleDateFormat = datetime4Fmt();
            }
            if (DateFormatTypeEnum.DATE_TIME_UTC.equals(type)) {
                simpleDateFormat = datetimeUtcFmt();
            }
            if (DateFormatTypeEnum.TIME.equals(type)) {
                simpleDateFormat = timeFmt();
            }
            if (DateFormatTypeEnum.TIME2.equals(type)) {
                simpleDateFormat = time2Fmt();
            }
        }
        if(simpleDateFormat!=null){
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
            return simpleDateFormat.format(date);
        }else{
            return null;
        }
    }

    /**
     * 当前系统时间增加一天
     * @return 返回当前系统时间增加一天后的Calendar
     */
    public static Calendar upOneDay() {
        return DateUtils.upAssignTime(DateUtils.MILLISECONDS_OF_DAY);
    }

    /**
     * 当前时间增加指定时间
     * @param time  指定时间(毫秒)
     * @return 返回当前系统时间增加指定毫秒后的Calendar
     */
    public static Calendar upAssignTime(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cal.getTimeInMillis()+time);
        return cal;
    }

    /**
     * 当前时间减少一天
     * @return 返回当前系统时间减少一天后的Calendar
     */
    public static Calendar downOneDay() {
        return DateUtils.downAssignTime(DateUtils.MILLISECONDS_OF_DAY);
    }

    /**
     * 当前时间减少指定时间
     * @param time  指定时间(毫秒)
     * @return 返回当前系统时间减少指定毫秒后的Calendar对象
     */
    public static Calendar downAssignTime(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(cal.getTimeInMillis() - time);
        return cal;
    }

    /**
     * 当前时间减少指定天数
     * @param day 减少天数
     * @return 返回当前系统时间减少指定天数后的Calendar对象
     */
    public static Calendar downAssignDay(int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR,cal.get(Calendar.DAY_OF_YEAR)-day);
        return cal;
    }

    /**
     * 当前时间减少指定月份
     * @param month 减少月份
     * @return 返回当前系统时间减少指定月份后的Calendar对象
     */
    public static Calendar downAssignMonth(int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH,cal.get(Calendar.MONTH)-month);
        return cal;
    }

    /**
     * Calendar时,分,秒,毫秒重置到0
     * @param cal 指定Calendar
     * @return 返回重置的Calendar
     */
    public static Calendar trim(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * 设置固定时间
     * @param date 需要设置的日期
     * @param hour 指定小时
     * @param minute 指定分钟
     * @param second 指定秒钟
     * @param milliSecond 指定毫秒
     * @return 返回设定固定时间的Calendar
     */
    public static Calendar setDateTime(Date date, int hour, int minute, int second, int milliSecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, milliSecond);
        return cal;
    }

    /**
     * 获取日期所在星期几
     * @param timeZone  指定时区
     * @param date      指定日期
     * @return 返回当前时间所属的星期(星期枚举类)
     */
    public static WeekTypeEnum getWeekTypeEnum(String timeZone,Date date){
        WeekTypeEnum weekType;
        int week = getWeekIndex(timeZone,date);
        switch(week){
            case 0:weekType = WeekTypeEnum.SUN;break;
            case 1:weekType = WeekTypeEnum.MON;break;
            case 2:weekType = WeekTypeEnum.TUE;break;
            case 3:weekType = WeekTypeEnum.WED;break;
            case 4:weekType = WeekTypeEnum.THU;break;
            case 5:weekType = WeekTypeEnum.FRI;break;
            case 6:weekType = WeekTypeEnum.SAT;break;
            default: weekType = null;
        }
        return weekType;
    }

    /**
     * 获取日期所在星期几(星期天-星期六:0-6)
     * @param timeZone  指定时区
     * @param date      指定日期
     * @return 返回当前时间所属的星期(星期下标)
     */
    public static int getWeekIndex(String timeZone,Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.setTimeZone(TimeZone.getTimeZone(timeZone));
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0){ week = 0; }
        return week ;
    }

    /**
     * 获取指定时间距离该时间0点0分0秒多长(取正整数)
     * @param date 指定的时间
     * @return  返回时间相差的毫秒数
     */
    public static long getTimeMillisOfDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long t1 = cal.getTimeInMillis();
        DateUtils.trim(cal);
        long t2 = cal.getTimeInMillis();
        return Math.abs(t1 - t2);
    }

    /**
     * 转换字符串为日期
     * utc类型日期请把Z替换成UTC在调用该函数,
     * 例:String dateStr = "1979-06-04T16:00:00Z",convertStringTODate(datetimeUtcFmt(),dateStr.replace("Z", " UTC"));
     * @param sdf       转换format对象
     * @param dateStr   转换类型
     * @return 返回转换后的日期对象 发生异常则返回null
     */
    public static Date convertStringTODate(DateFormat sdf, String dateStr) {
        try {
            String newDateStr = dateStr;
            if(!StringUtils.isBlank(dateStr)&&dateStr.contains(UTC_CONSTANTS_Z)){
                newDateStr = dateStr.replace(UTC_CONSTANTS_Z, UTC_CONSTANTS_UTC);
            }
            return sdf.parse(newDateStr);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 获取时间间隔天数
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 返回两个时间相隔的天数 发生异常时返回null
     */
    public static Long compareDateIntervalDays(Date startDate,Date endDate){
        try{
            Date newStartDate=dateFmt().parse(dateFmt().format(startDate));
            Date newEndDate=dateFmt().parse(dateFmt().format(endDate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(newStartDate);
            long time1 = cal.getTimeInMillis ();
            cal.setTime(newEndDate);
            long time2 = cal.getTimeInMillis ();
            return (time2-time1)/(MILLISECONDS_OF_DAY);
        }catch(Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }

    /**
     * 比较是否在开始日期和结束日期之间
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param date      需要比较的日期
     * @return 如果处于日期区间则返回true 否则返回false
     */
    public static boolean isBetweenDateStartAndEnd(Date startDate,Date endDate,Date date){
        boolean isBetween = false;
        try{
            if(startDate.getTime()<=date.getTime()&&date.getTime()<=endDate.getTime()){
                isBetween = true;
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            isBetween = false;
        }
        return isBetween;
    }

    /**
     * 比较是否在开始时间和结束时间之间
     * @param startTime 开始时间(HH:mm:ss)
     * @param endTime   结束时间(HH:mm:ss)
     * @param date      需要比较的时间
     * @return 如果处于时间区间则返回true 否则返回false
     */
    public static boolean isBetweenTimeStartAndEnd(String startTime,String endTime,Date date){
        boolean isBetween = false;
        try{
            Date startDate = convertStringTODate(timeFmt(),startTime);
            Date endDate = convertStringTODate(timeFmt(),endTime);
            Date newDate = convertStringTODate(timeFmt(),format(date, DateFormatTypeEnum.TIME));
            if(startDate.getTime()<=newDate.getTime()&&newDate.getTime()<=endDate.getTime()){
                isBetween = true;
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            isBetween = false;
        }
        return isBetween;
    }

    /**
     * 比较是否在开始时间和结束时间之间
     * @param startTime 开始时间(HH:mm)
     * @param endTime   结束时间(HH:mm)
     * @param date      需要比较的时间
     * @return 如果处于时间区间则返回true 否则返回false
     */
    public static boolean isBetweenTime2StartAndEnd(String startTime,String endTime,Date date){
        boolean isBetween = false;
        try{
            Date startDate = convertStringTODate(time2Fmt(),startTime);
            Date endDate = convertStringTODate(time2Fmt(),endTime);
            Date newDate = convertStringTODate(time2Fmt(),format(date, DateFormatTypeEnum.TIME2));
            if(startDate.getTime()<=newDate.getTime()&&newDate.getTime()<=endDate.getTime()){
                isBetween = true;
            }
        }catch(Exception e){
            log.error(e.getMessage(),e);
            isBetween = false;
        }
        return isBetween;
    }
}
