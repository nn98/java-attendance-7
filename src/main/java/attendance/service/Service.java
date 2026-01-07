package attendance.service;

import attendance.domain.AttendanceDay;
import attendance.domain.Attendances;
import attendance.dto.AttendanceLine;
import attendance.util.AttendanceMapper;
import attendance.util.MissionError;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Service {

    private final Attendances attendances;
    private final AttendanceMapper attendanceMapper;

    public Service(Attendances attendances) {
        this.attendances = attendances;
        this.attendanceMapper = new AttendanceMapper();
    }

    public List<AttendanceLine> getAttendanceLinesByCrewName(String crewName) {
        return attendanceMapper.toAttendanceLines(attendances.getAttendancesByCrewName(crewName));
    }

    public AttendanceLine insertAttendance(String crewName, String attendanceTime) {
        return attendances.insertAttendanceIfCrewExist(crewName, attendanceTime);
    }

    public void isCrewExist(String crewName) {
        attendances.getCrewIfExist(crewName);
    }

    public void isSchoolDay() {
        LocalDate today = DateTimes.now().toLocalDate();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        boolean isSchoolDay = AttendanceDay.isSchoolDay(dayOfWeek);
        if (!isSchoolDay) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", Locale.KOREAN);
            String todayString = today.format(formatter);
            throw MissionError.NON_SCHOOL_DAY.exception(todayString);
        }
    }
}
