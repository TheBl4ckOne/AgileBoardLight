package controller;


import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import programm.Programm;
import views.ProjectCreateScreen;
import views.ProjectScreen;

import java.util.Optional;

public class ProjectCreateController extends ActionEvent{

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

    public void handleSaveProject(ActionEvent actionEvent) {

        //TODO Datenbankverbindung herstellen und eingegebenen Daten speichern

        TextField txtfProjectname = new TextField();
        String strProjectname = txtfProjectname.getText();
        TextField txtfProjectDescription = new TextField();
        String strProjectDescription = txtfProjectDescription.getText();
        TextField txtfProjectTeam = new TextField();
        String strProjectTeam = txtfProjectTeam.getText();
        TextField txtfDeadline = new TextField(); //TODO: Datumsauswahl aus Kalender möglich?
        String strDeadline = txtfDeadline.getText();

        //TODO: Daten zwischen ProjectCreateController und ProjectScreenController über das Model verbinden

        ProjectScreen.createProjectElement();

        ProjectScreen ps = new ProjectScreen(Programm.mainStage);
        ps.initForm();
        ps.showProjectScreen();

    }
}
