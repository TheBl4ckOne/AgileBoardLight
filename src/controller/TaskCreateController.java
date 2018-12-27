package controller;

import javafx.event.ActionEvent;
import models.Task;
import programm.Programm;
import views.ProjectScreen;
import views.TaskScreen;

import java.beans.EventHandler;

import static programm.Programm.mainStage;

public class TaskCreateController {

    public void handleSaveTask(ActionEvent actionEvent) {
        //Speichern und zurück zur Projektseite
        Task t = new Task();
    }

    public void handleAbortTask(ActionEvent actionEvent) {
        TaskScreen ts = new TaskScreen(mainStage);
        ts.initForm();
        ts.showProjectScreen();

        //zurück zur Projektseite

    }
}
