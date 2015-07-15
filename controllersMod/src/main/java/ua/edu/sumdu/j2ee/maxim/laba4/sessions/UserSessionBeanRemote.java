package ua.edu.sumdu.j2ee.maxim.laba4.sessions;

import java.sql.SQLException;

import javax.ejb.Remote;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.User;


@Remote
public interface UserSessionBeanRemote {
	public User getUserWithLoginPassword(String login,String password);
}
