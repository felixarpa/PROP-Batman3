package view;

import javafx.scene.layout.VBox;
import presentation.RelationshipRelevanceResultPresenter;

public class RelationshipRelevanceResultView extends BaseView {

    VBox contentVBox;
    

    public RelationshipRelevanceResultView(RelationshipRelevanceResultPresenter relationshipRelevanceResultPresenter) {
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

    public void askSimilarOp() {

    }

    public void setContent(int index, String nodeSrc, String nodeDst, double relevance) {

    }
}
