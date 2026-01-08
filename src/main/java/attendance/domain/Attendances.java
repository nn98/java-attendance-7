package attendance.domain;

import attendance.dto.AttendanceLine;
import attendance.dto.AttendanceUpdate;
import attendance.util.AttendanceMapper;
import attendance.util.MissionError;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public AttendanceLine insertAttendanceIfCrewExist(String crewName, Attendance attendance) {
        Crew crew = getCrewIfExist(crewName);
        return insertAttendance(crew, attendance);
    }

    public Crew getCrewIfExist(String crewName) {
        System.out.println(crewName);
        Crew crew = new Crew(crewName);
        return attendances.keySet().stream()
                .filter(c -> c.equals(crew))
                .findFirst()
                .orElseThrow(MissionError.NON_EXIST_CREW::exception);
    }

    public AttendanceUpdate getAttendanceByCrewNameAndUpdateDateTime(String crewName, LocalDateTime updateDateTime) {
        List<Attendance> attendanceList = getAttendancesByCrewName(crewName);
        return updateAttendance(attendanceList, updateDateTime);
    }

    private AttendanceUpdate updateAttendance(List<Attendance> attendanceList, LocalDateTime updateDateTime) {
        LocalDate updateDate = updateDateTime.toLocalDate();
        System.out.println(updateDate);
        Attendance before = attendanceList.stream().filter(attendance -> {
            return attendance.comPareDate(updateDate);
        }).findFirst().orElseThrow();
        int targetIndex = attendanceList.indexOf(before);
        Attendance after = new Attendance(updateDateTime);
        attendanceList.set(targetIndex, after);
        return getAttendanceUpdate(before, after);
    }

    private AttendanceUpdate getAttendanceUpdate(Attendance before, Attendance after) {
        AttendanceLine beforeLine = AttendanceMapper.toAttendanceLine(before);
        AttendanceLine afterLine = AttendanceMapper.toAttendanceLine(after);
        return new AttendanceUpdate(beforeLine, afterLine);
    }
}
