package com.ssm.demo.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

public class CollectionUtil {
	private static transient final String DEFAULT_SPLIT_STR = ",";

	/**
	 * ����ת�б�
	 *
	 * @param arr
	 *            an array of T objects.
	 * @param <T>
	 *            a T object.
	 * @return a {@link java.util.List} object.
	 */
	public static final <T> List<T> array2List(T[] arr) {
		if (ArrayUtils.isEmpty(arr)) {
			return null;
		}
		return Arrays.asList(arr);
	}

	/**
	 * ����תSET
	 *
	 * @param arr
	 *            an array of T objects.
	 * @param <T>
	 *            a T object.
	 * @return a {@link java.util.Set} object.
	 */
	public static final <T> Set<T> array2Set(T[] arr) {
		if (ArrayUtils.isEmpty(arr)) {
			return null;
		}
		return new LinkedHashSet<T>(Arrays.asList(arr));
	}

	/**
	 * �ϲ�����
	 *
	 * @param collections
	 *            a {@link java.util.Collection} object.
	 * @param <T>
	 *            a T object.
	 * @return a {@link java.util.List} object.
	 */
	@SuppressWarnings("unchecked")
	public static final <T> List<T> collections2List(Collection<T>... collections) {
		if (ArrayUtils.isEmpty(collections)) {
			return null;
		}

		final List<T> li = new ArrayList<T>();
		for (Collection<T> foo : collections) {
			if (CollectionUtils.isNotEmpty(foo)) {
				li.addAll(foo);
			}
		}
		return li;
	}

	/**
	 * �ϲ�����
	 *
	 * @param collections
	 *            a {@link java.util.Collection} object.
	 * @param <T>
	 *            a T object.
	 * @return a {@link java.util.Set} object.
	 */
	@SuppressWarnings("unchecked")
	public static final <T> Set<T> collections2Set(Collection<T>... collections) {
		if (ArrayUtils.isEmpty(collections)) {
			return null;
		}
		final Set<T> set = new LinkedHashSet<T>();
		for (Collection<T> foo : collections) {
			if (CollectionUtils.isNotEmpty(foo)) {
				set.addAll(foo);
			}
		}
		return set;
	}

	/**
	 * ƴ�Ӽ����ַ���
	 *
	 * @param collection
	 *            a {@link java.util.Collection} object.
	 * @param joinStr
	 *            a {@link java.lang.String} object.
	 * @return a {@link java.lang.String} object.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final String join(final Collection collection, final String joinStr) {
		if (CollectionUtils.isEmpty(collection)) {
			return "";
		}
		if (joinStr == null) {
			throw new IllegalArgumentException("join string is null.");
		}

		Object[] arr = ArrayUtil.toArray(collection, Object.class);
		return ArrayUtil.join(arr, joinStr);
	}

	/**
	 * ƴ�Ӽ����ַ���
	 *
	 * @param collection
	 *            a {@link java.util.Collection} object.
	 * @return a {@link java.lang.String} object.
	 */
	@SuppressWarnings("rawtypes")
	public static final String join(final Collection collection) {
		return join(collection, DEFAULT_SPLIT_STR);
	}

	/**
	 * ����ѿ�����
	 *
	 * @param crossArgs
	 *            a {@link java.util.List} object.
	 * @param <T>
	 *            a T object.
	 * @return a {@link java.util.List} object.
	 */
	public static <T> List<List<T>> decartes(List<List<T>> crossArgs) {

		// ������ѿ���������
		int rows = crossArgs.size() > 0 ? 1 : 0;

		for (List<T> data : crossArgs) {
			rows *= data.size();
		}

		// �ѿ�����������¼
		int[] record = new int[crossArgs.size()];

		List<List<T>> results = new ArrayList<List<T>>();

		// �����ѿ�����
		for (int i = 0; i < rows; i++) {
			List<T> row = new ArrayList<T>();

			// ���ɵѿ�������ÿ������
			for (int index = 0; index < record.length; index++) {
				row.add(crossArgs.get(index).get(record[index]));
			}

			results.add(row);
			crossRecord(crossArgs, record, crossArgs.size() - 1);
		}

		return results;
	}

	/**
	 * @param sourceArgs
	 * @param record
	 * @param level
	 */
	private static <T> void crossRecord(List<List<T>> sourceArgs, int[] record, int level) {
		record[level] = record[level] + 1;

		if (record[level] >= sourceArgs.get(level).size() && level > 0) {
			record[level] = 0;
			crossRecord(sourceArgs, record, level - 1);
		}
	}

	/**
	 * ��list��ȡ��ĳ������
	 * 
	 * @param <T>
	 * @param datas
	 * @param page
	 * @param PAGESIZE
	 * @return
	 */
	public static <T> List<T> getLimit(List<T> datas, int begin, int end) {
		List<T> objects = new ArrayList<T>();
		if (datas.size() <= begin) {
			return objects;
		}
		int temp = (datas.size() > end) ? end : datas.size();
		for (int i = begin; i < temp; i++) {
			objects.add(datas.get(i));
		}
		return objects;
	}
}
