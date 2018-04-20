package cn.edu.sdjzu.xg.xkgl.domain;


import java.io.Serializable;
import java.util.Date;



public class Notice implements Comparable<Notice>,Serializable
{

	
	private int id;

	private String title;

	private String text;

	private Date issueTime;

	public EduAdmin eduAdmin;


	public Notice(int id, String title, String text, Date issueTime, EduAdmin eduAdmin) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.issueTime = issueTime;
		this.eduAdmin = eduAdmin;
	}

	public Notice(String title, String text, Date issueTime, EduAdmin eduAdmin) {
		this.title = title;
		this.text = text;
		this.issueTime = issueTime;
		this.eduAdmin = eduAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	public EduAdmin getEduAdmin() {
		return eduAdmin;
	}

	public void setEduAdmin(EduAdmin eduAdmin) {
		this.eduAdmin = eduAdmin;
	}

	@Override
	public int compareTo(Notice o) {
		return 1;
	}
}

