package com.pb.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Employee entity. @author MyEclipse Persistence Tools
 */

public class Employee implements java.io.Serializable {

	// Fields

	private Integer empId;
	private Department department;
	private String empName;
	private String phone;
	private Float salary;
	private Integer age;
	private Date jobyear;
	private String job;
	private String gender;
	private Set managesForMgrId = new HashSet(0);
	private Set managesForEmpId = new HashSet(0);

	// Constructors

	/** default constructor */
	public Employee() {
	}

	/** minimal constructor */
	public Employee(String empName, Float salary, String job, String gender) {
		this.empName = empName;
		this.salary = salary;
		this.job = job;
		this.gender = gender;
	}

	/** full constructor */
	public Employee(Department department, String empName, String phone,
			Float salary, Integer age, Date jobyear, String job, String gender,
			Set managesForMgrId, Set managesForEmpId) {
		this.department = department;
		this.empName = empName;
		this.phone = phone;
		this.salary = salary;
		this.age = age;
		this.jobyear = jobyear;
		this.job = job;
		this.gender = gender;
		this.managesForMgrId = managesForMgrId;
		this.managesForEmpId = managesForEmpId;
	}

	// Property accessors

	public Integer getEmpId() {
		return this.empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmpName() {
		return this.empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Float getSalary() {
		return this.salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getJobyear() {
		return this.jobyear;
	}

	public void setJobyear(Date jobyear) {
		this.jobyear = jobyear;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set getManagesForMgrId() {
		return this.managesForMgrId;
	}

	public void setManagesForMgrId(Set managesForMgrId) {
		this.managesForMgrId = managesForMgrId;
	}

	public Set getManagesForEmpId() {
		return this.managesForEmpId;
	}

	public void setManagesForEmpId(Set managesForEmpId) {
		this.managesForEmpId = managesForEmpId;
	}

}