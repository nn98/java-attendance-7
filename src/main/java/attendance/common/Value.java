package attendance.common;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Value {

    public static final DateTimeFormatter FULL_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm", Locale.KOREAN);
    public static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm", Locale.KOREAN);
    public static final DateTimeFormatter CSV_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", Locale.KOREAN);
}
