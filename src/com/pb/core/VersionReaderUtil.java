package com.pb.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pb.util.AppUtil;


public class VersionReaderUtil {
	
	static Log log = LogFactory.getLog(VersionReaderUtil.class);
	
	/**
	 * 判断程序是否已产品方式运行，若resources目录下存在debug.properties文件则认为是产品模式
	 * 产品模式和debug模式区别：
	 * 1、产品模式下校验rmi访问的ip有效性
	 * 2、debug模式适用于ST、UAT环境，产品模式适用于模拟、生产环境 
	 * Function Name: isProdMode
	 * @return
	 */
	public static boolean isProdMode()
	{
		String debugPath = AppUtil.getClassPath()+"/resources/debug.properties";
		if(new File(debugPath).exists())
			return false;
		else
			return true;
	}
	
	
	/**
	 * 获取PTRD完整版本号
	 * Function Name: getPtrdFullVersion
	 * @return
	 */
	public static String getPtrdWebVersion()
	{
		try {
			BufferedReader fr = new BufferedReader(new FileReader((AppUtil.getClassPath()+"/resources/version.txt")));
			return fr.readLine();
		} catch (Exception e) {
			log.error("获取PTRD版本文件失败");
		}
		return "";
	}
	
}
