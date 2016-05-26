package view;

import javafx.scene.layout.VBox;
import presentation.RelationshipRelevanceResultPresenter;

public class RelationshipRelevanceResultView extends BaseView {
    VBox contentVBox;
    

    RelationshipRelevanceResultView(RelationshipRelevanceResultPresenter relationshipRelevanceResultPresenter) {
        presenter = relationshipRelevanceResultPresenter;
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    private void initializePanes() {}
    private void initializeViews(){}
    private void buildPanes(){}
    private void setListeners() {}
}
