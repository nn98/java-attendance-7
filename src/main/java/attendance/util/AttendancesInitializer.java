package attendance.util;

import attendance.domain.Attendance;
import attendance.domain.Attendances;
import java.util.List;

public class AttendancesInitializer {

    private final Attendances attendances;
    private final CsvLoader csvLoader;
    private final AttendanceMapper attendanceMapper;

    public AttendancesInitializer(CsvLoader csvLoader, AttendanceMapper attendanceMapper) {
        this.csvLoader = csvLoader;
        this.attendances = new Attendances();
        this.attendanceMapper = attendanceMapper;
        generateAttendances();
    }

    private void generateAttendances() {
        List<String[]> records = csvLoader.getRecords();
        String[] label = records.removeFirst();
        records.forEach(this::putRecordByAttendances);
    }

    private void putRecordByAttendances(String[] record) {
        String crewName = record[0].trim();
        String attendanceDateTime = record[1].trim();
        Attendance attendance = attendanceMapper.dateTimeToAttendance(attendanceDateTime);
        attendances.insertAttendance(crewName, attendance);
    }

    public Attendances getAttendances() {
        return attendances;
    }
}
