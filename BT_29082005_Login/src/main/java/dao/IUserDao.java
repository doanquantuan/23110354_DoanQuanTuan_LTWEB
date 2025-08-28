package dao;

import java.util.List;

import model.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findByID(int id);
	
	void insert(UserModel user);
	
	UserModel findByUserName(String username);
}
