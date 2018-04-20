package cn.edu.sdjzu.xg.xkgl.domain;

import java.io.Serializable;
import java.util.Set;

public class Course implements Comparable<Course>,Serializable
{
	private int id;

	private String no;

	private String title;

	private int max;

	private int min;
	
	private int accumulation;

	private int hours;

	private String time;

	private int credit;

	private boolean status;

	public Set<CourseSelection> courseSelection;

	public Teacher teacher;

	public CourseType courseType;


	public Course(String no,
				  String title,
				  int max,
				  int min,
				  int accumulation,
				  int hours,
				  String time,
				  int credit,
				  boolean status,
				  Teacher teacher,
				  CourseType courseType){
		this.no = no;
		this.title = title;
		this.max = max;
		this.min = min;
		this.accumulation = accumulation;
		this.hours = hours;
		this.time = time;
		this.credit = credit;
		this.status = status;
		this.teacher = teacher;
		this.courseType = courseType;
	}

	public Course(int id,
				  String no,
				  String title,
				  int max,
				  int min,
				  int accumulation,
				  int hours,
				  String time,
				  int credit,
				  boolean status,
				  Teacher teacher,
				  CourseType courseType) {
		this.id = id;
		this.no = no;
		this.title = title;
		this.max = max;
		this.min = min;
		this.accumulation = accumulation;
		this.hours = hours;
		this.time = time;
		this.credit = credit;
		this.status = status;
		this.teacher = teacher;
		this.courseType = courseType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getAccumulation() {
		return accumulation;
	}

	public void setAccumulation(int accumulation) {
		this.accumulation = accumulation;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Set<CourseSelection> getCourseSelection() {
		return courseSelection;
	}

	public void setCourseSelection(Set<CourseSelection> courseSelection) {
		this.courseSelection = courseSelection;
	}

	public  Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	@Override
	public int compareTo(Course o) {
		return 1;
	}
}

