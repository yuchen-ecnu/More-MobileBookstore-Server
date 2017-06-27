package com.pb.controller.bookstore;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.entity.Bookstore;
import com.pb.json.BaseJson;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/bookStore")
public class BookStoreController extends BaseController{
	
	@RequestMapping(value = "/getAllBookStore", method = { RequestMethod.POST })
	@ResponseBody
	public String getAllBookStore (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		List<Bookstore> list = bookStoreService.getAllBookStore();
		
		bj.setObj(list);
		bj.setErrorMsg("获取成功！");
		bj.setRetcode("0000");
		return JsonUtil.getInstance().obj2json(bj);
	}
}
