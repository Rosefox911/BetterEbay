package classes;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the likes database table.
 * 
 */
@Embeddable
public class LikePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String username;

	private String sku;

	public LikePK() {
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSku() {
		return this.sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof LikePK)) {
			return false;
		}
		LikePK castOther = (LikePK)other;
		return 
			this.username.equals(castOther.username)
			&& this.sku.equals(castOther.sku);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.username.hashCode();
		hash = hash * prime + this.sku.hashCode();
		
		return hash;
	}
	public LikePK(String username, String sku) {
		super();
		this.username = username;
		this.sku = sku;
	}
}