package cn.edu.sdjzu.xg.xkgl.domain;


import java.io.Serializable;

public class SysAdmin extends User implements Comparable<SysAdmin>,Serializable
{

	private String name;


	private String no;

	private String sex;

	private String password;

	private String username;

	private int id;


	public SysAdmin(int id, String password, String username,String name, String no, String sex) {
		this.name = name;
		this.no = no;
		this.sex = sex;
		this.password = password;
		this.username = username;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(SysAdmin o) {
		return 1;
	}
}

