package attendance;

import attendance.controller.Controller;
import attendance.util.CsvLoader;
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

    public InputView inputView() {
        if (inputView == null) {
            inputView = new InputView();
        }
        return inputView;
    }

    public OutputView outputView() {
        if (outputView == null) {
            outputView = new OutputView();
        }
        return outputView;
    }

    public Controller controller() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public CsvLoader csvLoader() throws IOException {
        if (csvLoader == null) {
            csvLoader = new CsvLoader(CSV_PATH);
            csvLoader.getRecords().stream()
                    .map(Arrays::toString)
                    .forEach(System.out::println);
        }
        return csvLoader;
    }
}
