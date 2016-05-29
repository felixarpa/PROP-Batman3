package view;


import domain.DomainController;
import presentation.AddFavoriteTermSearchPresenter;

public class AddFavoriteTermSearchView extends MainView {

    public AddFavoriteTermSearchView(AddFavoriteTermSearchPresenter addFavoriteTermSearchPresenter) {
        super(addFavoriteTermSearchPresenter, DomainController.allTermNames());
        search.release();
        manageFavoriteTopics.press();

    }
}
