package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.User;

public interface IUserDao {
	List<User> findAll();
	
	User findByID(int id);
	
	User findOne(int id);
	
	User findOne(String username);
	
	void insert(User user);
	
	User findByUserName(String username);
	
	void insertregister(User user);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	void updatestatus(User user);

	boolean updateCode(String email, String code);

	boolean updatePassword(String email, String newPassword);
}
