package com.bookaholic.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.service.AccountService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtUtils {
	@Autowired
	private AccountService accountService;
	
	// expire time 12 hours
	private static final long EXPIRE_TIME = 12 * 60 * 60 * 1000;
	// key for encryption
	private static final String KEY = "BoOkAhOlIc";
	
	public static String createToken(UUID userId, String username) {
		Map<String, Object> header = new HashMap();
		header.put("typ", "JWT");
		header.put("alg", "HS256");
		String id = userId.toString();
		
		JwtBuilder builder = Jwts.builder().setHeader(header)
							.setId(id)
							.setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
							.setSubject(username)
							.setIssuedAt(new Date())
							.signWith(SignatureAlgorithm.HS256, KEY);
		return builder.compact();
	}
	
	/**
	 * verify token
	 * @param token token in the request header
	 * @return 0-failed 1-pass 2-expired
	 */
	public int verify(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			return 2;
		}
		String id = claims.getId();
		UUID userId = UUID.fromString(id);
		UserEntity userEntity = accountService.queryUserByUserId(userId);
		if(userEntity != null)
			return 1;
		else
			return 0;
	}
	
	/**
	 * login with token
	 * @param token token in the request header
	 * @return userInfo
	 */
	public Map<String, Object> tokenLogin(String token) {
		Claims claims = null;
		try {
			claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		} catch (SignatureException e) {
			return null;
		}
		String id = claims.getId();
		UUID userId = UUID.fromString(id);
		UserEntity userEntity = accountService.queryUserByUserId(userId);
		if(userEntity != null) {
			Map<String, Object> userInfoMap = new HashMap<>();
			userInfoMap.put("username", userEntity.getUsername());
			userInfoMap.put("role", userEntity.getRole());
			userInfoMap.put("token", token);	
			return userInfoMap;
		}
		else {
			return null;
		}
	}
	
}
