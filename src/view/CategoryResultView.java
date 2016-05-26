package view;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.CategoryResultPresenter;

import java.util.ArrayList;

public class CategoryResultView extends ListView {

    private ArrayList<Label> relevance;

    private Label relevanceLabel;

    public CategoryResultView(CategoryResultPresenter presenter){
        super(presenter);
        this.presenter = presenter;

        initializePanes();
        initializeViews();
        setListeners();
        completePanes();
        topBarPane.setCenter(contentVBox);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();

        relevanceLabel = new Label("Relevance");
        relevanceLabel.setTextFill(Config.LABEL_CLEAR_COLOR);
        relevanceLabel.setMinSize(200, 20);
        relevanceLabel.setMaxSize(200, 24);
        relevanceLabel.setFont(new Font("Arial bold", 24));

        idLabel.setMinSize(100, 20);
        idLabel.setMaxSize(100, 24);

        relevance = new ArrayList<>(Config.LISTS_SIZE);


        Font font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 18);

        for (int i = 0; i < 10; ++i) {
            relevance.add(new Label());
            relevance.get(i).setMinSize(100, 20);
            relevance.get(i).setMaxSize(100, 24);
            relevance.get(i).setFont(font);
            relevance.get(i).setTextFill(Paint.valueOf("white"));

            ids.get(i).setMinSize(100, 24);
            ids.get(i).setMaxSize(100, 24);
        }
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
        buildLine();
        buildPanes();
        titlesHBox.getChildren().add(relevanceLabel);
    }

    protected void buildLine() {
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

    public void askSimilarOp() {

    }
}
