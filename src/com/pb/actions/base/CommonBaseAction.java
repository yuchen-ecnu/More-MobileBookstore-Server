package com.pb.actions.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.pb.cst.Constants;
import com.pb.json.BaseJson;
import com.pb.services.common.CommService;


/**
 * 
 * 文件名稱：CommonBaseAction
 * 描述: 基本操作的Action基类,登录/登出等
 * @author Zhang Xiaofeng
 * @create 2012-4-10
 * 汇环
 */
public class CommonBaseAction extends BaseAction {
	
	@Resource
	protected CommService commService;
	
	private BaseJson queryJson =new BaseJson();
	
	public BaseJson getQueryJson() {
		return queryJson;
	}

	public void setQueryJson(BaseJson queryJson) {
		this.queryJson = queryJson;
	}
	
	public String checkAuth(){
		queryJson =new BaseJson();
			Map map=new HashMap<String, String>();
			String urls=getHttpRequest().getParameter("url");
			ObjectMapper mapper = new ObjectMapper();
			try {
				List<String> urlList = mapper.readValue(urls,
						List.class);
				for(String url:urlList){
					Set allModuleUrls=(Set)getServletContext(Constants.MODULE_URLS);
					Set moduleUrls=(Set)getSession(Constants.MODULE_URLS);
					if(allModuleUrls.contains(url)){
						if(moduleUrls.contains(url)){
							map.put(url, 0);
						}else{
							map.put(url, 1);
						}
					}else {
						map.put(url, 0);
					}
				}
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			queryJson.setObj(map);
		return "jsonResult";
	}
}
