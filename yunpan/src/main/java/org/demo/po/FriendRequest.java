package org.demo.po;

import java.io.Serializable;

public class FriendRequest implements Serializable{
	private int id;
	private Integer uid;
	private Integer rqid;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getRqid() {
		return rqid;
	}
	public void setRqid(Integer rqid) {
		this.rqid = rqid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
}
