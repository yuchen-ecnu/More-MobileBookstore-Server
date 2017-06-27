package com.pb.services.bookShelf;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pb.entity.Address;
import com.pb.entity.Book;
import com.pb.entity.Bookstore;
import com.pb.entity.History;
import com.pb.entity.User;
import com.pb.json.BookJson;
import com.pb.json.ReservationJson;
import com.pb.json.ShelfInfoJson;
import com.pb.services.common.CommService;
import com.pb.util.GeoHash;
import com.pb.util.pushutils.PushCilent;

@Service(value = "bookShelfService")
public class BookShelfService extends CommService {

	public int getBooksByUserId(String id, List<BookJson> result) {

		List<User> ul = baseDAO.findByHQL("from User u where u.userId = ?",
				new Object[] { Integer.valueOf(id) });
		if (ul.isEmpty()) {
			return 1;
		}
		User u = ul.get(0);
		Bookstore store = u.getBookstore();
		if (store == null) {
			return 3;
		}
		List<Book> bl = baseDAO.findByHQL(
				"from Book b where b.bookstore.storeId = ?",
				new Object[] { store.getStoreId() });
		if (bl.isEmpty()) {
			return 2;
		}
		for (Book b : bl) {
			result.add(b2bj(b));
		}
		return 0;
	}

	static BookJson b2bj(Book b) {
		BookJson bj = new BookJson();
		bj.setAuthor(b.getAuthor());
		bj.setBinding(b.getBinding());
		bj.setBookId(b.getBookId());
		bj.setBookImageURI(b.getImage());
		bj.setIsbn(b.getIsbn());
		bj.setDescribes(b.getDescribes());
		bj.setDouBanURI(b.getUrl());
		bj.setPage(b.getPage());
		bj.setPrice(b.getPrice());
		bj.setPublisher(b.getPublisher());
		bj.setRating(b.getRating());
		bj.setStoreTime(b.getStoreTime());
		bj.setStoreDescribe(b.getBookstore().getStoreDescribe());
		bj.setStoreId(b.getBookstore().getStoreId());
		bj.setStoreName(b.getBookstore().getStoreName());
		bj.setTitle(b.getTitle());
		bj.setMessage(b.getMessage());
		return bj;
	}

	public int addBook(String id, String iSBN, String userid, String author,
			String url, String binding, String price, String pages,
			String image, String rating, String publisher, String storeTime,
			String title, String summary, String message) {

		List<User> userlist = baseDAO.findByHQL(
				"from User u where u.userId = ?",
				new Object[] { Integer.parseInt(userid) });
		if (userlist.isEmpty()) {
			return 1;
		}
		User u = userlist.get(0);
		u.setScore(u.getScore() + 1);
		baseDAO.update(u);
		Bookstore bs = u.getBookstore();
		Book b = new Book();
		History h = new History();
		b.setAuthor(author.substring(0,author.length()-1));
		b.setBinding(binding);
		b.setBookstore(bs);
		b.setDescribes(" ");
		b.setImage(image);
		b.setIsbn(iSBN);
		b.setMessage(" ");
		b.setPage(pages);
		b.setPrice(price);
		b.setPublisher(publisher);
		b.setRating(rating);
		b.setStoreTime(storeTime);
		b.setTitle(title);
		b.setUrl(url);
		if (message != null) {
			h.setMessage(message);
			b.setMessage(message);
		}
		b.setDescribes(summary);

		h.setBookName(title);
		h.setIsbn(iSBN);
		h.setOperTime(storeTime);
		h.setOperType("上架");
		h.setUser(u);

		try {
			baseDAO.save(b);
			baseDAO.save(h);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
		return 0;
	}

	public int getBooksByBookId(String id, List<BookJson> result) {

		List<Book> booklist = baseDAO.findByHQL(
				"from Book b where b.bookId = ?",
				new Object[] { Integer.parseInt(id) });
		if (booklist.isEmpty()) {
			return 1;
		}
		Book b = booklist.get(0);
		BookJson bj = b2bj(b);
		result.add(bj);
		return 0;
	}

	public int deleteBooksByBookId(String id) {

		List<Book> booklist = baseDAO.findByHQL(
				"from Book b where b.bookId = ?",
				new Object[] { Integer.parseInt(id) });
		Book b = booklist.get(0);
		Bookstore bs = b.getBookstore();
		List<User> userlist = baseDAO.findByHQL(
				"from User u where u.bookstore = ?", new Object[] { bs });
		User u = userlist.get(0);
		int score = u.getScore();
		if (score <= 0) {
			return 1;
		} else {
			u.setScore(score + 1);
		}
		baseDAO.update(u);
		baseDAO.delete(b);
		return 0;
	}

	public int createShelf(String id, String latitude, String longtitude,
			String city, String province, String storeDescribe) {

		List<User> userlist = baseDAO.findByHQL(
				"from User u where u.userId = ?",
				new Object[] { Integer.parseInt(id) });
		User u = userlist.get(0);
		Address address = new Address();
		address.setCity(city);
		address.setProvince(province);
		address.setLatitude(Double.valueOf(latitude));
		address.setLongtitude(Double.valueOf(longtitude));
		List<Integer> Addlist = baseDAO.findBySQL(
				"select max(addr_id) from address", new Object[] {});
		int max_id = Addlist.get(0);
		address.setAddrId(max_id + 1);

		GeoHash g = new GeoHash(Double.parseDouble(latitude),
				Double.parseDouble(longtitude));
		baseDAO.executeSQL(
				"insert into bookstore.address (province, city, latitude, longtitude, addr_id,geohash) values (?, ?, ?, ?, ?,?)",
				new Object[] { province, city, latitude, longtitude,
						max_id + 1, g.getGeoHashBase32() });

		Bookstore bs = new Bookstore();

		List<Integer> storelist = baseDAO.findBySQL(
				"select max(store_id) from bookstore", new Object[] {});
		int max_store_id = storelist.get(0);
		bs.setStoreId(max_store_id + 1);
		bs.setAddress(address);
		bs.setStoreDescribe(storeDescribe);
		bs.setStoreName(u.getUserName() + "的书架");

		baseDAO.executeSQL(
				"insert into bookstore.bookstore (addr_id, store_name, store_describe, store_id) values (?, ?, ?, ?)",
				new Object[] { max_id + 1, u.getUserName() + "的书架",
						storeDescribe, max_store_id + 1 });
		u.setBookstore(bs);
		baseDAO.save(u);
		return 0;
	}

	public int getBooksByShelfId(String id, List<BookJson> result) {
		List<Book> bl = baseDAO.findByHQL(
				"from Book b where b.bookstore.storeId = ?",
				new Object[] { Integer.valueOf(id) });
		if (bl.isEmpty()) {
			return 2;
		}
		for (Book b : bl) {
			result.add(b2bj(b));
		}
		return 0;
	}

	public int getInfosByShelfId(String id, List<ShelfInfoJson> result) {
		int storeid = Integer.valueOf(id);
		List<User> userlist = baseDAO.findByHQL(
				"from User u where u.bookstore.storeId = ?",
				new Object[] { storeid });
		ShelfInfoJson sij = new ShelfInfoJson();
		User u = userlist.get(0);
		sij.setPhone(u.getPhone());
		sij.setImageURI(u.getHeadPic());
		sij.setShelfDescribe(u.getBookstore().getStoreDescribe());
		sij.setShelfName(u.getBookstore().getStoreName());
		result.add(sij);
		return 0;
	}
	public int updateLocation(String id, String latitude, String longtitude) {

		List<User> userlist = baseDAO.findByHQL(
				"from User u where u.userId = ?",
				new Object[] { Integer.parseInt(id) });
		User u = userlist.get(0);
		Address a = u.getBookstore().getAddress();
		a.setLatitude(Double.valueOf(latitude));
		a.setLongtitude(Double.valueOf(longtitude));
		GeoHash g = new GeoHash(Double.parseDouble(latitude),
				Double.parseDouble(longtitude));
		String geohash = g.getGeoHashBase32( );
		a.setGeohash(geohash);
		baseDAO.saveOrUpdate(a);
		return 0;
	}

	public int booking(String id, String bookid) {
		List<Book> booklist = baseDAO.findByHQL(
				"from Book b where b.bookId = ?",
				new Object[] { Integer.parseInt(bookid) });
		List<User> userlist = baseDAO.findByHQL(
				"from User u where u.userId = ?",
				new Object[] { Integer.parseInt(id) });
		Book b = booklist.get(0);
		User u = userlist.get(0);
		if (b.getBookstore().getStoreId() == (u.getBookstore().getStoreId())) {
			return 1;
		} else if (b.getUser() != null) {
			return 2;
		}
		b.setUser(u);
		baseDAO.saveOrUpdate(b);

		List<User> sendToUser = baseDAO.findByHQL(
				"from User user where user.bookstore.storeId = ?",
				new Object[] { b.getBookstore().getStoreId() });
		if (sendToUser.get(0) != null
				&& sendToUser.get(0).getDevicetoken() != null) {
			PushCilent cilent = new PushCilent("593a02e3677baa3e380022ad",
					"odhwtn3yljwfidaipbbepwy42ocnb74n");
			try {
				String title = "预约提醒";
				String text = "您有一本书被预约啦！快去看看吧~";
				cilent.sendAndroidUnicast(sendToUser.get(0).getDevicetoken(),
						title, text, "reverse");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int reservationInfoForSender(String id, List<ReservationJson> result) {
		// TODO Auto-generated method stub
		List<User> userlist = baseDAO.findByHQL(
				"from User u where u.userId = ?",
				new Object[] { Integer.valueOf(id) });
		if (userlist.isEmpty()) {
			return 1;
		}
		if (userlist.get(0).getBookstore() == null) {
			return 2;
		}
		// 找到了这个人拥有的所有书
		List<Book> booklist = baseDAO.findByHQL(
				"from Book b where b.user.userId = ?",
				new Object[] { Integer.valueOf(id) });
		for (Book b : booklist) {
			ReservationJson rj = new ReservationJson();
			rj.setBookName(b.getTitle());
			List<User> ulist = baseDAO.findByHQL("from User u where u.bookstore.storeId = ?" , new Object[]{b.getBookstore().getStoreId()});
			rj.setUserName(ulist.get(0).getUserName());
			rj.setPhone(ulist.get(0).getPhone());
			rj.setBookId(b.getBookId());
			result.add(rj);
		}
 
		return 0;
	}

	public int reservationInfoForReceiver(String id,
			List<ReservationJson> result) {
		// TODO Auto-generated method stub
		List<User> userlist = baseDAO.findByHQL(
				"from User u where u.userId = ?",
				new Object[] { Integer.valueOf(id) });
		if (userlist.isEmpty()) {
			return 1;
		}
		if (userlist.get(0).getBookstore() == null) {
			return 2;
		}
		// 找到了这个人拥有的所有书
		List<Book> booklist = baseDAO.findByHQL(
				"from Book b where b.bookstore.storeId = ?",
				new Object[] { userlist.get(0).getBookstore().getStoreId() });
		for (Book b : booklist) {
			ReservationJson rj = new ReservationJson();
			if (b.getUser() != null) {
				rj.setBookName(b.getTitle());
				rj.setPhone(b.getUser().getPhone());
				rj.setUserName(b.getUser().getUserName());
				rj.setBookId(b.getBookId());
				result.add(rj);
			}
		}

		return 0;
	}

	public int deleteReservationByBookId(String id) {
		List<Book> booklist = baseDAO.findByHQL(
				"from Book b where b.bookId = ?",
				new Object[] { Integer.valueOf(id) });
		Book b = booklist.get(0);
		b.setUser(null);
		baseDAO.update(b);
		return 0;
	}
}
