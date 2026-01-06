package attendance.controller;

import attendance.converter.Converter;
import attendance.util.MissionError;
import java.util.function.Supplier;

public class InputTemplate {
    public <T> T execute(Supplier<String> inputSupplier, Converter<String, T> converter) {
        try {
            String input = inputSupplier.get();
            return converter.convert(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(MissionError.INVALID_INPUT.exception());
        }
    }
}
