package attendance;

import attendance.controller.Controller;
import attendance.controller.InputHandler;
import attendance.controller.InputTemplate;
import attendance.domain.Attendances;
import attendance.service.Service;
import attendance.util.CsvLoader;
import attendance.util.CsvMapper;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.io.IOException;

public class Config {

    private static final String CSV_PATH = "src/main/resources/attendances.csv";

    private InputView inputView;
    private OutputView outputView;
    private Controller controller;
    private CsvLoader csvLoader;
    private Service service;
    private InputHandler inputHandler;
    private InputTemplate inputTemplate;

    public Controller controller() {
        if (controller == null) {
            controller = new Controller(inputHandler(), outputView(), service());
        }
        return controller;
    }

    private InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    private OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    private Service service() {
        if (service == null) {
            service = new Service(attendances());
        }
        return service;
    }

    private Attendances attendances() {
        return CsvMapper.toAttendances(csvLoader().getRecords());
    }

    private CsvLoader csvLoader() {
        if (csvLoader == null) {
            try {
                csvLoader = new CsvLoader(CSV_PATH);
            } catch (IOException exception) {
                throw new IllegalStateException();
            }
        }
        return csvLoader;
    }

    private InputHandler inputHandler() {
        if (inputHandler == null) {
            inputHandler = new InputHandler(inputView(), inputTemplate());
        }
        return inputHandler;
    }

    private InputTemplate inputTemplate() {
        if (inputTemplate == null) {
            inputTemplate = new InputTemplate();
        }
        return inputTemplate;
    }
}
