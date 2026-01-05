package attendance;

import attendance.controller.Controller;
import attendance.domain.Attendances;
import attendance.service.Service;
import attendance.util.CsvLoader;
import attendance.util.CsvMapper;
import attendance.view.InputView;
import attendance.view.OutputView;

import java.io.IOException;
import java.util.Arrays;

public class Config {

    private static final String CSV_PATH = "src/main/resources/attendances.csv";

    private InputView inputView;
    private OutputView outputView;
    private Controller controller;
    private CsvLoader csvLoader;
    private Service service;

    public Controller controller() {
        if (controller == null) {
            controller = new Controller(inputView(), outputView(), service());
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

    private Attendances attendances() {
        CsvMapper csvMapper = new CsvMapper();
        return csvMapper.toAttendances(csvLoader().getRecords());
    }

    private Service service() {
        if (service == null) {
            service = new Service(attendances());
        }
        return service;
    }
}
