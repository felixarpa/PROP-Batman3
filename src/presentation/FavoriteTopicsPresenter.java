package presentation;

import domain.UserController;
import view.FavoriteTopicsView;

import java.util.ArrayList;

public class FavoriteTopicsPresenter extends BasePresenter  {

    ArrayList<String> favourites;

    public FavoriteTopicsPresenter() {
        favourites = UserController.getFavouriteTerms();
        actualView = new FavoriteTopicsView(this);

    }
    void onClickRemoveTopicButton(int index) {

    }

    void onClickAddTopicButton() {

    }


}
