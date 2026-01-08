package attendance.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SchoolDays {

    private final List<LocalDate> schoolDays;

    public SchoolDays(LocalDate today) {
        schoolDays = new ArrayList<>();
        generateSchoolDays(today);
    }

    public List<LocalDate> getSchoolDays() {
        return schoolDays;
    }

    private void generateSchoolDays(LocalDate today) {
        LocalDate start = today.withDayOfMonth(1);
        for (LocalDate date = start; !date.isAfter(today); date = date.plusDays(1)) {
            if (AttendanceDay.isSchoolDay(date.getDayOfWeek())) {
                schoolDays.add(date);
            }
        }
    }
}
