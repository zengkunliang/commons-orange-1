package com.gorange.discount.engine.utils;


import com.gorange.discount.engine.enums.BusinessMessageEnum;
import com.gorange.discount.engine.model.discount.business.result.GoodsDiscountResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 国际化工具类
 * @author zengkunliang
 * @version 1.0.0
 * @since 1.0.0
 */
public class I18nUtils {
    private static Logger log = LoggerFactory.getLogger(I18nUtils.class);
    /**
     * 国际化语言代码
     */
    public static final String DEFAULT_LANGUAGE_CODE = "zh_CN";
    /**
     * 国际化语言配置缓存
     */
    private static Map<String, Map<String, String>> LANGUAGE_CACHE = new HashMap<>();

    static {
        I18nUtils.cacheLanguageMapping();
    }

    /**
     * 获取国际化文本
     * @param languageCode          国际化语言代码
     * @param businessMessageEnum   业务处理消息枚举类
     * @return 返回国际化文本
     */
    public static String getI18NMessage(String languageCode, BusinessMessageEnum businessMessageEnum) {
        String message = businessMessageEnum.getDefaultMsg();
        Iterator<Map.Entry<String,Map<String, String>>> iterator = I18nUtils.LANGUAGE_CACHE.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,Map<String,String>> itemMap = iterator.next();
            String code = itemMap.getKey();
            if(languageCode.equals(code)){
                Map<String,String> mappingMap = itemMap.getValue();
                if(mappingMap.containsKey(businessMessageEnum.getLanguageCode())){
                    return mappingMap.get(businessMessageEnum.getLanguageCode());
                }
                break;
            }
        }
        return message;
    }

    /**
     * 缓存国际化语言配置
     */
    public static void cacheLanguageMapping(){
        log.debug("缓存国际化语言配置");
        Set<String> languageKeySet = new HashSet<>();
        Locale[] localeList = Locale.getAvailableLocales();
        for (Locale locale : localeList) {
            String languageKey = locale.getLanguage()+"_"+locale.getCountry();
            if(!languageKeySet.contains(languageKey)&&!StringUtils.isBlank(locale.getLanguage())&&!StringUtils.isBlank(locale.getCountry())){
                languageKeySet.add(languageKey);
                ResourceBundle bundle = ResourceBundle.getBundle("message" , new Locale(locale.getLanguage(),locale.getCountry(),""));
                if(bundle!=null&&bundle.keySet()!=null&&!bundle.keySet().isEmpty()){
                    bundle.keySet().forEach(key -> {
                        try{
                            String message = new String(bundle.getString(key).getBytes("ISO-8859-1"), "UTF8");
                            if (!I18nUtils.LANGUAGE_CACHE.containsKey(languageKey)) {
                                Map<String,String> infoMap = new LinkedHashMap<>();
                                infoMap.put(key, message);
                                I18nUtils.LANGUAGE_CACHE.put(languageKey, infoMap);
                            }else{
                                I18nUtils.LANGUAGE_CACHE.get(languageKey).put(key, message);
                            }
                        }catch (Exception e){
                            log.error(e.getMessage(),e);
                        }
                    });
                }
            }
        }
    }
}
