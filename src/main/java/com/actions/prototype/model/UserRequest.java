package com.actions.prototype.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

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
	@Pattern(regexp = "^([a-zA-Z0-9_]){5,25}$")
	private String username;
	
	@NotNull
	@Pattern(regexp = "^([a-zA-Z]){2,25}$")
	private String firstName;
	
	@NotNull
	@Pattern(regexp = "^([a-zA-Z]){2,25}$")
	private String lastName;
	
	@NotNull
	@Pattern(regexp = "^([a-zA-Z0-9_.$]){6,16}$")
	private String password;
	
	@NotNull
	@Pattern(regexp = "^([a-zA-Z0-9_.$]){6,16}$")
	private String passwordConfirmation;
	
	@Pattern(regexp = "^(?:[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9]{2,4})?$")
	private String email;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
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
