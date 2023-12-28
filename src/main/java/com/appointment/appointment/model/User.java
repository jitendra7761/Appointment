package com.appointment.appointment.model;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String userName;

	@Column
	private String userPass;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRole> userRoles = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;

		for (UserRole r : userRoles) {
			r.setUser(this);
		}
	}

	public Collection<String> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}

