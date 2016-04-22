package com.actions.prototype.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.actions.prototype.exception.user.UserException;
import com.actions.prototype.model.resource.user.User;

/**
 * <p>
 * UserDaoImpl class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Repository
public class UserDaoImpl implements UserDao {
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	/**
	 * <p>
	 * Constructor for UserDaoImpl.
	 * </p>
	 * 
	 * @param dataSource
	 *            a {@link javax.sql.DataSource} object.
	 */
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	/** {@inheritDoc} */
	@Override
	public User findByUsername(String username) {
		try {
			return jdbcTemplate.queryForObject("SELECT u.username AS username, u.first_name AS firstName, u.last_name AS lastName, "
					+ "u.password AS password, u.email AS email, u.birth_date AS birthDate, ur.role AS role "
					+ "FROM User u JOIN User_roles ur ON (u.username = ur.username) WHERE u.username = :username AND u.enabled = TRUE",
					new MapSqlParameterSource("username", username),
					(rs, rowNum) -> {
						return User.builder()
							.username(rs.getString("username"))
							.firstName(rs.getString("firstName"))
							.lastName(rs.getString("lastName"))
							.password(rs.getString("password"))
							.email(rs.getString("email"))
							.birthDate(rs.getDate("birthDate"))
							.type(rs.getString("role")).build();
					});
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean insert(User user) {
		if (findByUsername(user.getUsername()) == null) {
			final MapSqlParameterSource userParamMap = new MapSqlParameterSource();
			userParamMap.addValue("username", user.getUsername());
			userParamMap.addValue("firstName", user.getFirstName());
			userParamMap.addValue("lastName", user.getLastName());
			userParamMap.addValue("password", user.getPassword());
			userParamMap.addValue("email", user.getEmail());
			userParamMap.addValue("birthDate", user.getBirthDate());
			if (jdbcTemplate.update("INSERT INTO User VALUES(:username, :firstName, :lastName, :password, :email, :birthDate, TRUE)", userParamMap) == 1) {
				final MapSqlParameterSource userRoleParamMap = new MapSqlParameterSource();
				userRoleParamMap.addValue("username", user.getUsername());
				userRoleParamMap.addValue("role", user.getType());
				return jdbcTemplate.update("INSERT INTO User_roles(username, role) VALUES(:username, :role);", userRoleParamMap) == 1;
			}
			
			return false;
		}
		
		throw new UserException("Username already exist. Please use another one.");
	}
	
	/** {@inheritDoc} */
	@Override
	public Boolean update(User user) {
		final MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("username", user.getUsername());
		paramMap.addValue("firstName", user.getFirstName());
		paramMap.addValue("lastName", user.getLastName());
		paramMap.addValue("password", user.getPassword());
		paramMap.addValue("email", user.getEmail());
		paramMap.addValue("birthDate", user.getBirthDate());
		return jdbcTemplate.update("UPDATE User SET username = :username, first_name = :firstName, last_name = :lastName, "
				+ "password = :password, email = :email, birth_date = :birthDate, enabled = :enabled", paramMap) == 1;
	}
	
	/** {@inheritDoc} */
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
