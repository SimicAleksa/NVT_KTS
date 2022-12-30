const API_BASE_URL = "http://localhost:8000";

// login related urls
export const API_LOGIN_URL = API_BASE_URL + "/api/unauth/login";
export const API_SEND_PASS_RESET_EMAIL_URL = API_BASE_URL + "/api/mail/password-reset/";
export const API_PASS_RESET_URL = API_BASE_URL + "/api/user/password-reset";
