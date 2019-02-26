package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    public static int intCurrentProjectIndex;
    private static TaskScreen ts;

    public static void handleTaskCreate(ActionEvent actionEvent) {
        TaskCreateScreen tc = new TaskCreateScreen(Programm.mainStage, intCurrentProjectIndex);
        tc.initForm();
        tc.showTaskCreate();
    }

    public void handleHome(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Zurück zur Startseite?");
        alert.setHeaderText("Wollen Sie wirklich zurück zur Startseite? Alle Änderungen gehen verloren.");
        alert.setContentText("Sind Sie damit einverstanden?");

        ButtonType btnJa = new ButtonType("JA");
        ButtonType btnNein = new ButtonType("NEIN", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnJa,btnNein);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnJa){
            ProjectScreen ps = new ProjectScreen(Programm.mainStage);
            ps.initForm();
            ps.showProjectScreen();
        }

    }

    public static void handleChangeTask(ActionEvent actionEvent){
        Label lblCurrentTaskLabel = (Label) actionEvent.getSource();
        HBox hbCurrentTaskElement =  (HBox) lblCurrentTaskLabel.getParent();
        Task currentTask = (Task) hbCurrentTaskElement.getUserData();

        TaskCreateScreen tcs = new TaskCreateScreen(Programm.mainStage, intCurrentProjectIndex);
        tcs.initForm(currentTask);
        tcs.showTaskCreate();
    }

    public static void handleDeleteTask(ActionEvent actionEvent){
        MenuItem miCurrentTask = (MenuItem) actionEvent.getSource();
        HBox hbCurrentTask = (HBox) miCurrentTask.getUserData();
        Task currentTask = (Task) hbCurrentTask.getUserData();

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aufgabe löschen?");
        alert.setHeaderText("Wollen Sie diese Aufgabe wirklich endgültig löschen?");

        ButtonType btnJa = new ButtonType("JA");
        ButtonType btnNein = new ButtonType("NEIN", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnJa,btnNein);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnJa){
            currentTask.deleteTask();
            ts.removeTaskElement(hbCurrentTask);
        }
    }

    public static void handleUpTask(ActionEvent actionEvent){
        MenuItem miCurrentTask = (MenuItem) actionEvent.getSource();
        HBox hbCurrentTask = (HBox) miCurrentTask.getUserData();
        Task currentTask = (Task) hbCurrentTask.getUserData();
        currentTask.upTask();
        ts.updateTaskElement(hbCurrentTask);
        }

    public static void handleDownTask(ActionEvent actionEvent){
        MenuItem miCurrentTask = (MenuItem) actionEvent.getSource();
        HBox hbCurrentTask = (HBox) miCurrentTask.getUserData();
        Task currentTask = (Task) hbCurrentTask.getUserData();
        currentTask.downTask();
        ts.updateTaskElement(hbCurrentTask);
    }


    public static void handleOpenTask(javafx.scene.input.MouseEvent mouseEvent) {
        Label lblCurrentTaskLabel = (Label) mouseEvent.getSource();
        HBox hbCurrentTaskElement =  (HBox) lblCurrentTaskLabel.getParent();
        Task currentTask = (Task) hbCurrentTaskElement.getUserData();

        TaskCreateScreen tcs = new TaskCreateScreen(Programm.mainStage, intCurrentProjectIndex);
        tcs.initForm(currentTask);
        tcs.showTaskCreate();
    }

    public void setIntCurrentProjectIndex(int intCurrentProjectIndex) {
        this.intCurrentProjectIndex = intCurrentProjectIndex;
    }

    public void setTs(TaskScreen ts) {
        this.ts = ts;
    }
}
