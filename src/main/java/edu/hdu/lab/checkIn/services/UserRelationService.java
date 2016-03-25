package edu.hdu.lab.checkIn.services;

import java.util.List;

import edu.hdu.lab.checkIn.model.Address;
import edu.hdu.lab.checkIn.model.User;
import edu.hdu.lab.checkIn.model.UserRelation;

public interface UserRelationService {

	public int addUserRelation(UserRelation user);

	public int updateUserRelation(UserRelation user);

	public int deleteUserRelation(UserRelation user);

	public List<UserRelation> getUsers(UserRelation user);

	public List<UserRelation> getUser(int id);
	
}
