package com.actions.prototype.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.actions.prototype.common.JsonDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * <p>
 * User class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@Data
@Entity
@Table(name = "User")
public class User implements Serializable {

	/** Constant <code>serialVersionUID=1374992277407756043L</code> */
	private static final long serialVersionUID = 1374992277407756043L;
	
	@Id
	@Column
	private String username;
	
	@Column
	private String firstName;
	
	@Column
	private String lastName;
	
	@Column
	@JsonIgnore
	private String password;
	
	@Column
	private String email;
	
	@Column
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date birthDate;
	
	@Column
	private String type;
	
	@Column
	@JsonIgnore
	private Boolean enabled;
}
