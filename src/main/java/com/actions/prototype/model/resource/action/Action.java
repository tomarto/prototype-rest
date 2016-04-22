package com.actions.prototype.model.resource.action;

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

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * Action class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Data
@Builder
@Entity
@Table(name = "Action")
public class Action implements Serializable {

	/** Constant <code>serialVersionUID=-8499257725243365603L</code> */
	private static final long serialVersionUID = -8499257725243365603L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date createdDate;
	
	@Column
	@JsonSerialize(using = JsonDateSerializer.class)
	private Date dueDate;
}
