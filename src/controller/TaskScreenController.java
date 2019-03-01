package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Task;
import programm.Programm;
import views.ProjectScreen;
import views.TaskCreateScreen;
import views.TaskScreen;

import java.util.Optional;

public class TaskScreenController {
    @FXML
    public  GridPane gpTaskCategories;
    @FXML
    public VBox vbToDo;
    @FXML
    public VBox vbInProgress;
    @FXML
    public VBox vbDone;
    @FXML
    public Label lblProjectname;

    private int _intCurrentProjectIndex;
    private TaskScreen _ts;


    public void handleTaskCreate(ActionEvent actionEvent) {
        TaskCreateScreen tc = new TaskCreateScreen(Programm.mainStage, _intCurrentProjectIndex);
        tc.initForm();
        tc.showTaskCreate();
    }

    public void handleHome(ActionEvent actionEvent) {
            ProjectScreen ps = new ProjectScreen(Programm.mainStage);
            ps.initForm();
            ps.showProjectScreen();
    }

    public void handleChangeTask(ActionEvent actionEvent){
        MenuItem miCurrentTaskMenuItem = (MenuItem) actionEvent.getSource();
        VBox vbCurrentTaskElement =  (VBox) miCurrentTaskMenuItem.getUserData();
        Task currentTask = (Task) vbCurrentTaskElement.getUserData();

        TaskCreateScreen tcs = new TaskCreateScreen(Programm.mainStage, _intCurrentProjectIndex);
        tcs.initForm(currentTask);
        tcs.showTaskCreate();
    }

    public void handleDeleteTask(ActionEvent actionEvent){
        MenuItem miCurrentTask = (MenuItem) actionEvent.getSource();
        VBox vbCurrentTask = (VBox) miCurrentTask.getUserData();
        Task currentTask = (Task) vbCurrentTask.getUserData();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aufgabe löschen?");
        alert.setHeaderText("Wollen Sie diese Aufgabe wirklich endgültig löschen?");

        ButtonType btnJa = new ButtonType("JA");
        ButtonType btnNein = new ButtonType("NEIN", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnJa,btnNein);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnJa){
            currentTask.deleteTask();
            _ts.removeTaskElement(vbCurrentTask);
        }
    }

    public void handleUpTask(ActionEvent actionEvent){
        MenuItem miCurrentTask = (MenuItem) actionEvent.getSource();
        VBox vbCurrentTask = (VBox) miCurrentTask.getUserData();
        Task currentTask = (Task) vbCurrentTask.getUserData();
        currentTask.upTask();
        _ts.updateTaskElement(vbCurrentTask);
        }

    public void handleDownTask(ActionEvent actionEvent){
        MenuItem miCurrentTask = (MenuItem) actionEvent.getSource();
        VBox vbCurrentTask = (VBox) miCurrentTask.getUserData();
        Task currentTask = (Task) vbCurrentTask.getUserData();
        currentTask.downTask();
        _ts.updateTaskElement(vbCurrentTask);
    }


    public void handleOpenTask(MouseEvent mouseEvent) {
        Label lblCurrentTaskLabel = (Label) mouseEvent.getSource();
        //Zweifaches getParent da erst der Layoutcontainer hbTaskinfo "übersprungen" werden muss
        VBox vbCurrentTaskElement =  (VBox) lblCurrentTaskLabel.getParent().getParent();
        Task currentTask = (Task) vbCurrentTaskElement.getUserData();

        TaskCreateScreen tcs = new TaskCreateScreen(Programm.mainStage, _intCurrentProjectIndex);
        tcs.initForm(currentTask);
        tcs.showTaskCreate();
    }

    public void set_intCurrentProjectIndex(int _intCurrentProjectIndex) {
        this._intCurrentProjectIndex = _intCurrentProjectIndex;
    }

    public int get_intCurrentProjectIndex() {
        return _intCurrentProjectIndex;
    }

    public void set_ts(TaskScreen _ts) {
        this._ts = _ts;
    }
}
