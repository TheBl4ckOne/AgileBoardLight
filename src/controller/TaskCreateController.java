package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.Employee;
import models.Task;
import programm.Programm;
import views.TaskScreen;

import java.util.ArrayList;

import static programm.Programm.mainStage;

public class TaskCreateController {

    @FXML
    public TextField txtfTaskname;

    @FXML
    public TextArea txtaTaskDescription;

    @FXML
    public TextField txtfTaskEmployee;

    public void handleSaveTask(ActionEvent actionEvent) {
        //Speichern und zurück zur Projektseite

        String strTaskName = txtfTaskname.getText();
        String strTaskDescription = txtaTaskDescription.getText();
        String strTaskCategory = ""; //TODO: TaskCategory als Enum
        ArrayList<Employee> alTaskEmployees = null;

        String[] strTaskTeam = txtfTaskEmployee.getText().split(",");

        ArrayList<Employee> alEmployees =  new ArrayList<Employee>();
        for (String s: strTaskTeam) {
            alEmployees.add(new Employee(s));
        }

        Task t = new Task(strTaskName, strTaskDescription, strTaskCategory, alTaskEmployees);

        Programm.tasks.add(t);

        TaskScreen ts = new TaskScreen(Programm.mainStage);
        ts.initForm();
        ts.showTaskScreen();

    }

    public void handleAbortTask(ActionEvent actionEvent) {
        TaskScreen ts = new TaskScreen(mainStage);
        ts.initForm();
        ts.showTaskScreen();

        //zurück zur Projektseite

    }
}
