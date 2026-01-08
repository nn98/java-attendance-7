package attendance.domain;

import java.util.List;

public class AttendanceStatistics {

    private final String printFormat = "출석: %d회\n지각: %d회\n결석: %d회\n%s";
    private int present;
    private int late;
    private int absence;
    private Sanction sanction;

    public AttendanceStatistics(List<Attendance> attendances) {
        this.present = 0;
        this.late = 0;
        this.absence = 0;
        attendances.forEach(attendance -> {
            AttendanceStatus attendanceStatus = attendance.getAttendanceStatus();
            countStatistics(attendanceStatus);
        });
        checkSanction();
    }

    private void countStatistics(AttendanceStatus attendanceStatus) {
        if (attendanceStatus.equals(AttendanceStatus.PRESENT)) {
            present += 1;
        }
        if (attendanceStatus.equals(AttendanceStatus.LATE)) {
            late += 1;
        }
        if (attendanceStatus.equals(AttendanceStatus.ABSENCE)) {
            absence += 1;
        }
    }

    private void checkSanction() {
        int totalAbsence = late / 3 + absence;
        sanction = Sanction.getSanctionByAbsenceCount(totalAbsence);
    }

    @Override
    public String toString() {
        return String.format(printFormat, present, late, absence, sanction.toString());
    }
}
