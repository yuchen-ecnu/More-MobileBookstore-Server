package com.pb.services.bookstore;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Bookstore;
import com.pb.services.common.CommService;

@Service(value = "BookStoreService")
public class BookStoreService extends CommService {
	/**
	 * 获取所有书屋的位置信息
	 * @return
	 * 		所有单车的位置信息List
	 */
	public List<Bookstore> getAllBookStore() {
		List<Bookstore> result = baseDAO.findByHQL("from Bookstore bs");
		
		return result;
	}
}