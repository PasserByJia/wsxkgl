package cn.edu.sdjzu.xg.xkgl.domain;


import java.io.Serializable;
import java.sql.Date;

public class OpenPeriod  implements Comparable<OpenPeriod>,Serializable
{
	private int id;

	private Date startTime;

	private Date endTime;

	public OpenPeriod(){
		super();
	}

	public OpenPeriod(int id, Date startTime, Date endTime) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public OpenPeriod(Date startTime, Date endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public int compareTo(OpenPeriod o) {
		return 1;
	}
}

