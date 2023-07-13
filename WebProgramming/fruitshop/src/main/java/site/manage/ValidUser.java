package site.manage;

import java.io.Serializable;

public class ValidUser implements Serializable {
	private int id;
	private String email;
	private String fullname;
	
	public ValidUser(int id, String email, String name) {
		this.id = id;
		this.email = email;
		this.fullname = name;
	}

	public int getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getFullName() {
		return this.fullname;
	}
}
