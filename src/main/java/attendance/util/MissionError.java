package attendance.util;

public enum MissionError {

    ERROR_PREFIX("[ERROR] "),
    INVALID_INPUT("잘못된 형식을 입력하였습니다."),
    NON_EXIST_CREW("등록되지 않은 닉네임입니다."),
    NON_SCHOOL_DAY("은 등교일이 아닙니다.");

    private final String message;

    MissionError(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(ERROR_PREFIX.message + this.message);
    }

    public IllegalArgumentException exception(String message) {
        return new IllegalArgumentException(ERROR_PREFIX.message + message + this.message);
    }
}
