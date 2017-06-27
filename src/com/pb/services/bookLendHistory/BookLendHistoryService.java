package com.pb.services.bookLendHistory;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Book;
import com.pb.entity.Bookstore;
import com.pb.entity.User;
import com.pb.json.BookJson;
import com.pb.json.BookLendHistoryItem;
import com.pb.services.common.CommService;


@Service(value = "bookLendHistory")
public class BookLendHistoryService  extends CommService{

	public int getBookLendHistoryByUserId(String id, List<BookLendHistoryItem> result) {
		// TODO Auto-generated method stub
		List<User> ul = baseDAO.findByHQL("from User u where u.userId = ?", new Object[]{Integer.valueOf(id)});
		if(ul.isEmpty()){
			return 1;
		}
		List<BookLendHistoryItem> bl = baseDAO.findBySQLForVO("SELECT book_name as bookName,oper_time as operTime,oper_type as operType,isbn as ISBN,message as message FROM history WHERE user_id = ?",BookLendHistoryItem.class,new Object[]{Integer.parseInt(id)});
		if(bl.isEmpty()){
			return 2;
		}
		for(BookLendHistoryItem b:bl){
			result.add(b);
		}
		return 0;
	}
}
