package utils;

import java.io.IOException;
import java.io.Reader;

public class StringConverter {

    public static String convert(Reader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[256];
        while (reader.read(buffer) != -1) {
            sb.append(buffer);
        }
        return sb.toString().trim();
    }

}
