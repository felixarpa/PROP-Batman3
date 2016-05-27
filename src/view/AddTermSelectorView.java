package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import presentation.AddTermSelectorPresenter;
import view.ListView;

import java.util.ArrayList;

public class AddTermSelectorView  extends ListView{

    private ArrayList<ImageButton> addButtons;
    private ArrayList<Label> relevance;

    public AddTermSelectorView(AddTermSelectorPresenter addTermSelectorPresenter) {
        presenter = addTermSelectorPresenter;
        initializePanes();
        initializeViews();
        completePanes();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);

    }

    protected void initializeViews(){
        super.initializeViews();
        addButtons = new ArrayList<>(Config.LISTS_SIZE);
        relevance = new ArrayList<>(Config.LISTS_SIZE);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            Label label = new Label();
            relevance.add(label);

            ImageButton imageButton = new ImageButton("../images", "xButton", 1, 1);
            addButtons.add(imageButton);

        }
    }


    protected void setListeners() {
        super.setListeners();
        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            int x = i;
            addButtons.get(x).setOnMousePressed(event -> addButtons.get(x).press());
            addButtons.get(x).setOnMouseReleased(
                    event -> {
                        addButtons.get(x).release();
                        ((AddTermSelectorPresenter) presenter).addTerm(x);
                    }
            );
        }

    }

    @Override
    public void setContent(int index, String node) {
        super.setContent(index, node);
        int i = index % Config.LISTS_SIZE;
        String[] elements = node.split("\t");
        relevance.get(i).setText(elements[2]);
    }

    @Override
    protected void completePanes() {
        buildLine();
        buildPanes();
    }

    @Override
    protected void buildLine() {
        int i = 0;
        for (HBox line : results) {
            line.getChildren().addAll(
                    numbers.get(i),
                    names.get(i),
                    ids.get(i),
                    relevance.get(i),
                    addButtons.get(i)
            );
            ++i;
        }
    }
}
