package com.test.shiftapplication.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "WSR_DETAILS")
public class WSRModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WSR_ID")
	private long WSRId;
	@Column(name = "USER_NAME")
	private String userName;
	@Column(name = "COGNIZANT_ID")
	private String cognizantId;
	@Column(name = "LIST_OF_JIRAS")
	@ElementCollection
	private List<String> listOfJiras;
	@Column(name = "WSR_DATE")
	private String wSRDate;

	public long getWSRId() {
		return WSRId;
	}

	public void setWSRId(long wSRId) {
		WSRId = wSRId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCognizantId() {
		return cognizantId;
	}

	public void setCognizantId(String cognizantId) {
		this.cognizantId = cognizantId;
	}

	public List<String> getListOfJiras() {
		return listOfJiras;
	}

	public void setListOfJiras(List<String> listOfJiras) {
		this.listOfJiras = listOfJiras;
	}

	public String getWSRDate() {
		return wSRDate;
	}

	public void setWSRDate(String wSRDate) {
		this.wSRDate = wSRDate;
	}
}
