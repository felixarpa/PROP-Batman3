package presentation;

import domain.DomainController;
import domain.UserController;
import sun.applet.Main;
import util.ProjectConstants;
import view.BaseView;
import view.MyApp;


public abstract class  BasePresenter extends Presenter {

    protected static DomainController domainController = new DomainController();
    private static boolean sessionClosed = false;
    protected BaseView actualView;

    void startSession() {
        if (sessionClosed) domainController.recalculate();
        else sessionClosed = true;
    }

    public void manageFavoriteTopics() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new FavoriteTopicsPresenter());
    }

    public void search() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new MainPresenter());
    }

    public void settings(){
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new SettingsPresenter());
    }

    public void relationshipSearch(){
        actualView.destroy();
        actualView = null;
//        MyApp.changePresenter(new RelationshipRelevanceResultPresenter());
    }

    public void clickAuthors() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new CategoryResultPresenter(ProjectConstants.AUTHOR_TYPE));
    }

    public void clickPapers() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new CategoryResultPresenter(ProjectConstants.PAPER_TYPE));
    }

    public void clickConferences() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new CategoryResultPresenter(ProjectConstants.CONFERENCE_TYPE));
    }

    public void clickTerms() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new CategoryResultPresenter(ProjectConstants.TERM_TYPE));
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