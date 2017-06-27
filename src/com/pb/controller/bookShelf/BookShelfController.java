package com.pb.controller.bookShelf;

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
import com.pb.json.ReservationJson;
import com.pb.json.ShelfInfoJson;
import com.pb.services.bookShelf.BookShelfService;
import com.pb.util.JsonUtil;

@Controller
@RequestMapping("/bookShelf")
public class BookShelfController  extends BaseController {
	@RequestMapping(value = "/getBooksByUserId", method = { RequestMethod.POST })
	@ResponseBody
	public String getBooksByUserId (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("userid");
		
		List<BookJson> result = new ArrayList<BookJson>();
		
		int resultCode = bookShelfService.getBooksByUserId(id,result);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("用户登录信息已过期，请重新登录");
		}else if(resultCode ==3){
			bj.setRetcode("0003");
			bj.setErrorMsg("没有创建书架");
		}else if(resultCode ==2){
			bj.setRetcode("0002");
			bj.setErrorMsg("书架里还没有书呢~");
		}
		else{
			bj.setRetcode("0000");
			bj.setErrorMsg("获取成功");
			bj.setObj(result);
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/getBooksByShelfId", method = { RequestMethod.POST })
	@ResponseBody
	public String getBooksByShelfId (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("shelfid");
		
		List<BookJson> result = new ArrayList<BookJson>();
		
		int resultCode = bookShelfService.getBooksByShelfId(id,result);
		if(resultCode ==2){
			bj.setRetcode("0002");
			bj.setErrorMsg("书架里还没有书呢~");
		}
		else{
			bj.setRetcode("0000");
			bj.setErrorMsg("获取成功");
			bj.setObj(result);
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	
	@RequestMapping(value = "/getInfosByShelfId", method = { RequestMethod.POST })
	@ResponseBody
	public String getInfosByShelfId (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("shelfid");
		
		List<ShelfInfoJson> result = new ArrayList<ShelfInfoJson>();
		
		int resultCode = bookShelfService.getInfosByShelfId(id,result);
		if(resultCode ==2){
			bj.setRetcode("0002");
			bj.setErrorMsg("书架里还没有书呢~");
		}
		else{
			bj.setRetcode("0000");
			bj.setErrorMsg("获取成功");
			bj.setObj(result);
		}
		return JsonUtil.getInstance().obj2json(bj);
	}

	@RequestMapping(value = "/getBooksByBookId", method = { RequestMethod.POST })
	@ResponseBody
	public String getBooksByBookId (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("bookid");
		
		List<BookJson> result = new ArrayList<BookJson>();
		
		int resultCode = bookShelfService.getBooksByBookId(id,result);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("书籍为检索到");
		}
		else{
			bj.setRetcode("0000");
			bj.setErrorMsg("获取成功");
			bj.setObj(result.get(0));
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/addBook", method = { RequestMethod.POST })
	@ResponseBody
	public String addBook (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("userid");
		String ISBN = request.getParameter("isbn");
		String userid = request.getParameter("userid");
		String author = request.getParameter("author");
		String url = request.getParameter("url");
		String binding = request.getParameter("binding");
		String price = request.getParameter("price");
		String pages = request.getParameter("pages");
		String image = request.getParameter("image");
		String rating = request.getParameter("rating");
		String publisher = request.getParameter("publisher");
		String storeTime = request.getParameter("storetime");
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		String message = request.getParameter("message");
		List<BookJson> result = new ArrayList<BookJson>();
		int resultCode = bookShelfService.addBook(id,ISBN,userid,author,url,binding,price,pages,image,rating,publisher,storeTime,title,summary,message);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("用户登录信息已过期，请重新登录");
		}else if(resultCode ==2){
			bj.setRetcode("0002");
			bj.setErrorMsg("后台出错啦~~~");
		}else{
			bj.setRetcode("0000");
			bj.setErrorMsg("获取成功");
			bj.setObj(result);
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/deleteBooksByBookId", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteBooksByBookId (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("bookid");
		
		int resultCode = bookShelfService.deleteBooksByBookId(id);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("积分不足，请先赚取积分。");
		}else{
			bj.setRetcode("0000");
			bj.setErrorMsg("删除成功");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	

	@RequestMapping(value = "/createShelf", method = { RequestMethod.POST })
	@ResponseBody
	public String createShelf (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("userid");
		String latitude  = request.getParameter("latitude");
		String longtitude =  request.getParameter("longtitude");
		String city =  request.getParameter("city");
		String province =  request.getParameter("province");
		String storeDescribe = request.getParameter("storeDescribe");
		int resultCode = bookShelfService.createShelf(id,latitude,longtitude,city,province,storeDescribe);
			bj.setRetcode("0000");
			bj.setErrorMsg("添加成功");
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/updateLocation", method = { RequestMethod.POST })
	@ResponseBody
	public String updateLocation (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("userid");
		String latitude  = request.getParameter("latitude");
		String longtitude =  request.getParameter("longtitude");
		int resultCode = bookShelfService.updateLocation(id,latitude,longtitude);
			bj.setRetcode("0000");
			bj.setErrorMsg("更新成功");
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/booking", method = { RequestMethod.POST })
	@ResponseBody
	public String booking (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("userid");
		String bookid = request.getParameter("bookid");
		int resultCode = bookShelfService.booking(id,bookid);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("真调皮，不要预定自己的书哦~");
		}else if(resultCode ==2){
			bj.setRetcode("0002");
			bj.setErrorMsg("真可惜，已经被预约了呢...");
		}else{
			bj.setRetcode("0000");
			bj.setErrorMsg("预约成功");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/reservationInfoForReceiver", method = { RequestMethod.POST })
	@ResponseBody
	public String reservationInfoForReceiver (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("userid");
		List<ReservationJson> result = new ArrayList<ReservationJson>();
		int resultCode = bookShelfService.reservationInfoForReceiver(id,result);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("用户信息过时，请重新登录");
		}else if(resultCode ==2){
			bj.setRetcode("0002");
			bj.setErrorMsg("你还没有书架，快去我的书架当中创建一个吧~");
		}else{
			bj.setRetcode("0000");
			bj.setErrorMsg("获取成功");
			bj.setObj(result);
		}
		if(result.isEmpty()){
			bj.setRetcode("0004");
			bj.setErrorMsg("你还没有预约任何书哦");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/reservationInfoForSender", method = { RequestMethod.POST })
	@ResponseBody
	public String reservationInfoForSender (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("userid");
		List<ReservationJson> result = new ArrayList<ReservationJson>();
		int resultCode = bookShelfService.reservationInfoForSender(id,result);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("用户信息过时，请重新登录");
		}else if(resultCode ==2){
			bj.setRetcode("0002");
			bj.setErrorMsg("你还没有书架，快去我的书架当中创建一个吧~");
		}else{
			bj.setRetcode("0000");
			bj.setErrorMsg("获取成功");
			bj.setObj(result);
		}
		if(result.isEmpty()){
			bj.setRetcode("0004");
			bj.setErrorMsg("你还没有预约任何书哦");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
	@RequestMapping(value = "/deleteReservationByBookId", method = { RequestMethod.POST })
	@ResponseBody
	public String deleteReservationByBookId (HttpServletRequest request,
			HttpServletResponse response){
		BaseJson bj = new BaseJson();
		String id = request.getParameter("bookid");
		
		int resultCode = bookShelfService.deleteReservationByBookId(id);
		if(resultCode ==1){
			bj.setRetcode("0001");
			bj.setErrorMsg("积分不足，请先赚取积分。");
		}else{
			bj.setRetcode("0000");
			bj.setErrorMsg("删除成功");
		}
		return JsonUtil.getInstance().obj2json(bj);
	}
	
}
