package presentation;

import domain.AdminController;
import domain.DomainController;
import domain.UserController;
import sun.applet.Main;
import util.ProjectConstants;
import view.BaseView;
import view.MyApp;


public abstract class  BasePresenter extends Presenter {

    protected static AdminController adminController = new AdminController();
    private static boolean sessionClosed = false;
    protected BaseView actualView;

    BasePresenter() {
        if (domainController == null) domainController = new DomainController();
    }

    void startSession() {
        if (sessionClosed) DomainController.recalculate();
        else sessionClosed = true;
        DomainController.getCurrentUser().updateTerms();
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
        MyApp.changePresenter(new RelationSelectorPresenter());
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

    public void adminMode() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new AddNodePresenter());
    }

    public void logout() {
        UserController.logOut();
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new LoginPresenter());
    }

    public void exit() {
        UserController.logOut();
        domainController.close();
        actualView.destroy();
        actualView = null;
        MyApp.exit();
    }

}