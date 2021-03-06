package presentation;

import domain.UserController;
import exceptions.IncorrectPassword;
import exceptions.PasswordMissMatch;
import view.MyApp;
import view.SettingsView;

import java.awt.*;

public class SettingsPresenter extends BasePresenter  {

    public SettingsPresenter() {
        actualView = new SettingsView(this);
        MyApp.startScene(actualView.getContent());
    }

    public void onSaveChangesClick() {
        SettingsView view = (SettingsView)actualView;
        if (view.getNewPassword().length() == 0) {
            view.showError("Incorrect password");
            return;
        }
        try {
            UserController.changePassword(view.getCurrentPassword(), view.getNewPassword(), view.getConfirmNewPassword());
            view.showSuccess("Password changed successfully");
        } catch (IncorrectPassword incorrectPassword) {
            view.showError("The password you entered is not your current password");
        } catch (PasswordMissMatch passwordMissMatch) {
            view.showError("The confirmed password does not match with the password entered");
        }
    }

    public void onClearGraph() {
        adminController.resetGraph();
    }

    public void onResetGraph() {
        adminController.restoreGrapgh();
    }
}
