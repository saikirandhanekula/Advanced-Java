package com.java.JDBC;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class RegistrationDemo {
	Connection con;
	private static final String CREATE_TABLE = "create table registration (Firstname varchar(20) not null, Lastname varchar(20) not null, Email varchar(20) not null, Username varchar(20) not null, Password varchar(20) not null)";
	private static final String INSERT_REGISTER = "insert into registration values(?,?,?,?,?)";
	private static final String UPDATE_EMAIL = "update registration set Email = ? where Username =?";
	private static final String DELETE_EMAIL = "delete from registration where Email = ? and Username = ?";
	private static final String PRINT_DETAILS = "select Firstname,Lastname from registration where Username = 's";

	public void getConnection(){
		try{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/mydb", "postgres", "SaiKiran16");
			
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException s){
			s.printStackTrace();
		}
	}

	public  void createRegistration() {
		boolean isTableExists = false;
		Statement stmt = null;
		try{
			getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select exists (select 1 from pg_tables where schemaname = 'public' and tablename = 'registration')");
			while(rs.next()){
				isTableExists = rs.getBoolean(1);
			}
			if(!isTableExists){
				stmt.execute(CREATE_TABLE);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
		finally{
			try{
				stmt.close();
			}
			catch(SQLException sq){
				sq.printStackTrace();
			}
		}
	}
	public void saveRegistrationInfo(RegistrationDetails info){
		PreparedStatement ps = null;
		try{
			getConnection();
			ps = con.prepareStatement(INSERT_REGISTER);
			ps.setString(1, info.getFirstname());
			ps.setString(2, info.getLastname());
			ps.setString(3, info.getEmail());
			ps.setString(4, info.getUsername());
			ps.setString(5, info.getPassword());
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				ps.close();
			}
			catch(SQLException s){
				s.printStackTrace();
			}
		}
	}
	public void updateEmail(String email, String username){
		PreparedStatement ps = null;
		try{
			getConnection();
			ps = con.prepareStatement(UPDATE_EMAIL);
			ps.setString(1, email);
			ps.setString(2, username);
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				ps.close();
			}
			catch(SQLException s){
				s.printStackTrace();
			}
		}
	}
	public void deleteEmail(String email, String username){
		PreparedStatement ps = null;
		try{
			getConnection();
			ps = con.prepareStatement(DELETE_EMAIL);
			ps.setString(1, email);
			ps.setString(2, username);
			ps.executeUpdate();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				ps.close();
			}
			catch(SQLException s){
				s.printStackTrace();
			}
		}
	}
	public void getDetails(){
		PreparedStatement ps = null;
		ArrayList<RegistrationDetails> al = new ArrayList<>();
		try{
			getConnection();
			ps = con.prepareStatement(PRINT_DETAILS);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				RegistrationDetails rd = new RegistrationDetails();
				String fn = rs.getString(1);
				String ln = rs.getString(2);
				rd.setFirstname(fn);
				rd.setEmail(ln);
				al.add(rd);
				
			}
			System.out.println(al.get(0).getFirstname().toString()+"\t"+al.get(1).getLastname().toString());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				ps.close();
			}
			catch(SQLException s){
				s.printStackTrace();
			}
		}
	}

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		RegistrationDetails details = new RegistrationDetails();
		ArrayList<String> userdata = new ArrayList<>();
		RegistrationDemo demo = new RegistrationDemo();
		System.out.println("Enter no. of users");
		int size = sc.nextInt();
		for(int i=0; i<size; i++){
			System.out.println("Enter your First name");
			String firstname = sc.next();
			details.setFirstname(firstname);
			System.out.println("Enter your Last name");
			String lastname = sc.next();
			details.setLastname(lastname);
			System.out.println("Enter your E-mail");
			String email = sc.next();
			details.setEmail(email);
			System.out.println("Enter your Username");
			String username = sc.next();
			details.setUsername(username);
			System.out.println("Enter your Password");
			String password = sc.next();
			details.setPassword(password);
			userdata.add(i, "details");
		demo.getConnection();
		demo.createRegistration();
		demo.saveRegistrationInfo(details);
		}
		userdata.get(0);
		demo.deleteEmail("saikiran@gmail.com", "saikiranrocks");
		demo.getDetails();
		sc.close();
	}
}