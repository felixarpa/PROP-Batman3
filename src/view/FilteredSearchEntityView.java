package view;


import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.FilteredSearchEntityPresenter;
import presentation.FilteredSearchPresenter;
import util.ProjectConstants;

import java.util.ArrayList;

public class FilteredSearchEntityView extends FilteredSearchView {


    private int ascendingNames;
    private int ascendingIds;
    private int ascendingRelevances;

    public FilteredSearchEntityView(FilteredSearchEntityPresenter filteredSearchEntityPresenter) {
        super(filteredSearchEntityPresenter);
        initializeActualNode();
        ascendingNames = -1;
        ascendingIds = -1;
        ascendingRelevances = 0;
        setCorrectOrder();
        topBarPane.setTop(actualNodeBox);
    }


    @Override
    protected void initializeActualNode() {
        actualNodeBox = new HBox();
        actualNodeBox.setMaxSize(900,110);
        actualNodeBox.setMinSize(900,110);
        actualNodeBox.setPadding(new Insets(4,0,4,100));
        actualNodeBox.setAlignment(Pos.CENTER_LEFT);
        String actualNodeBG =  this.getClass().getResource("../images/blueTopBar.png").toExternalForm();
        actualNodeBox.setStyle(
                "-fx-background-image: url('" + actualNodeBG + "'); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-size: 900 110;"
        );
        actualNodeName = new Label(((FilteredSearchPresenter)presenter).nodeName);
        actualNodeName.setFont(titleFont);
        actualNodeName.setTextFill(Paint.valueOf("white"));
        actualNodeName.setMaxWidth(400);
        actualNodeName.setMinWidth(400);
        actualNodeRelevance = new Label(((FilteredSearchPresenter)presenter).nodeRelevance);
        actualNodeRelevance.setFont(titleFont);
        actualNodeRelevance.setTextFill(Paint.valueOf("white"));
        actualNodeId = new Label(((FilteredSearchPresenter)presenter).nodeId);
        actualNodeId.setFont(titleFont);
        actualNodeId.setTextFill(Paint.valueOf("white"));
        actualNodeId.setMinWidth(100);
        actualNodeId.setMaxWidth(100);
        actualNodeLabel = new Label(((FilteredSearchPresenter)presenter).nodeLabel);
        actualNodeLabel.setFont(titleFont);
        actualNodeLabel.setTextFill(Paint.valueOf("white"));
        actualNodeLabel.setMaxWidth(74);
        actualNodeLabel.setMinWidth(74);
        actualNodeBox.getChildren().addAll(
                actualNodeName,
                actualNodeLabel,
                actualNodeId,
                actualNodeRelevance
        );
    }

    @Override
    protected void setListeners() {
        super.setListeners();
        nameLabel.setOnMouseReleased(
                event -> {
                    ascendingRelevances = -1;
                    ascendingIds = -1;
                    ((FilteredSearchPresenter) presenter).reorder(
                            ProjectConstants.NAME_ORDER,
                            (ascendingNames != 1)
                    );
                    ascendingNames = (ascendingNames != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );

        relevanceLabel.setOnMouseReleased(
                event -> {
                    ascendingNames = -1;
                    ascendingIds = -1;
                    ((FilteredSearchPresenter) presenter).reorder(
                            ProjectConstants.RELEVANCE_ORDER,
                            (ascendingRelevances != 1)
                    );
                    ascendingRelevances = (ascendingRelevances != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );

        idLabel.setOnMouseReleased(
                event -> {
                    ascendingNames = -1;
                    ascendingRelevances = -1;
                    ((FilteredSearchPresenter) presenter).reorder(
                            ProjectConstants.ID_ORDER,
                            (ascendingIds != 1)
                    );
                    ascendingIds = (ascendingIds != 1) ? 1 : 0;
                    setCorrectOrder();
                }
        );
    }

    @Override
    public void setContent(int index, String node, int type, int listSize) {
        String[] elements = node.split("\t");
        if (!elements[0].equals("")) {
            number.get(type).get(index%listSize).setText(Integer.toString(index+1));
            number.get(type).get(index%listSize).setMinWidth(50);
            number.get(type).get(index%listSize).setMaxWidth(50);
            //number.get(type).get(index%listSize).setPadding(new Insets(0,0,0,0));
            id.get(type).get(index%listSize).setText(elements[1]);
            id.get(type).get(index%listSize).setMinWidth(100);
            id.get(type).get(index%listSize).setMaxWidth(100);
            //id.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            Label laux = new Label();
            laux.setFont(font);
            laux.setTextFill(Paint.valueOf("white"));
            laux.setText(elements[2]);
            laux.setMinWidth(300);
            laux.setMaxWidth(300);
            relevance.get(type).get(index%listSize).getChildren().clear();
            relevance.get(type).get(index%listSize).getChildren().add(laux);
            //relevance.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));

            label.get(type).get(index%listSize).setText(elements[3]);
            label.get(type).get(index%listSize).setMinWidth(75);
            label.get(type).get(index%listSize).setMaxWidth(75);
            //label.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
        }
        else {
            number.get(type).get(index%listSize).setText("");
            number.get(type).get(index%listSize).setMinWidth(10);
            number.get(type).get(index%listSize).setMaxWidth(10);
            //number.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            id.get(type).get(index%listSize).setText("");
            id.get(type).get(index%listSize).setMinWidth(100);
            id.get(type).get(index%listSize).setMaxWidth(100);
            //id.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            Label laux = new Label();
            laux.setText("");
            laux.setMinWidth(300);
            laux.setMaxWidth(300);
            relevance.get(type).get(index%listSize).getChildren().clear();
            relevance.get(type).get(index%listSize).getChildren().add(laux);
            //relevance.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
            label.get(type).get(index%listSize).setText("");
            label.get(type).get(index%listSize).setMinWidth(75);
            label.get(type).get(index%listSize).setMaxWidth(75);
            //label.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));
        }
        name.get(type).get(index%listSize).setText(elements[0]);
        name.get(type).get(index%listSize).setMinWidth(400);
        name.get(type).get(index%listSize).setMaxWidth(400);
        //name.get(type).get(index%listSize).setPadding(new Insets(0,50,0,0));

    }

    private void setCorrectOrder() {
        setCorrectOrder(ascendingNames, nameLabel, "Name");
        setCorrectOrder(ascendingRelevances, relevanceLabel, "Relevance");
        setCorrectOrder(ascendingIds, idLabel, "ID");
    }

    private void setCorrectOrder(int orderStatus, Label label, String name) {
        switch (orderStatus) {
            case -1:
                label.setText(name + " ▬");
                break;

            case 0:
                label.setText(name + " ▼");
                break;

            case 1:
                label.setText(name + " ▲");
                break;
        }
    }


}
