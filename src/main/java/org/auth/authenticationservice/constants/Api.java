package org.auth.authenticationservice.constants;

public class Api {
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String REFRESH_TOKEN = "refreshToken";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String SCOPE_ADMIN = "SCOPE_ADMIN";
    public static final String SCOPE_USER = "SCOPE_USER";

    public static final String[] PUBLIC_ROUTE = {
        "/swagger-ui/**",
        "/v3/**",
        "/api/**/public/**",
        "/actuator/**"
    };

}
