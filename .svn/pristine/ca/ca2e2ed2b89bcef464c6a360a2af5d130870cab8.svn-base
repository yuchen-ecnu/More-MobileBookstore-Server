package com.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jfree.util.Log;

/**
 * 日期工具类
 * File name：StringUtil
 * Date: 2012-11-29
 * Author: Admin
 * Description：
*/
public class StringUtil {

	/** 
	 * Function Name: replace
	 * @param queryStr 原字符串
	 * @param oldstr 指定字符串
	 * @param replacement 给定字符串
	 * @return
	 * description:根据给定字符串替换原字符串中存在的指定字符串
	 * Modification History:
	 */
	public static String replace(String queryStr, String oldstr, String replacement) {
		return StringUtils.replace(queryStr, oldstr, replacement);
	}
	
	/**
	 * 将字符串转成UTF8格式
	 * Function Name: getUTF8String
	 * @param src
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String getUTF8String(String src) {
		try {
			return new String(src.getBytes("ISO8859_1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return src;
		}
	}

	/**
	 * 将字符串转成ISO格式
	 * Function Name: getISOString
	 * @param src
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String getISOString(String src) {
		try {
			return new String(src.getBytes("UTF-8"), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return src;
		}
	}

	/**
	 * html代码转义
	 * Function Name: FullHTMLEncode
	 * 
	 * @param aTagFragment
	 * @return
	 */
	public static String FullHTMLEncode(String aTagFragment) {
		if (aTagFragment == null) {
			return "";
		}
		final StringBuffer result = new StringBuffer();
		final StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
		char character = iterator.current();
		while (character != StringCharacterIterator.DONE) {
			if (character == '<') {
				result.append("&lt;");
			} else if (character == '>') {
				result.append("&gt;");
			} else if (character == '\"') {
				result.append("&quot;");
			} else if (character == '\'') {
				result.append("&#039;");
			} else if (character == '\\') {
				result.append("&#092;");
			} else if (character == '&') {
				result.append("&amp;");
			} else {
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	/**
	 * 校验是否含有html代码
	 * Function Name: haveHTMLcode
	 * @param aTagFragment
	 * @return
	 */
	public static boolean haveHTMLcode(String aTagFragment) {
		if (aTagFragment == null) {
			return false;
		}
		final StringCharacterIterator iterator = new StringCharacterIterator(aTagFragment);
		char character = iterator.current();
		while (character != StringCharacterIterator.DONE) {
			if (character == '<') {
				return true;
			} else if (character == '>') {
				return true;
			} else if (character == '\"') {
				return true;
			} else if (character == '\'') {
				return true;
			} else if (character == '\\') {
				return true;
			} else if (character == '&') {
				return true;
			}
			character = iterator.next();
		}
		return false;
	}


	/**
	 * Function Name: objToString
	 * @param obj
	 * @return 返回String数据
	 * description: 将Object转成string类型
	 * Modification History:
	 */
	public static String objToString(Object obj){
		String str = "";
		if(obj != null){
			str = obj+"";
		}
		return str;
	}
	
	/**
	 * 
	 * Function Name: objToBigDecimal
	 * @param obj
	 * @return 返回BigDecimal数据
	 * description: 将Object转成BigDecimal类型
	 * Modification History:
	 */
	public static BigDecimal objToBigDecimal(Object obj){
		BigDecimal bigDec = null;
		if(obj != null){
			bigDec = (BigDecimal) obj;
		}
		return bigDec;
	}
	
	/**
	 * 
	 * Function Name: objToDouble
	 * @param obj
	 * @return 返回double数据
	 * description:将Object转成double类型
	 * Modification History:
	 */
	public static Double objToDouble(Object obj){
		Double doubleObj = null;
		if(obj != null){
			doubleObj = (Double) obj;
		}
		return doubleObj;
	} 
	
	/**
	 * 
	 * Function Name: objToDate
	 * @param obj
	 * @return 返回Date数据
	 * description:将Object转成Date类型
	 * Modification History:
	 */
	public static Date objToDate(Object obj){
		Date date = null;
		if(obj != null){
			date = (Date) obj;
		}else if(obj != null){
			date = DateUtil.getDate(obj+"", "yyyy-MM-dd");
		}
		return date;
	} 
	
	/**
	 * 
	 * Function Name: objToInt
	 * @param obj
	 * @return 返回double数据
	 * description:将Object转成int类型
	 * Modification History:
	 */
	public static int objToInt(Object obj){
		Integer integer = null;
		if(obj != null){
			integer = (Integer) obj;
		}
		return integer;
	} 
	
	/**
	 * 
	 * Function Name: objToTimeStamp
	 * @param obj
	 * @return 返回TimeStamp数据
	 * description:将Object转成TimeStamp类型
	 * Modification History:
	 */
	public static Timestamp objToTimeStamp(Object obj){
		Timestamp tt = null;
		if(obj != null){
			tt = (Timestamp) obj;
		}
		return tt;
	} 
	
	/**
	 * 
	 * Function Name: objToTimeLong
	 * @param obj
	 * @return 返回Long数据
	 * description:将Object转成Long类型
	 * Modification History:
	 */
	public static Long objToTimeLong(Object obj){
		Long l = null;
		if(obj != null){
			l = (Long) obj;
		}
		return l;
	} 
	
	/**
	 * 格式化价格的输出，目前控制强制保留两位小数
	 * Function Name: formatPrice
	 * @param number
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatPrice(double number)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	
	/**
	 * 格式化价格的输出，目前控制强制保留两位小数
	 * Function Name: formatPrice
	 * @param number
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatPrice(BigDecimal number)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	
	/**
	 * 格式化量的输出，不显示小数
	 * Function Name: formatAmount
	 * @param amount
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatAmount(double amount)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);
		nf.setGroupingUsed(false);
		return nf.format(amount);
	}
	
	/**
	 * 格式化量的输出，不显示小数
	 * Function Name: formatAmount
	 * @param amount
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatAmount(BigDecimal amount)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(0);
		nf.setGroupingUsed(false);
		return nf.format(amount);
	}
	
	/**
	 * 根据小数位数格式化数字，强制显示val位小数位.不四舍五入
	 * Function Name: formatNumber
	 * @param number
	 * @param val
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatBigDecimal(BigDecimal number,int val,boolean isForceMax)
	{
		DecimalFormat nf = new DecimalFormat();
		nf.setMaximumFractionDigits(val);
		if(isForceMax)
			nf.setMinimumFractionDigits(val);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	/**
	 * 根据小数位数格式化数字，强制显示val位小数位.四舍五入,千分符显示
	 * Function Name: formatNumber
	 * @param number
	 * @param val
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatBigDecimal(BigDecimal number,int val)
	{
		DecimalFormat nf = new DecimalFormat();
		nf.setMaximumFractionDigits(val);
		nf.setMinimumFractionDigits(val);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		nf.setGroupingUsed(true);
		return nf.format(number);
	}
	/**
	 * 根据小数位数格式化数字，强制显示val位小数位d.
	 * 四舍五入处理
	 * Function Name: formatNumber
	 * @param number
	 * @param val
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatNumber(double number,int val)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(val);
		nf.setMinimumFractionDigits(val);
		nf.setRoundingMode(RoundingMode.HALF_UP);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	
	/**
	 * 根据设置格式化数字，val为最多的小数位数，末尾不自动补0
	 * Function Name: formatNumberByMaxFrac
	 * @param number
	 * @param val
	 * @return 
	 * description:
	 * Modification History:
	 */
	public static String formatNumberByMaxFrac(double number,int val)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(val);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	/**
	 * 根据小数位数格式化数字，强制显示val位小数位,千分符显示
	 * Function Name: formatNumber
	 * @param number
	 * @param val
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatNumberUseGrp(BigDecimal number,int val)
	{
		if(number==null) return "";
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(val);
		nf.setMinimumFractionDigits(val);
		nf.setGroupingUsed(true);
		return nf.format(number);
	}
	/**
	 * 根据设置格式化数字，val为最多的小数位数，末尾不自动补0,千分符显示
	 * Function Name: formatNumberByMaxFrac
	 * @param number
	 * @param val
	 * @return 
	 * description:
	 * Modification History:
	 */
	public static String formatNumberByMaxFracUseGrp(BigDecimal number,int val)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(val);
		nf.setGroupingUsed(true);
		return nf.format(number);
	}
	/**
	 * 根据小数位数格式化数字，val为最多的小数位数，末尾不自动补0,
	 * Function Name: formatNumber
	 * @param number
	 * @param val
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatNumberByMaxFrac(BigDecimal number,int val)
	{
		if(number==null) return "";
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(val);
		return nf.format(number);
	}
	/**
	 * 根据小数位数格式化数字，强制显示val位小数位
	 * Function Name: formatNumber
	 * @param number
	 * @param val
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatNumber(BigDecimal number,int val)
	{
		if(number==null) return "";
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(val);
		nf.setMinimumFractionDigits(val);
		return nf.format(number);
	}
	/**
	 * 根据小数位数格式化Imix数字，至少显示val位小数位
	 * Function Name: formatNumber
	 * @param number
	 * @param val
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String formatImixNumber(double number,int val)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(val);
		nf.setMinimumFractionDigits(val);
		nf.setGroupingUsed(false);
		return nf.format(number);
	}
	
	/**
	 * 获取UTF8编码下字符的长度
	 * Function Name: getStrLength
	 * @param str
	 * @return
	 * description:
	 * Modification History:
	 */
	public static int getStrLength(String str)
	{
		return getStrLength(str, "UTF-8");
	}
	
	/**
	 * 根据字符集获取字符长度
	 * Function Name: getStrLength
	 * @param str
	 * @param charset
	 * @return
	 * description:
	 * Modification History:
	 */
	private static int getStrLength(String str,String charset)
	{
		int length = 0;
		try {
			length = str.getBytes(charset).length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return length;
	}
	
	/**
	 * 根据最大长度以utf8格式截取字符串
	 * Function Name: cuteStrByUTF8
	 * @param str
	 * @return
	 * description:
	 * Modification History:
	 */
	public static String cutStrByUTF8(String str,int maxSize)
	{
		String utf8Str = "";
		try {
			byte[] tmp = str.getBytes("UTF-8");
			if(maxSize<tmp.length)
				utf8Str = new String(tmp,0,maxSize,"UTF-8");
			else
				utf8Str = new String(tmp,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return utf8Str;
	}
	
	
	/**
	 * 将字符串转换为byte数组
	 * @param str
	 * @return
	 */
	public static byte[] getByteInStr(String str)
	{
		if(StringUtils.isEmpty(str))
			return null;
		try {
			return str.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Function Name: getStrFromByte
	 * 将数组转换为字符串
	 * @param b
	 * @return
	 */
	public static String getStrFromByte(byte[] b)
	{
		if(null==b || b.length==0)
			return "";
		try {
			return new String(b,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * Function Name: isBlank
	 * 判断字符串是否为空（包含空格）
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		return StringUtils.isBlank(str);
	}
	
	/**
	 * Function Name: isEmpty
	 * 判断字符串是否为空（不包含空格）
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return StringUtils.isEmpty(str);
	}
	
	/**
	 * 手工转码，用于后台response中写回中文等场景
	 * @param url
	 * @return
	 */
	public static String encodeUrl(String url){
		try {
			return new String(url.getBytes("GBK"),"ISO8859-1");
			//return URLEncoder.encode(url,"GBK");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}
	
	/**
	 * Function Name: formatNumberStr
	 * Description： 数值字符串转为BigDecimal，按照保留精度，四舍五入
	 * @param numberStr 数值字符串
	 * @param scale 保留小数位数（四舍五入）
	 * @return BigDecimal
	 */
	public static BigDecimal formatNumberStr(String numberStr,Integer scale){
		BigDecimal bigVal = null;
		try {
			bigVal = new BigDecimal(numberStr);
			if(null == scale)
				return bigVal;
			return bigVal.setScale(scale,BigDecimal.ROUND_HALF_UP);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将lcm2库中ISO编码转为GBK，防止中文乱码
	 * Function Name :
	 * @param str
	 * @return
	 * Description : 
	 * Modification History :
	 */
	public static String getGBKString(String str){
		try {
			if(!StringUtils.isEmpty(str))
				return new String(str.getBytes("ISO-8859-1"),"GBK");
			else
				return "";
		} catch (UnsupportedEncodingException e) {
			Log.error(e);
		}
		return "";
	}
	
	/**
	　　* 将元数据前补零，补后的总长度为指定的长度，以字符串的形式返回
	　　* @param sourceDate
	　　* @param formatLength
	　　* @return 重组后的数据
	　　*/

	public static String frontCompWithZore(int sourceData,int formatLength)
	{
		String newString = String.format("%0"+formatLength+"d", sourceData);
		return newString;
	}
	
	public static String frontCompWithZore(double sourceData,int formatLength)
	{
		String newString = String.format("%0"+formatLength+"d", sourceData);
		return newString;
	}
	
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	}  
	
    public static boolean isNumeric(String str){  
        Pattern pattern = Pattern.compile("[0-9]*");  
        return pattern.matcher(str).matches();     
    }  
    
    /**
	 * new文件名= 时间 + 全球唯一编号
	 * @param fileName old文件名
	 * @return new文件名
	 */
    public static String generateFileName(String fileName) {
		//时间
        DateFormat df = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");   
        String formatDate = df.format(new Date());
        //全球唯一编号
        String uuid=UUID.randomUUID().toString();
        int position = fileName.lastIndexOf(".");   
        String extension = fileName.substring(position);   
        return formatDate + uuid + extension;   
    }
} 