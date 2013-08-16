package com.avicit.bis.system.role.entity;

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

import com.avicit.bis.system.resource.entity.Resource;
import com.avicit.bis.system.user.entity.User;
import com.avicit.framework.support.entity.BaseEntity;


@Entity
@Table(name="SYS_ROLE")
public class Role extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Integer id;
	/**
	 * 角色名称
	 */
	private String name;
	/**
	 * 角色代码
	 */
	private String code;
	/**
	 * 角色描述
	 */
	private String description;
	
	/**
	 * 该角色对应的资源菜单
	 */
	private List<Resource> resources;
	
	/**
	 * 该角色对应的用户集合
	 */
	private List<User> users;
	
	private String roleLevel;

	public Role(){
		
	}
	
	
	@Id 
	@GeneratedValue
	@Column(name="ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="NAME")
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	@Column(name="CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(targetEntity=Resource.class,fetch=FetchType.LAZY)
	@JoinTable(name="SYS_ROLE_RESOURCE",joinColumns=@JoinColumn(name = "ROLE_ID"),inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
	@Fetch(FetchMode.SUBSELECT)
	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	@ManyToMany(targetEntity=User.class,fetch=FetchType.LAZY)
	@JoinTable(name="SYS_USER_ROLE",joinColumns=@JoinColumn(name = "ROLE_ID"),inverseJoinColumns = @JoinColumn(name = "USER_ID"))
	@Fetch(FetchMode.SUBSELECT)
	public List<User> getUsers() {
		return users;
	}

	

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Column(name="ROLE_LEVEL")
	public String getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(String roleLevel) {
		this.roleLevel = roleLevel;
	}

	@Override
	public String toString() {
		return this.code;
	}

	
}
