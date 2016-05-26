package presentation;

import view.Config;

import java.util.ArrayList;

public abstract class ListPresenter extends BasePresenter {

    private ArrayList<String> result;
    private int index;

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
