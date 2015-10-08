package com.actions.prototype.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.actions.prototype.model.User;

/**
 * <p>
 * UserDaoImpl class.
 * </p>
 * 
 * @author Rafael Ortiz.
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
						final User user = new User();
						user.setUsername(rs.getString("username"));
						user.setFirstName(rs.getString("firstName"));
						user.setLastName(rs.getString("lastName"));
						user.setPassword(rs.getString("password"));
						user.setEmail(rs.getString("email"));
						user.setBirthDate(rs.getDate("birthDate"));
						user.setType(rs.getString("role"));
						return user;
					});
		} catch(IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public int insert(User user) {
		final MapSqlParameterSource userParamMap = new MapSqlParameterSource();
		userParamMap.addValue("username", user.getUsername());
		userParamMap.addValue("firstName", user.getFirstName());
		userParamMap.addValue("lastName", user.getLastName());
		userParamMap.addValue("password", user.getPassword());
		userParamMap.addValue("email", user.getEmail());
		userParamMap.addValue("birthDate", user.getBirthDate());
		if(jdbcTemplate.update("INSERT INTO User VALUES(:username, :firstName, :lastName, :password, :email, :birthDate, TRUE)", userParamMap) == 1) {
			final MapSqlParameterSource userRoleParamMap = new MapSqlParameterSource();
			userRoleParamMap.addValue("username", user.getUsername());
			userRoleParamMap.addValue("role", user.getType());
			return jdbcTemplate.update("INSERT INTO User_roles(username, role) VALUES(:username, :role);", userRoleParamMap);
		}
		return 0;
	}
	
	/** {@inheritDoc} */
	@Override
	public int update(User user) {
		final MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("username", user.getUsername());
		paramMap.addValue("firstName", user.getFirstName());
		paramMap.addValue("lastName", user.getLastName());
		paramMap.addValue("password", user.getPassword());
		paramMap.addValue("email", user.getEmail());
		paramMap.addValue("birthDate", user.getBirthDate());
		return jdbcTemplate.update("UPDATE User SET username = :username, first_name = :firstName, last_name = :lastName, "
				+ "password = :password, email = :email, birth_date = :birthDate, enabled = :enabled", paramMap);
	}
}
