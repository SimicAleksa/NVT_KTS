package com.example.nvt_kts_back.configurations;

public class Settings {
    public static final String CROSS_ORIGIN_FRONTEND_PATH = "http://localhost:4200";

    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";
    public static final String DRIVER_ROLE_NAME = "ROLE_DRIVER";
    public static final String USER_ROLE_NAME = "ROLE_USER";

    public static final String PRE_AUTH_ADMIN_ROLE = "hasRole('ADMIN')";
    public static final String PRE_AUTH_DRIVER_ROLE = "hasRole('DRIVER')";
    public static final String PRE_AUTH_USER_ROLE = "hasRole('USER')";
    public static final String PRE_AUTH_DRIVER_USER_ROLE = "hasAnyRole('DRIVER','USER')";

    public static final String PRE_AUTH_DRIVER_USER_ADMIN_ROLE = "hasAnyRole('DRIVER','USER', 'ADMIN')";

    public static final String RESET_PASS_PAGE_URL = CROSS_ORIGIN_FRONTEND_PATH + "/login/reset-password/";
    public static final String CONFIRM_PASS_PAGE_URL = CROSS_ORIGIN_FRONTEND_PATH + "/chat/confirmRegistration/";

    public static final String FB_APP_ID = "904097183957711";
    public static final String FB_APP_SECRET = "44d9734e3b4cad4afa772b1538b13ded";

    public static final String GOOGLE_CLIENT_ID = "333701736099-t5838qp4nfmp4ijfq2bjt8vtp6lb8570.apps.googleusercontent.com";

    public static final String ADMIN_EMAIL = "admin@gmail.com";
    public static final double DEFAULT_LATITUDE = 45.25124;
    public static final double DEFAULT_LONGITUDE = 19.84719;
}
