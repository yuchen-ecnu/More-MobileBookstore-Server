package com.pb.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * User entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements java.io.Serializable {

	// Fields

	private Integer id;
	private Institution institution;
	private String username;
	private String password;
	private String nickname;
	private String role;
	private Date createData;
	private Date lastLogin;
	private String headPic;
	private String email;
	@JsonIgnore
	private Set projects = new HashSet(0);
	@JsonIgnore
	private Set subprojects = new HashSet(0);
	@JsonIgnore
	private Set subprojects_1 = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Institution institution, String username, String password, String role, Date createData) {
		this.institution = institution;
		this.username = username;
		this.password = password;
		this.role = role;
		this.createData = createData;
	}

	/** full constructor */
	public User(Institution institution, String username, String password, String nickname, String role,
			Date createData, Date lastLogin, String headPic, String email, Set projects, Set subprojects,
			Set subprojects_1) {
		this.institution = institution;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.role = role;
		this.createData = createData;
		this.lastLogin = lastLogin;
		this.headPic = headPic;
		this.email = email;
		this.projects = projects;
		this.subprojects = subprojects;
		this.subprojects_1 = subprojects_1;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Institution getInstitution() {
		return this.institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreateData() {
		return this.createData;
	}

	public void setCreateData(Date createData) {
		this.createData = createData;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getHeadPic() {
		return this.headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set getProjects() {
		return this.projects;
	}

	public void setProjects(Set projects) {
		this.projects = projects;
	}

	public Set getSubprojects() {
		return this.subprojects;
	}

	public void setSubprojects(Set subprojects) {
		this.subprojects = subprojects;
	}

	public Set getSubprojects_1() {
		return this.subprojects_1;
	}

	public void setSubprojects_1(Set subprojects_1) {
		this.subprojects_1 = subprojects_1;
	}

}