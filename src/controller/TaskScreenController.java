package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import programm.Programm;
import views.ProjectCreateScreen;
import views.ProjectScreen;
import views.TaskCreateScreen;
import views.TaskScreen;

import javax.swing.*;
import java.util.Optional;

public class TaskScreenController {
    @FXML
    public
    GridPane gpTaskCategories;

//Projektnamen hierher geben

    public static void handleTaskCreate(ActionEvent actionEvent) {
        TaskCreateScreen tc = new TaskCreateScreen(Programm.mainStage);
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

    public void handleSaveTasks(ActionEvent actionEvent){
        //TODO: Speichern der verschobenen Tasks

    }

    public static void handleChangeTask(ActionEvent actionEvent){

    }

    public static void handleDeleteTask(ActionEvent actionEvent){

    }

    public static void handleUpTask(ActionEvent actionEvent){
        //TODO: Methode zur Kontrolle ob es nicht schon in der ganz rechten oder linken Kategorie ist
    }

    public static void handleDownTask(ActionEvent actionEvent){

    }




}
