package programm;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import views.ProjectScreen;
import java.awt.event.ActionEvent;
import java.util.Optional;

import static javafx.scene.control.ButtonBar.ButtonData.CANCEL_CLOSE;

public class Programm extends Application{
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


        //TODO: Problem lösen, dass beim Drücken auf Nein trotzdem die Stage geschlossen wird, Vermutung: wenn es einmal auf closeRequest gesetzt ist, wird es auch geschlossen

        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {


                Alert closeWindowAlert = new Alert(Alert.AlertType.WARNING);
                closeWindowAlert.setTitle("Programm schließen?");
                closeWindowAlert.setHeaderText("Möchten Sie das Programm wirklich schließen? Alle nicht gespeicherten Änderungen gehen verloren.");
                closeWindowAlert.setContentText("Sind Sie damit einverstanden?");

                ButtonType btnJa = new ButtonType("JA", ButtonBar.ButtonData.CANCEL_CLOSE);
                ButtonType btnNein = new ButtonType("NEIN");

                closeWindowAlert.getButtonTypes().setAll(btnJa,btnNein);


                Optional<ButtonType> result = closeWindowAlert.showAndWait();
                if (result.get() == btnJa) {
                    System.out.println("Ich habs hierher geschafft");
                    mainStage.close();
                }

                else {
                    event.consume();
                }

            }


        });

    //public void handleCloseRequest(ActionEvent actionEvent){

    //}

        ps.showProjectScreen();

    }
}
