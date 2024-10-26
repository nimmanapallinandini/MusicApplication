package com.example.demo.services;

import com.example.demo.entities.Users;

public interface UsersService {
	public String addUser(Users user);
	public boolean emailExists(String emailid);
	public boolean validateUser(String emailid, String password);
	public String getRole(String emailid);
	public void updateUser(Users user);
	public Users getUser(String emailid);

}
