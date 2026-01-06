package attendance.controller;

import attendance.util.MissionError;
import java.util.Arrays;

public enum Menu {

    INSERT("1"),
    UPDATE("2"),
    SELECT("3"),
    SEL_RISK("4"),
    QUIT("Q");

    private final String command;

    Menu(String command) {
        this.command = command;
    }

    public static Menu findByCommand(String command) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.command.equals(command))
                .findFirst()
                .orElseThrow(MissionError.INVALID_INPUT::exception);
    }
}
