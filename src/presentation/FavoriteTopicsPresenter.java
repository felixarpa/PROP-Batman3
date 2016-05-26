package presentation;

import domain.UserController;
import view.FavoriteTopicsView;
import view.MyApp;

import java.util.ArrayList;

public class FavoriteTopicsPresenter extends BasePresenter  {

    ArrayList<String> favourites;

    public FavoriteTopicsPresenter() {
        favourites = UserController.getFavouriteTerms();
        actualView = new FavoriteTopicsView(this);
        MyApp.startScene(actualView.getContent());

    }
    void onClickRemoveTopicButton(int index) {

    }

    void onClickAddTopicButton() {

    }


}
