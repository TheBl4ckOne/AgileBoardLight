package programm;

import javafx.application.Application;
import javafx.stage.Stage;
import views.ProjectScreen;

public class Programm extends Application {
    public static void main(String[] args) {
        launch(args);
    }
//Hauptklasse in der die main ist und von der aus alles andere gestartet wird

    public void start(Stage primaryStage){
        ProjectScreen ps = new ProjectScreen(primaryStage);
            ps.initForm();
            ps.showProjectScreen();
    }

}
