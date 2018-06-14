package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class LikeDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em = null;
	
	static LikeDAO instance = null;
	
	public static LikeDAO getInstance() {
		if(instance == null) {
			instance = new LikeDAO();
		}
		return instance;
	}
	
	private LikeDAO() {
		em = factory.createEntityManager();
	}
	
	public void createLike (String username, String sku) {
		
		UserDAO userDao = UserDAO.getInstance();
		Like like = new Like(new LikePK(username,sku), 
				new java.sql.Timestamp(System.currentTimeMillis()), userDao.findUser(username));
		
		em.getTransaction().begin();
		em.persist(like);
		em.getTransaction().commit();
	}
	
	public Like userLiked(String username, String sku) {
		Like like = null;
		try {
			LikePK pk = new LikePK(username, sku);
			like = em.find(Like.class, pk);
		} catch (Exception e) {
			System.out.println("user liked checker failed");
		} 
		return like;
	}
	
	public List<Like> recentLikes(String username) {
		List<Like> likes = new ArrayList<Like>();
		
		Integer limiter = 5;
		
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT l FROM Like l WHERE l.id.username=:username ORDER BY l.dateliked DESC").setMaxResults(limiter);
		query.setParameter("username", username);
		try {
			likes = query.getResultList();
			return likes;
		}
		catch (NoResultException e){
			return null;
		} finally {
			em.getTransaction().commit();
		}
	}

	public void unLike(String username, String sku) {
		
		try {
			em.getTransaction().begin();
			Like like = em.find(Like.class, new LikePK(username, sku));
			em.remove(like);
		}
		catch (IllegalArgumentException e) {
			System.out.println("System trying to delete an invalid like!");
		} finally {
			em.getTransaction().commit();
		}
	}
	
	public static void main(String[] args) {
		
		LikeDAO dao = new LikeDAO();
		
		//dao.createLike("username1", "abc");

		List<Like> listOfResult = dao.recentLikes("username1");
		for (Like l : listOfResult) {
			System.out.println(l.getId().getSku());
			System.out.println(l.getDateliked());
		}
		System.out.println(dao.userLiked("username1", "afsafdsadf"));
		
	}
	
}
