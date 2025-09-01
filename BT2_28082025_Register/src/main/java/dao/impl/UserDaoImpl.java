package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import configs.DBConnectionSQL;
import dao.IUserDao;
import model.UserModel;

public class UserDaoImpl extends DBConnectionSQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;

	@Override
	public List<UserModel> findAll() {
		String sql = "SELECT * FROM [users]";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				List<UserModel> list = new ArrayList<UserModel>();
				list.add(new UserModel(
						rs.getInt("id"),
						rs.getString("email"),
						rs.getString("userName"),
						rs.getString("fullName"),
						rs.getString("passWord"),
						rs.getString("code")));
		
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO [users] (id, email, userName, fullName, passWord) VALUES (?, ?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getEmail());
			ps.setString(3,  user.getUserName());
			ps.setString(4, user.getFullName());
			ps.setString(5, user.getPassWord());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			UserDaoImpl userDao = new UserDaoImpl();
			System.out.println(userDao.findByUserName("ABCD"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM [users] WHERE userName = ? ";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setFullName(rs.getString("fullName"));
				user.setPassWord(rs.getString("passWord"));
			
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel findByID(int id) {
		String sql = "SELECT * FROM [users] WHERE id = ? ";
		try {
			conn = new DBConnectionSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(2, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setFullName(rs.getString("fullName"));
				user.setPassWord(rs.getString("passWord"));
			
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public UserModel findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public UserModel findOne(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void insertregister(UserModel user) {
		String sql = "Insert INTO Users (email, username, fullname, password) "
	               + "Values (?,?,?,?)";
	    try {
	        conn = new DBConnectionSQL().getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, user.getEmail());
	        ps.setString(2, user.getUserName());
	        ps.setString(3, user.getFullName());
	        ps.setString(4, user.getPassWord());

	        ps.executeUpdate();
	        ps.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
	}


	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
	    String sql = "Select * From Users where email = ?";
	    try {
	        conn = new DBConnectionSQL().getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, email);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            duplicate = true;
	        }
	        ps.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return duplicate;
	}


	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
	    String sql = "Select * From users where username = ?";
	    try {
	        conn = new DBConnectionSQL().getConnection();
	        ps = conn.prepareStatement(sql);
	        ps.setString(1, username);
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            duplicate = true;
	        }
	        ps.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return duplicate;
	}


	@Override
	public void updatestatus(UserModel user) {
		// TODO Auto-generated method stub
		
	}


	

}
