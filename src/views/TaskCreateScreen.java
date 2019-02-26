package views;


import controller.ProjectCreateController;
import controller.TaskCreateController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Employee;
import models.Task;
import programm.Programm;

import java.io.IOException;
import java.util.ArrayList;

public class TaskCreateScreen {

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;
    private TaskCreateController _tcc;

    public TaskCreateScreen(Stage stage, int intCurrProject) {
        //Konstruktor, wenn eine neue Aufgabe anzeigt werden soll
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("TaskCreateDisplay.fxml"));
            _parent = _loader.load();
            _tcc = _loader.getController();
            _tcc.setIntCurrentProjectIndex(intCurrProject);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void initForm() {
    //InitForm für eine neue Aufgabe
        _scene = new Scene(_parent, Programm.width, Programm.height);

        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        HBox hbEmployees = new HBox(10);
        hbEmployees.setId("hbEmployees");

        ArrayList<Employee> arrCurrEmployees =  Programm.projects.get(_tcc.getIntCurrentProjectIndex()).get_employees();

        for (Employee e: arrCurrEmployees) {
            CheckBox cbEmployee = new CheckBox();
            cbEmployee.setText(e.get_strEmployeeName());
            cbEmployee.setId(String.valueOf(arrCurrEmployees.indexOf(e)));
            hbEmployees.getChildren().add(cbEmployee);
        }

        _tcc.setbNewTask(true);
        _tcc.gpTaskData.add(hbEmployees,1,2);

    }

    public void initForm(Task task){
    //InitForm für Anzeige eines bestehenden Task
        _scene = new Scene(_parent, Programm.width, Programm.height);

        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        _tcc.txtfTaskname.setText(task.get_strTaskName());
        _tcc.txtaTaskDescription.setText(task.get_strTaskDescription());

        HBox hbEmployees = new HBox(10);
        hbEmployees.setId("hbEmployees");

        ArrayList<Employee> arrCurrEmployees =  Programm.projects.get(_tcc.getIntCurrentProjectIndex()).get_employees();
        ArrayList<Employee> arrTaskEmployees = task.get_alTaskEmployees();

        for (Employee projectEmployee: arrCurrEmployees) {
            CheckBox cbEmployee = new CheckBox();
            cbEmployee.setText(projectEmployee.get_strEmployeeName());
            cbEmployee.setId(String.valueOf(arrCurrEmployees.indexOf(projectEmployee)));
            for (Employee taskEmployee: arrTaskEmployees) {
                if (projectEmployee.get_intEmployeeId().equals(taskEmployee.get_intEmployeeId())){
                    cbEmployee.setSelected(true);
                }
            }
            hbEmployees.getChildren().add(cbEmployee);
        }
        _tcc.setbNewTask(false);
        _tcc.gpTaskData.setUserData(task);
        _tcc.gpTaskData.add(hbEmployees,1,2);
    }

    public void showTaskCreate() {
        _mainStage.show();
    }

}