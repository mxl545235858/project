package org.demo.po;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String account;
	private String password;
	private String donlowdpath;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getdonlowdpath() {
		return donlowdpath;
	}
	public void setdonlowdpath(String donlowdpath) {
		this.donlowdpath = donlowdpath;
	}
	
	
}
