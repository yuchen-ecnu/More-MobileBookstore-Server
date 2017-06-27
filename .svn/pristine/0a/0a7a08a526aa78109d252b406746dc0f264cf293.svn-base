package com.pb.controller.BookLendHistoryController;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pb.controller.BaseController;
import com.pb.json.BaseJson;
import com.pb.json.BookJson;
import com.pb.json.BookLendHistoryItem;
import com.pb.services.bookLendHistory.BookLendHistoryService;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/bookLendHistory")
public class BookLendHistoryController  extends BaseController {
	@RequestMapping(value = "/getBooksLendHistroyByUserId", method = { RequestMethod.POST })
	@ResponseBody
	public String getBooksLendHistroyByUserId (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("userid");
		
		List<BookLendHistoryItem> result = new ArrayList<BookLendHistoryItem>();
		
		int resultCode =bookLendHistoryService.getBookLendHistoryByUserId(id,result);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("用户登录信息已过期，请重新登录");
		}else if(resultCode ==2){
			bj.setRetcode("0002");
			bj.setErrorMsg("无借阅记录");
		}else{
			bj.setRetcode("0000");
			bj.setErrorMsg("获取成功");
			bj.setObj(result);
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
}
