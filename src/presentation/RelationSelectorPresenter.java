package presentation;

import view.MyApp;
import view.RelationSelectorView;

public class RelationSelectorPresenter extends BasePresenter {
    public RelationSelectorPresenter() {
        actualView = new RelationSelectorView(this);
        MyApp.startScene(actualView.getContent());

    }
}
