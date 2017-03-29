package com.java.JDBC;

public class RegistrationDetails {
	String Firstname;
	String Lastname;
	String Email;
	String username;
	String password;
	
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegistrationDetails [Firstname=" + Firstname + ", Lastname=" + Lastname
				+ ", Email=" + Email + ", username=" + username + ", password=" + password + "]";
	}
	
}
