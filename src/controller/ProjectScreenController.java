package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import programm.Programm;
import views.ProjectCreateScreen;
import views.ProjectScreen;
import views.TaskScreen;

import java.util.Arrays;

import static programm.Programm.mainStage;


public class ProjectScreenController extends ActionEvent {

    @FXML
    public GridPane gpProjectScreen;

    public void handleCreateProject(ActionEvent actionEvent) {
        ProjectCreateScreen pc = new ProjectCreateScreen(Programm.mainStage);
        pc.initForm();
        pc.showProjectCreate();
    }

    public static void handleProject(MouseEvent mouseEvent) {
        TaskScreen ts = new TaskScreen(mainStage);
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

    }

}

