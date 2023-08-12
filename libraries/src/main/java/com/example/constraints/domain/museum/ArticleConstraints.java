package com.example.constraints.domain.museum;

public class ArticleConstraints {
    private ArticleConstraints() {
        throw new IllegalStateException("Utility class");
    }

    public static final int MIN_TITLE_LENGTH = 3;
    public static final int MAX_TITLE_LENGTH = 300;
    public static final int MIN_CONTENT_LENGTH = 30;
    public static final int MAX_CONTENT_LENGTH = 3000;
}
