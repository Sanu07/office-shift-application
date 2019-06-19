package com.test.shiftapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS_DETAILS")
@NamedQuery(name = "StatusModel.findAll", query = "select s from StatusModel s order by s.statusId")
public class StatusModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STATUS_ID")
	private long statusId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "COGNIZANT_ID")
	private String cognizantId;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "STATUS_UPDATED_TIME")
	private String time;
	@Column(name = "STATUS_UPDATED_DATE")
	private String date;

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCognizantId() {
		return cognizantId;
	}

	public void setCognizantId(String cognizantId) {
		this.cognizantId = cognizantId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StatusModel() {
		super();
		// TODO Auto-generated constructor stub
	}
}
