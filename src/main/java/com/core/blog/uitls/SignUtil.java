package com.core.blog.uitls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SignUtil {
    private static Logger logger = LoggerFactory.getLogger(SignUtil.class);
    public static Map<String, Object> objectToMap(Object obj,String ...excludeFields) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            boolean b = false;
            for(String fieldName : excludeFields){
                if(fieldName.equals(field.getName())){
                    b = true;
                }
            }
            if(!b){
                try {
                    if(field.get(obj) != null){
                        map.put(field.getName(), field.get(obj));
                    }
                }catch (Exception e){
                    logger.error(e.getMessage());
                }
            }
        }
        return map;
    }

    public static String formatToSignStr(Map<String,Object> signMap){
        if(signMap == null || signMap.isEmpty()){
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        //自然顺序排序
        Map<String, Object> treeMap = sortMapByKey(signMap);
        int i = 0;
        for(String key : treeMap.keySet()){
            if(i != 0){
                stringBuffer.append("&");
            }
            stringBuffer.append(key).append("=").append(treeMap.get(key));
            i++;
        }
        return stringBuffer.toString();
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }

    public static class MapKeyComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}
