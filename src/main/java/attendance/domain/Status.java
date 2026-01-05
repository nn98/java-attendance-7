package attendance.domain;

public enum Status {

    PRESENT("출석"),
    LATE("지각"),
    ABSENCE("결석"),
    NONE("");

    final String KOREAN_STATUS;

    Status(String KOREAN_STATUS) {
        this.KOREAN_STATUS = KOREAN_STATUS;
    }

    public String getKOREAN_STATUS() {
        return KOREAN_STATUS;
    }
}
