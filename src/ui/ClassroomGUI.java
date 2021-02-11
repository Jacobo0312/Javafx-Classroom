package ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.Classroom;
import model.Gender;//Revisar esto
import model.UserAccount;

public class ClassroomGUI {

    @FXML
    private BorderPane pane;

    @FXML
    private TextField txtUser;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField createUser;

    @FXML
    private TextField createPassword;

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
        colBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("favoriteBrowsers"));

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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome.fxml"));
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


    }

    @FXML
    public void addUser(ActionEvent event) throws IOException {
        String username = createUser.getText();
        String password = createPassword.getText();

        String genderUser;
        if (male.isSelected()) {
            genderUser = "MALE";
        } else if (female.isSelected()) {
            genderUser = "FEMALE";
        } else {
            genderUser = "OTHER";
        }

        String careers="";

        if (ingindus.isSelected()) {
            careers+=("- ING_INDUSTRIAL");
        }

        if (ingsis.isSelected()) {
            careers+=("- ING_SISTEMAS");
        }

        if (ingtel.isSelected()) {
            careers+=("- ING_TELEMATICA");
        }//hacer un else if y en el ultimo else agregar un booleano

        String dateBirthday = birthday.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        String browsers="EDGE";
        photo = new Image("/images/alert.jpg"); // Para prueba
        loginUser=username;

        classroom.addUser(username, password, photo, genderUser, careers, dateBirthday, browsers);


        

        loadTable(event);

    }








}
