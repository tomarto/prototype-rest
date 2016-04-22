package com.actions.prototype.request.user;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import com.actions.prototype.exception.user.UserException;
import com.actions.prototype.model.resource.user.User;

import lombok.Setter;

/**
 * <p>
 * UserRequest class.
 * </p>
 * 
 * @author Omar Ortiz.
 */
@Setter
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
		if (password.equals(passwordConfirmation)) {
			return User.builder()
				.username(username)
				.firstName(firstName)
				.lastName(lastName)
				.password(password)
				.email(StringUtils.isBlank(email) ? null : email)
				.birthDate(birthDate)
				.type(StringUtils.isBlank(userType) ? "ROLE_USER" : userType).build();
		}
		
		throw new UserException("An error ocurred. Please verify the fields.");
	}
}
