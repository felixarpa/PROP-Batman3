package view;

import javafx.scene.layout.VBox;
import presentation.SimilarRelevancePresenter;

public class SimilarRelevanceView extends BaseView {

    VBox contentVBox;

    public SimilarRelevanceView(SimilarRelevancePresenter similarRelevancePresenter) {
        presenter = similarRelevancePresenter;
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
