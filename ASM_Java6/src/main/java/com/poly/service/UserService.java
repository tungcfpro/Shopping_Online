package com.poly.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.poly.dao.AccountDAO;
import com.poly.entity.Account;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	AccountDAO accountDAO;
	
	@Autowired
	BCryptPasswordEncoder pe;
	
	@Autowired
	HttpSession session;
	/*--Ma hoa mat khau--*/
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountDAO.findById(username).get();
		try {
			String password = account.getPassword();
			String[] roles = account.getAuthorities().stream()
					.map(authority -> authority.getRole().getId())
					.collect(Collectors.toList()).toArray(new String[0]);
			Map<String, Object> authentication = new HashMap<>();
			authentication.put("user", account);
			byte[] token = (username + ":" + account.getPassword()).getBytes();
			authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
			session.setAttribute("authentication", authentication);
			return User.withUsername(username)
					.password(pe.encode(password)).roles(roles).build();
		} catch (Exception e) {
			throw new UsernameNotFoundException(username + "not found");
		}
		
	}
	
	public void loginFormOAuth2(OAuth2AuthenticationToken oauth2) {
		String email = oauth2.getPrincipal().getAttribute("email");
		String password = Long.toHexString(System.currentTimeMillis());
		
		UserDetails user = User.withUsername(email).password(pe.encode(password)).roles("USER").build();
		
		Authentication auth = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	public Account getOneByUsername(String username) {
		return accountDAO.findById(username).get();
	}
}
