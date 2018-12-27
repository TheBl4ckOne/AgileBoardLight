package views;

import controller.TaskScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TaskScreen {

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;

    public TaskScreen(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("TaskScreenDisplay.fxml"));
            _parent = _loader.load();
            TaskScreenController tsc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initForm() {
        _scene = new Scene(_parent, 1200, 600);
        _mainStage.setScene(_scene);

    }

    public void showProjectScreen() {
        _mainStage.show();
    }
}


