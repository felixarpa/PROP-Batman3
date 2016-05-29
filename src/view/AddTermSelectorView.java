package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.AddTermSelectorPresenter;
import view.auxiliarViews.Config;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.ListView;

import java.util.ArrayList;

public class AddTermSelectorView  extends ListView{

    private Font font;

    private ArrayList<ImageButton> addButtons;
    private ArrayList<Label> relevance;

    public AddTermSelectorView(AddTermSelectorPresenter addTermSelectorPresenter) {
        presenter = addTermSelectorPresenter;
        intializeFonts();
        initializePanes();
        initializeViews();
        completePanes();
        setListeners();
        topBarPane.setCenter(contentVBox);

    }

    private void intializeFonts() {
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 20);
    }


    protected void initializeViews(){
        super.initializeViews();
        addButtons = new ArrayList<>(Config.LISTS_SIZE);
        relevance = new ArrayList<>(Config.LISTS_SIZE);

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            Label label = new Label();
            label.setFont(font);
            label.setTextFill(Paint.valueOf("white"));
            label.setMaxWidth(200);
            label.setMinWidth(200);
            relevance.add(label);
            ImageButton imageButton = new ImageButton("orangeAddTopicButton", 22, 22);
            //imageButton.setMaxSize(22,22);
            //imageButton.setMinSize(22,22);
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
        if (elements[0].length() == 0) {
            addButtons.get(i).setDisable(true);
            addButtons.get(i).setVisible(false);
        }
        else {
            addButtons.get(i).setDisable(false);
            addButtons.get(i).setVisible(true);
        }
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
