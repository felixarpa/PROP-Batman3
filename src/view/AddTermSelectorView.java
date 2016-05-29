package view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.AddTermSelectorPresenter;
import view.auxiliarViews.Config;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.ListView;

import java.util.ArrayList;

public class AddTermSelectorView  extends ListView {

    private Font font;

    private ArrayList<ImageButton> addButtons;
    private ArrayList<Label> relevance;
    private Label relevanceLabel;

    private boolean canTouch;

    public AddTermSelectorView(AddTermSelectorPresenter addTermSelectorPresenter) {
        canTouch = true;
        presenter = addTermSelectorPresenter;
        intializeFonts();
        initializePanes();
        initializeViews();
        completePanes();
        setListeners();
        manageFavoriteTopics.press();
        topBarPane.setCenter(contentVBox);

    }

    private void intializeFonts() {
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 20);
    }


    protected void initializeViews(){
        super.initializeViews();
        addButtons = new ArrayList<>(Config.LISTS_SIZE);
        relevance = new ArrayList<>(Config.LISTS_SIZE);


        relevanceLabel = new Label("Relevance");
        relevanceLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        relevanceLabel.setMinSize(200, 20);
        relevanceLabel.setMaxSize(200, 24);
        relevanceLabel.setFont(new Font("Arial bold", 24));

        for (int i = 0; i < Config.LISTS_SIZE; ++i) {
            Label label = new Label();
            label.setFont(font);
            label.setTextFill(Paint.valueOf("white"));
            label.setMaxSize(100,24);
            label.setMinSize(100,20);
            relevance.add(label);
            ImageButton imageButton = new ImageButton("orangeAddTopicButton", 22, 22);
            //imageButton.setMaxSize(22,22);
            //imageButton.setMinSize(22,22);
            addButtons.add(imageButton);
            ids.get(i).setMinSize(100, 24);
            ids.get(i).setMaxSize(100, 24);

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
                        if (canTouch) ((AddTermSelectorPresenter) presenter).addTerm(x);
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
        titlesHBox.getChildren().add(relevanceLabel);
    }

    @Override
    protected void buildLine() {
        int i = 0;
        for (HBox line : lineHBox) {
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

    public void startProgress(int index) {
        canTouch = false;
        ProgressIndicator progressIndicator = new ProgressIndicator(-1);
        progressIndicator.setMinSize(20, 20);
        progressIndicator.setMaxSize(20, 20);
        lineHBox.get(index).getChildren().set(4, progressIndicator);
    }

    public void stopProgress(int index) {
        lineHBox.get(index).getChildren().set(4, addButtons.get(index));
    }
}
