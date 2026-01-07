package attendance.controller;

import attendance.dto.AttendanceLine;
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
        service.init();
        Menu menu;
        do {
            menu = inputHandler.inputMenu();
            System.out.println(menu.name());
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
        String crewName = inputHandler.inputCrewName();
        String attendanceTime = inputHandler.inputAttendanceTime();
        service.insertAttendance(crewName, attendanceTime);
    }

    private void update() {

    }

    private void select() {
        String crewName = inputHandler.inputCrewName();
        List<AttendanceLine> attendanceLineList = service.getAttendanceLinesByCrewName(crewName);
        outputView.printAttendanceLines(attendanceLineList);
    }

    private void select_risk() {

    }
}
