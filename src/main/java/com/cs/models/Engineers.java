package com.cs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.cs.types.Role;

/**
 * This class defines the Engineer/Administrator info
 * 
 * @author chaitanya
 *
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "ID", "userName" }) })
public class Engineers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long userId;
	private boolean isAndroid;
	private String userName;
	private String userPassword;
	private Role userRole;
	private String userFullName;
	private long userContact;
	private String pushToken;
	private long amountWorked;

	public Engineers(String userName, String userPassword, Role userRole, String userFullName, long userContact,
			String pushToken) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
		this.userFullName = userFullName;
		this.userContact = userContact;
		this.pushToken = pushToken;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean isAndroid() {
		return isAndroid;
	}

	public void setAndroid(boolean isAndroid) {
		this.isAndroid = isAndroid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Role getUserRole() {
		return userRole;
	}

	public void setUserRole(Role userRole) {
		this.userRole = userRole;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public long getUserContact() {
		return userContact;
	}

	public void setUserContact(long userContact) {
		this.userContact = userContact;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public long getAmountWorked() {
		return amountWorked;
	}

	public void setAmountWorked(long amountWorked) {
		this.amountWorked = amountWorked;
	}

}
