package com.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;


/**
 * Created on 2012-03-28
 * <p>Title:       汇环_[子系统统名]_[模块名]/p>
 * <p>Description: 文件压缩</p>
 * @version        1.0
 */
public class ZipUtil {
	
	/**
	 * 
	 * @param files
	 * @param stream
	 */
	public static void createZipFile(File[] files,OutputStream stream)
	{
		ZipOutputStream zipOutputStream=new ZipOutputStream(stream);
		zipOutputStream.setEncoding("GBK");//modify by zhangxiaofeng 解决zip压缩包中文件名乱码问题

		try{
			ZipEntry zipEntry=null;
			byte[] buf=new byte[1024];
			int readlen=0;
			for (int i = 0; i <files.length; i++) {
			File f=(File)files[i];
			if( f.exists() && f.isFile())
			{
				//创建一个zipentry，并设置name和其它的一些属性
				zipEntry=new ZipEntry(f.getName());
				zipEntry.setSize(f.length());
				zipEntry.setTime(f.lastModified());
	
				//将zipentry加到zos中，再写入实际的文件内容
				zipOutputStream.putNextEntry(zipEntry);
				InputStream inputStream=new BufferedInputStream(new FileInputStream(f));
				while ((readlen=inputStream.read(buf, 0, 1024))!=-1) {
					zipOutputStream.write(buf, 0, readlen);
				}
				inputStream.close();
			}
		}
			
		}catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try{
				if(zipOutputStream!=null)
				{
					zipOutputStream.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param files
	 * @param stream
	 */
	public static void createCSVFile(File f,OutputStream stream)
	{

		try{
			byte[] buf=new byte[1024];
			int readlen=0;
			if(f.isFile())
			{
				//创建一个zipentry，并设置name和其它的一些属性
	
				//将zipentry加到zos中，再写入实际的文件内容
				InputStream inputStream=new BufferedInputStream(new FileInputStream(f));
				while ((readlen=inputStream.read(buf, 0, 1024))!=-1) {
					stream.write(buf, 0, readlen);
				}
				inputStream.close();
			}
		}catch ( Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			try{
				if(stream!=null)
				{
					stream.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
