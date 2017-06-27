package com.pb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

/**
 * File name：AppUtil 
 * Date: 2012-5-8 
 * Author: zhangxiaofeng 
 * Description： 
 * Modify History: 
 * 2012-5-8 zhangxiaofeng Initial
 * 2013-10-17		fanpengyao		国际化翻译方法transLocaleMessage的locale参数废弃（暂定），已在方法内部从request获取
 * 2013-10-18		fanpengyao		transOprtDesc方法已废弃，国际化翻译统一使用transLocaleMessage方法处理
 */
public class AppUtil {
	
	static Log log = LogFactory.getLog(AppUtil.class);
	/**
	 * 获取客户端IP地址信息
	 * @return
	 */
	public static String getClientIpAddr(){
		String ip = ServletActionContext.getRequest().getHeader("x-forwarded-for");
		if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=ServletActionContext.getRequest().getHeader("Proxy-Client-IP");
		}
		if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=ServletActionContext.getRequest().getHeader("WL-Proxy-Client-IP");
		}
		if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
			ip=ServletActionContext.getRequest().getRemoteAddr();
			if("0:0:0:0:0:0:0:1".equals(ip)){
				ip="127.0.0.1";
			}
		}
		return ip;
	}
	
	/**
	 * 获取本机IP地址
	 * Function Name: getLocalIpAddr
	 * @return
	 */
	public static String getLocalIpAddr()
	{
		String ret = "";
		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			if(!StringUtils.isEmpty(ip) && ip.indexOf(".")>0 )
				ret = ip;
			
		} catch (UnknownHostException e) {
			log.error("local ip get failed",e);
		}
		return ret;
	}
	
	/**
	 * 获取程序运行class路径
	 * Function Name: getClassPath
	 * @return
	 */
	public static String getClassPath() {
		String classPath = AppUtil.class.getClassLoader().getResource("").getPath();
		try {
			classPath = URLDecoder.decode(classPath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return classPath;
	}
	

	/**
	 * 根据jasper模板名称返回模板绝对路径 Function Name: getJasperReportModel
	 * 
	 * @param jaspername
	 * @return description: Modification History:
	 */
	public static String getJasperReportModel(String jaspername) {
		return ServletActionContext.getServletContext().getRealPath(
				"/web/jasperreports/" + jaspername);
	}
	
	
	/**
	 * 解析CSV文件
	 * Function Name: processCsvFile
	 * @param file
	 * @return
	 * description:
	 * Modification History:
	 */
	public static List<String[]> processCsvFile(File file, int commaCount){
		return processCsvFile(file,',',commaCount);
	}
	
	/**
	 * 解析CSV文件
	 * Function Name: processCsvFile
	 * @param file
	 *  @param file
	 * @return
	 * description:
	 * Modification History:
	 */
	public static List<String[]> processCsvFile(File file,char splitchar, int commaCount){
		List<String[]> datas = new ArrayList<String[]>(0);
		
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));
			String data = null;
			
			while((data = bufferReader.readLine()) != null){
				int count = 0;
				if(data.equals("")||data.length()==0){//如果CSV文件是单独的空行，跳过
					continue;
				}
				char[] chars = data.toCharArray();
				for(char c : chars){
					if (splitchar==c) {
						count++;
					}
				}
				if(count != commaCount){//不是规定的数量
					String[] strs = null; 
					datas.add(strs);
				}else{//否则拆分为数组
					String[] strs = data.split(String.valueOf("\\"+splitchar));
					datas.add(strs);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return datas;
	}
}
