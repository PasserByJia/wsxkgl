package cn.edu.sdjzu.xg.xkgl.domain;
import java.io.Serializable;
import java.util.Set;

public class Teacher extends User implements Comparable<Teacher>,Serializable
{

	private int id;

	private String username;

	private String password;

	private String sex;

	private String no;

	private String name;

	public Set<Course> course;

	public ProTitle proTitle;


	public Teacher(int id, String username, String password, String name, String no, String sex, ProTitle proTitle) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.no = no;
		this.name = name;
		this.proTitle = proTitle;
	}
	public Teacher(String username, String password, String name, String no, String sex, ProTitle proTitle) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.no = no;
		this.name = name;
		this.proTitle = proTitle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

	public ProTitle getProTitle() {
		return proTitle;
	}

	public void setProTitle(ProTitle proTitle) {
		this.proTitle = proTitle;
	}

	@Override
	public int compareTo(Teacher o) {
		return 1;
	}
}

