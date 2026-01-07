package attendance.domain;

import attendance.util.AttendanceMapper;
import attendance.util.MissionError;
import java.util.List;
import java.util.Map;

public class Attendances {

    Map<Crew, List<Attendance>> attendances;

    public Attendances(Map<Crew, List<Attendance>> attendances) {
        this.attendances = attendances;
    }

    public void printEveryAttendances() {
        attendances.forEach((key, value) -> {
            System.out.println(key);
            System.out.println("\t" + value);
        });
    }

    public List<Attendance> getAttendancesByCrewName(String crewName) {
        Crew crew = new Crew(crewName);
        return attendances.get(crew);
    }

    public void insertAttendanceIfCrewExist(String crewName, String attendanceTime) {
        List<Attendance> attendanceList = getAttendancesByCrewName(crewName);
        Attendance attendance = AttendanceMapper.todayTimeToAttendance(attendanceTime);
        attendanceList.add(attendance);
    }

    public Crew getCrewIfExist(String crewName) {
        Crew crew = new Crew(crewName);
        return attendances.keySet().stream()
                .filter(c -> c.equals(crew))
                .findFirst()
                .orElseThrow(MissionError.NON_EXIST_CREW::exception);
    }
}
