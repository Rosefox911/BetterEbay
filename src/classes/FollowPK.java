package classes;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the follows database table.
 * 
 */
@Embeddable
public class FollowPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String userfollowing;

	@Column(insertable=false, updatable=false)
	private String userfollowed;

	public FollowPK() {
	}
	public String getUserfollowing() {
		return this.userfollowing;
	}
	public void setUserfollowing(String userfollowing) {
		this.userfollowing = userfollowing;
	}
	public String getUserfollowed() {
		return this.userfollowed;
	}
	public void setUserfollowed(String userfollowed) {
		this.userfollowed = userfollowed;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FollowPK)) {
			return false;
		}
		FollowPK castOther = (FollowPK)other;
		return 
			this.userfollowing.equals(castOther.userfollowing)
			&& this.userfollowed.equals(castOther.userfollowed);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userfollowing.hashCode();
		hash = hash * prime + this.userfollowed.hashCode();
		
		return hash;
	}
	public FollowPK(String userfollowing, String userfollowed) {
		super();
		this.userfollowing = userfollowing;
		this.userfollowed = userfollowed;
	}
}