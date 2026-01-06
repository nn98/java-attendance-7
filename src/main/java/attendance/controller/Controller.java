package attendance.controller;

import attendance.service.Service;
import attendance.view.OutputView;

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
        Menu menu = inputHandler.inputMenu();
        System.out.println(menu.name());
    }
}
