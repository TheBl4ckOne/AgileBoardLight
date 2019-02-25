package views;

import controller.TaskScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Task;
import programm.Programm;

import java.io.IOException;

import static javafx.scene.layout.GridPane.getColumnIndex;

public class TaskScreen {

    private VBox vbEmployees;

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;
    private TaskScreenController tsc;

    public TaskScreen(Stage stage, int intCurrProject) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("TaskScreenDisplay.fxml"));
            _parent = _loader.load();
            tsc = _loader.getController();
            tsc.setIntCurrentProjectIndex(intCurrProject);
            tsc.setTs(this);
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

        for (Task t : Programm.projects.get(tsc.intCurrentProjectIndex).get_tasks()) {
            createTaskElement(t,Programm.projects.get(tsc.intCurrentProjectIndex).get_tasks().indexOf(t));
        }

    }

    private void createTaskElement(Task task, int index){

        HBox hbTaskElement = new HBox();
        hbTaskElement.getStyleClass().add("task-element-section");
        hbTaskElement.setId(Integer.toString(index));
        hbTaskElement.setUserData(task);

        MenuItem miUp = new MenuItem(">");
        miUp.setOnAction(TaskScreenController::handleUpTask);
        miUp.setUserData(hbTaskElement);

        MenuItem miDown = new MenuItem("<");
        miDown.setOnAction(TaskScreenController::handleDownTask);
        miDown.setUserData(hbTaskElement);


        MenuItem miChange = new MenuItem("ändern");
        miChange.setOnAction(TaskScreenController::handleChangeTask);


        MenuItem miDelete = new MenuItem("löschen");
        miDelete.setOnAction(TaskScreenController::handleDeleteTask);
        miDelete.setUserData(hbTaskElement);

        MenuButton mbtnTaskOptions = new MenuButton("...", null, miUp, miDown, miChange, miDelete);
        mbtnTaskOptions.getStyleClass().add("task-element-section");

        Label lblTaskName = new Label(task.get_strTaskName());
        lblTaskName.getStyleClass().add("task-element-section");
        lblTaskName.setOnMouseClicked(TaskScreenController::handleOpenTask);

        vbEmployees = new VBox(); //TODO: Hierher muss das Employee-Objekt übergeben werden > task.get_alTaskEmployees() Schleife mit dem divider , Durchlaufen und vbox befüllen?
        vbEmployees.getStyleClass().add("task-element-section");

        hbTaskElement.getChildren().addAll(vbEmployees, lblTaskName, mbtnTaskOptions);
        drawTaskElement(hbTaskElement);
    }

    private void drawTaskElement(HBox hbTaskElement){
        Task currentTask = (Task) hbTaskElement.getUserData();
        String strStatus = currentTask.get_strTaskStatus();

        switch (strStatus) {
            case "ToDo":
                tsc.vbToDo.getChildren().add(hbTaskElement);
                break;
            case "InProgress":
                tsc.vbInProgress.getChildren().add(hbTaskElement);
                break;
            case "Done":
                tsc.vbDone.getChildren().add(hbTaskElement);
                break;
        }
    }

    public void updateTaskElement(HBox hbCurrentTask){
        removeTaskElement(hbCurrentTask);
        drawTaskElement(hbCurrentTask);
    }

    public void removeTaskElement(HBox hbCurrentTask){
        VBox vBox = (VBox) hbCurrentTask.getParent();
        vBox.getChildren().remove(hbCurrentTask);
    }

    private void createEmployeeElement(){
        //Mitarbeiter Anfangsbuchhstaben als Label zur vbEmployees hinzufügen
    }

    public void showTaskScreen() {
        _mainStage.show();
    }
}


