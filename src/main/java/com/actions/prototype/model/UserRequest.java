package com.actions.prototype.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.actions.prototype.exception.user.UserException;

/**
 * <p>
 * UserRequest class.
 * </p>
 * 
 * @author Rafael Ortiz.
 */
public class UserRequest implements Serializable {

	/** Constant <code>serialVersionUID=2161915877944870239L</code> */
	private static final long serialVersionUID = 2161915877944870239L;

	@NotNull
	private String username;
	
	@NotNull
	private String firstName;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private String password;
	
	@NotNull
	private String passwordConfirmation;
	
	private String email;
	
	private Date birthDate;
	
	private String userType;
	
	public User buildUser() {
		if(password.equals(passwordConfirmation)) {
			final User user = new User();
			user.setUsername(username);
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPassword(password);
			user.setEmail(StringUtils.isBlank(email) ? null : email);
			user.setBirthDate(birthDate);
			user.setType(StringUtils.isBlank(userType) ? "ROLE_USER" : userType);
			return user;
		}
		throw new UserException("An error ocurred. Please verify the fields.");
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param passwordConfirmation the passwordConfirmation to set
	 */
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
