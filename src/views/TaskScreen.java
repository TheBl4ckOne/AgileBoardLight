package views;

import controller.TaskScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import programm.Programm;

import java.io.IOException;

import static programm.Programm.mainStage;

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

        mainStage.setFullScreen(true);

    }

    public void initForm() {
        _scene = new Scene(_parent, Programm.width, Programm.height);
        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

    }

    public void showTaskScreen() {
        _mainStage.show();
    }
}


