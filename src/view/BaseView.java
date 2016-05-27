package view;


import domain.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import presentation.BasePresenter;

public class BaseView {

    protected BorderPane basePane;
    BorderPane topBarPane;

    private ImageView applicationLogo;
    private ImageView exitIcon;
    private ImageView logoutIcon;
    private VBox sidebarOptions;
    private VBox sidebarBottom;

    private HBox sidebarBottomIcons;
    private VBox sidebar;

    private Font coolFont;

    private Text manageFavoriteTopicsText;
    private Text settingsText;
    private Text searchText;
    private Text relationshipSearchText;
    private Text adminModeText;
    private Text username;

    private ImageView appTitleImage;
    private HBox appTitleHBox;
    protected HBox categoryButtons;
    private VBox titleAndButtons;

    protected ImageButton authorsButton;
    protected ImageButton conferencesButton;
    protected ImageButton papersButton;
    protected ImageButton termsButton;

    protected BasePresenter presenter;

    public BaseView() {
        initializePanes();
        initializeViews();
        setListeners();
        buildPanes();
    }

    private void setListeners() {

        /*authorsButton.setOnMousePressed(
                event -> {
                    authorsButton.press();
                    lastPressed.release();
                    lastPressed = authorsButton;
                }
        );*/

        authorsButton.setOnMouseReleased(
                event -> {
                    presenter.clickAuthors();
                }
        );

        /*conferencesButton.setOnMousePressed(
                event -> {
                    conferencesButton.press();
                    lastPressed.release();
                    lastPressed = conferencesButton;
                }
        );*/

        conferencesButton.setOnMouseReleased(
                event -> presenter.clickConferences()
        );

        /*papersButton.setOnMousePressed(
                event -> {
                    papersButton.press();
                    lastPressed.release();
                    lastPressed = papersButton;
                }
        );*/

        papersButton.setOnMouseReleased(
                event -> presenter.clickPapers()
        );

        /*termsButton.setOnMousePressed(
                event -> {
                    termsButton.press();
                    lastPressed.release();
                    lastPressed = termsButton;
                }
        );*/

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

        adminModeText.setOnMouseReleased(
                event -> presenter.adminMode()
        );
    }

    public void destroy() { presenter = null; }

    private void initializePanes(){
        basePane = new BorderPane();
        basePane.setPadding(new Insets(0,0,0,0));

        sidebarOptions = new VBox(20);
        sidebarOptions.setPadding(new Insets(10,5,10,5));


        sidebarBottom = new VBox();
        if (UserController.isAdmin()) sidebarBottom.setPadding(new Insets(70,5,10,5));
        else sidebarBottom.setPadding(new Insets(125,5,10,5));

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
        coolFont = Font.loadFont(this.getClass().getResource("../fonts/Nilland-Black.ttf").toExternalForm(), 14.95);
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
        manageFavoriteTopicsText.setFont(coolFont);
        settingsText = new Text("Settings");
        settingsText.setFont(coolFont);
        searchText = new Text("Search");
        searchText.setFont(coolFont);
        relationshipSearchText = new Text("Relationship\nSearch");
        relationshipSearchText.setFont(coolFont);
        adminModeText = new Text("Admin\nMode");
        adminModeText.setFont(coolFont);
        username = new Text(UserController.getCurrentUserName());
        username.setFont(coolFont);
        username.setFill(Config.LABEL_CLEAR_COLOR);
        username.setTextAlignment(TextAlignment.CENTER);


        appTitleImage = new ImageView();
        Image image2 = new Image(this.getClass().getResourceAsStream("../images/appTitle.png"));

        appTitleImage.setImage(image2);
        appTitleImage.setFitWidth(409);
        appTitleImage.setFitHeight(106);
        authorsButton = new ImageButton("../images", "authorsButton", 220, 75);

        conferencesButton = new ImageButton("../images", "conferencesButton", 225, 75);

        papersButton = new ImageButton("../images", "papersButton", 225, 75);

        termsButton = new ImageButton("../images", "termsButton", 225, 75);
    }

    private void buildPanes(){

        sidebarBottomIcons.getChildren().add(logoutIcon);
        sidebarBottomIcons.getChildren().add(exitIcon);

        sidebarOptions.getChildren().add(manageFavoriteTopicsText);
        sidebarOptions.getChildren().add(settingsText);
        sidebarOptions.getChildren().add(searchText);
        sidebarOptions.getChildren().add(relationshipSearchText);
        if (UserController.isAdmin()) sidebarOptions.getChildren().add(adminModeText);
        sidebarBottom.getChildren().add(username);
        sidebarBottom.getChildren().add(sidebarBottomIcons);
        sidebarBottom.setAlignment(Pos.CENTER);

        String sidebarBG = this.getClass().getResource("../images/sidebarBackground.png").toExternalForm();
        sidebar.setStyle(
                "-fx-background-image: url('" + sidebarBG + "'); " +
                "-fx-background-position: center center; " +
                "-fx-background-repeat: stretch;" +
                "-fx-background-size: 147 576;" +
                "-fx-font: 16 Gotham;"
        );
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
                "-fx-background-size: " + Config.WINDOW_WIDTH + " " + Config.WINDOW_HEIGHT
        );
    }

    public Pane getContent() {return basePane;}

}