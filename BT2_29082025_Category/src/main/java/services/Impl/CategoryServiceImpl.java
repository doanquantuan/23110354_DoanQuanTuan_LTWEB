package services.Impl;

import java.util.List;

import dao.ICategoryDao;
import dao.impl.CategoryDaoImpl;
import model.CategoryModel;
import services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService{

	public ICategoryDao cateDao = new CategoryDaoImpl();
	@Override
	public List<CategoryModel> findAll() {

		return cateDao.findAll();
	}

	@Override
	public CategoryModel findByID(int id) {
		
		return cateDao.findByID(id);
	}

	@Override
	public void insert(CategoryModel category) {
		cateDao.insert(category);
		
	}

	@Override
	public void update(CategoryModel category) {
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findByID(category.getCategoryid());
		if (cate != null) {
			cateDao.update(category);
		}
	}

	@Override
	public void delete(int id) {
		CategoryModel cate = new CategoryModel();
		cate = cateDao.findByID(id);
		if (cate != null) {
			cateDao.delete(id);
		}
	}

	@Override
	public List<CategoryModel> findName(String keyword) {
		return cateDao.findName(keyword);
	}

}
