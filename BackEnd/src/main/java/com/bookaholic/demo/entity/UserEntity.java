package com.bookaholic.demo.entity;

import com.bookaholic.demo.model.UserPayload;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
	@GeneratedValue
	@Column(name="user_id", columnDefinition="uuid")
	private UUID userId;

	@Column(name="user_name", columnDefinition = "TEXT")
    private String username;

	@Column(name="password", columnDefinition = "TEXT")
    private String password;

	@Column(name="salt", columnDefinition = "TEXT")
	private String salt;

	@Column(name="sign_up_date")
    private Date signUpDate;

	@Column(name="role")
    private int role;

	public UserEntity() {
	}

	public UserEntity(UUID userId, String username, String password, String salt, Date signUpDate, int role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.salt = salt;
		this.signUpDate = signUpDate;
		this.role = role;
	}

	public UserEntity(UserPayload userPayload){
		this.username = userPayload.getUsername();
		this.password = userPayload.getPassword();
		this.signUpDate = new Date();
		this.role = userPayload.getRole();
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(Date signUpDate) {
		this.signUpDate = signUpDate;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
}