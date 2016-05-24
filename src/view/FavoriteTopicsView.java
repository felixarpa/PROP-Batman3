package view;

import javafx.scene.layout.VBox;
import presentation.FavoriteTopicsPresenter;

/**
 * Created by mario.fernandez on 24/05/2016.
 */
public class FavoriteTopicsView extends BaseView {

    VBox contentVBox;

    FavoriteTopicsView(FavoriteTopicsPresenter favoriteTopicsPresenter) {
        presenter = favoriteTopicsPresenter;
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

