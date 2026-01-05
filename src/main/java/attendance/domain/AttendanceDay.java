package attendance.domain;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public enum AttendanceDay {

    MONDAY(DayOfWeek.MONDAY, LocalTime.of(13, 0)),
    TUESDAY(DayOfWeek.TUESDAY, LocalTime.of(10, 0)),
    WEDNESDAY(DayOfWeek.WEDNESDAY, LocalTime.of(10, 0)),
    THURSDAY(DayOfWeek.THURSDAY, LocalTime.of(10, 0)),
    FRIDAY(DayOfWeek.FRIDAY, LocalTime.of(10, 0)),
    SATURDAY(DayOfWeek.SATURDAY, LocalTime.of(0, 0)),
    SUNDAY(DayOfWeek.SUNDAY, LocalTime.of(0, 0));

    final DayOfWeek day;
    final LocalTime begin;

    AttendanceDay(DayOfWeek day, LocalTime begin) {
        this.day = day;
        this.begin = begin;
    }

    public static AttendanceDay valueOf(LocalDateTime dateTime) {
        return Arrays.stream(AttendanceDay.values())
                .filter(attendanceDay -> attendanceDay.day == dateTime.getDayOfWeek())
                .findFirst()
                .orElseThrow();
    }

    public static Status checkStatusByDateTime(LocalDateTime dateTime) {
        AttendanceDay attendanceDay = valueOf(dateTime);
        if (attendanceDay == SATURDAY || attendanceDay == SUNDAY) {
            return Status.NONE;
        }
        Duration duration = Duration.between(attendanceDay.begin, dateTime.toLocalTime());
        return checkStatusByDuration(duration);
    }

    private static Status checkStatusByDuration(Duration duration) {
        long minutes = duration.toMinutes();
        if (minutes > 30) {
            return Status.ABSENCE;
        }
        if (minutes > 5) {
            return Status.LATE;
        }
        return Status.PRESENT;
    }
}
