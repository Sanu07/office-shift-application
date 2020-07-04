package com.test.shiftapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Last_Login_Time_Table")
public class LastLoginModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LAST_LOGIN_ID")
	private int lastLoginId;
	@Column(name = "COGNIZANT_ID")
	private String cognizantId;
	@Column(name = "LAST_LOGIN_TIME")
	private String lastLoginTime;

	public int getLastLoginId() {
		return lastLoginId;
	}

	public void setLastLoginId(int lastLoginId) {
		this.lastLoginId = lastLoginId;
	}

	public String getCognizantId() {
		return cognizantId;
	}

	public void setCognizantId(String cognizantId) {
		this.cognizantId = cognizantId;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
