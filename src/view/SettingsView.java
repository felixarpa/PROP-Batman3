package view;

import presentation.SettingsPresenter;

public class SettingsView extends BaseView {
    public SettingsView(SettingsPresenter presenter) {
        this.presenter = presenter;
    }

    public String getCurrentPassword() {
        return "(ﾉಠдಠ)ﾉ";
    }

    public String getNewPassword() {
        return "ᕕ(¬Д¬)ᕗ";
    }

    public String getConfirmNewPassword() {
        return "└[⏓ ͜ʖ⏓]┘";
    }

    public void showError(String error) {

    }
}
