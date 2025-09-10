package vn.iotstar.dao.impl;

import java.util.List;
import jakarta.persistence.*;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.entity.User;

public class UserDaoImpl implements IUserDao {

    @PersistenceUnit(unitName = "dataSource")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dataSource");

    @Override
    public List<User> findAll() {
    	EntityManager em = getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private EntityManager getEntityManager() {
    	return emf.createEntityManager();
	}

	@Override
    public User findByUserName(String username) {
		EntityManager em = getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);

            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    @Override
    public User findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void insert(User user) {
    	EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);  
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                           .setParameter("email", email)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean checkExistUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.userName = :username", Long.class)
                           .setParameter("username", username)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public boolean updateCode(String email, String code) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            int rows = em.createQuery("UPDATE User u SET u.code = :code WHERE u.email = :email")
                        .setParameter("code", code)
                        .setParameter("email", email)
                        .executeUpdate();
            tx.commit();
            return rows > 0;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    public boolean updatePassword(String email, String newPassword) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            int rows = em.createQuery("UPDATE User u SET u.passWord = :pw WHERE u.email = :email")
                        .setParameter("pw", newPassword)
                        .setParameter("email", email)
                        .executeUpdate();
            tx.commit();
            return rows > 0;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

	@Override
	public User findOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findOne(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertregister(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatestatus(User user) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
	    UserDaoImpl userDao = new UserDaoImpl();
	    User user = userDao.findByID(1);
	    
	    if (user != null) {
	        System.out.println("Found user: " + user.getUsername());
	    } else {
	        System.out.println("User not found!");
	    }
	}

}
