package attendance.controller;

import attendance.service.Service;
import attendance.view.InputView;
import attendance.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;
    private final Service service;

    public Controller(InputView inputView, OutputView outputView, Service service) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.service = service;
    }

    public void run() {
        service.init();
    }

    public void print() {
        String crewName = inputView.readTrimmedLine();
        outputView.printAttendanceLines(service.getAttendanceLinesByCrewName(crewName));
    }

}
