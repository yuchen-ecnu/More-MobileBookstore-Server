package com.pb.entity;

/**
 * History entity. @author MyEclipse Persistence Tools
 */

public class History implements java.io.Serializable {

	// Fields

	private Integer historyId;
	private User user;
	private String bookName;
	private String operTime;
	private String operType;
	private String isbn;
	private String message;

	// Constructors

	/** default constructor */
	public History() {
	}

	/** minimal constructor */
	public History(User user, String operTime) {
		this.user = user;
		this.operTime = operTime;
	}

	/** full constructor */
	public History(User user, String bookName, String operTime,
			String operType, String isbn, String message) {
		this.user = user;
		this.bookName = bookName;
		this.operTime = operTime;
		this.operType = operType;
		this.isbn = isbn;
		this.message = message;
	}

	// Property accessors

	public Integer getHistoryId() {
		return this.historyId;
	}

	public void setHistoryId(Integer historyId) {
		this.historyId = historyId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getOperTime() {
		return this.operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getOperType() {
		return this.operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}