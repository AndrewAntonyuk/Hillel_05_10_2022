package hw_10_Logger;

import java.util.EnumSet;

public enum LoggingLevel {
    DEBUG, INFO;

    public static boolean isValidEnum(String s) {
        for (LoggingLevel l : EnumSet.allOf(LoggingLevel.class)) {
            if (s.equalsIgnoreCase(l.toString())) {
                return true;
            }
        }
        return false;
    }
}
