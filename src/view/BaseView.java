package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import presentation.BasePresenter;
import presentation.Presenter;

import java.awt.*;


public class BaseView {

    private BorderPane basePane;
    protected BorderPane topBarPane;

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

    private ImageView appTitleImage;
    private HBox appTitleHBox;
    private HBox categoryButtons;
    private VBox titleAndButtons;

    private ImageButton authorsButton;
    private ImageButton conferencesButton;
    private ImageButton papersButton;
    private ImageButton termsButton;

    protected BasePresenter presenter;

    public BaseView() {
        initializePanes();
        initializeViews();
        buildPanes();
        setListeners();
    }

    private void setListeners() {

        authorsButton.setOnMousePressed(
                event -> authorsButton.changeButtonImage("../images/authorsButtonPressed.png")
        );

        authorsButton.setOnMouseReleased(
                event -> {
                    presenter.clickAuthors();
                }
        );

        conferencesButton.setOnMouseReleased(
                event -> presenter.clickConferences()
        );

        papersButton.setOnMousePressed(
                event -> presenter.clickPapers()
        );

        termsButton.setOnMouseReleased(
                event -> presenter.clickTerms()
        );

        logoutIcon.setOnMouseReleased(
                event -> presenter.logout()
        );

        exitIcon.setOnMouseReleased(
                event -> presenter.exit()
        );

        manageFavoriteTopicsText.setOnMouseReleased(
                event -> presenter.manageFavoriteTopics()
        );

        settingsText.setOnMouseReleased(
                event -> presenter.settings()
        );

        searchText.setOnMouseReleased(
                event -> presenter.search()
        );

        relationshipSearchText.setOnMouseReleased(
                event -> presenter.relationshipSearch()
        );
    }

    public void destroy() { presenter = null; }

    private void initializePanes(){
        basePane = new BorderPane();
        basePane.setPadding(new Insets(0,0,0,0));

        sidebarOptions = new VBox(20);
        sidebarOptions.setPadding(new Insets(10,5,10,5));


        sidebarBottom = new VBox();
        sidebarBottom.setPadding(new Insets(125,5,10,5));

        sidebarBottomIcons = new HBox();
        sidebarBottomIcons.setPadding(new Insets(15,5,5,5));
        sidebarBottomIcons.setSpacing(10);

        sidebar = new VBox();
        sidebar.setPadding(new Insets(0,0,0,0));

        topBarPane = new BorderPane();

        appTitleHBox = new HBox();

        appTitleHBox.setAlignment(Pos.CENTER);

        categoryButtons = new HBox();
        categoryButtons.setPadding(new Insets(10,0,0,0));
        titleAndButtons = new VBox();

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
        Image logoutIconImage = new Image(this.getClass().getResourceAsStream("../images/logoutIcon.png"));
        logoutIcon.setImage(logoutIconImage);
        logoutIcon.setFitWidth(45);
        logoutIcon.setFitHeight(45);

        manageFavoriteTopicsText = new Text("Manage\nFavorite Topics");
        settingsText = new Text("Settings");
        searchText = new Text("Search");
        relationshipSearchText = new Text("Relationship\nSearch");
        username = new Text("Username");
        username.setFill(javafx.scene.paint.Color.valueOf("#fdddc8"));
        username.setTextAlignment(TextAlignment.CENTER);

        appTitleImage = new ImageView();
        Image image2 = new Image(this.getClass().getResourceAsStream("../images/appTitle.png"));

        appTitleImage.setImage(image2);
        appTitleImage.setFitWidth(409);
        appTitleImage.setFitHeight(106);

        authorsButton = new  ImageButton("../images/authorsButton.png", 225, 75);

        conferencesButton = new ImageButton("../images/conferencesButton.png", 225, 75);

        papersButton = new ImageButton("../images/papersButton.png", 227, 75);

        termsButton = new ImageButton("../images/termsButton.png", 225, 75);

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
        sidebarBottom.setAlignment(Pos.CENTER);

        String sidebarBG = this.getClass().getResource("../images/sidebarBackground.png").toExternalForm();
        sidebar.setStyle("-fx-background-image: url('" + sidebarBG + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 147 576;" +
                "-fx-font: 16 Gotham;");
        sidebar.getChildren().add(applicationLogo);
        sidebar.getChildren().add(sidebarOptions);
        sidebar.getChildren().add(sidebarBottom);

        basePane.setLeft(sidebar);

        appTitleHBox.getChildren().add(appTitleImage);

        categoryButtons.getChildren().add(authorsButton);
        categoryButtons.getChildren().add(conferencesButton);
        categoryButtons.getChildren().add(papersButton);
        categoryButtons.getChildren().add(termsButton);

        titleAndButtons.getChildren().add(appTitleHBox);
        titleAndButtons.getChildren().add(categoryButtons);
        topBarPane.setTop(titleAndButtons);
       // topBarPane.setTop(categoryButtons);

        basePane.setCenter(topBarPane);

        String image = this.getClass().getResource("../images/background.jpg").toExternalForm();
        topBarPane.setStyle(
                "-fx-background-image: url('" + image + "'); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-size: 900 576"
        );
    }

    public Pane getContent() {
        return basePane;
    }

}