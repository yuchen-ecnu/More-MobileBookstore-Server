package com.pb.entity;

import java.sql.Timestamp;

/**
 * MobikeId entity. @author MyEclipse Persistence Tools
 */

public class MobikeId implements java.io.Serializable {

	// Fields

	private Timestamp time;
	private Integer biketype;
	private String distid;
	private String lon;
	private String lat;

	// Constructors

	/** default constructor */
	public MobikeId() {
	}

	/** full constructor */
	public MobikeId(Timestamp time, Integer biketype, String distid,
			String lon, String lat) {
		this.time = time;
		this.biketype = biketype;
		this.distid = distid;
		this.lon = lon;
		this.lat = lat;
	}

	// Property accessors

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getBiketype() {
		return this.biketype;
	}

	public void setBiketype(Integer biketype) {
		this.biketype = biketype;
	}

	public String getDistid() {
		return this.distid;
	}

	public void setDistid(String distid) {
		this.distid = distid;
	}

	public String getLon() {
		return this.lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return this.lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MobikeId))
			return false;
		MobikeId castOther = (MobikeId) other;

		return ((this.getTime() == castOther.getTime()) || (this.getTime() != null
				&& castOther.getTime() != null && this.getTime().equals(
				castOther.getTime())))
				&& ((this.getBiketype() == castOther.getBiketype()) || (this
						.getBiketype() != null
						&& castOther.getBiketype() != null && this
						.getBiketype().equals(castOther.getBiketype())))
				&& ((this.getDistid() == castOther.getDistid()) || (this
						.getDistid() != null && castOther.getDistid() != null && this
						.getDistid().equals(castOther.getDistid())))
				&& ((this.getLon() == castOther.getLon()) || (this.getLon() != null
						&& castOther.getLon() != null && this.getLon().equals(
						castOther.getLon())))
				&& ((this.getLat() == castOther.getLat()) || (this.getLat() != null
						&& castOther.getLat() != null && this.getLat().equals(
						castOther.getLat())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result
				+ (getBiketype() == null ? 0 : this.getBiketype().hashCode());
		result = 37 * result
				+ (getDistid() == null ? 0 : this.getDistid().hashCode());
		result = 37 * result
				+ (getLon() == null ? 0 : this.getLon().hashCode());
		result = 37 * result
				+ (getLat() == null ? 0 : this.getLat().hashCode());
		return result;
	}

}