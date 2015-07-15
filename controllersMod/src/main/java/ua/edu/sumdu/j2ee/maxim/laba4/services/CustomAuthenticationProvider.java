package ua.edu.sumdu.j2ee.maxim.laba4.services;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import ua.edu.sumdu.j2ee.maxim.laba4.entities.Role;
import ua.edu.sumdu.j2ee.maxim.laba4.entities.User;
import ua.edu.sumdu.j2ee.maxim.laba4.sessions.UserSessionBeanRemote;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	/*@Autowired
    private UserService userService;*/
	
	@EJB(mappedName="java:app/ejbModule-1.0/UserSessionBean!ua.edu.sumdu.j2ee.maxim.laba4.sessions.UserSessionBeanRemote")
    private UserSessionBeanRemote userSessionBeanRemote;
	
	@Autowired
	private MessageSource messageSource;
	
	public Authentication authenticate(Authentication authentication) throws BadCredentialsException, AuthenticationException {
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        
        //User user = (User) userService.loadUserByUsername(username);

        User user = userSessionBeanRemote.getUserWithLoginPassword(username, DigestUtils.md5Hex( password ) );
        
        if (user == null) {
            throw new BadCredentialsException("");
        }
 
        /*if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException();
        }*/
 
        Collection<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
        for (Role role : user.getAuthorities()) {
        	authorities.add( new SimpleGrantedAuthority(role.getAuthority()) );
        }
        
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	public boolean supports(Class<?>authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}


}
