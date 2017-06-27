package com.pb.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Department entity. @author MyEclipse Persistence Tools
 */

public class Department implements java.io.Serializable {

	// Fields

	private Integer deptId;
	private String deptName;
	private Integer proNumber;
	private Float budget;
	private Set employees = new HashSet(0);

	// Constructors

	/** default constructor */
	public Department() {
	}

	/** full constructor */
	public Department(String deptName, Integer proNumber, Float budget,
			Set employees) {
		this.deptName = deptName;
		this.proNumber = proNumber;
		this.budget = budget;
		this.employees = employees;
	}

	// Property accessors

	public Integer getDeptId() {
		return this.deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getProNumber() {
		return this.proNumber;
	}

	public void setProNumber(Integer proNumber) {
		this.proNumber = proNumber;
	}

	public Float getBudget() {
		return this.budget;
	}

	public void setBudget(Float budget) {
		this.budget = budget;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

}