package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Feetype entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Feetype implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	@JsonIgnore
	private Set modelrows = new HashSet(0);

	// Constructors

	/** default constructor */
	public Feetype() {
	}

	/** minimal constructor */
	public Feetype(String name) {
		this.name = name;
	}

	/** full constructor */
	public Feetype(String name, Set modelrows) {
		this.name = name;
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

	public Set getModelrows() {
		return this.modelrows;
	}

	public void setModelrows(Set modelrows) {
		this.modelrows = modelrows;
	}

}