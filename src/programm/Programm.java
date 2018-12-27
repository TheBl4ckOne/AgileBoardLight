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

        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                Alert closeWindowAlert = new Alert(Alert.AlertType.WARNING);
                closeWindowAlert.setTitle("Programm schließen?");
                closeWindowAlert.setHeaderText("Möchten Sie das Programm wirklich schließen? Alle nicht gespeicherten Änderungen gehen verloren.");
                closeWindowAlert.setContentText("Sind Sie damit einverstanden?");

                //TODO: Button Nein auf die linke Seite
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
