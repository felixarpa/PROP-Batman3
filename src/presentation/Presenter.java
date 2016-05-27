package presentation;

import domain.DomainController;
import domain.UserController;

public class Presenter {

    protected static DomainController domainController;

    public void saveAndExit() {
        if (domainController != null) {
            UserController.logOut();
            domainController.close();
        }
    }
}
