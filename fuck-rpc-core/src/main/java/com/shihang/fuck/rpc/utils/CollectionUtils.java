package com.shihang.fuck.rpc.utils;

import java.util.*;

/**
 * 集合工具类
 *
 * @author chenfuqian
 * @since 1.0.0
 */
public abstract class CollectionUtils {

    private CollectionUtils(){}

    /**
     * 判断一个集合是否为空
     *
     * @param collection 集合
     * @return
     */
    public static boolean isEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断一个数组是否为空
     *
     * @param obj 数组
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] obj){
        return obj == null || obj.length == 0;
    }


    /**
     * 判断一个map是否为空
     *
     * @param map
     * @return true  如果map为null或者是空
     */
    public static boolean isEmpty(Map<?, ?> map){
        return map == null || map.isEmpty();
    }

    /**
     * 将一个集合转换为List
     *
     * @param collection 集合
     * @return List
     */
    public static List<?> asList(Collection<?> collection){
        return new ArrayList<>(collection);
    }

    /**
     * 获取集合的第一个元素
     *
     * @param collection  集合
     * @param <E>
     * @return
     */
    public static <E> E firstElement(Collection<E> collection){
        if(isEmpty(collection)){
            return null;
        } else {
            return collection.iterator().next();
        }
    }

    /**
     * 给定index，获取对象
     * 如果集合的对象
     * @param collection 集合
     * @param index
     * @return  对象
     */
    public static <E> E indexedElement(Collection<E> collection, int index) {
        if(isEmpty(collection)){
            return null;
        } else if(index >= collection.size()){
            return lastElement(collection);
        } else if(index <= 0){
            return firstElement(collection);
        } else {
            E t = null;
            Iterator<E> iterator =  collection.iterator();
            int count = 0;
            while(iterator.hasNext()){
                if(count > index){
                    break;
                }
                t = iterator.next();
                count++;
            }
            return t;
        }
    }


    /**
     * 给定集合， 获取最后一个元素
     * @param collection 集合
     * @param <E>
     * @return 最后一个元素
     */
    public static <E> E lastElement(Collection<E> collection){
        if(isEmpty(collection)){
            return null;
        } else {
            E t = null;
            Iterator<E> iterator =  collection.iterator();
            while(iterator.hasNext()){
                t = iterator.next();
            }
            return t;
        }
    }

    /**
     * 判断一个数组是否为null或者是size==0
     * @param array
     * @param <T>
     * @return
     */
    public  static <T> boolean isEmptyArray(T[] array){
        return array == null || array.length == 0;
    }

}
