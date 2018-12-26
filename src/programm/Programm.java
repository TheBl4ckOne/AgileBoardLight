package programm;

import javafx.application.Application;
import javafx.stage.Stage;
import views.ProjectCreate;
import views.ProjectScreen;

public class Programm extends Application {
    //Stage global verfügbar machen um die angeziegt Scene wechseln zu können
    public static Stage mainStage;

    public static void main(String[] args) {
        launch(args);
    }
//Hauptklasse in der die main ist und von der aus alles andere gestartet wird

    public void start(Stage primaryStage){
        mainStage = primaryStage;
        ProjectScreen ps = new ProjectScreen(mainStage);
        ps.initForm();
        ps.showProjectScreen();

    }

}
