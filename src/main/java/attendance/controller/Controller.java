package attendance.controller;

import attendance.service.Service;
import attendance.util.CsvLoader;
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

    public void run(CsvLoader csvLoader) {

    }
}
