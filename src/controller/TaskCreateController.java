package controller;

import programm.Programm;
import views.ProjectScreen;
import views.TaskScreen;

import java.beans.EventHandler;

import static programm.Programm.mainStage;

public class TaskCreateController {

    public static void handleAbortTask(EventHandler actionEvent){
        TaskScreen ts = new TaskScreen(mainStage);
        ts.initForm();
        ts.showProjectScreen();

        //zurück zur Projektseite
    }

    public static void handleSaveTask(EventHandler actionEvent){
        //Speichern und zurück zur Projektseite
    }
}
