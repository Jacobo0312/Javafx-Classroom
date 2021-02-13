package ui;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Classroom;
import model.Gender;
import model.UserAccount;

public class ClassroomGUI {

    @FXML
    private BorderPane pane;

    @FXML
    private TextField txtUser;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField createUser;

    @FXML
    private PasswordField userPassword;

    @FXML
    private RadioButton male, female, other;

    @FXML
    private ToggleGroup gender;

    @FXML
    private CheckBox ingtel;

    @FXML
    private CheckBox ingsis;

    @FXML
    private CheckBox ingindus;

    @FXML
    private DatePicker birthday;

    @FXML
    private TextField directory;

    @FXML
    private TableView<UserAccount> table;

    @FXML
    private TableColumn<UserAccount, String> colName;

    @FXML
    private TableColumn<UserAccount, Gender> colGender;

    @FXML
    private TableColumn<UserAccount, String> colCareer;

    @FXML
    private TableColumn<UserAccount, String> colBirthday;

    @FXML
    private TableColumn<UserAccount, String> colBrowser;

    @FXML
    private ChoiceBox<String> txtBrowser;

    @FXML
    private ImageView profilePhoto;

    @FXML
    private Label labelUser;

    private Classroom classroom;

    private Image photo;
    private String loginUser;

    public ClassroomGUI(Classroom controller) {
        classroom = controller;

    }

    @FXML
    private void initializeTableView() {
        ObservableList<UserAccount> observableList;
        observableList = FXCollections.observableArrayList(classroom.getUsers());
        table.setItems(observableList);

        colName.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("username"));
        colGender.setCellValueFactory(new PropertyValueFactory<UserAccount, Gender>("Gender"));
        colCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("careers"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("birthday"));
        colBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("favoriteBrowser"));

    }

    @FXML
    public void loadTable(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("table.fxml"));
        fxmlLoader.setController(this);
        Parent tablePane = fxmlLoader.load();
        pane.getChildren().clear();
        pane.setCenter(tablePane);
        initializeTableView();

        profilePhoto.setImage(photo);
        labelUser.setText(loginUser);

    }

    @FXML
    public void loadWelcome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        fxmlLoader.setController(this);
        Parent welcome = fxmlLoader.load();
        pane.getChildren().clear();
        pane.setCenter(welcome);
    }

    @FXML
    public void loadFormulario(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("formulario.fxml"));
        fxmlLoader.setController(this);
        Parent formulario = fxmlLoader.load();
        pane.getChildren().clear();
        pane.setCenter(formulario);

        txtBrowser.getItems().addAll("EDGE", "FIREFOX", "OPERA", "SAFARI","CHROME","THOR");

    }

    @FXML
    public void addUser(ActionEvent event) throws IOException {

        try {
            boolean valid = true;
            String username = createUser.getText();
            String password = userPassword.getText();
            if (username.isEmpty() || password.isEmpty()) valid = false;

            String genderUser = "";
            if (male.isSelected()) {
                genderUser = "MALE";
            } else if (female.isSelected()) {
                genderUser = "FEMALE";
            } else if (other.isSelected()) {
                genderUser = "OTHER";
            } else valid = false;

            String careers = "";

            if (ingindus.isSelected()) {
                careers += ("- ING_INDUSTRIAL");
            }

            if (ingsis.isSelected()) {
                careers += ("- ING_SISTEMAS");
            }

            if (ingtel.isSelected()) {
                careers += ("- ING_TELEMATICA");
            }

            if (careers.isEmpty()) valid = false;

            String dateBirthday = birthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            String browser = (String) txtBrowser.getValue();
            if (browser.isEmpty()) valid=false;

            String ruta = directory.getText();
            photo = new Image("file:" + ruta);
            loginUser = username;

            if (valid) {

                classroom.addUser(username, password, photo, genderUser, careers, dateBirthday, browser);

                loadTable(event);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Account created");
                alert.setHeaderText(null);
                alert.setContentText("The new account has been created");

                alert.showAndWait();

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Validation Error");
                alert.setHeaderText(null);
                alert.setContentText("You must fill each field in the form");

                alert.showAndWait();

            }

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Validation Error");
            alert.setHeaderText(null);
            alert.setContentText("You must fill each field in the form");

            alert.showAndWait();
        }
    }

    @FXML
    public void chooserImage(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.jpg", "*.png"));
        File file = fc.showOpenDialog(null);

        if (file != null) {
            directory.setText(file.getAbsolutePath());
        } else {
            directory.setText("Invalid");

        }
    }

    @FXML
    void login(ActionEvent event) throws IOException {

        UserAccount user = classroom.getUser(txtUser.getText(), txtPassword.getText());

        if (user == null) {
            txtUser.clear();
            txtPassword.clear();

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Log in incorrect");
            alert.setHeaderText(null);
            alert.setContentText("The username and password given are incorrect");

            alert.showAndWait();

        } else {
            photo = user.getPhoto();
            loginUser = user.getUsername();
            loadTable(event);

        }

    }

}
