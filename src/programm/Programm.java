package programm;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.Project;
import models.Task;
import views.ProjectScreen;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;


public class Programm extends Application{
    public static Stage mainStage;
    public static ArrayList<Project> projects = new ArrayList<Project>();
    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static int width;
    public static int height;

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

        //databasetest();
        readFromDatabase();

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

    private void saveToDatabase(){
        Connection myConnection = null;
        String url = "jdbc:mysql://localhost:3306/agileboarddb?useUnicode=true&serverTimezone=CET";
        String driverClass = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "P@ssw0rd";
        try {
            //Treiber Laden und Verbindung aufbauen
            myConnection = DriverManager.getConnection(url,user,password);
            Statement statement = myConnection.createStatement();

            //Einfügen der Projekte aus

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readFromDatabase(){
        Connection myConnection = null;
        String url = "jdbc:mysql://localhost:3306/agileboarddb?useUnicode=true&serverTimezone=CET";
        String driverClass = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "P@ssw0rd";
        try {
            //Treiber Laden und Verbindung aufbauen
            myConnection = DriverManager.getConnection(url,user,password);
            Statement statement = myConnection.createStatement();

            String query = "SELECT * FROM projects";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String strProjctId = rs.getString("projectsId");
                String strProjectName = rs.getString("projectName");
                String strProjectDeadline = rs.getString("projectDeadline");
                String strProjectDescription = rs.getString("projectDescription");
                LocalDate ldtProjectdeadline = LocalDate.parse(strProjectDeadline);

                Project p = new Project(strProjctId,strProjectDeadline,strProjectDescription,ldtProjectdeadline);
                projects.add(p);
            }
            rs.close();
            myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void databasetest(){
        Connection myConnection = null;
        String url = "jdbc:mysql://localhost:3306/agileboarddb?useUnicode=true&serverTimezone=CET";
        String driverClass = "com.mysql.cj.jdbc.Driver";
        String user = "root";
        String password = "P@ssw0rd";

        try {
            //Treiber laden
            //Class.forName(driverClass);
            //Verbindun aufbauen

            myConnection = DriverManager.getConnection(url,user,password);
            System.out.println("Verbindung erfolgreich");

            //Abfrage
            String query = "SELECT * FROM project";
            Statement state = myConnection.createStatement();
            ResultSet rs = state.executeQuery(query);

            while (rs.next()){
                String pid = rs.getString("projectid");
                String name = rs.getString("projectname");
                System.out.println(pid + " --- " + name);
            }
            rs.close();
            myConnection.close();
        }catch (final Exception e){
            e.printStackTrace();
        }

    }
}
