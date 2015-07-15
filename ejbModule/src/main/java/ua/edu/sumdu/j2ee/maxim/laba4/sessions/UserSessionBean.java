package ua.edu.sumdu.j2ee.maxim.laba4.sessions;


import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.User;


@Stateless(mappedName="SessionsBean")
public class UserSessionBean implements UserSessionBeanRemote {
	
	//private static final Logger logger = LogManager.getLogger("EJB");
	
	@PersistenceContext(unitName = "SessionsBean")
	private EntityManager em;

	public User getUserWithLoginPassword(String login,String password) {
		/*Query query = em.createQuery("SELECT u FROM User u WHERE u.username=:username and u.password=:password", User.class)
					    .setParameter("username", login)
					    .setParameter("password", password);
		*/
		Query query = em.createNativeQuery("SELECT * FROM Users u WHERE u.login=? AND u.password=?",User.class)
						.setParameter(1, login)
						.setParameter(2, password);
		List<User> user = query.getResultList();
		if (user.isEmpty()) {
			return null;
		}
		return user.get(0);
	}
	
}
