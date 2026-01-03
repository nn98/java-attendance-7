package attendance.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvLoader {

    private final String path;
    private final List<String[]> records;

    public CsvLoader(String path) throws IOException {
        this.path = path;
        this.records = loadCsv();
    }

    private List<String[]> loadCsv() throws IOException {
        BufferedReader br = Files.newBufferedReader(Paths.get(path));
        List<String[]> records = br.lines()
                .map(line -> line.split(","))
                .collect(Collectors.toList());
        return records;
    }

    public List<String[]> getRecords() {
        return records;
    }
}
