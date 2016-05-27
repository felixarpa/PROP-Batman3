package presentation;

import domain.DomainController;
import view.MyApp;
import view.RemoveNodeView;

/**
 * Created by carlos.roldan on 26/05/2016.
 */
public class RemoveNodePresenter extends MainAdminPresenter {

    public RemoveNodePresenter() {
        actualView = new RemoveNodeView(this, DomainController.allNames());
        MyApp.startScene(actualView.getContent());
    }

    public void clickRemoveNodeButton() {
        System.out.println("Haz el presenter Juanmi (ﾉಠдಠ)ﾉ︵┻━┻");
    }
}
