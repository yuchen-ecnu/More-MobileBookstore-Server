package com.pb.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Project implements java.io.Serializable {

	// Fields

	private Integer id;
	private Model model;
	private User user;
	private String name;
	private String origin;
	private String subject;
	private String technology;
	private String keywords;
	private Integer haveBranch;
	private Date createDate;
	private Date lastModifyDate;
	@JsonIgnore
	private Set tabledatas = new HashSet(0);
	@JsonIgnore
	private Set subprojects = new HashSet(0);

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** minimal constructor */
	public Project(Model model, User user, String name, Integer haveBranch, Date createDate) {
		this.model = model;
		this.user = user;
		this.name = name;
		this.haveBranch = haveBranch;
		this.createDate = createDate;
	}

	/** full constructor */
	public Project(Model model, User user, String name, String origin, String subject, String technology,
			String keywords, Integer haveBranch, Date createDate, Date lastModifyDate, Set tabledatas,
			Set subprojects) {
		this.model = model;
		this.user = user;
		this.name = name;
		this.origin = origin;
		this.subject = subject;
		this.technology = technology;
		this.keywords = keywords;
		this.haveBranch = haveBranch;
		this.createDate = createDate;
		this.lastModifyDate = lastModifyDate;
		this.tabledatas = tabledatas;
		this.subprojects = subprojects;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Model getModel() {
		return this.model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTechnology() {
		return this.technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getHaveBranch() {
		return this.haveBranch;
	}

	public void setHaveBranch(Integer haveBranch) {
		this.haveBranch = haveBranch;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastModifyDate() {
		return this.lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	public Set getTabledatas() {
		return this.tabledatas;
	}

	public void setTabledatas(Set tabledatas) {
		this.tabledatas = tabledatas;
	}

	public Set getSubprojects() {
		return this.subprojects;
	}

	public void setSubprojects(Set subprojects) {
		this.subprojects = subprojects;
	}

}