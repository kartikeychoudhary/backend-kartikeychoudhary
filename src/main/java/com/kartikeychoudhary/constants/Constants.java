package com.kartikeychoudhary.constants;

public class Constants {
	private Constants() {}
	
	public static final String SECRET = "secret";
	public static final String JWT_PREFIX = "Bearer ";
	public static final String ROLE_PREFIX 	= "ROLE_";
	public static final String SUPER_ADMIN 	= "SUPER_ADMIN";
	public static final String ADMIN		= "ADMIN";
	public static final String USER		 	= "USER";
	
	public static final String LOGIN_URL 	= "api/v1/login";
	
	public static final String SUBJECT_LENGTH_ERROR = "Subject length exceded the max allowed value";
	public static final String EMAIL_LENGTH_ERROR = "Email not allowed";
	public static final String MESSAGE_LENGTH_ERROR = "Message length exceded the max allowed value";

	public static final String MESSAGE = "MESSAGE";
	public static final String RESPONSE = "RESPONSE";
	public static final String RESULT = "RESULT";
	public static final String ERROR = "ERROR";


	public static final String TOKEN = "TOKEN";
	public static final String REFRESH_TOKEN = "REFRESH_TOKEN";


}
