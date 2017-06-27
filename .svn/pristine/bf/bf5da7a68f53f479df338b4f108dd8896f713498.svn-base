package com.pb.controller.userRelated;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Institution;
import com.pb.json.BaseJson;
import com.pb.json.ModelTree;
import com.pb.util.JsonUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/institution")
public class InstitiutionController extends BaseController {
	// TODO ①
	@RequestMapping(value = "/getInstitution", method = { RequestMethod.POST })
	@ResponseBody
	public String getInstitution(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		System.out.println("---name---"+name);
		BaseJson bj = new BaseJson();
		List<Institution> list = institutionService.findAll();
		bj.setObj(list);

		 return JsonUtil.getInstance().obj2json(bj);
	
	 }
	/*
	@RequestMapping(value = "/test", method = { RequestMethod.GET })
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {
		
		BaseJson bj = new BaseJson();
		 ModelTree root = new ModelTree("0", "0", "000000", "00JJJ");
	        ModelTree node = null;
	        node = new ModelTree("1", "0", "111111", "11AAA");
	        root.add(node);
	        node = new ModelTree("2", "0", "222222", "11BBB");
	        root.add(node);
	        node = new ModelTree("3", "2", "333333", "11CCC");
	        root.add(node);
	        // JSONObject obj = JSONObject.fromObject(root);//有根
	        JSONArray obj = JSONArray.fromObject(root.getChildren());// 不要根
	        System.out.println(obj.toString());
		 return JsonUtil.getInstance().obj2json(root);
	 }*/
	 
}