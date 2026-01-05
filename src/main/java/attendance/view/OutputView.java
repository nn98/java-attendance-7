package attendance.view;

import attendance.dto.AttendanceLine;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]";

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printException(String message) {
        printLine(String.format("%s %s", ERROR_PREFIX, message));
    }

    public void printAttendanceLines(List<AttendanceLine> attendanceLinesByCrewName) {
        attendanceLinesByCrewName.forEach(attendanceLine -> printLine(attendanceLine.toString()));
    }
}
