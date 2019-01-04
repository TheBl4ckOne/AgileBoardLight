package views;

import controller.ProjectScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.Project;
import programm.Programm;

import java.io.IOException;

public class ProjectScreen {

    private static Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;
    private ProjectScreenController psc;

    public static Button btnProjectOptions;

    public ProjectScreen(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("ProjectScreenDisplay.fxml"));
            _parent = _loader.load();
            psc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initForm() {

        _scene = new Scene(_parent, Programm.width, Programm.height);
        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        for (Project p : Programm.projects) {
            createProjectElement(p,Programm.projects.indexOf(p));
        }
    }

    public void showProjectScreen() {
        _mainStage.show();
    }


    private void createProjectElement(Project project, int index) { // angezeigte Projektelemente auf der Startseite



        VBox vbProjectBox = new VBox();
        vbProjectBox.setOnMouseClicked(ProjectScreenController::handleProject);
       vbProjectBox.setStyle("-fx-border-color: black");
        vbProjectBox.getStyleClass().add("project-element-section");

        Label lblProjectname = new Label(project.get_strProjectName());
        lblProjectname.getStyleClass().add("project-element-section");
        btnProjectOptions = new Button("...");
        btnProjectOptions.getStyleClass().add("project-element-section");
        btnProjectOptions.setOnAction(ProjectScreenController::handleProjectOptions);
        TextArea txtaProjectDescription = new TextArea(project.get_strProjectDescription());

        HBox hbProjectHead = new HBox();
        hbProjectHead.getStyleClass().add("project-element-section");

        hbProjectHead.getChildren().addAll(lblProjectname, btnProjectOptions);
        vbProjectBox.getChildren().addAll(hbProjectHead, txtaProjectDescription);

       psc.gpProjectScreen.add(vbProjectBox, calcColIndex(index), calcRowIndex(index));
    }

    private int calcColIndex(int index){
        return index%2;
    }

    private int calcRowIndex(int index){
       return index/2;
    }
}
