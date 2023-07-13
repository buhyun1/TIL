package site.bean;

import java.io.Serializable;

public class Student extends Person {
	private int id = 0;
	private String major = null;
	private boolean fullTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public boolean isFullTime() {
		return fullTime;
	}

	public void setFullTime(boolean fullTime) {
		this.fullTime = fullTime;
	}
}