package com.cs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "userId", "userName" }) })
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String userName;
	@Column(unique = true)
	private long userContact;
	private String userEmail;
	private String userProfilePic;
	private boolean otpVerified;
	private String pushToken;

	
	public Customers(String userName, long userContact, String userEmail, boolean otpVerified, String pushToken) {
		super();
		this.userName = userName;
		this.userContact = userContact;
		this.userEmail = userEmail;
		this.otpVerified = otpVerified;
		this.pushToken = pushToken;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getUserContact() {
		return userContact;
	}

	public void setUserContact(long userContact) {
		this.userContact = userContact;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserProfilePic() {
		return userProfilePic;
	}

	public void setUserProfilePic(String userProfilePic) {
		this.userProfilePic = userProfilePic;
	}

	public boolean isOtpVerified() {
		return otpVerified;
	}

	public void setOtpVerified(boolean otpVerified) {
		this.otpVerified = otpVerified;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

}
