package controller;


import javafx.event.ActionEvent;
import programm.Programm;
import views.ProjectCreateScreen;
import views.TaskCreateScreen;
import views.TaskScreen;

public class TaskScreenController {

//Projektnamen hierher geben

    public void handleCreateTask(ActionEvent actionEvent) {
        TaskCreateScreen tc = new TaskCreateScreen(Programm.mainStage);
        tc.initForm();
        tc.showTaskCreate();
    }

}
