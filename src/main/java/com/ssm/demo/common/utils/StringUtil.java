package com.ssm.demo.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 *   
 * @version 1.0  
 *
 */
public class StringUtil {

	/**
	 * �û�������
	 */
	public static final Integer USERNAMELENGTH = 1;
	/**
	 * �û�����ʽ
	 */
	public static final Integer USERNAMEFORMAT = 2;
	/**
	 * �û���Ϊ��
	 */
	public static final Integer USERNAMENULL = 3;

	/**
	 * �ж��ַ����Ƿ�������
	 */
	public static boolean isInteger(String number) {
		boolean isNumber = false;
		if (isNotEmpty(number)) {
			isNumber = number.matches("^([1-9]\\d*)|(0)$");
		}
		return isNumber;
	}

	/**
	 * �ж��Ƿ��Ǻ���
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
     * �ж��ַ����Ƿ�Ϊ����
     */
    public static boolean isNumeric(String str) {
        return NumberUtils.isDigits(str);
    }
    
	/**
	 * �ж��û����Ƿ�Ϸ�
	 * 
	 * @param username
	 * @return 1:�����Ȳ��Ϸ� 2:�û�����ʽ���Ϸ� 3:���Ȳ���Ϊ��
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
	 * �ж��û����Ƿ�Ϸ�
	 * 
	 * @param username
	 * @return 1:�����Ȳ��Ϸ� 2:�û�����ʽ���Ϸ� 3:���Ȳ���Ϊ��
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
	 * �ж��ַ����Ƿ�Ϊ��
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
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(CharSequence str) {
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * ������ת��SQL��ʶ���ַ��� 123,321 id in ('123','321')
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
	 * ��������ɾ������ָ�����ȵ��ַ�
	 * 
	 * @param str
	 *            ����ɾ�����ַ���
	 * @param localStr
	 *            ǰ��λ�ã�before������λ�ã�after��
	 * @param delNum
	 *            ɾ������
	 * @return ɾ������ַ���
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
	 * ���ַ����ĵ�һ����ĸ��Ϊ��д����Ҫ����Java����Ѱ��get������
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
	 * �ж��ַ����Ƿ�Ϊ��
	 * 
	 * @param str
	 *            �����жϵ��ַ���
	 * @return �Ƿ�Ϊ�գ�Ϊ�շ���True����Ϊ�շ���False
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
	 * ��String�����ʽ��Stringת����String����
	 * 
	 * @param fields
	 *            �����ʽ��String�ַ���
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
