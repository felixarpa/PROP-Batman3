package view;

import javafx.scene.layout.VBox;
import presentation.SimilarRelevancePresenter;

/**
 * Created by mario.fernandez on 24/05/2016.
 */
public class SimilarRelevanceView extends BaseView {
    VBox contentVBox;

    SimilarRelevanceView(SimilarRelevancePresenter similarRelevancePresenter) {
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
