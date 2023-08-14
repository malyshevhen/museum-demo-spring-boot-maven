package com.example.constraints;

public class SharedConstraints {
    private SharedConstraints() {
        throw new IllegalStateException("Utility class!");
    }

    public static final int MIN_FIELD_LENGTH = 3;
    public static final int MAX_FIELD_LENGTH = 100;
}
