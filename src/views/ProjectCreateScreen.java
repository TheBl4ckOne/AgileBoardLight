package views;

import controller.ProjectCreateController;
import controller.ProjectScreenController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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
        _scene = new Scene(_parent, 1200, 600);
        _mainStage.setScene(_scene);
    }

    public void showProjectCreate() {
        _mainStage.show();
    }

}
