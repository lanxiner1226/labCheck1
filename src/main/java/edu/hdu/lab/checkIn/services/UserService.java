package edu.hdu.lab.checkIn.services;

import java.util.List;

import edu.hdu.lab.checkIn.model.Address;
import edu.hdu.lab.checkIn.model.User;

public interface UserService {

	public List<User> getUser(String user, String pwd);

	public int addUser(User user);

	public int updateUser(User user);

	public int deleteUser(int id);

	public List<User> getUsers(User user);
	
	public List<User> getUsersbyrole(User user);

	public List<User> getUser(int id);
	
	public int verifyUser(int id, String phonecode);
	
	public Address getAddress(int id);
	//define
	public List<User> getUserByName(User user);
	
}
