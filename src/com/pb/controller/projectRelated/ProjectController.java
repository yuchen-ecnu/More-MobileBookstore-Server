package com.pb.controller.projectRelated;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Project;
import com.pb.entity.Subproject;
import com.pb.entity.User;
import com.pb.json.BaseJson;
import com.pb.json.ModelTree;
import com.pb.services.projectRelated.ProjectService;
import com.pb.util.JsonUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController {
	
	@RequestMapping(value="/searchProjectInformation",method={ RequestMethod.POST})
	@ResponseBody
	public String searchProInformation(HttpServletRequest request, HttpServletResponse response){
		String name=request.getParameter("name");
		BaseJson bj=new BaseJson();
			List<Project> list=projectService.findByName(name);
			bj.setObj(list);
		
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value="/getProjectInformation",method={ RequestMethod.POST})
	@ResponseBody
	public String getProInformation(HttpServletRequest request,HttpServletResponse response){
		Integer userid=Integer.parseInt(request.getParameter("userid"));
		BaseJson bj=new BaseJson();
			List<Project> list=projectService.findAll(userid);
			bj.setObj(list);
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value="/getProjectRelation",method={RequestMethod.POST})
	@ResponseBody
	public String getProRelation(HttpServletRequest request,HttpServletResponse response){
		Integer projectid=Integer.parseInt(request.getParameter("projectid"));
		BaseJson bj=new BaseJson();
			List<User> list=projectService.findRelated(projectid);
			bj.setObj(list);
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value="/addProjectRelation",method={RequestMethod.POST})
	@ResponseBody
	public String addProRelation(HttpServletRequest request,HttpServletResponse response){
		Integer projectid=Integer.parseInt(request.getParameter("projectid"));
		Integer userid=Integer.parseInt(request.getParameter("userid"));
		BaseJson bj=new BaseJson();
			projectService.addRelation(projectid,userid);
			bj.setObj(projectid+"...."+userid);
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value="/deleteProjectRelation",method={RequestMethod.POST})
	@ResponseBody
	public String deleteProRelation(HttpServletRequest request,HttpServletResponse response){
		Integer projectid=Integer.parseInt(request.getParameter("projectid"));
		Integer userid=Integer.parseInt(request.getParameter("userid"));
		BaseJson bj=new BaseJson();
			projectService.deleteRelation(projectid,userid);
			bj.setObj(projectid+"...."+userid);
		return JsonUtil.getInstance().obj2json(bj);
	}
}