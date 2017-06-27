package com.pb.controller.template;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Model;
import com.pb.entity.Modelrow;
import com.pb.json.BaseJson;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/template")
public class TemplateController extends BaseController {
	
	
	/**
	 * @return 得到所有模板
	 */
	@RequestMapping(value = "/getModel", method = { RequestMethod.GET })
	@ResponseBody
	public String getModel(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("-------getModel---------");
		BaseJson bj = new BaseJson();
		List<Model> list = templateService.findModel();
		bj.setObj(list);
		for (Model model : list) {
			System.out.println(model);
		}
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}

	
	/**
	 * 得到所有模板数据
	 */
	@RequestMapping(value = "/getModelRow", method = { RequestMethod.GET })
	@ResponseBody
	public String getModelRow(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("-------getModelRow---------");
		BaseJson bj = new BaseJson();
		List<Modelrow> list = templateService.findModelrow();
		bj.setObj(list);
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}

	/**
	 * 通过名字得到模板
	 */
	@RequestMapping(value = "/getModelByName", method = { RequestMethod.POST })
	@ResponseBody
	public String getModelByName(HttpServletRequest request,
			HttpServletResponse response) {
		String name = request.getParameter("name");
		System.out.println("-------getModelByName--------");
		BaseJson bj = new BaseJson();
		List<Model> list = templateService.findModelByName(name);
		for (Model a : list) {
			System.out.println(a);
		}
		bj.setObj(list);
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}

	
	/**
	 * 暂时没用
	 */
	@RequestMapping(value = "/updateModel", method = { RequestMethod.POST })
	@ResponseBody
	public void updateModel(HttpServletRequest request,
			HttpServletResponse response) {
		String Id = request.getParameter("id");
		int id = Integer.parseInt(Id);
		List<Model> list = templateService.findModelById(id);
		templateService.updateModel(id, list.get(0));
	}

	/**
	 * 模板预览接口
	 */
	@RequestMapping(value = "/preview", method = { RequestMethod.POST })
	@ResponseBody
	public String preview(HttpServletRequest request, HttpServletResponse response) {
		String Id = request.getParameter("id");
		int id = Integer.parseInt(Id);
		List<Modelrow> list = templateService.selectByModelId(id);
		BaseJson bj = new BaseJson();
		bj.setObj(list);
		System.out.println(JsonUtil.getInstance().obj2json(bj));
		return JsonUtil.getInstance().obj2json(bj);
	}
}
