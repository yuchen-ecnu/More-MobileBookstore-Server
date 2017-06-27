package com.pb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Subproject entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Subproject implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Project project;
	private String role;//角色，管理或关联
	private String num;//子项目编号

	// Constructors

	/** default constructor */
	public Subproject() {
	}

	/** full constructor */
	public Subproject(User user, Project project, String role, String num) {
		this.user = user;
		this.project = project;
		this.role = role;
		this.num = num;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

}