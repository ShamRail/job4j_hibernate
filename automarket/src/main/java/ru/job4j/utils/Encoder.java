package ru.job4j.utils;

public class Encoder {

    public static final String SLASH_HTTP_ENCODING = "%2F";

    public static final String BACKSLASH_HTTP_ENCODING = "%5C";

    public static String encode(String input) {
        return (input != null) ? input.replaceAll("\\\\", BACKSLASH_HTTP_ENCODING)
                    .replaceAll("/", SLASH_HTTP_ENCODING) : null;
    }

}
