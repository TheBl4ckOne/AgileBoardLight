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

        _tcc.gpTaskData.add(hbEmployees,1,2);

    }

    public void showTaskCreate() {
        _mainStage.show();
    }

}