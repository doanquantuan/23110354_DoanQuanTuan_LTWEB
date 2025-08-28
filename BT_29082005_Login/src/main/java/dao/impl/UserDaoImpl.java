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
						rs.getString("avatar"),
						rs.getInt("roleid"),
						rs.getString("phone"),
						rs.getDate("createDate")));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO [users] (id, email, userName, fullName, passWord, avatar, roleid, phone, createDate) VALUES (?, ?, ?, ?, ?)";
		
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
			System.out.println(userDao.findByUserName("Tuan"));
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
				user.setUserName(rs.getString("userName").trim());
				user.setFullName(rs.getString("fullName"));
				user.setPassWord(rs.getString("passWord").trim());
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
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
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("userName"));
				user.setFullName(rs.getString("fullName"));
				user.setPassWord(rs.getString("passWord"));
				user.setAvatar(rs.getString("avatar"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreatedDate(rs.getDate("createdDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
