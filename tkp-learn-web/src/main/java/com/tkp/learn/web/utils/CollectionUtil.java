package com.tkp.learn.web.utils;

import java.util.List;

/**
 * author: itw_lixg01
 * Date: 2020/2/24 13:24
 * Description：集合工具类
 */
public class CollectionUtil<T> {

    private CollectionUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> String toString(List<T> list) {
        if (list == null) {
            return null;
        }

        StringBuffer result = new StringBuffer("[");
        for (int i = 0; i < list.size(); i++) {
            T t = list.get(i);
            if (i > 0) {
                result.append(", ");
            }
            result.append(t == null ? null : t);
        }
        result.append("]");

        return result.toString();
    }
}
