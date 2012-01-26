package com.rcc.opensourcecodesamplesronc.android.bean;

import java.io.Serializable;

/*
 * Simple java bean for Email address info
 */
public class EmailBean implements Serializable{

	private static final long serialVersionUID = -5311778304927005169L;
	
	private long _id;
	private long user_id;
	private String emailAddress;
	
	/*
	 * getter for email _id
	 * @return long
	 */
	public long get_id() {
		return _id;
	}
	
	/*
	 * setter for _id
	 * @param long
	 */
	public void set_id(long _id) {
		this._id = _id;
	}
	
	/*
	 * getter for user id
	 * @return long
	 */
	public long getUser_id() {
		return user_id;
	}
	
	/*
	 * setter for user id
	 * @param long
	 */
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	
	/*
	 * getter for email address
	 * @return String
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/*
	 * setter for email address
	 * @param String
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
