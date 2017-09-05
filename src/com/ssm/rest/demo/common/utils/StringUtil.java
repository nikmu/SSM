package com.ssm.rest.demo.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 *   
 * @version 1.0  
 *
 */
public class StringUtil {

	/**
	 * 用户名长度
	 */
	public static final Integer USERNAMELENGTH = 1;
	/**
	 * 用户名格式
	 */
	public static final Integer USERNAMEFORMAT = 2;
	/**
	 * 用户名为空
	 */
	public static final Integer USERNAMENULL = 3;

	/**
	 * 判断字符串是否是整数
	 */
	public static boolean isInteger(String number) {
		boolean isNumber = false;
		if (isNotEmpty(number)) {
			isNumber = number.matches("^([1-9]\\d*)|(0)$");
		}
		return isNumber;
	}

	/**
	 * 判断是否是汉语
	 * 
	 * @param hanyu
	 * @return
	 */
	public static boolean isHanYu(String hanyu) {
		boolean isHanYu = false;
		if (isNotEmpty(hanyu)) {
			isHanYu = hanyu.matches("^[\\u4e00-\\u9fa5]*$");
		}
		return isHanYu;
	}

	/**
	 * 判断用户名是否合法
	 * 
	 * @param username
	 * @return 1:代表长度不合法 2:用户名格式不合法 3:长度不能为空
	 */
	public static int isPassUserName(String username) {
		if (isNotEmpty(username)) {
			if (username.trim().length() > 18 || username.trim().length() < 4) {
				return 1;
			} else {
				if (!username
						.matches("^([a-zA-Z].+\\d*)|([\\u4e00-\\u9fa5]{3,8})$")) {
					return 2;
				}
			}
			return 0;
		}
		return 3;
	}

	/**
	 * 判断用户名是否合法
	 * 
	 * @param username
	 * @return 1:代表长度不合法 2:用户名格式不合法 3:长度不能为空
	 */
	public static int isPassPassword(String password) {
		if (isNotEmpty(password)) {
			if (password.trim().length() > 16 || password.trim().length() < 6) {
				return 1;
			} else {
				if (!password
						.matches("^([a-zA-Z].+(\\d*)|(([.]|[+]|[=]|[-]|[/])*))$")) {
					return 2;
				}
			}
			return 0;
		}
		return 3;
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(Object str) {
		boolean isEmpty = false;
		if (str != null) {
			if (str instanceof String) {
				String ss = str.toString();
				if (!ss.trim().equals("")) {
					isEmpty = true;
				}
			}
		}

		return isEmpty;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * 将数组转成SQL认识的字符串 123,321 id in ('123','321')
	 * 
	 * @param ids
	 * @return
	 */
	public static String fromArrayToStr(String[] ids) {
		StringBuilder stringBuilder = new StringBuilder();
		int length = ids.length;
		for (String id : ids) {
			stringBuilder.append("'" + id + "',");
		}
		if (length > 0) {
			stringBuilder.deleteCharAt(length - 1);
		}
		return stringBuilder.toString();
	}

	/**
	 * 根据条件删除两端指定长度的字符
	 * 
	 * @param str
	 *            所需删除的字符串
	 * @param localStr
	 *            前端位置（before）或后端位置（after）
	 * @param delNum
	 *            删除个数
	 * @return 删除后的字符串
	 */
	public static StringBuilder deleteString(String str, String localStr,
			int delNum) {
		StringBuilder strBuilder = new StringBuilder();
		if (localStr.intern() == "before") {
			str = str.substring(delNum, str.length());
		} else if (localStr.intern() == "after") {
			str = str.substring(0, str.length() - delNum);
		}
		return strBuilder.append(str);
	}

	/**
	 * 将字符串的第一个字母变为大写，主要用与Java反射寻找get方法名
	 * 
	 * @param fildeName
	 * @return
	 */
	public static String getMethodName(String oldStr) {
		char firstCh = oldStr.charAt(0);
		String newStr = "";
		if ((firstCh >= 'a' && firstCh <= 'z')
				|| (firstCh >= 'A' && firstCh <= 'Z')) {
			newStr = String.valueOf(firstCh).toUpperCase()
					+ oldStr.substring(1);
		}
		return newStr;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            所需判断的字符串
	 * @return 是否为空，为空返回True，不为空返回False
	 */
	public static boolean judgeStringIsNULL(String str) {
		boolean TF = false;
		if (str != "" && !("").equals(str) && !str.equals(null)
				&& !("null").equals(str)) {
			TF = true;
		}
		return TF;
	}

	/**
	 * 将String数组格式的String转换成String数组
	 * 
	 * @param fields
	 *            数组格式的String字符串
	 * @return
	 */
	public static String[] getArraystr(String fields) {
		fields = fields.substring(1, fields.length() - 1);
		String[] fieldstr = fields.split(",");
		for (int i = 0; i < fieldstr.length; i++) {
			fieldstr[i] = fieldstr[i].substring(1, fieldstr[i].length() - 1);
		}
		return fieldstr;
	}

}
