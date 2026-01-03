package attendance.util;

public enum MissionError {

    INVALID_INPUT("유효하지 않은 입력값입니다.");

    private final String message;

    MissionError(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }
}
