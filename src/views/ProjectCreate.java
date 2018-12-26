package views;

import controller.ProjectScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProjectCreate {

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;

    public ProjectCreate(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("ProjectCreateDisplay.fxml"));
            _parent = _loader.load();
            ProjectScreenController psc = _loader.getController();


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

    public static void createProject(){ //TODO: Projekterstellung & Datenbankspeicherung auf Model-Ebene

    }


}
