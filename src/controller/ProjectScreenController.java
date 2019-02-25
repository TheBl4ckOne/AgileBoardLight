package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Project;
import programm.Programm;
import views.ProjectCreateScreen;
import views.ProjectScreen;
import views.TaskScreen;

import static programm.Programm.mainStage;


public class ProjectScreenController extends ActionEvent {

    @FXML
    public GridPane gpProjectScreen;

    private static ProjectScreen ps;

    public void handleCreateProject(ActionEvent actionEvent) {
        ProjectCreateScreen pc = new ProjectCreateScreen(Programm.mainStage);
        pc.initForm();
        pc.showProjectCreate();
    }

    public static void handleSelectProject(MouseEvent mouseEvent) {
        VBox vbCurrProject = (VBox) mouseEvent.getSource();
        int intCurrProjectId = Integer.parseInt(vbCurrProject.getId());

        TaskScreen ts = new TaskScreen(mainStage, intCurrProjectId);
        ts.initForm();
        ts.showTaskScreen();

    }

    public static void handleChangeProject(ActionEvent actionEvent){
        ProjectCreateScreen pc = new ProjectCreateScreen(Programm.mainStage);

        //ProjectCreateScreen.lblCreateProject.setText("Projekt ändern");

        pc.initForm();
        pc.showProjectCreate();
        //TODO: Bereits eingetragenen Text bearbeitbar anzeigen + Überschrift zu "Projekt ändern" abändern
    }

    public static void handleDeleteProject(ActionEvent actionEvent){
        MenuItem miCurrentProject = (MenuItem) actionEvent.getSource();
        VBox vbCurrentProject = (VBox) miCurrentProject.getUserData();
        Project currentProject = (Project) vbCurrentProject.getUserData();
        currentProject.deleteProject();
        ps.removeProjectElement(vbCurrentProject);
    }

    public void setPs(ProjectScreen ps) {
        this.ps = ps;
    }
}

