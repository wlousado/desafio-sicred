package br.com.sicred.commons.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatterUtil {
    private static final String PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static String dateToString(LocalDateTime time){
        return DateTimeFormatter.ofPattern(PATTERN).format(time);
    }
}
