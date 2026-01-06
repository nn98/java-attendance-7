package attendance.util;

import attendance.domain.Attendance;
import attendance.domain.Status;
import attendance.dto.AttendanceLine;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AttendanceMapper {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MM월 dd일 EEEE HH:mm", Locale.KOREAN);

    public static Attendance dateTimeToAttendance(String dateTime) {
        LocalDateTime attendanceDateTime = LocalDateTime.parse(dateTime.trim(), DATE_TIME_FORMATTER);
        Attendance attendance = new Attendance(attendanceDateTime);
        return attendance;
    }

    public static List<AttendanceLine> toAttendanceLines(List<Attendance> attendances) {
        List<AttendanceLine> attendanceLines = new ArrayList<>();
        attendances.forEach(attendance -> {
            LocalDateTime attendanceTime = attendance.getAttendanceTime();
            Status attendanceStatus = attendance.getAttendanceStatus();
            AttendanceLine attendanceLine =
                    new AttendanceLine(attendanceTime.format(DATE_TIME_FORMATTER), attendanceStatus.getKOREAN_STATUS());
            attendanceLines.add(attendanceLine);
        });
        return attendanceLines;
    }
}
