package view;


import domain.DomainController;
import javafx.geometry.Insets;
import javafx.scene.control.ProgressIndicator;
import presentation.AddFavoriteTermSearchPresenter;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.Predictor;

public class AddFavoriteTermSearchView extends MainView {

    public AddFavoriteTermSearchView(AddFavoriteTermSearchPresenter addFavoriteTermSearchPresenter) {
        super(addFavoriteTermSearchPresenter, DomainController.allTermNames());
        search.release();
        manageFavoriteTopics.press();

    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        initializePredictor("Introduce the name of the term that you want to make favourite.");
    }
}
