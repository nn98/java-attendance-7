package attendance.util;

public enum MissionError {

    INVALID_INPUT("잘못된 형식을 입력하였습니다.");

    private final String message;

    MissionError(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }
}
