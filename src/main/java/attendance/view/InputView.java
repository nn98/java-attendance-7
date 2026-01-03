package attendance.view;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.List;

public class InputView {

    public String readLine() {
        return Console.readLine();
    }

    public String readTrimmedLine() {
        return Console.readLine().trim();
    }

    public List<String> readTokens(String delimiter) {
        String line = readLine();
        return Arrays.stream(line.split(delimiter, -1))
                .map(String::trim)
                .toList();
    }

    public List<String> readTokens() {
        return readTokens(" ");
    }

    public List<String> readCsvTokens() {
        return readTokens(",");
    }

    public void close() {
        Console.close();
    }
}
