package presentation;

import domain.UserController;
import javafx.application.Platform;
import view.AddTermSelectorView;
import view.auxiliarViews.Config;
import view.MyApp;

import java.util.ArrayList;

public class AddTermSelectorPresenter extends ListPresenter{

    public AddTermSelectorPresenter (ArrayList result) {
        actualView = new AddTermSelectorView(this);
        this.result = result;
        show();
        MyApp.startScene(actualView.getContent());
    }

    @Override
    public void show() {
        int max = index+ Config.LISTS_SIZE;
        if (max > result.size()) max = result.size();
        for (int i = 0; i < max-index; ++i) {
            ((AddTermSelectorView) actualView).setContent(index+i, result.get(index+i));
        }
        for (;max < index+Config.LISTS_SIZE; ++max) {
            ((AddTermSelectorView) actualView).setContent(max, "\t \t \t \t");
        }
    }

    public void addTerm(int index) {
        ((AddTermSelectorView)actualView).startProgress(index);
        Thread thread = new Thread(() -> {
            UserController.addNewFav(result.get(index));
            Platform.runLater(() -> {
                ((AddTermSelectorView)actualView).stopProgress(index);
                actualView.destroy();
                actualView = null;
                MyApp.changePresenter(new FavoriteTopicsPresenter());
            });
        });
        thread.start();

    }

}
