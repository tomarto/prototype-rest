package com.actions.prototype.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.actions.prototype.model.Action;

/**
 * <p>
 * ActionDaoImpl class.
 * </p>
 * 
 * @author Rafael Ortiz.
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
	public List<Action> findAll() {
		return jdbcTemplate.query("SELECT * FROM Action", new MapSqlParameterSource(),
				(rs, rowNum) -> {
					final Action action = new Action();
					action.setId(rs.getInt("id"));
					action.setName(rs.getString("name"));
					action.setCreatedDate(rs.getDate("created_date"));
					action.setDueDate(rs.getDate("due_date"));
					return action;
				});
	}
	
	/** {@inheritDoc} */
	@Override
	public void insert(Action action) {
		final MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", action.getId());
		paramMap.addValue("name", action.getName());
		paramMap.addValue("createdDate", action.getCreatedDate());
		paramMap.addValue("dueDate", action.getDueDate());
		jdbcTemplate.update("INSERT INTO Action VALUES(:id, :name, :createdDate, :dueDate)", paramMap);
	}
	
	/** {@inheritDoc} */
	@Override
	public void update(Action action) {
		final MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", action.getId());
		paramMap.addValue("name", action.getName());
		paramMap.addValue("createdDate", action.getCreatedDate());
		paramMap.addValue("dueDate", action.getDueDate());
		jdbcTemplate.update("UPDATE Action SET name = :name, createdDate :createdDate, dueDate = :dueDate WHERE id = :id", paramMap);
	}
	
	/** {@inheritDoc} */
	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("DELETE FROM Action WHERE id = :id", new MapSqlParameterSource("id", id));
	}
	
	/** {@inheritDoc} */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
