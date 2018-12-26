package controller;


import javafx.event.ActionEvent;
import programm.Programm;
import views.ProjectScreen;

public class ProjectCreateController {

    public void handleAbortProject(ActionEvent actionEvent){
        ProjectScreen ps = new ProjectScreen(Programm.mainStage);
        ps.initForm();
        ps.showProjectScreen();
    }
}
