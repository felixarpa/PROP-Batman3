package presentation;

import view.auxiliarViews.Config;

import java.util.ArrayList;

public abstract class ListPresenter extends BasePresenter {

    protected ArrayList<String> result;
    protected int index;

    public ListPresenter() {
        index = 0;
    }

    public ListPresenter(ArrayList<String> result) {
        this.result = result;
        index = 0;
    }

    public void showMore() {
        if (index + Config.LISTS_SIZE <= result.size()) {
            index += Config.LISTS_SIZE;
            show();
        }

    }

    public void showLess() {
        if (index - Config.LISTS_SIZE >= 0) {
            index = index - Config.LISTS_SIZE;
            show();
        }
    }

    protected abstract void show();


}
