package com.dia.ordinary.tool;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 开发公司：青岛海豚数据技术有限公司
 * 版权：青岛海豚数据技术有限公司
 * <p>
 * ObjectTransformation
 *
 * @author 刘志强
 * @created Create Time: 2019/1/28
 */
public class ObjectTransformation {


    /**
     *  对象或字符串转map
     * @param object
     * @return
     */
    public static Map<String, Object> toMap(Object object) {
        Map<String, Object> data = JSONObject.fromObject(object);
        return data;
    }
    /**
     * 对象转map
     * @param o
     * @param isNull 为true时 null值不要 false时 null值转换为 暂无此项数据
     * @return
     */
    public static Map<String,Object> getFiledsInfo(Object o, boolean isNull){
        List<Field> fieldList = new ArrayList<Field>() ;
        Class tempClass = o.getClass();
        //当父类为null的时候说明到达了最上层的父类(Object类).
        while (tempClass != null) {
            fieldList.addAll(Arrays.asList(tempClass .getDeclaredFields()));
            //得到父类,然后赋给自己
            tempClass = tempClass.getSuperclass();
        }
        Map infoMap=new HashMap();
        for(int i=0;i<fieldList.size();i++){
            Object value = getFieldValueByName(fieldList.get(i).getName(), o);
            if (StringUtils.equals(fieldList.get(i).getType().toString(),"class java.util.Date")){
                if (value != null && !StringUtils.equals(value.toString(),"")) {
                    value = DateUtils.formatDate((Date) value, null);
                }
            }
            if (isNull) {
                if (value != null) {
                    infoMap.put(fieldList.get(i).getName(), value);
                }
            } else {
                infoMap.put(fieldList.get(i).getName(), value != null ? value : "暂无此项数据");
            }
        }
        return infoMap;
    }


    // 取出类的属性名
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o, new Object[] {});
            return value;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}