package com.pb.controller.applicationTable;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.json.BaseJson;
import com.pb.json.ModelDataNode;
import com.pb.json.ModelNode;
import com.pb.json.NavBar;
import com.pb.util.JsonUtil;
import com.pb.util.ModelDataUtil;
import com.pb.util.ModelTreeUtil;
import com.pb.util.TableDataUtil;

@Controller
@RequestMapping("/table")
public class TableController extends BaseController{
	 
	/**
	 * 保存模板
	 * @param request
	 * @param response
	 * @param jsonModel 前端传过来的树结构的json字符串
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/model/add", method = { RequestMethod.POST})
	@ResponseBody
	public String modelAdd(HttpServletRequest request, HttpServletResponse response,@RequestParam("jsonModel") String jsonModel) throws UnsupportedEncodingException {
		
		BaseJson bj = new BaseJson();
		System.out.println(jsonModel);
		/*ModelTree mt = (ModelTree)JsonUtil.getInstance().json2obj(jsonModel, ModelTree.class);
		List<Modelrow> list = ModelTreeUtil.getInstance().modelTree2modelRows(mt);
		
		for(Modelrow mr:list){
			mr.setModel(tableService.getModel(1));
			tableService.add(mr);
		}
		*/
		
		ModelTreeUtil.getInstance().jsonModel2entityModelSave(jsonModel);
		
		
		bj.setRetcode("1");
		bj.setErrorMsg("模板保存成功");
		return JsonUtil.getInstance().obj2json(bj);
	}
	

	@RequestMapping(value = "/model/{id}", method = { RequestMethod.GET})
	@ResponseBody
	public String getInstitution(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) {
		
		BaseJson bj = new BaseJson();
		
		
		ModelNode mn = ModelTreeUtil.getInstance().entityModel2jsonModelget(id);
		bj.setObj(mn);

		return JsonUtil.getInstance().obj2json(bj);
	}
	/*
	*//**
	 * 保存新模板
	 * @param request
	 * @param response
	 * @param jsonModel 前端传过来的树结构的json字符串
	 * @return
	 * @throws UnsupportedEncodingException
	 *//*
	@RequestMapping(value = "/model/addNew", method = { RequestMethod.POST})
	@ResponseBody
	public String modelAddNew(HttpServletRequest request, HttpServletResponse response,@RequestParam("project_id") String project_id,@RequestParam("jsonModel") String jsonModel) throws UnsupportedEncodingException {
		
		BaseJson bj = new BaseJson();
		System.out.println(jsonModel);
		ModelTree mt = (ModelTree)JsonUtil.getInstance().json2obj(jsonModel, ModelTree.class);
		List<Modelrow> list = ModelTreeUtil.getInstance().modelTree2modelRows(mt);
		
		for(Modelrow mr:list){
			mr.setModel(tableService.getModel(1));
			tableService.add(mr);
		}
		
		
		ModelTreeUtil.getInstance().jsonModel2entityModelSaveNew(jsonModel,project_id);
		
		
		bj.setRetcode("1");
		bj.setErrorMsg("模板保存成功");
		return JsonUtil.getInstance().obj2json(bj);
	}
	*/
  /*  *//**
     * 第一次添加数据保存
     * @param request
     * @param response
     * @param jsonModel 当前的模板数据，有可能和使用的模板不一致，不一致保存为该项目的隐藏模板
     * @param jsonData 表数据
     * @return
     * @throws UnsupportedEncodingException
     *//*
	@RequestMapping(value = "/data/add", method = { RequestMethod.POST})
	@ResponseBody
	public String dataAdd(HttpServletRequest request, HttpServletResponse response,@RequestParam("jsonModel") String jsonModel,@RequestParam("jsonData") String jsonData,@RequestParam("institution_id") Integer institution_id) throws UnsupportedEncodingException {
		
		BaseJson bj = new BaseJson();
		System.out.println(jsonModel);
		System.out.println(jsonData);
		
		//模板和表数据转换并保存
		TableDataUtil.getInstance().jsonData2entityDataSave(jsonData, jsonModel,institution_id);
	
		bj.setRetcode("1");
		bj.setErrorMsg("保存成功");
		return JsonUtil.getInstance().obj2json(bj);
	}*/
	
	@RequestMapping(value = "/data/add", method = { RequestMethod.POST})
	@ResponseBody
	public void modelDataAdd(HttpServletRequest request, HttpServletResponse response,@RequestParam("jsonModelData") String jsonModelData) throws UnsupportedEncodingException {
		
		BaseJson bj = new BaseJson();
		String str = request.getParameter("institution_id");
		Integer institution_id=null;
		if(str!=null && str!=""){
			institution_id = Integer.valueOf(str);
		}
		
		ModelDataUtil.getInstance().saveModelData(jsonModelData, institution_id);
	    
		//return JsonUtil.getInstance().obj2json(bj);
	}
	
    /**
     * 获取项目数据包括模板和数据
     * @param request
     * @param response
     * @param id 项目id
     * @param institution_id 机构id，如果机构id为空，则返回总表数据，不为空就返回该项目的该机构的数据
     * @return
     * @throws UnsupportedEncodingException
     */
	@RequestMapping(value = "/data/{id}", method = { RequestMethod.GET})
	@ResponseBody
	public String dataGet(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) throws UnsupportedEncodingException {
		
		BaseJson bj = new BaseJson();
		String str = request.getParameter("institution_id");
		Integer institution_id=null;
		if(str!=null && str!=""){
			institution_id = Integer.valueOf(str);
		}
		
/*
		TableDataNode tdn = TableDataUtil.getInstance().dataNode2tableData(id,institution_id);
		int model_id = tableService.getProject(id).getModel().getId();
		ModelNode mn = ModelTreeUtil.getInstance().entityModel2jsonModelget(model_id);	
		ProjectDataNode pdn = new ProjectDataNode();
		pdn.setModel(mn);
		pdn.setTableData(tdn);*/
		
		ModelDataNode mdn = ModelDataUtil.getInstance().getModelData(id, institution_id);
	
		bj.setObj(mdn);
		return JsonUtil.getInstance().obj2json(bj);
	}

	
	

	@RequestMapping(value = "/navbar", method = { RequestMethod.GET})
	@ResponseBody
	public String getNavBar(HttpServletRequest request, HttpServletResponse response,@RequestParam("project_id") Integer project_id,@RequestParam("ins_id") Integer ins_id) {
		
		BaseJson bj = new BaseJson();
		
		
		NavBar nb = ModelDataUtil.getInstance().getNavBar(project_id, ins_id);
		bj.setObj(nb);

		return JsonUtil.getInstance().obj2json(bj);
	}
	
}
