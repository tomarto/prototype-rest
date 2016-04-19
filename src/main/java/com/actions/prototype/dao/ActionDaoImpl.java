package com.actions.prototype.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.actions.prototype.model.Action;
import com.actions.prototype.model.ActionRequest;

/**
 * <p>
 * ActionDaoImpl class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Repository
public class ActionDaoImpl implements ActionDao {
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	/**
	 * <p>
	 * Constructor for ActionDaoImpl.
	 * </p>
	 * 
	 * @param dataSource
	 *            a {@link javax.sql.DataSource} object.
	 */
	@Autowired
	public ActionDaoImpl(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	/** {@inheritDoc} */
	@Override
	public List<Action> findAll(ActionRequest request) {
		return jdbcTemplate.query(request.getQueryString(), request.getSqlParamMap(),
				(rs, rowNum) -> {
					return Action.builder()
						.id(rs.getInt("id"))
						.name(rs.getString("name"))
						.createdDate(rs.getDate("created_date"))
						.dueDate(rs.getDate("due_date")).build();
				});
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean insert(Action action) {
		final MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", action.getId());
		paramMap.addValue("name", action.getName());
		paramMap.addValue("createdDate", action.getCreatedDate());
		paramMap.addValue("dueDate", action.getDueDate());
		return jdbcTemplate.update("INSERT INTO Action VALUES(:id, :name, :createdDate, :dueDate)", paramMap) == 1;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean update(Action action) {
		final MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", action.getId());
		paramMap.addValue("name", action.getName());
		paramMap.addValue("createdDate", action.getCreatedDate());
		paramMap.addValue("dueDate", action.getDueDate());
		return jdbcTemplate.update("UPDATE Action SET name = :name, createdDate :createdDate, dueDate = :dueDate WHERE id = :id", paramMap) == 1;
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean delete(Integer id) {
		return jdbcTemplate.update("DELETE FROM Action WHERE id = :id", new MapSqlParameterSource("id", id)) == 1;
	}
	
	/** {@inheritDoc} */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
