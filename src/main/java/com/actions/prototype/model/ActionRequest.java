package com.actions.prototype.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import lombok.Data;

/**
 * <p>
 * ActionRequest class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
@Data
public class ActionRequest implements Serializable {

	/** Constant <code>serialVersionUID=3023428773679232526L</code> */
	private static final long serialVersionUID = 3023428773679232526L;
	/** Constant <code>FOUR=4</code> */
	private static final int FOUR = 4;
	/** Constant <code>SIX=6</code> */
	private static final int SIX = 6;
	
	private Integer id;
	
	@Pattern(regexp = "^([a-zA-Z1-9 ]){0,50}$")
	private String name;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date createdDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dueDate;
	
	/**
	 * <p>
	 * Build the query to retrieve Actions from database.
	 * </p>
	 *            
	 * @return a {@link java.lang.String} object.
	 */
	public String getQueryString() {
		final StringBuilder query = new StringBuilder("SELECT * FROM Action WHERE ");
		if(getId() != null) {
			query.append("id = :id AND ");
		}
		if(StringUtils.isNotBlank(getName())) {
			query.append("name = :name AND ");
		}
		if(getCreatedDate() != null) {
			query.append("created_date = :createdDate AND ");
		}
		if(getDueDate() != null) {
			query.append("due_date = :dueDate");
		}
		if(query.indexOf("AND") == query.length() - FOUR) {
			query.replace(query.length() - FOUR, query.length(), "");
		} else if(query.indexOf("WHERE") == query.length() - SIX) {
			query.replace(query.length() - SIX, query.length(), "");
		}
		return query.toString();
	}
	
	/**
	 * <p>
	 * Build the MapSqlParameterSource to retrieve Actions from database.
	 * </p>
	 *            
	 * @return a {@link org.springframework.jdbc.core.namedparam.MapSqlParameterSource} object.
	 */
	public MapSqlParameterSource getSqlParamMap() {
		final MapSqlParameterSource paramMap = new MapSqlParameterSource();
		if(getId() != null) {
			paramMap.addValue("id", getId());
		}
		if(StringUtils.isNotBlank(getName())) {
			paramMap.addValue("name", getName());
		}
		if(getCreatedDate() != null) {
			paramMap.addValue("createdDate", getCreatedDate());
		}
		if(getDueDate() != null) {
			paramMap.addValue("dueDate", getDueDate());
		}
		
		return paramMap;
	}
}
