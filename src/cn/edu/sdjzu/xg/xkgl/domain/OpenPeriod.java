package cn.edu.sdjzu.xg.xkgl.domain;


import java.io.Serializable;
import java.sql.Timestamp;

public class OpenPeriod  implements Comparable<OpenPeriod>,Serializable
{
	private int id;

	private Timestamp startTime;

	private Timestamp  endTime;

	public OpenPeriod(){
		super();
	}

	public OpenPeriod(int id, Timestamp  startTime, Timestamp  endTime) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public OpenPeriod(Timestamp  startTime, Timestamp  endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp  getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp  startTime) {
		this.startTime = startTime;
	}

	public Timestamp  getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp  endTime) {
		this.endTime = endTime;
	}

	@Override
	public int compareTo(OpenPeriod o) {
		return 1;
	}
}

