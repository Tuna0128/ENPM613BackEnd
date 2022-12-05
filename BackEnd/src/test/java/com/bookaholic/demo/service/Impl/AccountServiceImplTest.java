package com.bookaholic.demo.service.Impl;

import com.bookaholic.demo.BookaholicApplicationTests;
import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.model.UserPayload;
import com.bookaholic.demo.service.AccountService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test account service")
class AccountServiceImplTest extends BookaholicApplicationTests{

	@Autowired
	private AccountService accountService;
	
	@BeforeAll
	static void testBegin() {
		System.out.println("Test start...");
	}
	
//	@Test
//	void testID() {
//		UUID id = accountService.getUserIdByUsername("test_user");
//		assertNotNull(id);
//	}
	
	@Test
	@Timeout(value = 1500, unit = TimeUnit.MILLISECONDS)
	@Transactional
	@DisplayName("Test create a new teacher account")
	void testSaveNewTeacher() {
		UserPayload userPayload = new UserPayload();
		userPayload.setUsername("teacher1");
		userPayload.setPassword("pwd1111");
		userPayload.setRole(0);
		UserEntity userEntity = new UserEntity(userPayload);
		Boolean res = accountService.saveUser(userEntity);
		assertTrue(res);
	}
	
	@Test
	@Transactional
	@DisplayName("Test create a new student account")
	void testSaveNewStudent() {
		UserPayload userPayload = new UserPayload();
		userPayload.setUsername("student1");
		userPayload.setPassword("pwd2222");
		userPayload.setRole(1);
		UserEntity userEntity = new UserEntity(userPayload);
		Boolean res = accountService.saveUser(userEntity);
		assertTrue(res);
	}
	
	@Test
	@Transactional
	@DisplayName("Test create a new account with incomplete information")
	void testCreateInvalidUser() {
		UserPayload userPayload1 = new UserPayload();
		userPayload1.setUsername("user1");
		userPayload1.setRole(0);
		UserPayload userPayload2 = new UserPayload();
		userPayload2.setPassword("password2");
		userPayload2.setRole(1);
		UserEntity userEntity1 = new UserEntity(userPayload1);
		UserEntity userEntity2 = new UserEntity(userPayload2);
		UserEntity userEntity3 = null;
		assertAll("Create users with incomplete information",
				()->assertFalse(accountService.saveUser(userEntity1)),
				()->assertFalse(accountService.saveUser(userEntity2)),
				()->assertFalse(accountService.saveUser(userEntity3)));
	}
	
	@Test
	@Transactional
	@DisplayName("Test login with valid username and password")
	void testSuccessLoginWithPassword() {
		UserPayload userPayload = new UserPayload();
		userPayload.setUsername("user1");
		userPayload.setPassword("PASSWORD");
		userPayload.setRole(0);
		UserEntity userEntity = new UserEntity(userPayload);
		accountService.saveUser(userEntity);
		assertTrue(accountService.loginAuthenticationByInput("user1", "PASSWORD"));
	}
	
	@Test
	@Transactional
	@DisplayName("Test login with invalid password")
	void testLoginWithInvalidPassword() {
		UserPayload userPayload = new UserPayload();
		userPayload.setUsername("user1");
		userPayload.setPassword("PASSWORD");
		userPayload.setRole(0);
		UserEntity userEntity = new UserEntity(userPayload);
		accountService.saveUser(userEntity);
		assertFalse(accountService.loginAuthenticationByInput("user1", "INVALID"));
	}
	
	@Test
	@Transactional
	@DisplayName("Test get user id by name")
	void testGetUserIdByUsername() {
		UserPayload userPayload = new UserPayload();
		userPayload.setUsername("user_wanted");
		userPayload.setPassword("PASSWORD");
		userPayload.setRole(0);
		UserEntity userEntity = new UserEntity(userPayload);
		accountService.saveUser(userEntity);
		assertAll("Get user id by name",
				()->assertNotNull(accountService.getUserIdByUsername("user_wanted")),
				()->assertNull(accountService.getUserIdByUsername("user_not_exist")));
	}
	
	@Test
//	@RepeatedTest(3)
	@Transactional
	@DisplayName("Test get user role by name")
	void testGetUserRoleByUsername() throws InterruptedException {
		UserPayload userPayload = new UserPayload();
		userPayload.setUsername("random_user");
		userPayload.setPassword("PASSWORD");
		int role = new java.util.Random().nextBoolean() ? 1 : 0;
		userPayload.setRole(role);
		UserEntity userEntity = new UserEntity(userPayload);
		accountService.saveUser(userEntity);
		assertEquals(role, accountService.getUserRoleByUsername("random_user"));

	}
}
