package attendance.util;

import attendance.domain.Attendance;
import attendance.domain.Attendances;
import attendance.domain.Crew;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CsvMapper {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Attendances toAttendances(List<String[]> records) {
        String[] labels = records.removeFirst();
        System.out.println(Arrays.toString(labels));
        Map<Crew, List<Attendance>> allCrewAttendance = generateAllCrewAttendances(records);
        return new Attendances(allCrewAttendance);
    }

    private Map<Crew, List<Attendance>> generateAllCrewAttendances(List<String[]> records) {
        Map<Crew, List<Attendance>> attendances = new HashMap<>();

        for (String[] record:records) {
            String crewName = record[0].trim();
            LocalDateTime attendanceDateTime = LocalDateTime.parse(record[1].trim(), DATE_TIME_FORMATTER);
            Crew crew = new Crew(crewName);
            Attendance attendance = new Attendance(attendanceDateTime);

            List<Attendance> attendanceHistory = attendances.getOrDefault(crew, new ArrayList<>());
            attendanceHistory.add(attendance);
            attendances.put(crew, attendanceHistory);
        }

        return attendances;
    }
}
