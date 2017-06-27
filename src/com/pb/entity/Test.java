package com.pb.entity;

import java.sql.Timestamp;

/**
 * Test entity. @author MyEclipse Persistence Tools
 */

public class Test implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bikeid;
	private Timestamp starttime;
	private Timestamp endtime;
	private String startlon;
	private String startlat;
	private String endlon;
	private String endlat;

	// Constructors

	/** default constructor */
	public Test() {
	}

	/** full constructor */
	public Test(String bikeid, Timestamp starttime, Timestamp endtime,
			String startlon, String startlat, String endlon, String endlat) {
		this.bikeid = bikeid;
		this.starttime = starttime;
		this.endtime = endtime;
		this.startlon = startlon;
		this.startlat = startlat;
		this.endlon = endlon;
		this.endlat = endlat;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBikeid() {
		return this.bikeid;
	}

	public void setBikeid(String bikeid) {
		this.bikeid = bikeid;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getStartlon() {
		return this.startlon;
	}

	public void setStartlon(String startlon) {
		this.startlon = startlon;
	}

	public String getStartlat() {
		return this.startlat;
	}

	public void setStartlat(String startlat) {
		this.startlat = startlat;
	}

	public String getEndlon() {
		return this.endlon;
	}

	public void setEndlon(String endlon) {
		this.endlon = endlon;
	}

	public String getEndlat() {
		return this.endlat;
	}

	public void setEndlat(String endlat) {
		this.endlat = endlat;
	}

}