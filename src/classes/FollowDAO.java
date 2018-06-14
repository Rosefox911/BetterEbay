package classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FollowDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em = null;
	
	static FollowDAO instance = null;
	
	public static FollowDAO getInstance() {
		if(instance == null) {
			instance = new FollowDAO();
		}
		return instance;
	}
	
	private FollowDAO() {
		em = factory.createEntityManager();
	}
	
	public void createFollow (String username1, String username2) {	

		UserDAO userDao = UserDAO.getInstance();
		User user1 = userDao.findUser(username1);
		User user2 = userDao.findUser(username2);

		Follow follow = new Follow(new FollowPK(username1, username2),
				new java.sql.Timestamp(System.currentTimeMillis()), user1, user2);
		
		if (UserDAO.getInstance().findUser(follow.getId().getUserfollowed()) == null) {
			System.out.println("The user you are trying to follow does not exist!");	
		}
		else if (follow.getId().getUserfollowed().equals(follow.getId().getUserfollowing())) {
			System.out.println("User cannot follow himself!");
		}
		else {
			try {
				em.getTransaction().begin();
				em.persist(follow);
				em.getTransaction().commit();
			}
			catch (Exception e) {
				System.out.println("Unable to add follow, may result by duplicated pk");
			}
		}
	}
	
	public Follow findFollow(String follower, String followed) {
		
		return em.find(Follow.class, new FollowPK(follower, followed));
	}
	
	@SuppressWarnings("unchecked")
	public List<Follow> recentFollows(String username) {
		List<Follow> follows = new ArrayList<Follow>();
		
		Integer limiter = 5;
		
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT f FROM Follow f WHERE f.id.userfollowing=:username ORDER BY f.datefollowed DESC").setMaxResults(limiter);
		query.setParameter("username", username);
		em.getTransaction().commit();
		try {
			follows = (List<Follow>)query.getResultList();
			return follows;
		}
		catch (NoResultException e){
			return null;
		}
	}
	
	public void unFollow(String curUsername, String followedUsername) {
		
		try {
			em.getTransaction().begin();
			FollowPK pk = new FollowPK(curUsername, followedUsername);
			Follow follow = em.find(Follow.class, pk);
			em.remove(follow);
			em.getTransaction().commit();
		}
		catch (IllegalArgumentException e) {
			System.out.println("System trying to delete an invalid follow!");
		}
	}

	public static void main(String[] args) {
		FollowDAO dao = new FollowDAO();
		UserDAO userDao = UserDAO.getInstance();
		User user1 = userDao.findUser("username1");
		User user2 = userDao.findUser("username10");
		User user3 = userDao.findUser("username2");
		
		dao.createFollow("username1", "username2");
		Follow follow1 = new Follow(new FollowPK("username1","username10"),
				new java.sql.Timestamp(System.currentTimeMillis()), user1, user2);
		//dao.CreateFollow(follow1);
		List<Follow> listOfResult = dao.recentFollows("username1");
		for (Follow f : listOfResult) {
			System.out.println(f.getId().getUserfollowed());
			System.out.println(f.getDatefollowed());
		}
		dao.unFollow("username1", "username10");
	}
}
