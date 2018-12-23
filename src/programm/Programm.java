package programm;


import javafx.application.Application;
import javafx.stage.Stage;
import views.ProjectScreen;

public class Programm extends Application {
//Hauptklasse in der die main ist und von der aus alles andere gestartet wird

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        ProjectScreen projectScreen = new ProjectScreen(primaryStage);
        projectScreen.initForm();
        projectScreen.showProjectScreen();
    }
}
