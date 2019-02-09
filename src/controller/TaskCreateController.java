package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Employee;
import models.Project;
import models.Task;
import programm.Programm;
import views.TaskScreen;

import java.util.ArrayList;
import java.util.Optional;

import static programm.Programm.mainStage;

public class TaskCreateController {

    @FXML
    public TextField txtfTaskname;

    @FXML
    public TextArea txtaTaskDescription;

    @FXML
    public GridPane gpTaskData;

    private int intCurrentProjectIndex;

    public void handleSaveTask(ActionEvent actionEvent) {
        //Speichern und zurück zur Projektseite

        String strTaskName = txtfTaskname.getText();
        String strTaskDescription = txtaTaskDescription.getText();
        String strTaskCategory = Programm.enmTaskStatus.ToDo.toString();
        ArrayList<Employee> alTaskEmployees = new ArrayList<>();


        HBox hbEmployees = (HBox) gpTaskData.getScene().lookup("#hbEmployees");

        for (Node node: hbEmployees.getChildren()) {
            try{
                CheckBox cb = (CheckBox) node;

                if(cb.isSelected()){
                    //Die Id der CheckBox ist immer auch die ID des Employee, siehe TaskCreateScreen initForm
                    int intEmployeeId = Integer.parseInt(cb.getId());
                    Project p = Programm.projects.get(intCurrentProjectIndex);
                    Employee e = p.get_employees().get(intEmployeeId);
                    alTaskEmployees.add(e);
                }
            }catch (ClassCastException e){

            }
        }

        //Hinzufügen des Task zur Datenbank wird im Konstruktor des Task behandelt
        Project p = Programm.projects.get(intCurrentProjectIndex);
        new Task(strTaskName, strTaskDescription, strTaskCategory, alTaskEmployees, Integer.parseInt(p.get_strProjectId()));


        TaskScreen ts = new TaskScreen(Programm.mainStage, intCurrentProjectIndex);
        ts.initForm();
        ts.showTaskScreen();

    }

    public void handleAbortTask(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aufgabe erstellen Abbrechen?");
        alert.setHeaderText("Möchten Sie die Erstellung Ihrer Aufgabe wirklich abbrechen? Alle Änderungen gehen verloren.");
        alert.setContentText("Sind Sie damit einverstanden?");

        ButtonType btnJa = new ButtonType("JA");
        ButtonType btnNein = new ButtonType("NEIN", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnJa,btnNein);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnJa) {

            TaskScreen ts = new TaskScreen(mainStage, intCurrentProjectIndex);
            ts.initForm();
            ts.showTaskScreen();
        }
    }

    public void setIntCurrentProjectIndex(int intCurrentProjectIndex) {
        this.intCurrentProjectIndex = intCurrentProjectIndex;
    }

    public int getIntCurrentProjectIndex() {
        return intCurrentProjectIndex;
    }
}
