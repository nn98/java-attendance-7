package attendance.domain;

public enum AttendanceStatus {

    PRESENT("출석"),
    LATE("지각"),
    ABSENCE("결석"),
    NONE("");

    final String KOREAN_STATUS;

    AttendanceStatus(String KOREAN_STATUS) {
        this.KOREAN_STATUS = KOREAN_STATUS;
    }

    public String getKOREAN_STATUS() {
        return KOREAN_STATUS;
    }
}
