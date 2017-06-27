package com.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * 
 * File name：DateUtil
 * Date: 2012-11-29
 * Author: Admin
 * Description：
 * Modify History: Date Programmer Notes
 * ------ ------------ -------
 * 2011-12-01 Zhang Xiaofeng 创建
 * 2012-11-25 An Daobiao 重构分析，合并部分公共方法
 * 2012-11-29 Zhang Xiaofeng 重构，整合，和stringutils区别开，舍弃重复代码
 */
public class DateUtil {

	// three comman date format
	public static final String dashFormat = new String("yyyy-MM-dd");
	public static final String slashForamt = new String("yyyy/MM/dd");
	public static final String ymdFormat = new String("yyyyMMdd");
	public static final String timeFormat = new String("HH:mm:ss");
	public static final String dateTimeFormat = new String("yyyy-MM-dd HH:mm:ss");
	public static final String ignoreSecondFormat = new String("yyyy-MM-dd HH:mm");
	public static final String datetimemarkFormat = new String("yyyyMMddHHmmss");    //一般用于生成时间戳

	public static final long M_PER_DAY = 1000 * 60 * 60 * 24;

	/**
	 * 根据给定的格式对时间类型格式化
	 * 
	 * @param date 日期
	 * @param formatString 日期表达式
	 * @return description: Modification History:
	 */
	public static String getDateString(Date date, String formatString) {
		try {
			return (date != null) ? new SimpleDateFormat(formatString).format(date) : "";
		} catch (Throwable e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * get the default dateString with style "yyyy-MM-dd"
	 * 将日期格式化成"yyyy-MM-dd"样式
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date) {
		return (date != null) ? DateFormatUtils.format(date, dashFormat) : "";
	}

	/**
	 * get the default dateString with style "HH:mm:ss"
	 * 将日期格式化成"HH:mm:ss"样式
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeString(Timestamp time) {
		return (time != null) ? DateFormatUtils.format(time, timeFormat) : "";
	}

	/**
	 * get the default dateString with style "yyyy-MM-dd HH:mm:ss"
	 * 将日期格式化成"yyyy-MM-dd HH:mm:ss"样式
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateTimeString(Date date) {
		return (date != null) ? DateFormatUtils.format(date, dateTimeFormat) : "";
	}

	/**
	 * use the default date format "yyyy-MM-dd"
	 * 将"yyyy-MM-dd"格式的字符串转换为日期格式
	 * 
	 * @param dStr
	 * @return
	 */
	public static Date getDate(String dStr) {
		return (StringUtils.isEmpty(dStr)) ? null : getDate(dStr, dashFormat);
	}

	/**
	 * use the default date format "yyyy-MM-dd HH:mm:ss"
	 * 将"yyyy-MM-dd HH:mm:ss"格式的字符串转换为日期格式
	 * 
	 * @param dStr
	 * @return
	 */
	public static Date getDateTime(String dStr) {
		return (StringUtils.isEmpty(dStr)) ? null : getDate(dStr, dateTimeFormat);
	}

	/**
	 * 根据字符串、字符串格式转换为日期
	 * 
	 * @param dStr 字符串
	 * @param formatString 日期表达式
	 * @return description: Modification History:
	 */
	public static Date getDate(String dStr, String formatString) {
		SimpleDateFormat format = new SimpleDateFormat(formatString);
		format.setLenient(false);
		Date date = null;
		try {
			date = format.parse(dStr);
		} catch (ParseException ex) {
			ex.getStackTrace();
		}
		return date;
	}
	/**
	 * 将时间格式HH：mm：ss拼接在日期中，主要供imix方法使用
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date getDateTime(Date date, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		sdf.setLenient(false);
		try {
			return sdf.parse(DateUtil.getDateString(date, DateUtil.ymdFormat) + " " + time);
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 将时间格式HH：mm：ss拼接在日期中，返回时间类型
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static Timestamp getDateTimeRtnTime(Date date, String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp timeStamp = null;
		sdf.setLenient(false);
		try {
			String dateStr = sdf.format(date);
			timeStamp = Timestamp.valueOf(dateStr + " " + time);
		} catch (Exception e) {
		}
		return timeStamp;
	}

	/**
	 * 获取给定日期当日的00:00:00时间戳，即去除日期中含有的时间数据
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateIgnoreTime(Date date) {
		GregorianCalendar gday = new GregorianCalendar();
		gday.setTime(date);
		gday.set(Calendar.HOUR, 0);
		gday.set(Calendar.HOUR_OF_DAY, 0);
		gday.set(Calendar.MINUTE, 0);
		gday.set(Calendar.SECOND, 0);
		gday.set(Calendar.MILLISECOND, 0);
		return gday.getTime();
	}

	/**
	 * 获取当前日期
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 获取当前时间
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * convent method to get days after or before
	 * 根据天数偏移量计算日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(Date date, int days) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * convent method to get days after or before
	 * 根据年、月、日偏移量计算最终日期
	 * 
	 * @param date
	 * @param year
	 * @param month
	 * @param days
	 * @return
	 */
	public static Date getDateAfter(Date date, int year, int month, int days) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(GregorianCalendar.YEAR, year);
		calendar.add(GregorianCalendar.MONTH, month);
		calendar.add(GregorianCalendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 计算两个日期间间隔的天数
	 * 
	 * @param startDate 第一个日期
	 * @param endDate 相比较日期
	 * @return
	 */
	public static Long computeDateInterval(Date startDate, Date endDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long startTime = calendar.getTimeInMillis();

		calendar.setTime(endDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		long endTime = calendar.getTimeInMillis();

		return (endTime - startTime) / M_PER_DAY;
	}

	/**
	 * 获取日期中的年份数值
	 * 
	 * @param date
	 * @return
	 */
	public static int getYearForDate(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(GregorianCalendar.YEAR);
	}

	/**
	 * 获取日期中的月份数值 calendar从 0 计数，所以需加一，才为实际所称月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonthForDate(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(GregorianCalendar.MONTH) + 1;
	}

	/**
	 * 获取日期中的天数值
	 * 
	 * @param date
	 * @return description: Modification History:
	 */
	public static int getDayForDate(Date date) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(GregorianCalendar.DATE);
	}

	/**
	 * 校验日期格式是否正确
	 * 
	 * @param str
	 * @param formatString
	 * @return description: Modification History:
	 */
	public static boolean checkDateValidity(String str, String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		sdf.setLenient(false);
		try {
			sdf.parse(str);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 判断给定的日期是否是该月最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isLastDayOfMonth(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		return (cal.get(Calendar.DAY_OF_MONTH) == cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 校验某日是否在一段日期区间中
	 * 
	 * @param CompareDate 待比较日期
	 * @param date1 前置日期
	 * @param date2 后置日期
	 * @return
	 */
	public static boolean isBetween(Date CompareDate, Date date1, Date date2) {
		if (date2.before(date1)) {
			Date tmp = date1;
			date1 = date2;
			date2 = tmp;
		}
		return !(CompareDate.before(date1) || CompareDate.after(date2));
	}

	/**
	 * 日期比较函数
	 * 
	 * @param date1
	 * @param date2
	 * @return 比较两个日期的先后, date1>date2返回1， date1==date2返回0, date1<date2返回-1,
	 *         date1,date2数据错误返回-2 Modification History:
	 */
	public static int compareDate(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return -2;
		long temp = java.util.TimeZone.getDefault().getRawOffset();
		long result = (date2.getTime() + temp) / M_PER_DAY - (date1.getTime() + temp) / M_PER_DAY;
		if (result > 0) {
			return -1;
		} else if (result < 0) {
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * 校验时间字符是否符合逻辑 HH:mm格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkTimeHHmmPattern(String str) {
		Pattern p = Pattern.compile("^([0-1]\\d|2[0-3]):[0-5]\\d$");
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 校验时间字符是否符合逻辑 yyyy-MM-dd格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkDayYYYYmmDDPattern(String str) {
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]"
				+ "|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1]" + "[0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|"
				+ "[3579][26])00))-02-29)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	public static Timestamp formatTimestamp(String date){
		try {
			return Timestamp.valueOf(date);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
	}

}
