package views;

import controller.TaskScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import programm.Programm;

import java.io.IOException;

import static programm.Programm.mainStage;

public class TaskScreen {

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;

    @FXML
    GridPane gpTaskCategories;

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

        VBox vbToDo = new VBox();
        vbToDo.getChildren().add(new Label("Noch zu machen"));
        VBox vbAtWork = new VBox();
        vbAtWork.getChildren().add(new Label("In Arbeit"));
        VBox vbReady = new VBox();
        vbReady.getChildren().add(new Label("Fertig"));

        GridPane gp = (GridPane) _parent.lookup("#gpTaskCategories");
        gp.setStyle("-fx-border-color: black");

        vbToDo.getStyleClass().add("task-section");
        vbAtWork.getStyleClass().add("task-section");
        vbReady.getStyleClass().add("task-section");

        gp.add(vbToDo, 0, 0);
        gp.add(vbAtWork, 1, 0);
        gp.add(vbReady, 2, 0);

        //TODO: neue Tasks werden der gp hinzugefügt mit column und row-Index



    }

    public void showTaskScreen() {
        _mainStage.show();
    }
}


