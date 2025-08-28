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

}
