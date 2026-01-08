package attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Attendance {

    LocalDate attendanceDate;
    LocalTime attendanceTime;
    AttendanceStatus attendanceStatus;

    public Attendance(LocalDateTime attendanceTime) {
        this.attendanceDate = attendanceTime.toLocalDate();
        this.attendanceTime = attendanceTime.toLocalTime();
        checkAttendanceStatus();
    }

    public Attendance(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
        this.attendanceTime = null;
        this.attendanceStatus = AttendanceStatus.ABSENCE;
    }

    public boolean comPareDate(LocalDate date) {
        return attendanceDate.equals(date);
    }

    public LocalDateTime getAttendanceDateTime() {
        return LocalDateTime.of(attendanceDate, attendanceTime);
    }

    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }

    public LocalTime getAttendanceTime() {
        return attendanceTime;
    }

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "attendanceTime=" + attendanceTime +
                ", attendanceStatus=" + attendanceStatus +
                '}';
    }

    private void checkAttendanceStatus() {
        this.attendanceStatus = AttendanceDay.checkStatusByDateTime(getAttendanceDateTime());
    }
}
