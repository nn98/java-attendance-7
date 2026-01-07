package attendance.view;

import attendance.dto.AttendanceLine;
import java.util.List;

public class OutputView {

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printException(String message) {
        printLine(message);
    }

    public void printAttendanceLines(List<AttendanceLine> attendanceLinesByCrewName) {
        attendanceLinesByCrewName.forEach(attendanceLine -> printLine(attendanceLine.toString()));
    }
}
