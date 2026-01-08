package attendance.dto;

public record AttendanceUpdate(AttendanceLine before, AttendanceLine after) {
    @Override
    public String toString() {
        return before + " -> " + after.toStringWithoutDate() + " 수정 완료!";
    }
}
