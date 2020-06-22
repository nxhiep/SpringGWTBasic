package com.spring.gwt.shared.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.spring.gwt.shared.Config;

@Entity
public class UserInfo implements IBasic {

	private static final long serialVersionUID = 1L;
	@Id private String id;
	@Index private String name = Config.TEXT_EMPTY;
	@Index private String account = Config.TEXT_EMPTY;
	@Index private String fullName = Config.TEXT_EMPTY;
	@Index private String email = Config.TEXT_EMPTY;
	@Index String phoneNumber = Config.TEXT_EMPTY;
	private String password = Config.TEXT_EMPTY;
	private String avatarUrl = Config.TEXT_EMPTY;
	private String facebookId = Config.TEXT_EMPTY;
	private String facebookToken = Config.TEXT_EMPTY;
	private String facebookLink = Config.TEXT_EMPTY;
	private int gender = Config.GENDER_OTHER;
	@Index private Long lastUpdate;
	@Index private Long createDate;
	
	@Ignore private int loginStatus = Config.LOGIN_FAILED;
	
	public UserInfo() {}
	
	public UserInfo(int loginStatus) {}
	
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setLastUpdate(Long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Long getLastUpdate() {
		return lastUpdate;
	}
	
	@Override
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}

	public Long getCreateDate() {
		return createDate;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getFacebookToken() {
		return facebookToken;
	}

	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}

	public String getFacebookLink() {
		return facebookLink;
	}

	public void setFacebookLink(String facebookLink) {
		this.facebookLink = facebookLink;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public int getLoginStatus() {
		return loginStatus;
	}
	
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	
	public boolean isLogedIn() {
		return this.loginStatus == Config.LOGIN_SUCCESS;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
}
