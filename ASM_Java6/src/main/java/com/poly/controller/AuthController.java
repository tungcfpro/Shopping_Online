package com.poly.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entity.Account;
import com.poly.service.UserService;

@Controller
public class AuthController {
	@Autowired
	UserService userService;

	@GetMapping(value = "/auth/login/form")
	public String loginForm() {
		return "/admin/login.html";
	}


	@GetMapping("/auth/login/success")
	public String x(@RequestParam("username") String username) {
		UserDetails userDetails = userService.loadUserByUsername(username);
		String[] roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.toArray(String[]::new);
		for (String role : roles) { // Loop through the roles retrieved from authorities
			System.out.println(role);
			System.out.println(roles.toString());
			if ("ROLE_ADMIN".equalsIgnoreCase(role)) {
				return "redirect:/admin/index"; // Redirect to admin page
			} else if ("ROLE_USER".equalsIgnoreCase(role)) {
				return "redirect:/"; // Redirect to user's product list page
			}
			System.out.println(role);
			System.out.println(roles.toString());
		}
		return "redirect:/";

	}

//	private Account  {
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    if (authentication == null || AnonymousAuthenticationToken.class.
//	      isAssignableFrom(authentication.getClass())) {
//	        return false;
//	    }
//	    return authentication.isAuthenticated();
//	} 

	@RequestMapping("/oauth2/login/success")
	public String OAuthLogin(OAuth2AuthenticationToken oauth2, Model model) {
		userService.loginFormOAuth2(oauth2);
		model.addAttribute("message", "Success");
		return "redirect:/product/list";
	}

	@RequestMapping("/auth/login/error")

	public String error(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "forward:/auth/login/form";
	}

	@RequestMapping("/auth/logoff/success")
	public String logoff(Model model) {

		model.addAttribute("message", "Đăng xuất thành công!");
		return "forward:/auth/login/form";
	}

//	@RequestMapping("/auth/access/denied")
//	public String denied(Model model) {
//		model.addAttribute("message", "Bạn không có quyền truy xuất!");
//		return "forward:/auth/login/form";
//	}

}
