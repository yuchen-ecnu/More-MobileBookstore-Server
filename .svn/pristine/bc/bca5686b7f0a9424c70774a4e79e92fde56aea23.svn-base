package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Model entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Model implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String type;
	private Integer haveA;
	private Integer haveB;
	private String source;
	private String subject;
	@JsonIgnore
	private Set projects = new HashSet(0);
	@JsonIgnore
	private Set modelrows = new HashSet(0);

	// Constructors

	/** default constructor */
	public Model() {
	}

	/** minimal constructor */
	public Model(String name, String type) {
		this.name = name;
		this.type = type;
	}

	/** full constructor */
	public Model(String name, String type, Integer haveA, Integer haveB, String source, String subject, Set projects,
			Set modelrows) {
		this.name = name;
		this.type = type;
		this.haveA = haveA;
		this.haveB = haveB;
		this.source = source;
		this.subject = subject;
		this.projects = projects;
		this.modelrows = modelrows;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getHaveA() {
		return this.haveA;
	}

	public void setHaveA(Integer haveA) {
		this.haveA = haveA;
	}

	public Integer getHaveB() {
		return this.haveB;
	}

	public void setHaveB(Integer haveB) {
		this.haveB = haveB;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Set getProjects() {
		return this.projects;
	}

	public void setProjects(Set projects) {
		this.projects = projects;
	}

	public Set getModelrows() {
		return this.modelrows;
	}

	public void setModelrows(Set modelrows) {
		this.modelrows = modelrows;
	}

}