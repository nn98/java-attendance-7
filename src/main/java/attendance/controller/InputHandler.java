package attendance.controller;

import attendance.view.InputView;

public class InputHandler {

    private final InputView inputView;
    private final InputTemplate inputTemplate;

    public InputHandler(InputView inputView, InputTemplate inputTemplate) {
        this.inputView = inputView;
        this.inputTemplate = inputTemplate;
    }

    public Menu inputMenu() {
        String command = inputView.readLine();
        Menu menu = Menu.findByCommand(command);
        return menu;
    }

    public String inputCrewName() {
        String crewName = inputView.readLine();
        return crewName;
    }

    public String inputAttendanceTime() {
        String attendanceTime = inputView.readLine();
        return attendanceTime;
    }

    public int inputDate() {
        String day = inputView.readLine();
        int date = Integer.parseInt(day);
        return date;
    }

    public String inputTime() {
        String time = inputView.readLine();
        return time;
    }
}
