package vn.iotstar.services;

import java.util.List;

import vn.iotstar.entity.Category;

public interface ICategoryService {

	List<Category> findAll();
	
	Category findByID(int id);
	
	void insert(Category category);
	
	void update(Category category);
	
	void delete(int id);
	
	List<Category> findName(String keyword);
	
	List<Category> findByUserId(int userId);
	
	List<Category> findByRoleId(int roleId);
}
