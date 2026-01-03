package attendance;

import attendance.util.CsvLoader;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        run();
    }
    private static void run() {
        Config config = new Config();
        try {
            config.csvLoader();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
