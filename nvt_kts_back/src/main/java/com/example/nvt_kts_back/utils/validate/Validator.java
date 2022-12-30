package com.example.nvt_kts_back.utils.validate;

public class Validator {
    private static final String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";


    public static boolean isNotEmpty(final String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean isLenBetween(final String value, final int start, final int end) {
        return value.length() >= start && value.length() <= end;
    }

    public static boolean isLenGreaterThan(final String value, final int num) {
        return value.length() >= num;
    }

    public static boolean isLenLesserThan(final String value, final int num) {
        return value.length() <= num;
    }

    public static boolean isOfExactLength(final String value, final int num) {
        return value.length() == num;
    }

    public static boolean validateEmail(final String email) {
        return email.matches(emailRegex);
    }
}
