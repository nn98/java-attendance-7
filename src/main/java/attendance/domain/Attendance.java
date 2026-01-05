package attendance.domain;

import java.time.LocalDateTime;

public class Attendance {

    LocalDateTime attendanceTime;
    Status attendanceStatus;

    public Attendance(LocalDateTime attendanceTime) {
        this.attendanceTime = attendanceTime;
        checkAttendanceStatus();
    }

    private void checkAttendanceStatus() {
        this.attendanceStatus = AttendanceDay.checkStatusByDateTime(attendanceTime);
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
}
