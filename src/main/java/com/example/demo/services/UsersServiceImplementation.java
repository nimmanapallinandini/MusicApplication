package com.example.demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepository;
@Service
public class UsersServiceImplementation implements UsersService {
@Autowired
UsersRepository repo;
	@Override
	public String addUser(Users user) {
		repo.save(user);
		return "User is Created and Saved";
	}
	@Override
	public boolean emailExists(String emailid) {
		if(repo.findByEmailid(emailid)==null) {
		return false;
	}else {
		return true;
	}
	}
	@Override
	public boolean validateUser(String emailid, String password) {
		Users user=repo.findByEmailid(emailid);
		String db_password=user.getPassword();
		if(db_password.equals(password)){
			return true;
		}else {
		return false;
	}

}
	@Override
	public String getRole(String emailid) {
		return (repo.findByEmailid(emailid).getRole());
	}
	@Override
	public void updateUser(Users user) {
		// TODO Auto-generated method stub
		repo.save(user);
		
	}
	@Override
	public Users getUser(String emailid) {
		// TODO Auto-generated method stub
		return repo.findByEmailid(emailid);
	}
}
