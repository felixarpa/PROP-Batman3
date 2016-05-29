package presentation;

import domain.UserController;
import javafx.application.Platform;
import view.auxiliarViews.Config;
import view.FavoriteTopicsView;
import view.MyApp;

public class FavoriteTopicsPresenter extends ListPresenter  {


    public FavoriteTopicsPresenter() {
        result = UserController.getFavouriteTerms();
        actualView = new FavoriteTopicsView(this);
        show();
        MyApp.startScene(actualView.getContent());
    }

    public void onClickRemoveTopicButton(int index) {
        ((FavoriteTopicsView)actualView).startProgress(index);
        Thread thread = new Thread(() -> {
            UserController.deleteFav(result.get(index + this.index));
            result.remove(index+ this.index);
            Platform.runLater(() -> {
                ((FavoriteTopicsView)actualView).stopProgress(index);
                show();
            });
        });
        thread.start();
    }

    public void onClickAddTopicButton() {
        actualView.destroy();
        actualView = null;
        MyApp.changePresenter(new AddFavoriteTermSearchPresenter());
    }

    @Override
    public void show() {
        int max = index+ Config.LISTS_SIZE;
        if (max > result.size()) max = result.size();
        for (int i = 0; i < max-index; ++i) {
            ((FavoriteTopicsView) actualView).setContent(index+i, result.get(index+i));
        }
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((FavoriteTopicsView) actualView).setContent(max, "\t \t \t \t");
        }
    }



}
