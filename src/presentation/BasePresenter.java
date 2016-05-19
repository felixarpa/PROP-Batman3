package presentation;

import domain.DomainController;
import domain.UserController;
import sun.applet.Main;
import util.ProjectConstants;
import view.BaseView;
import view.MyApp;


public abstract class  BasePresenter extends Presenter {

    DomainController god = new DomainController();
    private BaseView baseView;
    private BaseView actualView;



    public BasePresenter() {
        baseView = new BaseView(this);

    }


    public void manageFavoriteTopics() {
        MyApp.changePresenter(new LoginPresenter());
    }

    public void search() {

    }

    public void settings(){

    }

    public void relationshipSearch(){

    }

    public void clickAuthors() {
    }

    public void clickPapers() {

    }

    public void clickConferences() {

    }

    public void clickTerms() {

    }

    public void logout() {
        UserController.logOut();
        baseView.destroy();
        baseView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void exit() {
        UserController.logOut();
        baseView.destroy();
        baseView = null;
        MyApp.exit();
    }

}