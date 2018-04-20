package cn.edu.sdjzu.xg.xkgl.domain;
import java.io.Serializable;
import java.util.Set;



public class Student extends User implements Comparable<Student>,Serializable {

	private int id;

	private String username;

	private String password;

	private String sex;

	private String no;

	private String name;


	public Set<CourseSelection> courseSelection;


	public Student(){
		super();
	}

	public Student(int id, String username, String password, String sex, String no, String name) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.no = no;
		this.name = name;
	}
	public Student(String username, String password, String sex, String no, String name) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.no = no;
		this.name = name;
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

	public Set<CourseSelection> getCourseSelection() {
		return courseSelection;
	}

	public void setCourseSelection(Set<CourseSelection> courseSelection) {
		this.courseSelection = courseSelection;
	}

	@Override
	public int compareTo(Student o) {
		return 1;
	}
}

