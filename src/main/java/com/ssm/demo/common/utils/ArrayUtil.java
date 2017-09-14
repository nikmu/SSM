package com.ssm.demo.common.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

public class ArrayUtil {
	private transient static final String DEFAULT_JOIN_STR = ",";

    /**
     * �����ַ��� �磺[1,2,3,4]�����᷵��"1,2,3,4"
     *
     * @param arr
     *            an array of {@link java.lang.Object} objects.
     * @return a {@link java.lang.String} object.
     */
    public static final String join(final Object[] arr) {
        return join(arr, DEFAULT_JOIN_STR);
    }

    /**
     * �����ַ��� ��: join([1,2,3,4],"x"]) ���᷵��"1x2x3x4"
     *
     * @param arr
     *            an array of {@link java.lang.Object} objects.
     * @param joinStr
     *            a {@link java.lang.String} object.
     * @return a {@link java.lang.String} object.
     */
    public static final String join(final Object[] arr, final String joinStr) {
        if (arr == null || arr.length < 1) {
            return StringUtils.EMPTY;
        }
        final StringBuffer sb = new StringBuffer(String.valueOf(arr[0]));
        for (int i = 1, len = arr.length; i < len; i++) {
            sb.append(StringUtils.isNotEmpty(joinStr) ? joinStr : StringUtils.EMPTY).append(String.valueOf(arr[i]));
        }
        return sb.toString();
    }

    /**
     * ����ת����
     *
     * @param collection
     *            ����
     * @param clazz
     *            ����
     * @return ����
     * @param <T>
     *            a T object.
     */
    @SuppressWarnings("unchecked")
    public static final <T> T[] toArray(final Collection<T> collection, final Class<T> clazz) {
        if (collection == null) {
            return null;
        }
        final T[] arr = (T[]) Array.newInstance(clazz, collection.size());
        return collection.toArray(arr);
    }

    /**
     * ����ת��������
     *
     * @param collection
     *            ����
     * @return ��������
     */
    public static final Object[] toObjectArray(final Collection<?> collection) {
        if (collection == null) {
            return null;
        }
        final Object[] arr = (Object[]) Array.newInstance(Object.class, collection.size());
        int i = 0;
        for (Iterator<?> it = collection.iterator(); it.hasNext();) {
            arr[i++] = it.next();
        }
        return arr;
    }
}
