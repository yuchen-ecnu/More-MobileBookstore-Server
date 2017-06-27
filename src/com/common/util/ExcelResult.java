package com.common.util;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
/**
 * Created on 2012-03-28
 * <p>Title:       汇环_Excel工具类</p>
 * <p>Description: 自定义处理结果类型,Excel工具类</p>
 * @version        1.0
 */
public class ExcelResult extends StrutsResultSupport {

	//定义HSSFWorkbook对象，代表excel工作表
	private HSSFWorkbook workbook=null;
	
	private void exportExcel(ActionContext ctx) throws Exception{
		//获取Request对象
		HttpServletRequest request=(HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
		//从请求中获取导出excel的标题行列表
		String[] titles=(String[])request.getAttribute("titles");
		//从请求中获取导出excel的数据列表
		List dataList=(List)request.getAttribute("dataList");
		//创建工作薄实例
		workbook=new HSSFWorkbook();
		//创建工作实例
		HSSFSheet sheet=workbook.createSheet("sheet");
		if(dataList!=null){
			//创建标题行
			HSSFRow titleRow=sheet.createRow(0);
			for(int i=0;i<titles.length;i++){
				HSSFCell cell=titleRow.createCell((short)i);//创建数据列
				cell.setCellValue(titles[i]);//给单元格赋值
			}
			//填充表格
			for(int i=0;i<dataList.size();i++){
				HSSFRow dataRow=sheet.createRow(i+1);//创建数据行
				Object obj=dataList.get(i);//获得dataList中的第i个对象
				//利用Java反射技术执行一个对象中所有的get方法
				Method[] methods=obj.getClass().getMethods();
				int j=0;
				for (Method method : methods) {
					//当方法以get开头但不包括getClass方法时
					if(method.getName().startsWith("get") && !method.getName().equals("getClass")){
						HSSFCell cell=dataRow.createCell((short)j++);//创建数据列
						Object value=method.invoke(obj);//执行get方法获取值
						cell.setCellValue(value.toString());//给单元格赋值
					}
				}
			}
		}
	}
	
	/*
	 * 重写doExecute方法
	 */
	protected void doExecute(String arg0, ActionInvocation invocation) throws Exception {
		//获取ActionContext对象实例
		ActionContext ctx=invocation.getInvocationContext();
		//通过ActionContext对象实例，获取response对象
		HttpServletResponse response=(HttpServletResponse)ctx.get(ServletActionContext.HTTP_RESPONSE);
		//获取OutputStream实例，用于将excel输出至流中
		OutputStream out=response.getOutputStream();
		//设置响应头与内容
		response.setHeader("Content-disposition", "attachment;filename=Data.xls");
		response.setContentType("application/msexcel;charset=UTF-8");
		exportExcel(ctx);//调用exportExcel方法
		//将工作表输出至输出流中
		workbook.write(out);
		//关闭输出流
		out.flush();
		out.close();
	}

}
