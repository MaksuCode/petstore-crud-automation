package model.objects.user;

public class User{

	private final String firstName;
	private final String lastName;
	private final String password;
	private final int userStatus;
	private final String phone;
	private final int id;
	private final String email;
	private final String username;

	public User(String firstName, String lastName, String password, int userStatus, String phone, int id, String email, String username) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.userStatus = userStatus;
		this.phone = phone;
		this.id = id;
		this.email = email;
		this.username = username;
	}

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getPassword(){
		return password;
	}

	public int getUserStatus(){
		return userStatus;
	}

	public String getPhone(){
		return phone;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

}
