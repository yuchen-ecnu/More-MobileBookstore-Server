package com.pb.controller.userRelated;
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
import com.pb.entity.User;
import com.pb.json.BaseJson;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/forgetPassword")

public class forgetPasswordController extends BaseController{
	@RequestMapping(value = "/getCaptcha", method = { RequestMethod.POST })
	@ResponseBody
	public String getEmail(HttpServletRequest request, HttpServletResponse response) {
		String subusername = request.getParameter("subusername");
		BaseJson bj = new BaseJson();
		List<User> list = userService.findPasswordByName(subusername);
		bj.setObj(list);
		return JsonUtil.getInstance().obj2json(bj);
	}
}
