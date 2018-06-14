package classes;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the follows database table.
 * 
 */
@Entity
@Table(name="follows")
@NamedQuery(name="Follow.findAll", query="SELECT f FROM Follow f")
public class Follow implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FollowPK id;

	private Timestamp datefollowed;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userfollowing")
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userfollowed")
	private User user2;

	public Follow() {
	}

	public FollowPK getId() {
		return this.id;
	}

	public void setId(FollowPK id) {
		this.id = id;
	}

	public Timestamp getDatefollowed() {
		return this.datefollowed;
	}

	public void setDatefollowed(Timestamp datefollowed) {
		this.datefollowed = datefollowed;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public Follow(FollowPK id, Timestamp datefollowed, User user1, User user2) {
		super();
		this.id = id;
		this.datefollowed = datefollowed;
		this.user1 = user1;
		this.user2 = user2;
	}

}