package views;

import controller.ProjectScreenController;
import controller.TaskScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Task;
import programm.Programm;

import java.io.IOException;

import static programm.Programm.mainStage;

public class TaskScreen {

    public VBox vbEmployees;

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;
    private TaskScreenController tsc;

    public TaskScreen(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("TaskScreenDisplay.fxml"));
            _parent = _loader.load();
            tsc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }

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

        for (Task t : Programm.tasks) {
            createTaskElement(t,Programm.tasks.indexOf(t));
        }

    }

    private void createTaskElement(Task task, int rowindex){

        HBox hbTaskElement = new HBox();
        hbTaskElement.getStyleClass().add("task-element-section");

        MenuItem miUp = new MenuItem(">");
        miUp.setOnAction(TaskScreenController::handleUpTask);

        MenuItem miDown = new MenuItem("<");
        miDown.setOnAction(TaskScreenController::handleDownTask);
        miDown.setDisable(true);

        MenuItem miChange = new MenuItem("ändern");
        miChange.setOnAction(TaskScreenController::handleChangeTask);


        MenuItem miDelete = new MenuItem("löschen");
        miDelete.setOnAction(TaskScreenController::handleDeleteTask);

        MenuButton mbtnTaskOptions = new MenuButton("...", null, miUp, miDown, miChange, miDelete);
        mbtnTaskOptions.getStyleClass().add("task-element-section");

        Label lblTaskName = new Label(task.get_strTaskName());
        lblTaskName.getStyleClass().add("task-element-section");
        lblTaskName.setOnMouseClicked(TaskScreenController::handleOpenTask);

        vbEmployees = new VBox(); //TODO: Hierher muss das Employee-Objekt übergeben werden > task.get_alTaskEmploees() Schleife mit dem divider , Durchlaufen und vbox befüllen?
        vbEmployees.getStyleClass().add("task-element-section");

        hbTaskElement.getChildren().addAll(vbEmployees, lblTaskName, mbtnTaskOptions);

        tsc.gpTaskCategories.add(hbTaskElement, 0, (rowindex+2));

    }

    private void createEmployeeElement(){
        //Mitarbeiter Anfangsbuchhstaben als Label zur vbEmployees hinzufügen
    }

    public void showTaskScreen() {
        _mainStage.show();
    }
}


