package cn.edu.sdjzu.xg.xkgl.domain;
import java.io.Serializable;
import java.util.Set;




public class EduAdmin extends User implements Comparable<EduAdmin>,Serializable
{
	private String name;

	private String no;

	private String sex;

	private String password;

	private String username;

	private int id;

	public Set<Notice> notice;

	public EduAdmin(){
		super();
	}

	public EduAdmin(String password, String username,String name, String no, String sex) {
		this.name = name;
		this.no = no;
		this.sex = sex;
		this.password = password;
		this.username = username;
	}
	public EduAdmin(int id,String password, String username, String name, String no, String sex) {
		this.id = id;
		this.name = name;
		this.no = no;
		this.sex = sex;
		this.password = password;
		this.username = username;
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

	public Set<Notice> getNotice() {
		return notice;
	}

	public void setNotice(Set<Notice> notice) {
		this.notice = notice;
	}

	@Override
	public int compareTo(EduAdmin o) {
		return 1;
	}
}

