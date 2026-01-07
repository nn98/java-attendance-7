package attendance.domain;

import attendance.dto.AttendanceLine;
import attendance.util.AttendanceMapper;
import attendance.util.MissionError;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attendances {

    Map<Crew, List<Attendance>> attendances;

    public Attendances() {
        this.attendances = new HashMap<>();
    }

    public void insertAttendance(String crewName, Attendance attendance) {
        Crew crew = new Crew(crewName);
        insertAttendance(crew, attendance);
    }

    public AttendanceLine insertAttendance(Crew crew, Attendance attendance) {
        List<Attendance> attendanceHistory = attendances.computeIfAbsent(crew, attendanceList -> new ArrayList<>());
        attendanceHistory.add(attendance);
        attendanceHistory.sort(Comparator.comparing(Attendance::getAttendanceTime));
        return AttendanceMapper.toAttendanceLine(attendance);
    }

    public List<Attendance> getAttendancesByCrewName(String crewName) {
        Crew crew = new Crew(crewName);
        return attendances.get(crew);
    }

    public AttendanceLine insertAttendanceIfCrewExist(String crewName, String attendanceTime) {
        Crew crew = getCrewIfExist(crewName);
        Attendance attendance = AttendanceMapper.todayTimeToAttendance(attendanceTime);
        return insertAttendance(crew, attendance);
    }

    public Crew getCrewIfExist(String crewName) {
        Crew crew = new Crew(crewName);
        return attendances.keySet().stream()
                .filter(c -> c.equals(crew))
                .findFirst()
                .orElseThrow(MissionError.NON_EXIST_CREW::exception);
    }
}
