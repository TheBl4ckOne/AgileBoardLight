package programm;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.DatabaseAgent;
import models.Project;
import models.Task;
import views.ProjectScreen;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class Programm extends Application{
    public static Stage mainStage;
    public static ArrayList<Project> projects = new ArrayList<>();
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static DatabaseAgent dbAgent = new DatabaseAgent();
    public static int width;
    public static int height;

    public enum enmTaskStatus{
        ToDo, InProgress, Done
    }

    public static void main(String[] args) {
        launch(args);

    }
    //Hauptklasse in der die main ist und von der aus alles andere gestartet wird

    public void start(Stage primaryStage){
        mainStage = primaryStage;
        final boolean resizable = mainStage.isResizable();
        mainStage.setResizable(!resizable); //Blockiert die Funktion des Maximieren-Buttons
        ProjectScreen ps = new ProjectScreen(mainStage);
        primaryStage.setTitle("Agile Board Light");

        width = 1400;
        height = 800;

        dbAgent.getAllProjects();

        ps.initForm();


        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                Alert closeWindowAlert = new Alert(Alert.AlertType.WARNING);
                closeWindowAlert.setTitle("Programm schließen?");
                closeWindowAlert.setHeaderText("Möchten Sie das Programm wirklich schließen? Alle nicht gespeicherten Änderungen gehen verloren.");
                closeWindowAlert.setContentText("Sind Sie damit einverstanden?");

                ButtonType btnNein = new ButtonType("NEIN", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType btnJa = new ButtonType("JA");

                closeWindowAlert.getButtonTypes().setAll(btnNein, btnJa);

                Optional<ButtonType> result = closeWindowAlert.showAndWait();
                if (result.get() == btnJa) {
                    mainStage.close();
                }

                else {
                    event.consume();
                }
            }
        });


        ps.showProjectScreen();
    }

}
