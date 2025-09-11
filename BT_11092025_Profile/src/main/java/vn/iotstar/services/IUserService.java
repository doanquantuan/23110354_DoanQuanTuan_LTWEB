package vn.iotstar.services;


import vn.iotstar.entity.User;

public interface IUserService {
	User login(String username, String password);
	
	User FindByUserName(String username);
	
	boolean register(String email, String password, String username, String fullname);
	
	void updatestatus(User user);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	void updateprofile(int userId, String fullName, String phone, String images);
}
