package com.pb.controller.user;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pb.controller.BaseController;
import com.pb.entity.Book;
import com.pb.entity.User;
import com.pb.json.BaseJson;
import com.pb.json.BookJson;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	@ResponseBody
	public String login (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String phone = request.getParameter("phonenumber");
		String password = request.getParameter("password");
		List<User> result = userService.login(phone, password);
		if(result.size()==0) {
			bj.setRetcode("0001");
			bj.setErrorMsg("密码错误！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("登陆成功！");
		}
		 
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/register", method = { RequestMethod.POST })
	@ResponseBody
	public String register (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String phonenumber = request.getParameter("phonenumber");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		List<User> result = userService.register(phonenumber, password,username);
		if(result.size()==0) {
			bj.setRetcode("0001");
			bj.setErrorMsg("注册失败！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("注册成功！");
		}
		 
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.POST })
	@ResponseBody
	public String update (HttpServletRequest request, HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String name = request.getParameter("username");
		String id = request.getParameter("id");
		String result = userService.updatename(name,id);
		if (result.equals("")){
			bj.setRetcode("0001");
			bj.setErrorMsg("修改失败！");
		}else{
			bj.setRetcode("0000");
			bj.setObj(result);
			bj.setErrorMsg("修改成功");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}

    @RequestMapping(value = "/updategender", method = {RequestMethod.POST })
    @ResponseBody
    public String updategender (HttpServletRequest request, HttpServletResponse response){
    	BaseJson bj = new BaseJson();
    	String gender = request.getParameter("gender");
    	String id = request.getParameter("id");
    	String result = userService.updategender(gender, id);
    	if(result.equals("0001"))
    	{
    		bj.setRetcode("0001");
    		bj.setErrorMsg("用户不存在");
    	}else if(result.equals("0002")){
    		bj.setErrorMsg("请求参数错误！");
    		bj.setRetcode("0002");
    	}else{
    		bj.setRetcode("0000");
    		bj.setObj(result);
    		bj.setErrorMsg("修改成功");
    	}
    	return JsonUtil.getInstance().obj2json(bj);
    }
    
    @RequestMapping(value = "/updateheadpic", method = {RequestMethod.POST })
    @ResponseBody
    public String updateheadpic (HttpServletRequest request, HttpServletResponse response){
    	BaseJson bj = new BaseJson();
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	String id = request.getParameter("userid");
    	String result = userService.updateheadpic(multipartRequest, id);
    	if(result.equals("0001"))
    	{
    		bj.setRetcode("0001");
    		bj.setErrorMsg("身份信息出错，请重新登陆后尝试！");
    	}else if(result.equals("0003")){
    		bj.setRetcode("0003");
    		bj.setErrorMsg("服务器出错，请联系管理员！");
    	}else{
    		bj.setRetcode("0000");
    		bj.setObj(result);
    		bj.setErrorMsg("修改成功！");
    	}
    	return JsonUtil.getInstance().obj2json(bj);
    }
    
    @RequestMapping(value = "/registerDevice", method = {RequestMethod.POST })
    @ResponseBody
    public String registerDevice (HttpServletRequest request, HttpServletResponse response){
    	BaseJson bj = new BaseJson();
    	String id = request.getParameter("userid");
    	String devicetoken = request.getParameter("devicetoken");
    	if(id==null||devicetoken == null||id.equals("")||devicetoken.equals("")){
    		bj.setRetcode("0002");
    		bj.setErrorMsg("参数错误！");
        	return JsonUtil.getInstance().obj2json(bj);
    	}
    	String result = userService.registerDevice(id, devicetoken);
    	if(result.equals("0001"))
    	{
    		bj.setRetcode("0001");
    		bj.setErrorMsg("身份信息出错，请重新登陆后尝试！");
    	}else{
    		bj.setRetcode("0000");
    		bj.setErrorMsg("修改成功！");
    	}
    	return JsonUtil.getInstance().obj2json(bj);
    }
}
