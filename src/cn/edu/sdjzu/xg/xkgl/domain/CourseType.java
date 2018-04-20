package cn.edu.sdjzu.xg.xkgl.domain;
import java.io.Serializable;
import java.util.Set;



public class CourseType implements Comparable<CourseType>,Serializable
{
	private String no;

	private String description;

	private int id;

	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

	public Set<Course> course;

	public CourseType(Integer id, String description, String no){
		this.id = id;
		this.description = description;
		this.no = no;
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


	@Override
	public int compareTo(CourseType o) {
		return 1;
	}
}

