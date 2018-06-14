package classes;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the likes database table.
 * 
 */
@Entity
@Table(name="likes")
@NamedQuery(name="Like.findAll", query="SELECT l FROM Like l")
public class Like implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private LikePK id;

	private Timestamp dateliked;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	public Like() {
		super();
	}

	public LikePK getId() {
		return this.id;
	}

	public void setId(LikePK id) {
		this.id = id;
	}

	public Timestamp getDateliked() {
		return this.dateliked;
	}

	public void setDateliked(Timestamp dateliked) {
		this.dateliked = dateliked;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Like(LikePK id, Timestamp dateliked, User user) {
		super();
		this.id = id;
		this.dateliked = dateliked;
		this.user = user;
	}

}