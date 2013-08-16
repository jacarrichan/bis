package com.avicit.framework.support.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.avicit.framework.context.spring.SpringContextBeanFactory;
import com.avicit.framework.support.dao.hibernate.impl.HibernateDaoSupport;

/**
 * 
 * @author Zhang Kaitao
 * 
 * @version 1.0, 2010-8-12
 */
public abstract class BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2035013017939483936L;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public <T,ID extends Serializable> HibernateDaoSupport<T,ID> getDAO() {
		String className = this.getClass().getSimpleName();
		String daoName = className.substring(0,1).toLowerCase() + className.substring(1, className.length()) + "Dao";
		return SpringContextBeanFactory.getBean(daoName);
	}
	
	@SuppressWarnings("unchecked")
	public <ID extends Serializable> ID save() {
		return (ID) this.getDAO().save(this);
	}
	
	public void delete() {
		this.getDAO().delete(this);
	}
	
	public void update() {
		this.getDAO().update(this);
	}

	// 创建日期
	protected Date createDate;
	// 更新日期
	protected Date modifyDate;
	// 创建人
	protected Integer createUserId;
	// 更新人
	protected Integer modifyUserId;
	// 版本号
	protected Integer version;
	// 是否删除
	protected String deleted = "N";

	/**
	 * @return the createDate
	 */
	@Column(name = "CREATE_DATE")
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifyDate
	 */
	@Column(name = "MODIFY_DATE")
	public Date getModifyDate() {
		return modifyDate;
	}

	/**
	 * @param modifyDate
	 *            the modifyDate to set
	 */
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	/**
	 * @return the createUserId
	 */
	@Column(name = "CREATE_USER_ID")
	public Integer getCreateUserId() {
		return createUserId;
	}

	/**
	 * @param createUserId
	 *            the createUserId to set
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * @return the modifyUserId
	 */
	@Column(name = "MODIFY_USER_ID")
	public Integer getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * @param modifyUserId
	 *            the modifyUserId to set
	 */
	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * @return the version
	 */
	@Version
	@Column(name = "VERSION")
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the deleted
	 */
	@Column(name = "DELETED")
	public String getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted
	 *            the deleted to set
	 */
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
}
