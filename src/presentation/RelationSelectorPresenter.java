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
            if (type1 == type2 && type1 == ProjectConstants.PAPER_TYPE) ((RelationSelectorView) actualView).showError("Cannot find relation between two papers.");
            else if (type1 == type2 && type1 == ProjectConstants.CONFERENCE_TYPE) ((RelationSelectorView) actualView).showError("Cannot find relation between two conferences.");
            else {
                ((RelationSelectorView)actualView).startProgress();
                Thread thread = new Thread(() -> {
                    actualView.destroy();
                    actualView = null;
                    ArrayList<String> result = domainController.thirdSearch(type1, type2);
                    MyApp.changePresenter(new RelationshipRelevanceResultPresenter(result, type1, type2));
                });
                thread.start();
            }
        }
        else ((RelationSelectorView) actualView).showError("Please especify the types.");
    }
}
