package view;


import com.sun.org.apache.bcel.internal.generic.POP;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class PopUp extends VBox{

    String name;
    Font font;
    ToggleGroup tooglegGroup;
    ArrayList<RadioButton> popUpButtons;
    Label popUpText;
    ImageButton popUpButton;
    ArrayList<HBox> popUpContents;

    //Pasas como parametro x lo que quieras que salga aqui:
    //Show x that are:
    //   As relevant as
    //   Less relevant as
    //   More relevant as
    public PopUp(String name) {
        this.name = name;
        initializeFonts();
        initializePanes();
        initializeViews();
        buildPanes();
    }

    private void initializeFonts() {
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14);
    }

    private void initializePanes() {
        tooglegGroup = new ToggleGroup();
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
                    radioButton.setText("Less relevant as");
                    radioButton.setSelected(true);
                    break;
                case 1:
                    radioButton.setText("As relevant as");
                    break;
                case 2:
                    radioButton.setText("More relevant as");

            }
            radioButton.setToggleGroup(tooglegGroup);
            radioButton.setFont(font);
            radioButton.setTextFill(Paint.valueOf("white"));
            popUpButtons.add(radioButton);
        }
        setMaxSize(350,170);
        setMinSize(350,170);
        setSpacing(10);
        setPadding(new Insets(5,5,5,5));
        setStyle(
                "-fx-background-color: #000000;"
        );
    }

    private void initializeViews() {

        popUpButton = new ImageButton("../images/", "prevButton", 60, 30);
        popUpText = new Label("Show " + name + " that are:");
        popUpText.setFont(font);
        popUpText.setTextFill(Paint.valueOf("white"));
        popUpText.setMaxWidth(200);
        popUpText.setMinWidth(200);
    }

    private void buildPanes() {
        popUpContents.get(0).getChildren().add(popUpText);
        popUpContents.get(0).setPadding(new Insets(5,0,0,5));

        VBox vaux = new VBox();
        vaux.getChildren().add(popUpButtons.get(0));
        vaux.getChildren().add(popUpButtons.get(1));
        vaux.getChildren().add(popUpButtons.get(2));
        vaux.setSpacing(10);
        vaux.setPadding(new Insets(5,0,0,0));
        popUpContents.get(1).getChildren().add(vaux);
        popUpContents.get(1).setAlignment(Pos.CENTER);


        popUpContents.get(2).getChildren().add(popUpButton);
        popUpContents.get(2).setAlignment(Pos.CENTER);

        getChildren().addAll(popUpContents);
        setOpacity(0.9);
    }

    public int getSelected() {
        for (int i = 0; i < 3; ++i) {
            if (popUpButtons.get(i).isSelected()) return i-1;
        }
        return -1;
    }

    public void setListeners(EventHandler<ActionEvent> eventListener) {
        popUpButton.setOnMousePressed(
                event -> {
                    popUpButton.press();
                }
        );
        popUpButton.setOnMouseReleased(
                event -> {
                    popUpButton.release();
                    eventListener.handle(null);
                }
        );
    }




}
