const API_BASE_URL = "http://localhost:8000";

// login related urls
export const API_LOGIN_URL = API_BASE_URL + "/api/unauth/login";
export const API_FB_LOGIN_URL = API_BASE_URL + "/api/unauth/fb-login";
export const API_SEND_PASS_RESET_EMAIL_URL = API_BASE_URL + "/api/mail/password-reset/";
export const API_PASS_RESET_URL = API_BASE_URL + "/api/user/password-reset";

//user related urls
export const API_USER_RIDE_HISTORY_URL = API_BASE_URL + "/api/rides/user/history";
export const API_DRIVER_REVIEWS_URL = API_BASE_URL + "/api/drivers/reviews";
export const API_NEW_REVIEWS_URL = API_BASE_URL + "/api/drivers/review/new";

export const API_DRIVER_RIDE_HISTORY_URL = API_BASE_URL + "/api/rides/driver/history";

export const API_ADMIN_RIDE_HISTORY_URL = API_BASE_URL + "/api/rides/admin/history";

