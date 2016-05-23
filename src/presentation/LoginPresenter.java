package presentation;

import domain.UserController;
import exceptions.ExistingUser;
import exceptions.IncorrectPassword;
import exceptions.NonExistentUser;
import view.LoginView;
import view.MyApp;

import java.lang.reflect.Array;
import java.util.ArrayList;


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
                System.out.println("Success login");
                loginView.destroy();
                loginView = null;
                //ESTO ES LO GUAY
                /*MainPresenter mainPresenter = new MainPresenter();
                mainPresenter.startSession();
                MyApp.changePresenter(mainPresenter);*/

                //PRUEBA DE FILTEREDSEARCHRESULT
                /* ArrayList<ArrayList<String>> aux = new ArrayList<>();
                ArrayList<String> aux2 = new ArrayList<>();
                aux2.add("Mario A. Nascimento\t113885\t2449.0518623648363\t3\tclass domain.graph.Author");
                aux2.add("Mario Marchand\t40245\t707.1466569609166\tclass domain.graph.Author");
                aux.add(aux2);
                MyApp.changePresenter(new FilteredSearchResultPresenter(aux));*/

                //PRUEBA DE RELEVANCETYPESELECTOR
                ArrayList<String> aux2 = new ArrayList<>();
                aux2.add("Mario A. Nascimento\t113885\t2449.0518623648363\t3\tclass domain.graph.Author");
                aux2.add("Mario Marchand\t40245\t707.1466569609166\t \tclass domain.graph.Author");
                MyApp.changePresenter(new RelevanceTypeSelectorPresenter(aux2));

            } catch (IncorrectPassword | NonExistentUser exception) {
                System.out.println(exception.getMessage());
                loginView.stopProgress();
                loginView.displayErrorMessage("Invalid password or username");
            }
        });
        thread.start();
    }

    public void register() {
        String username = loginView.getUsername();
        if (username.length() < 1) {
            loginView.displayErrorMessage("Username must have at least 1 character");
            return;
        }
        String password = loginView.getPassword();
        if (password.length() < 1) {
            loginView.displayErrorMessage("Password must have at least 1 character");
            return;
        }
        loginView.startProgress();
        Thread thread = new Thread(() -> {
            try {
                UserController.register(username, password);
                System.out.println("Success register");
            } catch (ExistingUser existingUser) {
                System.out.println(existingUser.getMessage());
                loginView.displayErrorMessage("User "+username+" already exists");
            }
            finally {
                loginView.stopProgress();
            }
        });
        thread.start();
    }
}
