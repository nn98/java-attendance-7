package attendance.dto;

import static attendance.common.Value.DATE_FORMAT;
import static attendance.common.Value.DATE_TIME_FORMAT;
import static attendance.common.Value.TIME_FORMAT;

import attendance.domain.Status;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AttendanceLine(LocalDate attendanceDate, LocalTime attendanceTime, Status status) {

    @Override
    public String toString() {
        if (attendanceTime == null) {
            return String.format("%s --:-- (%s)", attendanceDate.format(DATE_FORMAT), status.getKOREAN_STATUS());
        }
        LocalDateTime attendanceDateTime = LocalDateTime.of(attendanceDate, attendanceTime);
        return String.format("%s (%s)", attendanceDateTime.format(DATE_TIME_FORMAT), status.getKOREAN_STATUS());
    }

    public String toStringWithoutDate() {
        return String.format("%s (%s)", attendanceTime.format(TIME_FORMAT), status.getKOREAN_STATUS());
    }
}
