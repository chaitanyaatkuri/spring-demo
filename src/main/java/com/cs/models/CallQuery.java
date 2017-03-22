package com.cs.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.cs.types.QueryStatus;

/**
 * This class defines the call information.
 * 
 * @author chaitanya
 *
 */
@Entity
public class CallQuery {

	private Date dateOfCall = new Date();
	@Id
	@GeneratedValue
	@Column(unique = true)
	private long callId;
	private String customerName;
	private long customerContact;
	private String customerEmail;
	private String callQuery;
	private String engineerAssigned;
	private long engineerUserId;
	private long customerUserId;
	private QueryStatus callStatus;
	private long amount;
	private String paymentMethod;
	private String remarks;
	private String attachement;
	private Date dateToAttend;

	public Date getDateOfCall() {
		return dateOfCall;
	}

	public void setDateOfCall(Date dateOfCall) {
		this.dateOfCall = dateOfCall;
	}

	public long getCallId() {
		return callId;
	}

	public void setCallId(long callId) {
		this.callId = callId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(long customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCallQuery() {
		return callQuery;
	}

	public void setCallQuery(String callQuery) {
		this.callQuery = callQuery;
	}

	public String getEngineerAssigned() {
		return engineerAssigned;
	}

	public void setEngineerAssigned(String engineerAssigned) {
		this.engineerAssigned = engineerAssigned;
	}

	public QueryStatus getCallStatus() {
		return callStatus;
	}

	public void setCallStatus(QueryStatus callStatus) {
		this.callStatus = callStatus;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public long getEngineerUserId() {
		return engineerUserId;
	}

	public void setEngineerUserId(long userId) {
		this.engineerUserId = userId;
	}

	public long getCustomerUserId() {
		return customerUserId;
	}

	public void setCustomerUserId(long customerUserId) {
		this.customerUserId = customerUserId;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

	public Date getDateToAttend() {
		return dateToAttend;
	}

	public void setDateToAttend(Date dateToAttend) {
		this.dateToAttend = dateToAttend;
	}

}
