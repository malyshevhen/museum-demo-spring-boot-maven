package com.example.constraints.domain.constants;

import org.apache.commons.lang3.StringUtils;

public class TestConstants {
    private TestConstants() {
        throw new IllegalStateException("Util class!");
    }

    public static final String EMPTY_STRING = "   ";
    public static final String TWO_CHAR_STRING = StringUtils.repeat("C", 2);
    public static final String THIRTYONE_CHAR_STRING = StringUtils.repeat("C", 31);
    public static final String TWENTY_NINE_CHAR_STRING = StringUtils.repeat("C", 29);
    public static final String THREE_HUNDRED_ONE_CHAR_STRING = StringUtils.repeat("C", 301);
    public static final String THREE_THOUSENT_ONE_CHAR_STRING = StringUtils.repeat("C", 3001);
}
