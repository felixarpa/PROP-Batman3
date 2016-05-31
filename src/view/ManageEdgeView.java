package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import presentation.BasePresenter;
import view.auxiliarViews.Config;
import view.auxiliarViews.ImageButton;
import view.auxiliarViews.Predictor;

import java.util.Collection;
import java.util.List;

public abstract class ManageEdgeView extends MainAdminView {

    private HBox predictorHBox;

    private Predictor node1;
    private Predictor node2;

    private Label errorLabel;
    private HBox errorHBox;

    ImageButton button;

    private List<String> resultToPredict;
    private Font font;

    ManageEdgeView(BasePresenter presenter, List<String> resultToPredict) {
        this.presenter = presenter;
        this.resultToPredict = resultToPredict;
    }

    protected void initializePanes() {
        contentVBox = new VBox();
        contentVBox.setPadding(new Insets(0, 0, 0, 0));
        contentVBox.setSpacing(10);
        try {
            contentVBox.setAlignment(Pos.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }

        predictorHBox = new HBox();
        predictorHBox.setSpacing(10);
        predictorHBox.setPadding(new Insets(0,0,180,0));
        predictorHBox.setAlignment(Pos.CENTER);

        errorHBox = new HBox();
        errorHBox.setAlignment(Pos.CENTER);
        errorHBox.setMaxWidth(800);
        errorHBox.setMinWidth(800);
    }

    protected void initializeViews() {
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14);
        node1 = new Predictor(resultToPredict, "\t", Config.LISTS_SIZE, new Insets(0, 0, 0, 0), "Node 1");
        node1.setMinSize(400, 20);
        node1.setMaxSize(400, 24);

        node2 = new Predictor(resultToPredict, "\t", Config.LISTS_SIZE, new Insets(0, 0, 0, 0), "Node 2");
        node2.setMinSize(400, 20);
        node2.setMaxSize(400, 24);

        errorLabel = new Label();
        errorLabel.setFont(font);
        errorLabel.setTextFill(Paint.valueOf("#A51212"));

    }

    protected void buildPanes() {
        predictorHBox.getChildren().addAll(
                node1,
                node2
        );

        errorHBox.getChildren().add(errorLabel);
        contentVBox.getChildren().addAll(
                errorHBox,
                predictorHBox,
                button
        );

//        predictorHBox.toFront();
//        errorHBox.toBack();
        topBarPane.setCenter(contentVBox);

    }

    protected void setListeners() {
        button.setOnMousePressed(
                event -> button.press()
        );
    }

    public void showErrorMessage(String s) {
        errorLabel.setTextFill(Paint.valueOf("#A51212"));
        errorLabel.setText(s);
    }


    public void showSuccessMessage(String s) {
        errorLabel.setTextFill(Paint.valueOf("green"));

        errorLabel.setText(s);
    }

    public String getNode1() {
        return node1.getText();
    }

    public String getNode2() {
        return node2.getText();
    }

    public String getId1() {
        return node1.getIdSelected();
    }

    public String getId2() {return node2.getIdSelected();}

    public String getType1() {return node1.getTypeSelected();}

    public String getType2() {return node2.getTypeSelected();}
}
