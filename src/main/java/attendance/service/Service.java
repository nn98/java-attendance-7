package attendance.service;

import attendance.domain.Attendance;
import attendance.domain.AttendanceDay;
import attendance.domain.AttendanceStatistics;
import attendance.domain.Attendances;
import attendance.dto.AttendanceLine;
import attendance.dto.AttendanceUpdate;
import attendance.util.AttendanceMapper;
import attendance.util.MissionError;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Service {

    private final Attendances attendances;
    private final AttendanceMapper attendanceMapper;
    private final LocalDate today;

    public Service(Attendances attendances, AttendanceMapper attendanceMapper, LocalDate today) {
        this.attendances = attendances;
        this.attendanceMapper = attendanceMapper;
        this.today = today;
    }

    public List<AttendanceLine> getAttendanceLinesByCrewName(String crewName) {
        return attendanceMapper.toAttendanceLines(attendances.getAttendancesByCrewName(crewName));
    }

    public AttendanceLine insertAttendance(String crewName, String attendanceTime) {
        Attendance attendance = attendanceMapper.todayTimeToAttendance(attendanceTime);
        return attendances.insertAttendanceIfCrewExist(crewName, attendance);
    }

    public void isCrewExist(String crewName) {
        attendances.getCrewIfExist(crewName);
    }

    public void isSchoolDay(int date) {
        LocalDate targetDate = today.withDayOfMonth(date);
        DayOfWeek dayOfWeek = targetDate.getDayOfWeek();
        boolean isSchoolDay = AttendanceDay.isSchoolDay(dayOfWeek);
        if (!isSchoolDay) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM월 dd일 EEEE", Locale.KOREAN);
            String todayString = today.format(formatter);
            throw MissionError.NON_SCHOOL_DAY.exception(todayString);
        }
    }

    public void isSchoolDay() {
        isSchoolDay(today.getDayOfMonth());
    }

    public AttendanceUpdate updateAttendance(String crewName, int updateDate, String updateTime) {
        LocalDate targetDate = today.withDayOfMonth(updateDate);
        LocalDateTime updateDateTime = LocalDateTime.of(targetDate, LocalTime.parse(updateTime));
        return attendances.getAttendanceByCrewNameAndUpdateDateTime(crewName, updateDateTime);
    }

    public String getAttendanceStatisticsByCrewName(String crewName) {
        List<Attendance> attendanceList = attendances.getAttendancesByCrewName(crewName);
        AttendanceStatistics attendanceStatistics = new AttendanceStatistics(attendanceList);
        return attendanceStatistics.toString();
    }
}
