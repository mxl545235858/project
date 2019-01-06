package org.demo.po;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String account;
	private String password;
	private String filepath;
	
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
	public String getfilepath() {
		return filepath;
	}
	public void setfilepath(String filepath) {
		this.filepath = filepath;
	}
	
	
}
