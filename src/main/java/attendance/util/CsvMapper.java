package attendance.util;

import attendance.domain.Attendance;
import attendance.domain.Attendances;
import attendance.domain.Crew;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvMapper {

    public static Attendances toAttendances(List<String[]> records) {
        String[] labels = records.removeFirst();
        System.out.println(Arrays.toString(labels));
        Map<Crew, List<Attendance>> allCrewAttendance = generateAllCrewAttendances(records);
        return new Attendances(allCrewAttendance);
    }

    private static Map<Crew, List<Attendance>> generateAllCrewAttendances(List<String[]> records) {
        Map<Crew, List<Attendance>> attendances = new HashMap<>();

        for (String[] record : records) {
            String crewName = record[0].trim();
            String dateTime = record[1].trim();
            Crew crew = new Crew(crewName);
            Attendance attendance = AttendanceMapper.dateTimeToAttendance(dateTime);

            List<Attendance> attendanceHistory = attendances.getOrDefault(crew, new ArrayList<>());
            attendanceHistory.add(attendance);
            attendanceHistory.sort(Comparator.comparing(Attendance::getAttendanceTime));
            attendances.put(crew, attendanceHistory);
        }
        return attendances;
    }
}
