package cn.sit.edu.cs.exam.pol.web.utils;

import java.lang.reflect.Field;

/**
 * Created by lautner on 6/28/15.
 */
public class CommonUtils {
    /**
     * 用于两个对象之间相同字段的赋值
     * 需要字段名称相同，字段类型相同
     *
     * @author lautner on 6/28/15.
     * @param resourceObject 数据源对象
     * @param targetObject 需要赋值的对象
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static void copyProperty(Object resourceObject, Object targetObject) throws SecurityException, IllegalArgumentException, IllegalAccessException{
        //新的class
        Class resourceClass = resourceObject.getClass();
        //老的class
        Class targetClass = targetObject.getClass();
        //该类所有的属性
        Field[] resourceFields = resourceClass.getDeclaredFields();
        //新的属性
        Field resourceField = null;
        //老的属性
        Field targetField = null;
        for(Field f : resourceFields){
            //类中的属性名称
            String fieldName = f.getName();
            //通过属性名称获取属性
            try {
                resourceField = resourceClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                continue;
            }
            //获取属性的值时需要设置为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
            //值为 false 则指示反射的对象应该实施 Java 语言访问检查。
            resourceField.setAccessible(true);

            //根据属性获取对象上的值
            Object resourceFieldValue = resourceField.get(resourceObject);

            try {
                targetField = targetClass.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                continue;
            }
            targetField.setAccessible(true);

            //如果两个字段类型不一样，跳过
            if(!resourceField.getType().getName().equals(targetField.getType().getName())){
                continue;
            }

            targetField.set(targetObject, resourceFieldValue);
        }
    }

    /**
     *  字段验证，是否跳出本次循环
     * @author lautner on 6/28/15.
     * @param object
     * @return true 是 有null或者默认值
     *         false 否 有默认值
     */
    private static boolean validate(Object object){
        return object == null;
    }

}