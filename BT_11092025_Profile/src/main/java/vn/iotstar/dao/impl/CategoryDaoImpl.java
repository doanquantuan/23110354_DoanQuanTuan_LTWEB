package vn.iotstar.dao.impl;

import java.util.List;

import vn.iotstar.dao.impl.CategoryDaoImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.entity.Category;



public class CategoryDaoImpl implements ICategoryDao{

	@PersistenceUnit(unitName = "dataSource")
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dataSource");
	
	@Override
	public List<Category> findAll() {
		EntityManager em = getEntityManager();
        try {
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
            return query.getResultList();
        } finally {
            em.close();
        }
	}

	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	@Override
	public Category findByID(int id) {
		EntityManager em = emf.createEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
	}

	@Override
	public void insert(Category category) {
		EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
	}

	@Override
	public void update(Category category) {
		EntityManager em = getEntityManager();
	    try {
	        em.getTransaction().begin();
	        
	        // Lấy entity hiện tại từ DB
	        Category existingCategory = em.find(Category.class, category.getCategoryId());
	        if (existingCategory != null) {
	            existingCategory.setCategoryName(category.getCategoryName());
	            existingCategory.setImages(category.getImages());
	            existingCategory.setStatus(category.getStatus());
	            // Không set User => UserID giữ nguyên
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) em.getTransaction().rollback();
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}

	@Override
	public void delete(int id) {
		EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Category category = em.find(Category.class, id);
            if (category != null) {
                em.remove(category);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
	}

	@Override
	public List<Category> findName(String keyword) {
		EntityManager em = getEntityManager();
        try {
            TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c WHERE c.categoryName LIKE :keyword", Category.class);
            query.setParameter("keyword", "%" + keyword + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
	}
	
	@Override
	public List<Category> findByUserId(int userId) {
	    EntityManager em = getEntityManager();
	    try {
	        TypedQuery<Category> query = em.createQuery(
	            "SELECT c FROM Category c WHERE c.userId = :userId", Category.class);
	        query.setParameter("userId", userId);
	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}
	
	@Override
	public List<Category> findByRoleId(int roleId) {
	    EntityManager em = getEntityManager();
	    try {
	        TypedQuery<Category> query = em.createQuery(
	            "SELECT c FROM Category c JOIN User u ON c.userId = u.userId WHERE u.roleId = :roleId",
	            Category.class);
	        query.setParameter("roleId", roleId);
	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}


	
	public static void main(String[] args) {
		try {
			CategoryDaoImpl cateDao = new CategoryDaoImpl();
			System.out.println(cateDao.findByRoleId(3));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
