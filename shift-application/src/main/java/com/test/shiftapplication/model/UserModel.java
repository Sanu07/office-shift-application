package com.test.shiftapplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_DETAILS")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private long userId;
	private String name;
	private String cognizantId;
	private String email;
	private String phone;
	private String password;
	private String imageURL;
	private String lastloginTime;

	public String getLastloginTime() {
		return lastloginTime;
	}

	public void setLastloginTime(String lastloginTime) {
		this.lastloginTime = lastloginTime;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCognizantId() {
		return cognizantId;
	}

	public void setCognizantId(String cognizantId) {
		this.cognizantId = cognizantId;
	}

	public UserModel() {
		super();
	}

	public UserModel(long userId, String name, String cognizantId, String email, String phone, String password,
			String imageURL, String lastloginTime) {
		super();
		this.userId = userId;
		this.name = name;
		this.cognizantId = cognizantId;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.imageURL = imageURL;
		this.lastloginTime = lastloginTime;
	}
}
