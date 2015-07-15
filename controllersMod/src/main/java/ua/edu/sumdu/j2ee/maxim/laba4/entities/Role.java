package ua.edu.sumdu.j2ee.maxim.laba4.entities;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="roles")
public class Role implements Serializable {

	private static final long serialVersionUID = -4953244457791305915L;

	@Id
	@Column(name="id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLES_SEQN")
	private Long id;
	
	@Column(name="name")
	private String authority;
	
	@ManyToMany( mappedBy="roles" )
    private List<User> usersList;  
	
	public Long getId() { 
		return id; 
	}
	
	/**
	 * Method return login's user.
	 * @return name of authority.
	 */
	public String getAuthority() {
		return "ROLE_"+authority;
	}

	public List<User> getUsersList() {
		return usersList;
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
	public void setAuthority(String name) { 
		this.authority = authority; 
	}
	
	public void setUsersList(List<User> users) {
		this.usersList = users;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().append(getId()).append(" ").append(getAuthority()).toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Role ? 
				(id == ((Role) obj).id) : 
				false;
	}
	
	
}
