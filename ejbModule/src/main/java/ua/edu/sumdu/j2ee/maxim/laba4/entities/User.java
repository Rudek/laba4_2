package ua.edu.sumdu.j2ee.maxim.laba4.entities;


import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = -4953244457791305915L;

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQN")
	private Long id;
	
	@Column(name="login")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@ManyToMany(targetEntity = Role.class,
				fetch=EAGER,
				cascade=CascadeType.ALL)
	@JoinTable(name="roles_users",
			   joinColumns={ @JoinColumn(name="id_user", referencedColumnName="id") },
			   inverseJoinColumns={ @JoinColumn(name="id_role", referencedColumnName="id") })
	private List<Role> roles;
	
	/**
	 * Method return id of user.
	 * @return id of user.
	 */
	public Long getId() { 
		return id; 
	}
	
	/**
	 * Method return login's user.
	 * @return name of product.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Method return password's user.
	 * @return category of product.
	 */
	public String getPassword() { 
		return password; 
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Role> getAuthorities() {
		return roles;
	}
	
	/**
	 * Set id of user.
	 * @param id - identification number of user
	 */
	public void setId(Long id) { 
		this.id = id;
	}
	
	/**
	 * Set name of user.
	 * @param login - login's user.
	 */
	public void setUsername(String username) { 
		this.username = username; 
	}
	/**
	 * Set password for user.
	 * @param password - password's user.
	 */
	public void setPassword(String password) { 
		this.password = password; 
	}
	
	public void setAuthorities( List<Role> roles ) {
		this.roles = roles;
	}
	
	@Override
	public String toString(){
		return new StringBuilder().append(getId())
								  .append(" ")
								  .append( getUsername() )
								  .append(" ")
								  .append( getPassword() )
								  .append(" ")
								  .append(getAuthorities()).toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof User ? 
				(id == ((User) obj).id) : 
				false;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
