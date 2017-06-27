package com.pb.services.userRelated;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pb.entity.Bookstore;
import com.pb.entity.User;
import com.pb.services.common.CommService;
@Service(value = "userService")
public class UserService extends CommService {


	public  List<User> findAll() {
		List<User> list = baseDAO.findByHQL("from User");
		return list;
	}
	public List<User> findPasswordByName(String name){
		List<User> list = baseDAO.findByHQL("from User m where m.username = ? ",new Object[] {name } );
		return list;
	}
	
	public List<User> findEmailByUsername(String name){
		List<User> list = baseDAO.findByHQL("from User user where user.username = ? ",new Object[] {name } );
		return list;
	}
	public void updatePassword(String newpsd,User one){
		one.setPassword(newpsd);
		baseDAO.update(one);
	}
	
	/**
	 * 登录
	 * @param phonenumber
	 * @param password
	 * @return
	 */
	public List<User> login(String phonenumber,String password){
		List<User> list = baseDAO.findByHQL("FROM User user WHERE user.phone = ? AND user.password = ? ", new Object[]{phonenumber,password});
		return list;
	}
	
	/**
	 * 注册
	 * @param phonenumber
	 * @param password
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<User> register(String phonenumber, String password,String username){
		List<User> result = new ArrayList<User>();
		List<User> test = baseDAO.findByHQL("FROM User user WHERE user.phone = ? ", new Object[]{phonenumber});
		if(test.size()!=0){
			return result;
		}
		User user = new User();
		user.setPhone(phonenumber);
		user.setPassword(password);
		user.setUserName("未命名");
		user.setScore(0);
		baseDAO.save(user);
		result = baseDAO.findByHQL("FROM User user WHERE user.phone = ? AND user.password = ? ", new Object[]{phonenumber,password});
		return result;
	}
	
	/**
	 * 修改昵称	
	 * @param name
	 * @param id
	 * @return
	 */
	public String updatename(String name,String id){
		List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?",new Object[]{Integer.parseInt(id)});
		if(temp.size()==0) return "";
		User user = temp.get(0);
		if(name!=null) user.setUserName(name);
		if(user.getBookstore()!=null){
			Bookstore b = user.getBookstore();
			b.setStoreName(name + "的书架");
			baseDAO.save(b);
		}
		baseDAO.save(user);
		return name;
	}

    /**
     *修改性别
     *@param gender
     *@id
     *@return
     */
    public String updategender(String gender, String id){
    	List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?",new Object[]{Integer.parseInt(id)});
    	if(temp.size() == 0) return "0001";
    	User user = temp.get(0);
    	if(gender != null) {
    		user.setGender(gender);
        	baseDAO.save(user);
        	return gender;
    	}else{
        	return "0002";	
    	}
    }
    
    /**
     * 注册设备号
     * @param id
     * @param devicetoken
     * @return
     */
    public String registerDevice(String id, String devicetoken){
    	List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?",new Object[]{Integer.parseInt(id)});
    	if(temp.size() == 0) return "0001";
    	User user = temp.get(0);
		user.setDevicetoken(devicetoken);
    	baseDAO.save(user);
    	return "0000";
    }
    
    public String updateheadpic(MultipartHttpServletRequest multipartRequest, String userid){
    	List<User> temp = baseDAO.findByHQL("from User user where user.userId = ?",new Object[]{Integer.parseInt(userid)});
    	if(temp.size() == 0) return "0001";
    	
    	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        /** 构建文件保存的目录* */
        String logoPathDir = "/upload/"
                + dateformat.format(new Date());
        /** 得到文件保存目录的真实路径* */
        String logoRealPathDir = multipartRequest.getSession().getServletContext().getRealPath(logoPathDir);
        /** 根据真实路径创建目录* */
        File logoSaveFile = new File(logoRealPathDir);
        if (!logoSaveFile.exists())
            logoSaveFile.mkdirs();
        /** 页面控件的文件流* */
        MultipartFile multipartFile = multipartRequest.getFile("headpic");
        /** 获取文件的后缀* */
        String suffix = multipartFile.getOriginalFilename().substring(
                multipartFile.getOriginalFilename().lastIndexOf("."));
        /** 使用UUID生成文件名称* */
        String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
        /** 拼成完整的文件保存路径加文件* */
        String fileName = logoRealPathDir + File.separator + logImageName;
        File file = new File(fileName);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException e) {
        	return "0003";
        } catch (IOException e) {
        	return "0003";
        }
        /** 打印出上传到服务器的文件的绝对路径* */
        System.out.println("****************"+fileName+"**************");
    	String uri = "http://115.159.35.11:8080/bookstore"+logoPathDir+"/"+logImageName;
    	User user = temp.get(0);
    	user.setHeadPic(uri);
    	baseDAO.save(user);
    	return uri;
    }
}