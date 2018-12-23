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
import views.ProjectCreate;


public class ProjectScreenController extends ActionEvent {


    public void handleCreateProject(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource(); //TODO: Verfügbarmachen der Stage an dieser Stelle
        Window theStage = source.getScene().getWindow();

        /*Stage primaryStage = (Stage) .//.getScene().getWindow();

        ProjectCreate pc = new ProjectCreate(primaryStage);


        pc.initForm();
        pc.showProjectCreate();
        */

        ProjectCreate.createProject();

    }

    public void handleProjectOptions(ActionEvent actionEvent) {
        ListView<String> lvProjectOptions  = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "löschen", "ändern");
        lvProjectOptions.setItems(items);

    }
}

