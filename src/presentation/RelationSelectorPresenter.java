package presentation;

import domain.DomainController;
import util.ProjectConstants;
import view.MyApp;
import view.RelationSelectorView;

import java.util.ArrayList;

public class RelationSelectorPresenter extends BasePresenter {

    public RelationSelectorPresenter() {
        actualView = new RelationSelectorView(this);
        MyApp.startScene(actualView.getContent());
    }

    public void onClickSearch() {
        int type1 = ((RelationSelectorView)actualView).getFirstType();
        int type2 = ((RelationSelectorView)actualView).getSecondType();
        if (type1 != -1 && type2 != -1) {
            if (type1 == type2 && type1 == ProjectConstants.PAPER_TYPE)
                ((RelationSelectorView) actualView).showError("Cannot find relation between two papers.");
            else {
                actualView.destroy();
                actualView = null;
                MyApp.changePresenter(new RelationshipRelevanceResultPresenter(type1, type2));
            }
        }
    }
}
