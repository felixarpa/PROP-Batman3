package presentation;

import domain.UserController;
import exceptions.ExistingUser;
import exceptions.IncorrectPassword;
import exceptions.NonExistentUser;
import view.LoginView;
import view.MyApp;


public class LoginPresenter extends Presenter {

    private LoginView loginView;

    public LoginPresenter() {
        loginView = new LoginView(this);
        MyApp.startScene(loginView.getContent());
    }

    public void login() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        loginView.startProgress();
        Thread thread = new Thread(() -> {
            try {
                UserController.logIn(username, password);
                System.out.println("Success");
                loginView.displaySuccessMessage();
                loginView.destroy();
                loginView = null;
                MyApp.changePresenter(new MainPresenter());
                MyApp.testView();
            } catch (IncorrectPassword | NonExistentUser exception) {
                System.out.println(exception.getMessage());
                loginView.stopProgress();
                loginView.displayErrorMessage();
            }
        });
        thread.start();
    }

    public void register() {
        String username = loginView.getUsername();
        String password = loginView.getPassword();
        loginView.startProgress();
        Thread thread = new Thread(() -> {
            try{
                UserController.register(username, password);
                System.out.println("Success");
                loginView.displaySuccessMessage();
            } catch (ExistingUser existingUser) {
                System.out.println(existingUser.getMessage());
                loginView.displayErrorMessage();
            }
            finally {
                loginView.stopProgress();
            }
        });
        thread.start();
    }
}
