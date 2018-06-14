package classes;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private String avatar;

	private String bio;

	private String email;

	private String firstname;

	private String lastname;

	private String password;
	
	private String sex;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="user")
	private List<Comment> comments;

	//bi-directional many-to-one association to Follow
	@OneToMany(mappedBy="user1")
	private List<Follow> follows1;

	//bi-directional many-to-one association to Follow
	@OneToMany(mappedBy="user2")
	private List<Follow> follows2;

	//bi-directional many-to-one association to Like
	@OneToMany(mappedBy="user")
	private List<Like> likes;

	public User() {
		super();
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getBio() {
		return this.bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setUser(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setUser(null);

		return comment;
	}

	public List<Follow> getFollows1() {
		return this.follows1;
	}

	public void setFollows1(List<Follow> follows1) {
		this.follows1 = follows1;
	}

	public Follow addFollows1(Follow follows1) {
		getFollows1().add(follows1);
		follows1.setUser1(this);

		return follows1;
	}

	public Follow removeFollows1(Follow follows1) {
		getFollows1().remove(follows1);
		follows1.setUser1(null);

		return follows1;
	}

	public List<Follow> getFollows2() {
		return this.follows2;
	}

	public void setFollows2(List<Follow> follows2) {
		this.follows2 = follows2;
	}

	public Follow addFollows2(Follow follows2) {
		getFollows2().add(follows2);
		follows2.setUser2(this);

		return follows2;
	}

	public Follow removeFollows2(Follow follows2) {
		getFollows2().remove(follows2);
		follows2.setUser2(null);

		return follows2;
	}

	public List<Like> getLikes() {
		return this.likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}

	public Like addLike(Like like) {
		getLikes().add(like);
		like.setUser(this);

		return like;
	}

	public Like removeLike(Like like) {
		getLikes().remove(like);
		like.setUser(null);

		return like;
	}
	

	public User(String username, String avatar, String bio, String email,
			String firstname, String lastname, String password) {
		super();
		this.username = username;
		this.avatar = avatar;
		this.bio = bio;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
	}
	
	public User(String username, String avatar, String bio, String email,
			String firstname, String lastname, String password, String sex) {
		super();
		this.username = username;
		this.avatar = avatar;
		this.bio = bio;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.sex = sex;
	}

}