package cn.edu.sdjzu.xg.xkgl.domain;
import java.io.Serializable;
import java.util.Set;




public class ProTitle implements Comparable<ProTitle>,Serializable
{

	
	private String no;

	private String description;

	private int id;

	public Set<Teacher> teacher;

	public ProTitle(){
		super();
	}

	public ProTitle(int id ,String no, String description) {
		this.no = no;
		this.description = description;
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Teacher> getTeacher() {
		return teacher;
	}

	public void setTeacher(Set<Teacher> teacher) {
		this.teacher = teacher;
	}

	@Override
	public int compareTo(ProTitle o) {
		return 2;
	}
}

