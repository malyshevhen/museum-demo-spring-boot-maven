package com.example.constraints.domain.users;

public class UserConstraints {
    private UserConstraints() {
        throw new IllegalStateException("UserConstraints could not be instantiated!");
    }

    /**
     * This regular expression enforces that the password must
     * have at least one alphabetic character and at least one digit,
     * and it must be at least 8 characters long.
     */
    public static final String PASSWORD_REGEXP = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    /**
     * Email validation regular expression.
     */
    public static final String EMAIL_REGEXP = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";

    /**
     * Minimal length of user`s firstname and lastname.
     */
    public static final int MIN_NAME_LENGTH = 3;

    /**
     * Maximum length of user`s firstname  and lastname.
     */
    public static final int MAX_NAME_LENGTH = 30;

    /**
     * Maximum length of the password.
     */
    public static final int PASSWORD_MAX_LENGTH = 25;
}
