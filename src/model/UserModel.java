package model;

public class UserModel {
	protected int id;
	protected String name;
	protected String user;
	protected String email;
	protected String phone;
	protected String password;
	
	public UserModel() {

	}
	
	public UserModel(int id, String name, String user, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
