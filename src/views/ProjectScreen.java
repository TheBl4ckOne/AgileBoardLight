package views;


import controller.ProjectScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import programm.Programm;

import java.io.IOException;

public class ProjectScreen {

    private static Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;

    public ProjectScreen(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("ProjectScreenDisplay.fxml"));
            _parent = _loader.load();
            ProjectScreenController psc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initForm() {
        _scene = new Scene(_parent, 1200, 600);
        _mainStage.setScene(_scene);
    }

    public void showProjectScreen() {
        _mainStage.show();
    }


    public static void createProjectElement() { //TODO: sichtbar machen der Startseite (ProjectScreen) und Hinzufügen eines Dummy-Projektes (unbefüllt) + EventHandler auf vbProjectBox setzen

        VBox vbProjectBox = new VBox(10);
        Label lblProjectName = new Label("Projektname"); //TODO: mit der Eingabe verknüpfen
        Button btnProjectOptions = new Button("...");
        btnProjectOptions.setOnAction(ProjectScreenController::handleProjectOptions);
        Text txtProjectDescription = new Text();
        HBox hbProjectHead = new HBox();

        hbProjectHead.getChildren().addAll(lblProjectName, btnProjectOptions);
        vbProjectBox.getChildren().addAll(hbProjectHead, txtProjectDescription);

        _parent.lookup("gpProjectscreen");

        ProjectCreate pc = new ProjectCreate(Programm.mainStage);
        pc.initForm();
        pc.showProjectCreate();

    }

}
