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
import com.pb.entity.User;
import com.pb.json.BaseJson;
import com.pb.json.ModelTree;
import com.pb.services.userRelated.UserService;
import com.pb.util.JsonUtil;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/creat")
public class InstitutionCreation extends BaseController {
	@RequestMapping(value = "/createcinstitution", method = { RequestMethod.POST })
	@ResponseBody
	public String createinsititution (HttpServletRequest request, HttpServletResponse response) {
		String loginname = request.getParameter("loginname");
		String insitutitionname = request.getParameter("institutionname");
		String password=request.getParameter("password");
		String projectCout=request.getParameter("projectCout");
		String conUserCout=request.getParameter("conUserCout");
		BaseJson bj = new BaseJson();
		return JsonUtil.getInstance().obj2json(bj);
	}
}
