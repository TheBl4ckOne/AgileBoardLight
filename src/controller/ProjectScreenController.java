package controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import programm.Programm;
import views.ProjectCreateScreen;
import views.ProjectScreen;
import views.TaskScreen;

import java.util.Arrays;

import static programm.Programm.mainStage;


public class ProjectScreenController extends ActionEvent {


    public void handleCreateProject(ActionEvent actionEvent) {
        ProjectCreateScreen pc = new ProjectCreateScreen(Programm.mainStage);
        pc.initForm();
        pc.showProjectCreate();
    }

    public static void handleProjectOptions(ActionEvent actionEvent) {
        VBox vBox = new VBox(ProjectScreen.btnProjectOptions);

        ListView<String> lvProjectOptions  = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (
                "löschen", "ändern");

       /* final ListView lvProjectOptions = new ListView(FXCollections.observableList(Arrays.asList("löschen", "ändern"));
        lv.setOnMouseClicked(new EventHandler<MouseEvent>() {

        System.out.println(lvProjectOptions.getSelectionModel().getSelectedItem());

        lvProjectOptions.setItems(items);
    */
    }

    public static void handleProject(MouseEvent mouseEvent) {
        TaskScreen ts = new TaskScreen(mainStage);
        ts.initForm();
        ts.showTaskScreen();

    }
}

