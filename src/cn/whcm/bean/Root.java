package cn.whcm.bean;

public class Root {
	Integer id;
	String name;
	String password;
	public Root() {
	}
	
	
	public Root(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}


	public Root(Integer id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}


	@Override
	public String toString() {
		return "Root [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
