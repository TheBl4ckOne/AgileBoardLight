package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import models.Project;
import programm.Programm;
import views.ProjectCreateScreen;
import views.ProjectScreen;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class ProjectCreateController extends ActionEvent{


    //Lädt das Element mit der Entsprechenden ID aus dem FXML
    @FXML
    private TextField txtfProjectname, txtfProjectDescription, txtfProjectTeam;
    @FXML
    private DatePicker dtpiDeadline;
    @FXML
    private Button btnAbortProject, btnSaveProject;
    @FXML
    private BorderPane bpProjectCreateDisplay;

    public void handleAbortProject(ActionEvent actionEvent){ //siehe ProjectChangeController

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

        //TODO Datenbankverbindung herstellen und eingegebenen Daten speichern

        //CHANGE Im alten Stand würden neue Textfelder im Code generiert und deren Text abgefragt
        //  Die im Code generierten Textfelder sind nicht die selben wie die angezeigten Textfelder, deren Text wiederum nicht abgefragt wurde
        //  --> Die Elemente werden nun aus der FXML geladen, siehe Oben

        String strProjectname = txtfProjectname.getText();
        String strProjectDescription = txtfProjectDescription.getText();
        String strProjectTeam = txtfProjectTeam.getText();

        Project project = new Project(strProjectname, strProjectDescription,);

        LocalDate ldtDeadline =  dtpiDeadline.getValue();

        //TODO: Daten zwischen ProjectCreateController und ProjectScreenController über das Model verbinden

        ProjectScreen.createProjectElement();

        ProjectScreen ps = new ProjectScreen(Programm.mainStage);
        ps.initForm();
        ps.showProjectScreen();

    }
}
