package view;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.BasePresenter;
import presentation.Presenter;


public class BaseView {

    private BorderPane basePane;
    private BorderPane topBarPane;

    private ImageView applicationLogo;
    private ImageView exitIcon;
    private ImageView logoutIcon;
    private VBox sidebarOptions;
    private VBox sidebarBottom;

    private HBox sidebarBottomIcons;
    private VBox sidebar;

    private Text manageFavoriteTopicsText;
    private Text settingsText;
    private Text searchText;
    private Text relationshipSearchText;
    private Text username;



    private Presenter baseViewPresenter;

    public BaseView(Presenter baseViewPresenter){
        this.baseViewPresenter = new Presenter();
        initializePanes();
        initializeViews();
        buildPanes();
        //setListeners();
    }

    public void destroy() { baseViewPresenter = null; }

    private void initializePanes(){
        basePane = new BorderPane();
        basePane.setPadding(new Insets(0,0,0,147));

        sidebarOptions = new VBox();
        sidebarOptions.setPadding(new Insets(10,5,10,5));

        sidebarBottom = new VBox();
        sidebarBottom.setPadding(new Insets(5,5,10,5));

        sidebarBottomIcons = new HBox();
        sidebarBottomIcons.setPadding(new Insets(5,5,5,5));

        sidebar = new VBox();
        sidebar.setPadding(new Insets(10,0,10,0));

    }

    private void initializeViews(){
        applicationLogo = new ImageView();
        Image appLogoImage = new Image(this.getClass().getResourceAsStream("../images/sidebarLogo.png"));
        applicationLogo.setImage(appLogoImage);
        applicationLogo.setFitWidth(105);
        applicationLogo.setFitHeight(105);

        exitIcon = new ImageView();
        Image exitIconImage = new Image(this.getClass().getResourceAsStream("../images/exitIcon.png"));
        exitIcon.setImage(exitIconImage);
        exitIcon.setFitWidth(44);
        exitIcon.setFitHeight(46);

        logoutIcon = new ImageView();
        Image logoutIconImage = new Image(this.getClass().getResourceAsStream("../images/exitIcon.png"));
        logoutIcon.setImage(logoutIconImage);
        logoutIcon.setFitWidth(45);
        logoutIcon.setFitHeight(45);

        manageFavoriteTopicsText = new Text("Manage\n Favorite Topics");
        settingsText = new Text("Settings");
        searchText = new Text("Search");
        relationshipSearchText = new Text("Relationship\n Search");
        username = new Text("Username");

    }

    private void buildPanes(){

        sidebarBottomIcons.getChildren().add(logoutIcon);
        sidebarBottomIcons.getChildren().add(exitIcon);

        sidebarOptions.getChildren().add(manageFavoriteTopicsText);
        sidebarOptions.getChildren().add(settingsText);
        sidebarOptions.getChildren().add(searchText);
        sidebarOptions.getChildren().add(relationshipSearchText);

        sidebarBottom.getChildren().add(username);
        sidebarBottom.getChildren().add(sidebarBottomIcons);

        String sidebarBG = this.getClass().getResource("../images/sidebarBackground.png").toExternalForm();
        sidebar.setStyle("-fx-background-image: url('" + sidebarBG + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 147 576");
        sidebar.getChildren().add(applicationLogo);
        sidebar.getChildren().add(sidebarOptions);
        sidebar.getChildren().add(sidebarBottom);

        basePane.setLeft(sidebar);
    }

    public Pane getContent() {
        return basePane;
    }



}