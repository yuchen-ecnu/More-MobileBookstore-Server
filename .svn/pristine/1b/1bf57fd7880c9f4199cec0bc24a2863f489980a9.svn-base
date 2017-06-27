package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Institution entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Institution implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private Integer projectCount;
	private Integer conUserCount;
	@JsonIgnore
	private Set tabledatas = new HashSet(0);
	@JsonIgnore
	private Set users = new HashSet(0);

	// Constructors

	/** default constructor */
	public Institution() {
	}

	/** minimal constructor */
	public Institution(String name, Integer projectCount, Integer conUserCount) {
		this.name = name;
		this.projectCount = projectCount;
		this.conUserCount = conUserCount;
	}

	/** full constructor */
	public Institution(String name, Integer projectCount, Integer conUserCount, Set tabledatas, Set users) {
		this.name = name;
		this.projectCount = projectCount;
		this.conUserCount = conUserCount;
		this.tabledatas = tabledatas;
		this.users = users;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProjectCount() {
		return this.projectCount;
	}

	public void setProjectCount(Integer projectCount) {
		this.projectCount = projectCount;
	}

	public Integer getConUserCount() {
		return this.conUserCount;
	}

	public void setConUserCount(Integer conUserCount) {
		this.conUserCount = conUserCount;
	}

	public Set getTabledatas() {
		return this.tabledatas;
	}

	public void setTabledatas(Set tabledatas) {
		this.tabledatas = tabledatas;
	}

	public Set getUsers() {
		return this.users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

}