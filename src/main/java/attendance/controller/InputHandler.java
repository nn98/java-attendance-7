package attendance.controller;

import attendance.view.InputView;

public class InputHandler {

    private final InputView inputView;
    private final InputTemplate inputTemplate;

    public InputHandler(InputView inputView, InputTemplate inputTemplate) {
        this.inputView = inputView;
        this.inputTemplate = inputTemplate;
    }

    public Menu inputMenu() {
        String command = inputView.readLine();
        Menu menu = Menu.findByCommand(command);
        return menu;
    }
}
