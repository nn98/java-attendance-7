package attendance.service;

import attendance.domain.Attendances;
import attendance.dto.AttendanceLine;
import attendance.util.AttendanceMapper;
import java.util.List;

public class Service {

    private final Attendances attendances;
    private final AttendanceMapper attendanceMapper;

    public Service(Attendances attendances) {
        this.attendances = attendances;
        this.attendanceMapper = new AttendanceMapper();
    }

    public void init() {
    }

    public List<AttendanceLine> getAttendanceLinesByCrewName(String crewName) {
        return attendanceMapper.toAttendanceLines(attendances.getAttendancesByCrewName(crewName));
    }

    public AttendanceLine insertAttendance(String crewName, String attendanceTime) {
        return attendances.insertAttendanceIfCrewExist(crewName, attendanceTime);
    }

}
