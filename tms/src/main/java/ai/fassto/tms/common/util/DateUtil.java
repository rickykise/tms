package ai.fassto.tms.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter YYYYMMDD_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private DateUtil() {
    }

    public static String getTodayYYYYMMDD() {
        return LocalDateTime.now().format(YYYYMMDD_FORMATTER);
    }

    public static LocalDate parseToLocalDate(String date) {
        return LocalDate.parse(date, YYYYMMDD_FORMATTER);
    }

    public static String parseToStringDateYYYYMMDD(LocalDate localDate) {
        return localDate.format(YYYYMMDD_FORMATTER);
    }
}
