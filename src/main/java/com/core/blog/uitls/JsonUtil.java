package com.core.blog.uitls;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.apache.commons.lang.StringUtils;


import java.util.*;

/**
 * 包名：com.bfdl.order.util.
 * 版权：Copyright XXX. All Rights Reserved.
 * 描述详情：
 * 创建者： sage.
 * 创建时间：2018/7/27-11:48.
 * <p>
 * 修改者：sage.
 * 修改时间：2018/7/27-11:48.
 * 修改原因：
 * 修改内容：
 */
public class JsonUtil {
    /**
     * 该方法功能详细描述：过滤空值
     *
     * @param
     * @return 创建人：sage
     * 创建时间：2018/8/3-9:21
     * 修改人：sage
     * 修改时间：2018/8/3-9:21
     * 修改内容：
     */
    private static ValueFilter filter = new ValueFilter() {

        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null) {
                /*if (v instanceof List){
                    return List.class;
                }else if (v instanceof Map){
                    return Map.class;
                }else if (v instanceof ArrayList){
                    return ArrayList.class;
                }else if (v instanceof Arrays){
                    return Arrays.class;
                }else if (v instanceof HashMap){
                    return HashMap.class;
                }else {
                    return "";
                }*/
                return "";
            }
            return v;
        }
    };

    /**
     * 将对象转换成json
     * 创建人：ystwo
     * 创建时间：2017/4/10 0010-20:35
     *
     * @param obj
     * @return String
     */
    public static String aliToJson(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * 将json字符串转换对象
     * 创建人：ystwo
     * 创建时间：2017/4/10 0010-20:35
     *
     * @param jsonStr json串
     * @param cls     转换后的对象Class
     * @return T
     */
    public static <T> T aliToBean(String jsonStr, Class<T> cls) {
        return JSON.parseObject(jsonStr, cls);
    }

    /**
     * 将json串转换成Map
     * 创建人：ystwo
     * 创建时间：2017/4/10 0010-20:36
     *
     * @param
     * @return
     */
    public static Map aliToMap(String jsonStr) {
        return JSON.parseObject(jsonStr);
    }


    /**
     * 〈一句话简述alibabatoBeanList的作用〉
     * 该方法功能详细描述：将一个json集合转成相对应的List<class>的集合
     *
     * @param jsonString, cls]
     * @return java.util.List<T>
     * 创建人：ystwo
     * 创建时间：2017/4/18-14:26
     * 修改人：ystwo
     * 修改时间：2017/4/18-14:26
     * 修改内容：
     */
    public static <T> List<T> alibabatoBeanList(String jsonString, Class<T> cls) {
        List<T> list = JSON.parseArray(jsonString, cls);
        //List<T> list = jsonArray.toJavaList(cls);
        return list;
    }

    /**
     * 该方法功能详细描述：将jsonArray 转成List集合 该方法已作废，请使用alibabatoBeanList方法
     *
     * @param object, cls]
     * @return java.util.List<T>
     * 创建人：ystwo
     * 创建时间：2017/5/8-10:24
     * 修改人：ystwo
     * 修改时间：2017/5/8-10:24
     * 修改内容：
     */
    public static <T> List<T> objectToBeanList(Object object, Class<T> cls) {
        if (object == null) {
            return null;
        }
        String json = JSON.toJSONString(object);

        List<T> list = JSON.parseArray(json, cls);
        return list;
    }

    public static <T> T objectToBean(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        String json = JSON.toJSONString(source);
        return aliToBean(json, target);
    }

    /**
     * 该方法功能详细描述： json 转成 TreeMap 类型,sort 排序方法，传空值或asc 则表示 升序，传desc 则表示 降序
     *
     * @param json
     * @return java.util.Map
     * 创建人：ystwo
     * 创建时间：2017/9/18-13:39
     * 修改人：ystwo
     * 修改时间：2017/9/18-13:39
     * 修改内容：
     */
    public static TreeMap toTreeMap(String json, String sort) {
        TreeMap map = null;
        if (StringUtils.isNotEmpty(sort)) {
            //如果不为空并且sort = desc  则表示降序排序
            map = new TreeMap(new MapKeyComparator("DESC"));
        } else {
            //升序
            map = new TreeMap(new MapKeyComparator(""));
        }

        Map object = aliToMap(json);
        if (object != null) {
            Iterator itr = object.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry entry = (Map.Entry) itr.next();
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public static class MapKeyComparator implements Comparator<String> {
        String sort = "";

        public MapKeyComparator(String sort) {
            this.sort = sort;
        }

        @Override
        public int compare(String str1, String str2) {
            if (StringUtils.isNotEmpty(sort)) {
                //降序
                return str2.compareTo(str1);
            } else {
                //升序
                return str1.compareTo(str2);
            }
        }
    }


    /**
     * 将json串转换成集合
     * 创建人：hebl
     * 创建时间：2018/8/1 11:37
     *
     * @param jsonStr
     * @return List
     */
    public static List aliToList(String jsonStr) {
        return JSON.parseArray(jsonStr, Object.class);
    }


    /**
     * 将对象转换成json
     * 创建人：ystwo
     * 创建时间：2017/4/10 0010-20:35
     *
     * @param obj
     * @return String
     */
    @Deprecated
    public static String zuofeiAliToJson(Object obj) {
        return JSON.toJSONString(obj, filter);
    }

}
