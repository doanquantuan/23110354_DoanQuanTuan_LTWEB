package dao;

import java.util.List;

import model.UserModel;

public interface IUserDao {
	List<UserModel> findAll();
	
	UserModel findByID(int id);
	
	UserModel findOne(int id);
	
	UserModel findOne(String username);
	
	void insert(UserModel user);
	
	UserModel findByUserName(String username);
	
	void insertregister(UserModel user);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	void updatestatus(UserModel user);
}
