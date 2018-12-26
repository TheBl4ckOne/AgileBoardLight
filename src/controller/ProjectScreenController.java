package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import programm.Programm;
import views.ProjectCreate;


public class ProjectScreenController extends ActionEvent {


    public void handleCreateProject(ActionEvent actionEvent) {
        ProjectCreate pc = new ProjectCreate(Programm.mainStage);
        pc.initForm();
        pc.showProjectCreate();
    }

    public void handleProjectOptions(ActionEvent actionEvent) {
        ListView<String> lvProjectOptions  = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "löschen", "ändern");
        lvProjectOptions.setItems(items);

    }
}

