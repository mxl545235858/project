package org.demo.po;

import java.io.Serializable;

public class Share implements Serializable{
	private int id;
	private Integer uid;
	private Integer pathsid;
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
	public Integer getPathsid() {
		return pathsid;
	}
	public void setPathsid(Integer pathsid) {
		this.pathsid = pathsid;
	}

	
}
