package views;

import controller.TaskScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        vbToDo.getStyleClass().add("task-section");
        VBox vbAtWork = new VBox();
        vbAtWork.getChildren().add(new Label("In Arbeit"));
        vbAtWork.getStyleClass().add("task-section");
        VBox vbReady = new VBox();
        vbReady.getChildren().add(new Label("Fertig"));
        vbReady.getStyleClass().add("task-section");

        GridPane gp = (GridPane) _parent.lookup("#gpTaskCategories");

        Button btnAddTask = new Button("+");
        btnAddTask.getStyleClass().add("task-section");
        btnAddTask.setOnAction(TaskScreenController::handleTaskCreate);
        gp.add(btnAddTask, 0,1);

        gp.add(vbToDo, 0, 0);
        gp.add(vbAtWork, 1, 0);
        gp.add(vbReady, 2, 0);

        //TODO: neue Tasks werden der gp hinzugefügt mit column und row-Index



    }

    private void createTaskElement(){

    }

    public void showTaskScreen() {
        _mainStage.show();
    }
}


