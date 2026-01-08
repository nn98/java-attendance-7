package attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Attendance {

    LocalDateTime attendanceTime;
    Status attendanceStatus;

    public Attendance(LocalDateTime attendanceTime) {
        this.attendanceTime = attendanceTime;
        checkAttendanceStatus();
    }

    public boolean comPareDate(LocalDate date) {
        return attendanceTime.toLocalDate().equals(date);
    }

    public LocalDateTime getAttendanceTime() {
        return attendanceTime;
    }

    public Status getAttendanceStatus() {
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
        this.attendanceStatus = AttendanceDay.checkStatusByDateTime(attendanceTime);
    }
}
