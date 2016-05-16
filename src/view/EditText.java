package view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class EditText extends Pane {

    private HBox hBox;
    private Label label;
    private TextField textField;

    public EditText() {
        hBox = new HBox();
        label = new Label();
        textField = new TextField();
        build();
    }

    public EditText(String name) {
        hBox = new HBox();
        label = new Label(name);
        textField = new TextField();
        build();
    }
    public EditText(String name, Pos alignment) {
        hBox = new HBox();
        label = new Label(name);
        textField = new TextField();
        build();
        hBox.setAlignment(alignment);
    }

    private void build() {
        label.setTextFill(Config.LABEL_TEXT_COLOR);
        hBox.setSpacing(4);
        hBox.getChildren().add(label);
        hBox.getChildren().add(textField);
        hBox.setAlignment(Pos.CENTER);
    }

    public void setName(String name) {
        label.setText(name);
    }

    public Pane getBase() {
        return hBox;
    }

    public String getText() {
        return textField.getText();
    }

}
