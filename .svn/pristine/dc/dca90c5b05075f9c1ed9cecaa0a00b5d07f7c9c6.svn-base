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
@RequestMapping("/resetPassword")

public class resetPasswordController extends BaseController{
	@RequestMapping(value = "/reset", method = { RequestMethod.POST })
	@ResponseBody
	public String reset(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String psd=request.getParameter("psd");
		String repsd=request.getParameter("repsd");
		BaseJson bj = new BaseJson();
		List<User> list = userService.findPasswordByName(username);
		if(psd.equals(repsd)){    
			bj.setRetcode("success");
			userService.updatePassword(psd,list.get(0));
		}else {
			bj.setErrorMsg("The passwords goes different.");
		}
		bj.setObj(list);
		return JsonUtil.getInstance().obj2json(bj);
		
	}

}
