package controller;


import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import models.Employee;
import models.Project;
import programm.Programm;
import views.ProjectScreen;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class ProjectCreateController extends ActionEvent{


    //Lädt das Element mit der Entsprechenden ID aus dem FXML
    @FXML
    public TextField txtfProjectname, txtfProjectTeam;
    @FXML
    public TextArea txtaProjectDescription;
    @FXML
    public DatePicker dtpiDeadline;
    @FXML
    public Label lblEmployees;


    private boolean bNewProject;

    public void handleAbortProject(ActionEvent actionEvent){

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Projekt erstellen Abbrechen?");
        alert.setHeaderText("Möchten Sie die Erstellung Ihres Projektes wirklich abbrechen? Alle Änderungen gehen verloren.");
        alert.setContentText("Sind Sie damit einverstanden?");

        ButtonType btnJa = new ButtonType("JA");
        ButtonType btnNein = new ButtonType("NEIN", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(btnJa,btnNein);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == btnJa){

            ProjectScreen ps = new ProjectScreen(Programm.mainStage);
            ps.initForm();
            ps.showProjectScreen();
        }

        else {
            alert.close();
        }
    }
    @FXML //Ermöglicht den aufruf der private Methode aus der FXML heraus
    private void handleSaveProject(ActionEvent actionEvent) {
        //CHANGE Im alten Stand würden neue Textfelder im Code generiert und deren Text abgefragt
        //  Die im Code generierten Textfelder sind nicht die selben wie die angezeigten Textfelder, deren Text wiederum nicht abgefragt wurde
        //  --> Die Elemente werden nun aus der FXML geladen, siehe Oben

        String strProjectname = txtfProjectname.getText();
        String strProjectDescription = txtaProjectDescription.getText();
        String[] strProjectTeam = lblEmployees.getText().split(", ");
        LocalDate ldtDeadline =  dtpiDeadline.getValue();

        ArrayList<Employee> alEmployees = new ArrayList<>();
        //For-Each Schleife: kürzere Methode zum durchlaufen von Arrays als for-Schleife
        //Die Laufvariable ist vom Datentyp der Elemente des zu durchlaufenden Arrays(hier String)
        //In den Klammern nach dem For steht zunächst die Dekleration der Laufvariable gefolgt von einem Doppelpunkt danach kommt der Name des ARrays das durchlaufen werden soll
        //Mit jedem Durchlauf nimmt die Laufwariable nun den Wert des Arrays an der aktuellen Stelle an

        for (String s: strProjectTeam) {
            alEmployees.add(new Employee(s));
        }

        if(bNewProject){
            // Hinzufügen des neuen Projektes zur Datenbank wir im Konstruktor des Projektes behandelt
            new Project(strProjectname,strProjectDescription,ldtDeadline,alEmployees);
        }else {
           Project p = (Project) txtfProjectname.getUserData();
           p.set_strProjectName(strProjectname);
           p.set_strProjectDescription(strProjectDescription);
           p.set_ldtDeadline(ldtDeadline);

            for (Employee localProjectEmployee:p.get_employees()) {
                if (localProjectEmployee.get_intEmployeeId() == null){
                    Programm.dbAgent.InsertEmployeeIntoDatabase(localProjectEmployee);
                }
            }

           Programm.dbAgent.UpdateProject(p);
        }

        ProjectScreen ps = new ProjectScreen(Programm.mainStage);
        ps.initForm();
        ps.showProjectScreen();
    }

    public void handleNewEmployee(ActionEvent actionEvent) {
        if (!bNewProject){
            Project p = (Project) txtfProjectname.getUserData();
            p.get_employees().add(new Employee(txtfProjectTeam.getText(),p.get_intProjectId()));
        }

        if (lblEmployees.getText().equals("")){
            lblEmployees.setText(txtfProjectTeam.getText());
            txtfProjectTeam.setText("");
        }else {
            lblEmployees.setText(lblEmployees.getText() + ", " + txtfProjectTeam.getText());
            txtfProjectTeam.setText("");
        }
    }

    public void setbNewProject(boolean bNewProject) {
        this.bNewProject = bNewProject;
    }
}
