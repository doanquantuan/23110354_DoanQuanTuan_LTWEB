package services.Impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import model.UserModel;
import services.IUserService;

public class UserService implements IUserService {
	// lay toan bo ham trong tang Dao cua user
	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUserName(username);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname) {
		if (userDao.checkExistEmail(email)) {
			return false;
		}
		
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		userDao.insertregister(new UserModel(email, username, fullname, password, ""));
		return true;
	}



	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public void updatestatus(UserModel user) {
		// TODO Auto-generated method stub
		
	}

}
