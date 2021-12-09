package com.revature.JJLZ.util;

import com.revature.JJLZ.service.UserService;
import com.revature.JJLZ.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.*;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private UserService userService;

	public User parseToken (String token) {
		try {
			Claims body = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();

			return userService.findUserById(Integer.parseInt((String) body.get("userId")));
		} catch (JwtException | ClassCastException e) {
			return null;
		}
	}

	public String generateToken (User u) {
		Claims claims = Jwts.claims().setSubject(u.getUsername());
			claims.put("userId", u.getUserId() + "");

		return Jwts.builder()
			.setClaims(claims)
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}
}
