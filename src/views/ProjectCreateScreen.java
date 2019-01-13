package views;

import controller.ProjectCreateController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Project;
import programm.Programm;

import java.io.IOException;

public class ProjectCreateScreen {

    public static String strProjectname;
    public static String strProjectDescription;
    public static String strProjectTeam;
    public static String strDeadline;

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;

    @FXML
    BorderPane bpProjectCreateDisplay;

    @FXML
    Button btnAbortProject;

    @FXML
    Button btnSaveProject;

    @FXML
    public static Label lblCreateProject;

    @FXML
    public static TextArea txtaProjectDescription;

    public ProjectCreateScreen(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("ProjectCreateDisplay.fxml"));
            _parent = _loader.load();
            ProjectCreateController pcc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initForm() {
        _scene = new Scene(_parent, Programm.width, Programm.height);

        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

    }

    public  void  AddTfNewEmployee(){

    }

    public void showProjectCreate() {
        //txtaProjectDescription.setText(strProjectDescription);
        //label.setTextProperty.bind(folder); = Label an String binden

        _mainStage.show();
    }

}
