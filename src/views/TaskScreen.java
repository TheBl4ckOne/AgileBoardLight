package views;

import controller.TaskScreenController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import programm.Programm;

import java.io.IOException;

import static programm.Programm.mainStage;

public class TaskScreen {

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;

    @FXML
    GridPane gpTaskCategories;

    public TaskScreen(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("TaskScreenDisplay.fxml"));
            _parent = _loader.load();
            TaskScreenController tsc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }

        mainStage.setFullScreen(true);

    }

    public void initForm() {
        _scene = new Scene(_parent, Programm.width, Programm.height);
        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        VBox box1 = new VBox();
        box1.getChildren().add(new Label("Noch zu machen"));
        VBox box2 = new VBox();
        box2.getChildren().add(new Label("In Arbeit"));
        VBox box3 = new VBox();
        box3.getChildren().add(new Label("Fertig"));

        GridPane gp = (GridPane) _parent.lookup("#gpTaskCategories");

        gp.add(box1, 0, 0);
        gp.add(box2, 1, 0);
        gp.add(box3, 2, 0);

        for (int i = 0 ; i < 3 ; i++) {
            ColumnConstraints cc = new ColumnConstraints();

            cc.setPercentWidth(100.0/3.0); //TODO: jede Spalte soll ein drittel der gesamten Seitenbreite einnehmen
            cc.setHgrow(Priority.ALWAYS);
            gp.getColumnConstraints().add(cc);
        }

        RowConstraints rc = new RowConstraints();
        rc.setVgrow(Priority.ALWAYS);
        gp.getRowConstraints().add(rc);

        //TODO: neue Tasks werden der gp hinzugefügt mit column und row-Index



    }

    public void showTaskScreen() {
        _mainStage.show();
    }
}


