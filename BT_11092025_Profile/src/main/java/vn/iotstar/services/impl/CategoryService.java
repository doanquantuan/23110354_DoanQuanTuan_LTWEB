package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;

public class CategoryService implements ICategoryService{
	
	public ICategoryDao cateDao = new CategoryDaoImpl();

	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category findByID(int id) {
		return cateDao.findByID(id);
	}

	@Override
	public void insert(Category category) {
		cateDao.insert(category);
	}

	@Override
	public void update(Category category) {
		Category cate = new Category();
		cate = cateDao.findByID(category.getCategoryId());
		if (cate != null) {
			cateDao.update(category);
		}
	}

	@Override
	public void delete(int id) {
		Category cate = new Category();
		cate = cateDao.findByID(id);
		if (cate != null) {
			cateDao.delete(id);
		}
	}

	@Override
	public List<Category> findName(String keyword) {
		return cateDao.findName(keyword);
	}

	@Override
	public List<Category> findByUserId(int userId) {
		return cateDao.findByUserId(userId);
	}

	@Override
	public List<Category> findByRoleId(int roleId) {
		return cateDao.findByRoleId(roleId);
	}

}
