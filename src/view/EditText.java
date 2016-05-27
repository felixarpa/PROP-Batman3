package view;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

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
        label.setTextFill(Config.LABEL_DARK_COLOR);
        hBox.setSpacing(4);
        hBox.getChildren().add(label);
        hBox.getChildren().add(textField);
        hBox.setAlignment(Pos.CENTER);
    }

    public void setFill(Paint paint){
        label.setTextFill(paint);
    }

    public void setName(String name) {
        label.setText(name);
    }

    public void setFont(Font font){ label.setFont(font);}

    public void setMinSize(int width, int height) {
        textField.setMinSize(width, height);
    }

    public void setMaxSize(int width, int height) {
        textField.setMaxSize(width, height);
    }

    public Pane getBase() {
        return hBox;
    }

    public String getText() {
        return textField.getText();
    }

}
