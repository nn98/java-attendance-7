package attendance.dto;

public record AttendanceLine(String formattedTime, String status) {

    @Override public String toString() {
        return String.format("%s (%s)", formattedTime, status);
    }
}
