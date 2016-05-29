package view.popUpMaterial;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import view.auxiliarViews.ImageButton;

import java.util.ArrayList;

public class RelevancePopUpView extends VBox {

    private Font font;
    private ArrayList<RadioButton> popUpButtons;
    private ImageButton popUpButton;
    private ArrayList<HBox> popUpContents;
    private OnSelectRelevance onSelectRelevance;

    public RelevancePopUpView(OnSelectRelevance onSelectRelevance) {
        this.onSelectRelevance = onSelectRelevance;
        initializeFonts();
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
    }

    private void initializeFonts() {
        font = Font.loadFont(this.getClass().getResource("../../fonts/Nilland-Black.ttf").toExternalForm(), 14);
    }

    private void initializePanes() {
        ToggleGroup tooglegGroup = new ToggleGroup();

        popUpContents = new ArrayList<>(3);
        for (int i = 0; i < 3; ++i) {
            HBox haux = new HBox();
            popUpContents.add(haux);
        }

        popUpButtons = new ArrayList<>(3);
        for (int i = 0; i < 3; ++i) {
            RadioButton radioButton = new RadioButton();
            switch (i) {
                case 0:
                    radioButton.setText("Less relevant");
                    radioButton.setSelected(true);
                    break;
                case 1:
                    radioButton.setText("As relevant as");
                    break;
                case 2:
                    radioButton.setText("More relevant");

            }
            radioButton.setToggleGroup(tooglegGroup);
            radioButton.setFont(font);
            radioButton.setTextFill(Paint.valueOf("white"));
            popUpButtons.add(radioButton);
        }

        setMaxSize(350,190);
        setMinSize(350,190);
        setSpacing(10);
        setPadding(new Insets(5,5,5,5));
        setStyle(
                "-fx-background-color: #000000;"
        );
    }

    private void initializeViews() {
        popUpButton = new ImageButton("acceptButton", 141, 54);
    }

    private void buildPanes() {
        VBox vaux = new VBox();
        vaux.getChildren().add(popUpButtons.get(0));
        vaux.getChildren().add(popUpButtons.get(1));
        vaux.getChildren().add(popUpButtons.get(2));
        vaux.setSpacing(10);
        vaux.setPadding(new Insets(5,0,0,0));

        popUpContents.get(0).setPadding(new Insets(5,0,0,5));
        popUpContents.get(1).getChildren().add(vaux);
        popUpContents.get(1).setAlignment(Pos.CENTER);
        popUpContents.get(2).getChildren().add(popUpButton);
        popUpContents.get(2).setAlignment(Pos.CENTER);

        getChildren().addAll(popUpContents);
        setOpacity(0.9);
    }

    private int getSelected() {
        for (int i = 0; i < 3; ++i) {
            if (popUpButtons.get(i).isSelected()) return i-1;
        }
        return -1;
    }

    public void setListeners() {
        popUpButton.setOnMousePressed(
                event -> popUpButton.press()
        );
        popUpButton.setOnMouseReleased(
                event -> {
                    popUpButton.release();
                    onSelectRelevance.onAccept(getSelected());
                }
        );
    }




}
