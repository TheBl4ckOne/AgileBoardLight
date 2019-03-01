package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Project;
import programm.Programm;
import views.ProjectCreateScreen;
import views.ProjectScreen;
import views.TaskScreen;

import java.util.Optional;

import static programm.Programm.mainStage;


public class ProjectScreenController extends ActionEvent {

    @FXML
    public GridPane gpProjectScreen;

    private ProjectScreen ps;

    public void handleCreateProject(ActionEvent actionEvent) {
        ProjectCreateScreen pc = new ProjectCreateScreen(Programm.mainStage);
        pc.initForm();
        pc.showProjectCreate();
    }

    public void handleSelectProject(MouseEvent mouseEvent) {
        VBox vbCurrProject = (VBox) mouseEvent.getSource();
        int intCurrProjectId = Integer.parseInt(vbCurrProject.getId());

        TaskScreen ts = new TaskScreen(mainStage, intCurrProjectId);
        ts.initForm();
        ts.showTaskScreen();

    }

    public void handleChangeProject(ActionEvent actionEvent){
        MenuItem miCurrentProject = (MenuItem) actionEvent.getSource();
        VBox vbCurrentProjectElement = (VBox) miCurrentProject.getUserData();
        Project currentProject = (Project) vbCurrentProjectElement.getUserData();
        ProjectCreateScreen pc = new ProjectCreateScreen(Programm.mainStage);
        pc.initForm(currentProject);
        pc.showProjectCreate();
    }

    public void handleDeleteProject(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Projekt löschen?");
        alert.setHeaderText("Möchten Sie das Projekt endgültig löschen?");
        alert.setContentText("Sind Sie damit fortfahren?");

        ButtonType btnJa = new ButtonType("JA");
        ButtonType btnNein = new ButtonType("NEIN", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnJa,btnNein);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnJa){
            MenuItem miCurrentProject = (MenuItem) actionEvent.getSource();
            VBox vbCurrentProject = (VBox) miCurrentProject.getUserData();
            Project currentProject = (Project) vbCurrentProject.getUserData();
            currentProject.deleteProject();
            ps.refreshProjectElements();
        }

        else {
            alert.close();
        }
    }

    public void setPs(ProjectScreen ps) {
        this.ps = ps;
    }
}

