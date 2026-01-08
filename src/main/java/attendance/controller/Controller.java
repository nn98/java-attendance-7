package attendance.controller;

import attendance.dto.AttendanceLine;
import attendance.dto.AttendanceUpdate;
import attendance.service.Service;
import attendance.view.OutputView;
import java.util.List;

public class Controller {

    private final InputHandler inputHandler;
    private final OutputView outputView;
    private final Service service;

    public Controller(InputHandler inputHandler, OutputView outputView, Service service) {
        this.inputHandler = inputHandler;
        this.outputView = outputView;
        this.service = service;
    }

    public void run() {
        Menu menu;
        do {
            menu = inputHandler.inputMenu();
            execute(menu);
        } while (!menu.equals(Menu.QUIT));
    }

    private void execute(Menu menu) {
        if (menu.equals(Menu.INSERT)) {
            insert();
        }
        if (menu.equals(Menu.UPDATE)) {
            update();
        }
        if (menu.equals(Menu.SELECT)) {
            select();
        }
        if (menu.equals(Menu.SEL_RISK)) {
            select_risk();
        }
    }

    private void insert() {
        service.isSchoolDay();

        String crewName = inputHandler.inputCrewName();
        service.isCrewExist(crewName);

        String attendanceTime = inputHandler.inputAttendanceTime();
        AttendanceLine attendanceResult = service.insertAttendance(crewName, attendanceTime);
        outputView.printLine(attendanceResult.toString());
    }

    private void update() {
        String crewName = inputHandler.inputCrewName();
        service.isCrewExist(crewName);

        int date = inputHandler.inputDate();
        service.isSchoolDay(date);

        String updateTime = inputHandler.inputTime();
        AttendanceUpdate attendanceUpdate = service.updateAttendance(crewName, date, updateTime);
        outputView.printLine(attendanceUpdate.toString());
    }

    private void select() {
        String crewName = inputHandler.inputCrewName();
        List<AttendanceLine> attendanceLineList = service.getAttendanceLinesByCrewName(crewName);
        String attendanceStatistics = service.getAttendanceStatisticsByCrewName(crewName);
        outputView.printAttendanceLines(attendanceLineList);
        outputView.printLine(attendanceStatistics);
    }

    private void select_risk() {

    }
}
