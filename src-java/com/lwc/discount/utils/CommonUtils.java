package com.lwc.discount.utils;

import com.lwc.discount.enums.DateFormatTypeEnum;
import com.lwc.discount.enums.ExpressionKeyEnum;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.zip.CRC32;

/**
 * 公共工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class CommonUtils {
    private static Logger log = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 获取32位随机字串<br>
     * 通过【UUID】获取字串
     * @return 返回32位长度的随机字串
     */
    public static String getRandomString32(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 获取32位随机数字字串<br>
     * 通过【yyyyMMddHHmmss+(uuid转数字)+随机数】获取字串
     * @return 返回32位长度的随机数字字串
     */
    public static String getRandomNumber32(){
        int length = CommonUtils.getRandomString32().length();
        StringBuffer randomStr = new StringBuffer();

        String dateStr = DateUtils.format(new Date(), DateFormatTypeEnum.DATE_TIME3);
        randomStr.append(dateStr);

        CRC32 crc32 = new CRC32();
        crc32.update(UUID.randomUUID().toString().getBytes());
        String uuidNumberStr = String.valueOf(crc32.getValue());
        randomStr.append(uuidNumberStr);

        Random random = new Random();
        int suplus = length - randomStr.length();
        for(int i=1;i<=suplus;i++){
            randomStr.append(String.valueOf(random.nextInt(9)));
        }
        return randomStr.toString();
    }

    /**
     * 判断表达式验证
     * @param srcObj        需要比较的值
     * @param expressionMap 比较的表达式map
     * @return 验证通过返回true 否则返回false
     */
    public static boolean judgementExpression(Object srcObj,Map expressionMap){
        boolean meet = true;
        if(expressionMap!=null){
            Iterator<Map.Entry<Object, Object>> iterator = expressionMap.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<Object, Object> expressionMapEntry = iterator.next();
                Object expression = expressionMapEntry.getKey();
                Object expressionValue = expressionMapEntry.getValue();
                ExpressionKeyEnum expressionKeyEnum = ExpressionKeyEnum.getExpressionKeyEnum(expression.toString());
                if(expressionKeyEnum!=null){
                    try{
                        if(ExpressionKeyEnum.IN==expressionKeyEnum){
                            meet = ArrayUtils.contains(((List)expressionValue).toArray(),srcObj);
                        }else if(ExpressionKeyEnum.NOT_IN==expressionKeyEnum){
                            meet = !ArrayUtils.contains(((List)expressionValue).toArray(),srcObj);
                        }else if(ExpressionKeyEnum.EQ==expressionKeyEnum){
                            if(srcObj instanceof Date){
                                meet = ((Date)srcObj).getTime()==DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),((String)expressionValue)).getTime();
                            }else{
                                meet = srcObj.equals(expressionValue);
                            }
                        }else if(ExpressionKeyEnum.NOT_EQ==expressionKeyEnum){
                            if(srcObj instanceof Date){
                                meet = ((Date)srcObj).getTime()!=DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),((String)expressionValue)).getTime();
                            }else{
                                meet = !srcObj.equals(expressionValue);
                            }
                        }else if(ExpressionKeyEnum.GR==expressionKeyEnum){
                            if(srcObj instanceof Date){
                                meet = ((Date)srcObj).getTime()>DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),((String)expressionValue)).getTime();
                            }else{
                                meet = ((Number)srcObj).doubleValue()>((Number)expressionValue).doubleValue();
                            }
                        }else if(ExpressionKeyEnum.GR_AND_EQ==expressionKeyEnum){
                            if(srcObj instanceof Date){
                                meet = ((Date)srcObj).getTime()>=DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),((String)expressionValue)).getTime();
                            }else{
                                meet = ((Number)srcObj).doubleValue()>=((Number)expressionValue).doubleValue();
                            }
                        }else if(ExpressionKeyEnum.LE==expressionKeyEnum){
                            if(srcObj instanceof Date){
                                meet = ((Date)srcObj).getTime()<DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),((String)expressionValue)).getTime();
                            }else{
                                meet = ((Number)srcObj).doubleValue()<((Number)expressionValue).doubleValue();
                            }
                        }else if(ExpressionKeyEnum.LE_AND_EQ==expressionKeyEnum){
                            if(srcObj instanceof Date){
                                meet = ((Date)srcObj).getTime()<=DateUtils.convertStringTODate(DateUtils.datetimeUtcFmt(),((String)expressionValue)).getTime();
                            }else{
                                meet = ((Number)srcObj).doubleValue()<=((Number)expressionValue).doubleValue();
                            }
                        }else{
                            meet = false;
                        }
                    }catch (Exception e){
                        log.error(e.getMessage(),e);
                        meet = false;
                    }
                }else{
                    meet = false;
                }
                if(!meet){
                    break;
                }
            }
        }
        return meet;
    }

    /**
     * 字符串填充指定字符
     * @param obj           需要补齐的值
     * @param length        需要返回的字符串长度
     * @param padding       指定补充的字符
     * @param isPaddingRight 是否右补 true:右补 false:左补
     * @return 返回补齐的字符
     */
    public static String paddingAssignStr(Object obj, int length, String padding, boolean isPaddingRight){
        String str = "";
        if(obj!=null){ str = obj.toString();}
        StringBuilder stringBuilder = new StringBuilder(str);
        while(stringBuilder.length() < length){
            if(isPaddingRight){
                stringBuilder.append(padding);
            }else{
                stringBuilder.insert(0,padding);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 对象深度复制
     * @param obj   需要复制的对象
     * @param <T>   范型
     * @return 返回被复制的对象
     */
    public static <T> T deepCopy(Object obj){
        try{
            if(obj!=null){
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(obj);

                ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bis);
                return (T)ois.readObject();
            }else{
                return null;
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return null;
        }
    }
}
