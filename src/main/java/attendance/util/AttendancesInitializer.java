package attendance.util;

import attendance.domain.Attendance;
import attendance.domain.Attendances;
import attendance.domain.Crew;
import attendance.domain.SchoolDays;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class AttendancesInitializer {

    private final Attendances attendances;
    private final CsvLoader csvLoader;
    private final AttendanceMapper attendanceMapper;
    private final SchoolDays schoolDays;
    private final LocalDate today;

    public AttendancesInitializer(CsvLoader csvLoader, AttendanceMapper attendanceMapper, SchoolDays schoolDays,
                                  LocalDate today) {
        this.csvLoader = csvLoader;
        this.attendances = new Attendances();
        this.attendanceMapper = attendanceMapper;
        this.schoolDays = schoolDays;
        this.today = today;
        generateAttendances();
        checkAbsence();
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

    private void checkAbsence() {
        Set<Crew> crews = attendances.getCrews();
        crews.forEach(crew -> {
            List<Attendance> attendanceList = attendances.getAttendancesByCrewName(crew.getName());
            checkAbsence(crew, attendanceList);
        });
    }

    private void checkAbsence(Crew crew, List<Attendance> attendanceList) {
        schoolDays.getSchoolDays().forEach(schoolDate -> {
            Attendance attendance = attendances.getAttendanceByDate(attendanceList, schoolDate);
            if (attendance == null) {
                Attendance absenceAttendance = attendanceMapper.toAbsenceAttendance(schoolDate);
                attendances.insertAttendance(crew, absenceAttendance);
            }
        });
    }

    public Attendances getAttendances() {
        return attendances;
    }
}
