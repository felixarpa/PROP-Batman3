package presentation;

import domain.DomainController;
import domain.UserController;
import sun.applet.Main;
import util.ProjectConstants;
import view.BaseView;
import view.MyApp;


public abstract class  BasePresenter extends Presenter {

    DomainController god = new DomainController();
    private BaseView actualView;



    public BasePresenter() {

    }


    public void manageFavoriteTopics() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new FavoriteTopicsPresenter());
    }

    public void search() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void settings(){
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void relationshipSearch(){
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void clickAuthors() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void clickPapers() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void clickConferences() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void clickTerms() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void logout() {
        UserController.logOut();
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void exit() {
        UserController.logOut();
        actualView.destroy();
        actualView = null;
        MyApp.exit();
    }

}