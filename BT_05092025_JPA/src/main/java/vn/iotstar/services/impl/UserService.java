package vn.iotstar.services.impl;


import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.entity.User;
import vn.iotstar.services.IUserService;

public class UserService implements IUserService {
	
	IUserDao userDao = new UserDaoImpl();


	@Override
	public User login(String username, String password) {
		User user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public User FindByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname) {
		
		return true;
	}

	@Override
	public void updatestatus(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

}
