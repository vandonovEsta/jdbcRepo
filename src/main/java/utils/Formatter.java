package utils;

import java.util.Arrays;
import java.util.List;

public class Formatter {
    public static String listToString(List list) {
        String result = "";
        result = Arrays.toString(list.toArray())
                .replace("[", "")
                .replace("]", "");
        return result;
    }
}
