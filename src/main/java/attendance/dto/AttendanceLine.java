package attendance.dto;

import static attendance.common.Value.FULL_DATE_TIME_FORMAT;
import static attendance.common.Value.TIME_FORMAT;

import java.time.LocalDateTime;

public record AttendanceLine(LocalDateTime attendanceTime, attendance.domain.Status status) {

    @Override
    public String toString() {
        return String.format("%s (%s)", attendanceTime.format(FULL_DATE_TIME_FORMAT), status.getKOREAN_STATUS());
    }

    public String toStringWithoutDate() {
        return String.format("%s (%s)", attendanceTime.format(TIME_FORMAT), status.getKOREAN_STATUS());
    }
}
