package com.rcc.opensourcecodesamplesronc.android.bean;

import java.io.Serializable;

/*
 * Simple java bean for holding user data
 */
public class UserBean implements Serializable{

	private static final long serialVersionUID = -5775662328880315700L;

	
	private long _id;
	private String user;
	private String firstName;
	private String lastName;
	
	/*
	 * getter for user _id
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
	 * getter for user
	 * @return String
	 */
	public String getUser() {
		return user;
	}
	
	/*
	 * setter for user
	 * @param String
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	/*
	 * getter for first name
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/*
	 * setter for first name
	 * @param String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/*
	 * getter for last name
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}
	
	/*
	 * setter for last name
	 * @param String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
