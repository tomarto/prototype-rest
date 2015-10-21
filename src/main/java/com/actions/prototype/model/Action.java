package com.actions.prototype.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.actions.prototype.common.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * <p>
 * Action class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@Entity
@Table(name = "Action")
public class Action implements Serializable {

	/** Constant <code>serialVersionUID=-8499257725243365603L</code> */
	private static final long serialVersionUID = -8499257725243365603L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private Date createdDate;
	
	@Column
	private Date dueDate;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the createdDate
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	
	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	/**
	 * @return the dueDate
	 */
	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDueDate() {
		return dueDate;
	}
	
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
}
