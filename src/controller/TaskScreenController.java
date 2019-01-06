package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import programm.Programm;
import views.ProjectCreateScreen;
import views.ProjectScreen;
import views.TaskCreateScreen;
import views.TaskScreen;

import javax.swing.*;

public class TaskScreenController {
    @FXML
    public
    GridPane gpTaskCategories;


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
        ProjectScreen ps = new ProjectScreen(Programm.mainStage);
        ps.initForm();
        ps.showProjectScreen();
    }

    public void handleSaveTasks(ActionEvent actionEvent){
        //TODO: Speichern der verschobenen Tasks

    }


}
