package com.example.constants;

import com.example.constraints.museum.ArticleConstraints;
import com.example.constraints.museum.AuthorConstraints;
import com.example.constraints.museum.EventConstraints;
import com.example.constraints.users.UserConstraints;
import org.apache.commons.lang3.StringUtils;

public class TestConstants {
    private TestConstants() {
        throw new IllegalStateException("Utility class!");
    }

    public static final String EMPTY_STRING = "   ";

    public static class Users {
        private Users() {
            throw new IllegalStateException("Utility class");
        }

        public static final String UNDERSIZED_USER_FIELD
                = buildUndersizedString(UserConstraints.MIN_NAME_LENGTH);
        public static final String OVERSIZED_USER_FIELD
                = buildOversizedString(UserConstraints.MAX_NAME_LENGTH);
    }

    public static class Authors {
        private Authors() {
            throw new IllegalStateException("Utility class");
        }

        public static final String UNDERSIZED_USERNAME_FIELD
                = buildUndersizedString(AuthorConstraints.MIN_USERNAME_LENGTH);
        public static final String OVERSIZED_USERNAME_FIELD
                = buildOversizedString(AuthorConstraints.MAX_USERNAME_LENGTH);
    }

    public static class Articles {
        private Articles() {
            throw new IllegalStateException("Utility class");
        }

        public static final String UNDERSIZED_TITLE_FIELD
                = buildUndersizedString(ArticleConstraints.MIN_TITLE_LENGTH);
        public static final String OVERSIZED_TITLE_FIELD
                = buildOversizedString(ArticleConstraints.MAX_TITLE_LENGTH);
        public static final String UNDERSIZED_CONTENT_FIELD
                = buildUndersizedString(ArticleConstraints.MIN_CONTENT_LENGTH);
        public static final String OVERSIZED_CONTENT_FIELD
                = buildOversizedString(ArticleConstraints.MAX_CONTENT_LENGTH);
    }

    public static class Events {
        private Events() {
            throw new IllegalStateException("Utility class");
        }

        public static final String UNDERSIZED_TITLE_FIELD
                = buildUndersizedString(EventConstraints.MIN_TITLE_LENGTH);
        public static final String OVERSIZED_TITLE_FIELD
                = buildOversizedString(EventConstraints.MAX_TITLE_LENGTH);
        public static final String UNDERSIZED_CONTENT_FIELD
                = buildUndersizedString(EventConstraints.MIN_CONTENT_LENGTH);
        public static final String OVERSIZED_CONTENT_FIELD
                = buildOversizedString(EventConstraints.MAX_CONTENT_LENGTH);
    }

    protected static String buildUndersizedString(int length) {
        return StringUtils.repeat("C", length - 1);
    }

    protected static String buildOversizedString(int length) {
        return StringUtils.repeat("C", length + 1);
    }
}
