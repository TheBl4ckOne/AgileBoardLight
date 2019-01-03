package views;


import controller.ProjectCreateController;
import controller.TaskCreateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programm.Programm;

import java.io.IOException;

public class TaskCreateScreen {

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;

    public TaskCreateScreen(Stage stage) {

        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("TaskCreateDisplay.fxml"));
            _parent = _loader.load();
            TaskCreateController tcc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initForm() {
        _scene = new Scene(_parent, Programm.width, Programm.height);

        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

    }

    public void showTaskCreate() {
        _mainStage.show();
    }

}




