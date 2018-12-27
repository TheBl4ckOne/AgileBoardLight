package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import programm.Programm;
import views.ProjectCreateScreen;


public class ProjectScreenController extends ActionEvent {


    public void handleCreateProject(ActionEvent actionEvent) {
        ProjectCreateScreen pc = new ProjectCreateScreen(Programm.mainStage);
        pc.initForm();
        pc.showProjectCreate();
    }

    public static void handleProjectOptions(ActionEvent actionEvent) {
        ListView<String> lvProjectOptions  = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "löschen", "ändern");
        lvProjectOptions.setItems(items);
    }


}

