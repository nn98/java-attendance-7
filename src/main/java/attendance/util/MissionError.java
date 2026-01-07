package attendance.util;

public enum MissionError {

    INVALID_INPUT("잘못된 형식을 입력하였습니다."),
    NON_EXIST_CREW("등록되지 않은 닉네임입니다.");

    private final String message;

    MissionError(String message) {
        this.message = message;
    }

    public IllegalArgumentException exception() {
        return new IllegalArgumentException(this.message);
    }
}
