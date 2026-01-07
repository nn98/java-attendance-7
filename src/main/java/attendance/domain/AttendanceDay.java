package attendance.domain;

import attendance.util.MissionError;
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
        return findByDayOfWeek(dateTime.getDayOfWeek());
    }

    public static AttendanceDay findByDayOfWeek(DayOfWeek dayOfWeek) {
        return Arrays.stream(AttendanceDay.values())
                .filter(attendanceDay -> attendanceDay.day.equals(dayOfWeek))
                .findFirst()
                .orElseThrow(MissionError.INVALID_INPUT::exception);
    }

    public static boolean isSchoolDay(DayOfWeek dayOfWeek) {
        AttendanceDay attendanceDay = findByDayOfWeek(dayOfWeek);
        if (attendanceDay.equals(AttendanceDay.SATURDAY) || attendanceDay.equals(AttendanceDay.SUNDAY)) {
            return false;
        }
        return true;
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
