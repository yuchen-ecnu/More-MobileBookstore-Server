package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Modelrow entity. @author MyEclipse Persistence Tools
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Modelrow implements java.io.Serializable {

	// Fields

	private Integer id;
	private Model model;
	private Modelrow modelrow;
	private Feetype feetype;
	private String sequence;
	private String name;
	@JsonIgnore
	private Set modelrows = new HashSet(0);
	@JsonIgnore
	private Set tabledatas = new HashSet(0);

	// Constructors

	/** default constructor */
	public Modelrow() {
	}

	/** minimal constructor */
	public Modelrow(Model model, Feetype feetype, String sequence, String name) {
		this.model = model;
		this.feetype = feetype;
		this.sequence = sequence;
		this.name = name;
	}

	/** full constructor */
	public Modelrow(Model model, Modelrow modelrow, Feetype feetype, String sequence, String name, Set modelrows,
			Set tabledatas) {
		this.model = model;
		this.modelrow = modelrow;
		this.feetype = feetype;
		this.sequence = sequence;
		this.name = name;
		this.modelrows = modelrows;
		this.tabledatas = tabledatas;
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

	public Modelrow getModelrow() {
		return this.modelrow;
	}

	public void setModelrow(Modelrow modelrow) {
		this.modelrow = modelrow;
	}

	public Feetype getFeetype() {
		return this.feetype;
	}

	public void setFeetype(Feetype feetype) {
		this.feetype = feetype;
	}

	public String getSequence() {
		return this.sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
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

	public Set getTabledatas() {
		return this.tabledatas;
	}

	public void setTabledatas(Set tabledatas) {
		this.tabledatas = tabledatas;
	}

}