package views;

import controller.ProjectCreateController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.Employee;
import models.Project;
import programm.Programm;

import java.io.IOException;
import java.time.LocalDateTime;

public class ProjectCreateScreen {
    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;
    private  ProjectCreateController _pcc;

    public ProjectCreateScreen(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("ProjectCreateDisplay.fxml"));
            _parent = _loader.load();
            _pcc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initForm() {
        _scene = new Scene(_parent, Programm.width, Programm.height);

        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        _pcc.dtpiDeadline.setValue(LocalDateTime.now().toLocalDate());

        _pcc.setbNewProject(true);
    }

    public void initForm(Project currentProject) {
        _scene = new Scene(_parent, Programm.width, Programm.height);

        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        _pcc.txtfProjectname.setText(currentProject.get_strProjectName());
        //Das Project-Objekt word als Userdata an das Textfield PProjektname gehangen um es im Controller ansprechbar zu machen
        _pcc.txtfProjectname.setUserData(currentProject);

        _pcc.txtaProjectDescription.setText(currentProject.get_strProjectDescription());
        _pcc.dtpiDeadline.setValue(currentProject.get_ldtDeadline());
        for (Employee projectEmployee: currentProject.get_employees()) {
            if(_pcc.lblEmployees.getText().equals("")){
                _pcc.lblEmployees.setText(projectEmployee.get_strEmployeeName());
            }else{
                _pcc.lblEmployees.setText(_pcc.lblEmployees.getText() + ", " + projectEmployee.get_strEmployeeName());
            }
        }
        _pcc.setbNewProject(false);
    }


    public void showProjectCreate() {
        _mainStage.show();
    }

}
