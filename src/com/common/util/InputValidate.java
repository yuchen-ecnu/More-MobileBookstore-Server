package com.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 数据数据校验
 * File name：InputValidate
 * Date: 2012-6-15
 * Author: Zhang Xiaofeng
 * Description：
 * Modify History: Date Programmer Notes
 */
public final class InputValidate {

	/**
	 * 匹配整数 判断是否是整数 true 是 false 否
	 */
	public static boolean isInteger(String num) {
		boolean result = false;

		String pattern = "^-?\\d+$";

		result = num.matches(pattern);
		return result;
	}

	/**
	 * 匹配整数 判断是否是double类型 true 是 false 否
	 */
	public static boolean isDouble(String num) {
		boolean result = false;

		String pattern = "^[-+]?(\\d+(\\.\\d*)?|\\.\\d+)([eE]([-+]?([012]?\\d{1,2}|30[0-7])|-3([01]?[4-9]|[012]?[0-3])))?$";

		result = num.matches(pattern);
		return result;
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if (value != null) {
			value = value.trim();
		}
		if (null == value || "".equals(value) || value.length() < 1) {
			return true;
		} else {

			return false;
		}
	}

	/**
	 * 匹配email地址
	 * 
	 * @param mail
	 *            邮件地址
	 */

	public static boolean isEmail(String mail) {
		boolean result = false;
		if (!StringUtils.isEmpty(mail) && mail.length() <= 8)
			return false;

		String pattern = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

		result = mail.matches(pattern);
		return result;
	}

	/**
	 * 匹配手机号
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {

		boolean result = false;

		String pattern = "^0?1\\d{10}$";

		result = mobile.matches(pattern);
		return result;

	}

	/**
	 * 校验日期是否为yyyy-mm-dd的格式
	 * Function Name: isValidDate
	 * 
	 * @param sDate
	 * @return
	 *         description:
	 *         Modification History:
	 */
	public static boolean isValidDate(String sDate) {
		if(!StringUtil.isEmpty(sDate)){
			String reg = "[1-9]{1}\\d{3}-\\d{2}-\\d{2}";
			Pattern pattern = Pattern.compile(reg);
			Matcher match = pattern.matcher(sDate);
			if(match.matches()){
				SimpleDateFormat format = new SimpleDateFormat(DateUtil.dashFormat);//yyyy-mm-dd
				format.setLenient(false);//false为严格解析
				try{
					format.parse(sDate);
					return true;
				}catch (ParseException e) {
					return false;
				}
			}
		}
		return false;
	}
	/**
	 * Function Name: isNumber
	 * 
	 * @param str
	 * @return
	 *         description: 验证数字
	 *         Modification History:
	 */
	public static boolean isNumber(String str) {
		if (str == null || str.equals("")) {
			return false;
		}
		return isInteger(str) || isDouble(str);
	}

	/**
	 * Function Name: isTime
	 * 
	 * @param str
	 * @return
	 *         description: 验证时间是否为HH:mm:ss的格式
	 *         Modification History:
	 */
	public static boolean isTime(String str) {
		if (str.length() != 8) {
			return false;
		}
		if(!str.matches("(([0-1][0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]"))
			return false;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			Date date = sdf.parse(str);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * Function Name: isTelOrFax
	 * 
	 * @param str
	 * @return
	 *         description: 校验普通电话、传真号码：可含有“-,#”
	 *         Modification History:
	 */
	public static boolean isTelOrFax(String str) {
		boolean result = false;
		if (StringUtils.isEmpty(str)) {
			return result;
		}
		String pattern = "^(^[\\d]+[\\d-+#]*)$";
		result = str.matches(pattern);
		return result;
	}

	/**
	 * 
	 * Function Name: isUsrLoginId
	 * 
	 * @param str
	 * @return
	 *         description:校验用户登录名规则true:通过，false：不通过
	 *         Modification History:
	 */
	public static boolean isUsrLoginId(String str) {
		return !isEmpty(str) && !str.matches("^([\\d]{6})$") && str.matches("^([\\w\\d_.@]{3,20})$");
	}

	/**
	 * 
	 * Function Name: isUsrPassword
	 * 
	 * @param str
	 * @return
	 *         description:校验用户密码规则true:通过，false：不通过
	 *         Modification History:
	 */
	public static boolean isUsrPassword(String str) {
		Pattern pattern1 = Pattern.compile("[a-zA-Z]");
		Pattern pattern2 = Pattern.compile("[0-9]");
		Matcher matcher1 = pattern1.matcher(str);
		Matcher matcher2 = pattern2.matcher(str);
		boolean isContainNumAndWord = matcher1.find() && matcher2.find();// 必须包含一个英文字符和整数
		return !isEmpty(str) && isContainNumAndWord && str.matches("^([\\w\\d_.@]{8,10})$");
	}

	/**
	 * 校验字符串是否超出最大长度
	 * Function Name: checkLength
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static boolean checkLength(String str, int maxLength) {
		boolean result = false;
		if (StringUtil.isEmpty(str))
			result = true;
		if (!StringUtil.isEmpty(str) && str.length() <= maxLength)
			result = true;
		return result;
	}
	
	/**
	 * 验证字符最大长度，中文占3位
	 * Function Name: checkLengthWithCHN
	 * 
	 * @param str
	 * @param maxLength
	 * @return
	 */
	public static boolean checkLengthWithCHN(String inputText, int maxLength) {
		int totalLength = 0;
		for (int i = 0; i<inputText.length(); i++){
			String buffer = inputText.substring(i, i+1);
		    if (buffer.matches("[^\\x00-\\xff]")) {
		    	totalLength += 3;  // 中文+3
		    } else {
		        totalLength += 1;  // 其他+1
		    }
		}
		if (totalLength > maxLength) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 校验字符串是否包含html代码
	 * Function Name: containsHtmlCode
	 * 
	 * @param str
	 * @return
	 *         description:
	 *         Modification History:
	 */
	public static boolean containsHtmlCode(final String str) {
		if (StringUtil.isEmpty(str)) {
			return false;
		}
		String regExHtml = "<[\\s\\S]*>";// 定义html表达式：<*>
		Pattern pattern = Pattern.compile(regExHtml);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
}
