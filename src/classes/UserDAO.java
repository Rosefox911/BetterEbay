package classes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Project");
	EntityManager em = null;
	static UserDAO instance = null;
	
	public static UserDAO getInstance() {
		if(instance == null) {
			instance = new UserDAO();
		}
		return instance;
	}
	
	private UserDAO() {
		em = factory.createEntityManager();
	}
	
	public void createUser (User user) {
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
	
	public User findUser(String username) {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:username");
		query.setParameter("username", username);
		try {
			User user = (User) query.getSingleResult();
			return user;
		}
		catch (NoResultException e){
			return null;
		} finally {
			em.getTransaction().commit();
		}
	}
	
	public Boolean findUserByUsernamePassword(String username, String password) {
		
		try {
			User user = em.find(User.class, username);
			if (user.getPassword().equals(password)) {
				return true;
			}
			return false; 
		}
		catch (Exception e) {
			System.out.println("Invalid username");
		}
		
		return false;
	}
	
	public void deleteUser(String username) {
		
		try {
			User user = em.find(User.class, username);		
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		}
		catch (IllegalArgumentException e) {
			System.out.println("System trying to delete an invalid user!");
		}
	}
	
	public String getAvatarFromUsername(String username) {
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:username");
		query.setParameter("username", username);
		try {
			User user = (User) query.getSingleResult();
			String avatar = user.getAvatar();
			return avatar;
		}
		catch (NoResultException e){
			return null;
		} finally {
			em.getTransaction().commit();
		}
	}
	
	public static void main(String[] args) {
		
		UserDAO dao = UserDAO.getInstance();
		
		User user1 = new User("jorged", "http://i.imgur.com/ALSP7jW.jpg", "jorge's bio, sup", "jorge@hotmail.com", "Jorge", "Delgado", "pass");
		User user2 = new User("username2", "pass2","Josh","Joshson", "josh@thatguy.com", "imgur.com/stuff", "just some stuf");
		dao.createUser(user1);
		//System.out.println(dao.findUser("username211"));
		//dao.deleteUser("haha");
	}

}
