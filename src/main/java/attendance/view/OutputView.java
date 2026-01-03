package attendance.view;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]";

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printException(String message) {
        printLine(String.format("%s %s", ERROR_PREFIX, message));
    }
}
