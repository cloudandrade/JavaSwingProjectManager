package model;

public class UserModel {
	protected int id;
	protected String name;
	protected String username;
	protected String email;
	protected String phone;
	protected String password;
	
	public UserModel() {

	}
	
	public UserModel( String name, String username, String email, String phone, String password) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
	
	public UserModel(int id, String name, String username, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
