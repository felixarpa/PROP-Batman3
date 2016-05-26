package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import presentation.FavoriteTopicsPresenter;

import java.util.ArrayList;

public class FavoriteTopicsView extends ListResult {

    private ArrayList<ImageButton> deleteButtons;
    private ArrayList<Label> relevance;

    public FavoriteTopicsView(FavoriteTopicsPresenter favoriteTopicsPresenter) {
        super(favoriteTopicsPresenter);
        presenter = favoriteTopicsPresenter;
        initializePanes();
        initializeViews();
        completePanes();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }
    
    protected void initializeViews(){
        super.initializeViews();
        deleteButtons = new ArrayList<>(Config.LISTS_SIZE);
        relevance = new ArrayList<>(Config.LISTS_SIZE);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            Label label = new Label();
            relevance.add(label);
        }
    }

    protected void setListeners() {
        super.setListeners();
    }

    @Override
    public void setContent(int index, String node) {
        int i = index++ % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        names.get(i).setText(elements[0]);
        ids.get(i).setText(elements[1]);
        relevance.get(i).setText(elements[2]);

        if (elements[0].equals("")) numbers.get(i).setText("");
        else numbers.get(i).setText(index + "");
    }

    @Override
    protected void completePanes() {
        int i = 0;
        for (HBox line : results) {
            line.getChildren().addAll(
                    numbers.get(i),
                    names.get(i),
                    ids.get(i),
                    relevance.get(i)
            );
            ++i;
        }
    }

}

