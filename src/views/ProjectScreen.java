package views;


import controller.ProjectScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Project;
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
        for (Project p: Programm.projects) {
            createProjectElement(p);
        }
    }

    public void showProjectScreen() {
        _mainStage.show();
    }


    public  void createProjectElement(Project project) { // angezeigte Projektboxen auf der Startseite

        VBox vbProjectBox = new VBox(10);
        Label lblProjectname = new Label(project.get_strProjectName());
        Button btnProjectOptions = new Button("...");
        btnProjectOptions.setOnAction(ProjectScreenController::handleProjectOptions);
        Text txtProjectDescription = new Text(project.get_strProjectDescription());
        HBox hbProjectHead = new HBox();

        hbProjectHead.getChildren().addAll(lblProjectname, btnProjectOptions);
        vbProjectBox.getChildren().addAll(hbProjectHead, txtProjectDescription);

        GridPane gp = (GridPane) _parent.lookup("#gpProjectScreen");
        gp.add(vbProjectBox,0,0);
    }
}
