package attendance.domain;

import java.util.Arrays;

public enum Sanction {

    WEEDING("제적 대상자", 5),
    INTERVIEW("면담 대상자", 2),
    WARING("경고 대상자", 1),
    NONE("없음", 0);

    private final String sanctionInfo;
    private final int absenceCount;

    Sanction(String sanctionInfo, int absenceCount) {
        this.sanctionInfo = sanctionInfo;
        this.absenceCount = absenceCount;
    }

    public static Sanction getSanctionByAbsenceCount(int absenceCount) {
        return Arrays.stream(Sanction.values())
                .filter(sanction -> {
                    return sanction.absenceCount < absenceCount;
                })
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return sanctionInfo;
    }
}
