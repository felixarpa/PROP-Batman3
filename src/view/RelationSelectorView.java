package view;

import javafx.event.EventHandler;
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
        dropDownHBox = new HBox();
        searchButtonHBox = new HBox();
        contentVBox = new VBox();
        firstDropDownMenuVBox = new VBox();
        secondDropDownMenuVBox = new VBox();
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
        searchButton = new ImageButton("../images","searchButton",227,50);
        selectFirstTypeText = new Text("Select first node type: ");
        selectFirstTypeText.setFont(font);
        selectFirstTypeText.setFill(Paint.valueOf("white"));
        selectSecondTypeText = new Text("Select second node type: ");
        selectSecondTypeText.setFont(font);
        selectSecondTypeText.setFill(Paint.valueOf("white"));

    }
    private void buildPanes() {
        firstDropDownMenuVBox.getChildren().add(selectFirstTypeText);
        firstDropDownMenuVBox.getChildren().add(firstDropDownMenu);
        secondDropDownMenuVBox.getChildren().add(selectSecondTypeText);
        secondDropDownMenuVBox.getChildren().add(secondDropDownMenu);
        dropDownHBox.getChildren().add(firstDropDownMenuVBox);
        dropDownHBox.getChildren().add(secondDropDownMenuVBox);
        searchButtonHBox.getChildren().add(searchButton);
        contentVBox.getChildren().add(dropDownHBox);
        contentVBox.getChildren().add(searchButtonHBox);
    }

    private void setListeners(){
        searchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        searchButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((RelationSelectorPresenter)presenter).onClickSearch();
            }
        });
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
