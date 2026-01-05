package attendance.domain;

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
        Crew target = new Crew(crewName);
        return attendances.get(target);
    }
}
