package ua.edu.sumdu.j2ee.maxim.laba4.sessions;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.Category;
import ua.edu.sumdu.j2ee.maxim.laba4.entities.CategoryLevel;
import ua.edu.sumdu.j2ee.maxim.laba4.entities.User;


@Remote
public interface UserSessionBeanRemote {
	public User getUserWithLoginPassword(String login,String password);
}
