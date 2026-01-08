package attendance.util;

import static attendance.common.Value.CSV_DATE_TIME_FORMAT;
import static attendance.common.Value.TIME_FORMAT;

import attendance.common.Value;
import attendance.domain.Attendance;
import attendance.domain.Status;
import attendance.dto.AttendanceLine;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceMapper {

    private final LocalDate today;

    public AttendanceMapper(LocalDate today) {
        this.today = today;
    }

    public static AttendanceLine toAttendanceLine(Attendance attendance) {
        LocalDateTime attendanceTime = attendance.getAttendanceTime();
        Status attendanceStatus = attendance.getAttendanceStatus();
        AttendanceLine attendanceLine = new AttendanceLine(attendanceTime, attendanceStatus);
        return attendanceLine;
    }

    private LocalDateTime parseAttendanceDateTime(String attendanceTime) {
        try {
            if (attendanceTime.contains("-")) {
                return LocalDateTime.parse(attendanceTime, CSV_DATE_TIME_FORMAT);
            }
            LocalTime time = LocalTime.parse(attendanceTime, TIME_FORMAT);
            return LocalDateTime.of(today, time);
        } catch (Exception exception) {
            throw MissionError.INVALID_INPUT.exception();
        }
    }

    public Attendance todayTimeToAttendance(String attendanceTime) {
        Attendance attendance = new Attendance(parseAttendanceDateTime(attendanceTime));
        return attendance;
    }

    public Attendance dateTimeToAttendance(String attendanceTime) {
        LocalDateTime attendanceDateTime = LocalDateTime.parse(attendanceTime.trim(), Value.CSV_DATE_TIME_FORMAT);
        Attendance attendance = new Attendance(attendanceDateTime);
        return attendance;
    }

    public List<AttendanceLine> toAttendanceLines(List<Attendance> attendances) {
        List<AttendanceLine> attendanceLines = new ArrayList<>();
        attendances.forEach(attendance -> attendanceLines.add(toAttendanceLine(attendance)));
        return attendanceLines;
    }
}
