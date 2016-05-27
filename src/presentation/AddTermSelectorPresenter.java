package presentation;

import view.AddTermSelectorView;
import view.Config;

import java.util.ArrayList;

public class AddTermSelectorPresenter extends ListPresenter{

    public AddTermSelectorPresenter (ArrayList result) {
        super();
        actualView = new AddTermSelectorView(this);
        this.result = result;
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


}
