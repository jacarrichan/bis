/**
 * User.java
 * Product:Grgbanking
 * Version:1.0
 * Copyright 2010 by Grgbanking
 * All Rights Reserved.
 */
package com.avicit.bis.system.user.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


import com.avicit.bis.system.role.entity.Role;
import com.avicit.framework.support.entity.BaseEntity;




/**
 * 用户信息实体类
 * @ClassName com.grgbanking.entity.User
 * @Author Administrator
 * @Version 1.0
 * @Date 2009-12-3 上午12:09:18
 */
@Entity
@Table(name = "SYS_USER")
public class User extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	private Integer userId;
	
	private String loginName;
	
	private String userName;
	
	private String passWord;
	
	private String style;
	
	private String layout;
	
	private String lastLoginIP;
	
	private Date lastLoginTime;
	
	private List<Role> roles;

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name="USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name="PASS_WORD")
	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	@Column(name="STYLE")
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Column(name="LAYOUT")
	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	@ManyToMany(targetEntity=Role.class,fetch=FetchType.LAZY)
	@JoinTable(name="SYS_USER_ROLE",joinColumns=@JoinColumn(name = "USER_ID"),inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@Fetch(FetchMode.SUBSELECT)
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Column(name="LAST_LOGIN_IP")
	public String getLastLoginIP() {
		return lastLoginIP;
	}


	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	@Column(name="LAST_LOGIN_TIME")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	
	
}
