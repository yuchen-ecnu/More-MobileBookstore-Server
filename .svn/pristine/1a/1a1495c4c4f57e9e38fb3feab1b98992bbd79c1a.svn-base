package com.pb.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Project entity. @author MyEclipse Persistence Tools
 */

public class Project implements java.io.Serializable {

	// Fields

	private Integer proId;
	private String proName;
	private Date startTime;
	private Float proBudget;
	private Set manages = new HashSet(0);

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** full constructor */
	public Project(String proName, Date startTime, Float proBudget, Set manages) {
		this.proName = proName;
		this.startTime = startTime;
		this.proBudget = proBudget;
		this.manages = manages;
	}

	// Property accessors

	public Integer getProId() {
		return this.proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getProName() {
		return this.proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Float getProBudget() {
		return this.proBudget;
	}

	public void setProBudget(Float proBudget) {
		this.proBudget = proBudget;
	}

	public Set getManages() {
		return this.manages;
	}

	public void setManages(Set manages) {
		this.manages = manages;
	}

}