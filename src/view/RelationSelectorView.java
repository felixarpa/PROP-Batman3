package view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import presentation.RelationSelectorPresenter;
import util.ProjectConstants;

/**
 * Created by mario.fernandez on 26/05/2016.
 */
public class RelationSelectorView extends BaseView {

    VBox contentVBox;
    private Font font;
    private ComboBox firstDropDownMenu;
    private ComboBox secondDropDownMenu;
    private HBox dropDownHBox;
    private HBox searchButtonHBox;
    private VBox firstDropDownMenuVBox;
    private VBox secondDropDownMenuVBox;
    private ImageButton searchButton;
    private Text selectFirstTypeText;
    private Text selectSecondTypeText;
    private HBox firstTypeTextHBox;
    private HBox secondTypeTextHbox;
    private HBox switchButtonHBox;
    private ImageButton switchButton;

    public RelationSelectorView(RelationSelectorPresenter relationSelectorPresenter){
        presenter = relationSelectorPresenter;
        initializeFonts();
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
        topBarPane.setCenter(contentVBox);
    }

    private void initializeFonts() {
        font = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14);
    }

    private void initializePanes() {
        switchButtonHBox = new HBox();
        switchButtonHBox.setAlignment(Pos.CENTER);
        switchButtonHBox.setPadding(new Insets(0,0,0,35));
        firstTypeTextHBox = new HBox();
        secondTypeTextHbox = new HBox();
        firstTypeTextHBox.setAlignment(Pos.CENTER);
        secondTypeTextHbox.setAlignment(Pos.CENTER);
        firstTypeTextHBox.setPadding(new Insets(0,0,20,0));
        secondTypeTextHbox.setPadding(new Insets(0,0,20,0));
        dropDownHBox = new HBox();
        dropDownHBox.setAlignment(Pos.CENTER);
        searchButtonHBox = new HBox();
        searchButtonHBox.setAlignment(Pos.CENTER);
        searchButtonHBox.setPadding(new Insets(50,0,0,0));
        contentVBox = new VBox();
        contentVBox.setPadding(new Insets(100,0,100,0));
        firstDropDownMenuVBox = new VBox();
        firstDropDownMenuVBox.setAlignment(Pos.CENTER);
        secondDropDownMenuVBox = new VBox();
        secondDropDownMenuVBox.setAlignment(Pos.CENTER);
        secondDropDownMenuVBox.setPadding(new Insets(0,0,0,30));
    }

    private void initializeViews() {
        firstDropDownMenu = new ComboBox();
        firstDropDownMenu.getItems().addAll(
                "Author",
                "Conference",
                "Paper",
                "Term"
        );

        secondDropDownMenu = new ComboBox();
        secondDropDownMenu.getItems().addAll(
                "Author",
                "Conference",
                "Paper",
                "Term"
        );

        switchButton = new ImageButton("../images","xButton",10,10);
        searchButton = new ImageButton("../images","searchButton",143,51);
        selectFirstTypeText = new Text("First node type: ");
        selectFirstTypeText.setFont(font);
        selectFirstTypeText.setFill(Paint.valueOf("white"));
        selectSecondTypeText = new Text("Second node type: ");
        selectSecondTypeText.setFont(font);
        selectSecondTypeText.setFill(Paint.valueOf("white"));

    }
    private void buildPanes() {
        firstTypeTextHBox.getChildren().add(selectFirstTypeText);
        firstDropDownMenuVBox.getChildren().add(firstTypeTextHBox);
        firstDropDownMenuVBox.getChildren().add(firstDropDownMenu);

        secondTypeTextHbox.getChildren().add(selectSecondTypeText);
        secondDropDownMenuVBox.getChildren().add(secondTypeTextHbox);
        secondDropDownMenuVBox.getChildren().add(secondDropDownMenu);

        switchButtonHBox.getChildren().add(switchButton);

        dropDownHBox.getChildren().add(firstDropDownMenuVBox);
        dropDownHBox.getChildren().add(switchButtonHBox);
        dropDownHBox.getChildren().add(secondDropDownMenuVBox);
        searchButtonHBox.getChildren().add(searchButton);
        contentVBox.getChildren().add(dropDownHBox);
        contentVBox.getChildren().add(searchButtonHBox);
    }

    private void setListeners(){
        searchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                searchButton.press();
            }
        });

        searchButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                searchButton.release();
                ((RelationSelectorPresenter)presenter).onClickSearch();
            }
        });

        switchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchButton.press();
            }
        });

        switchButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switchButton.release();
                switchTypes();
            }
        });
    }

    private void switchTypes() {
        Object aux = firstDropDownMenu.getValue();
        firstDropDownMenu.setValue(secondDropDownMenu.getValue());
        secondDropDownMenu.setValue(aux);

    }

    private int getType(String type) {

        switch(type){
            case "Author":
                return ProjectConstants.AUTHOR_TYPE;
            case "Conference":
                return ProjectConstants.CONFERENCE_TYPE;
            case "Paper":
                return ProjectConstants.PAPER_TYPE;
            default:
                return ProjectConstants.TERM_TYPE;
        }
    }

    public int getFirstType() {
        return getType(firstDropDownMenu.getValue().toString());
    }

    public int getSecondType() {
        return getType(secondDropDownMenu.getValue().toString());

    }

    public void showError() {

    }
}
