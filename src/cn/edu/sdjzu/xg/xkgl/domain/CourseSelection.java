package cn.edu.sdjzu.xg.xkgl.domain;

import java.io.Serializable;

public class CourseSelection implements Comparable<CourseSelection>,Serializable
{
    private int id;

	public Course course;

	public Student student;

	public CourseSelection(int id, Course course, Student student) {
		this.id = id;
		this.course = course;
		this.student = student;
	}

	public CourseSelection(Course course, Student student) {
		this.course = course;
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public int compareTo(CourseSelection o) {
		return 1;
	}
}

