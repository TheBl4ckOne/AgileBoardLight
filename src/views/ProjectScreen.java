package views;

import controller.ProjectScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    public static MenuButton mbtnProjectOptions;

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
        vbProjectBox.setStyle
                (
                    "-fx-border-color: black;"
                    + "-fx-max-height: 20em;"
                    + "-fx-min-height: 20em;"
                    + "-fx-min-width: 30.8em;"
                    + "-fx-max-width: 30.8em;"
                    + "-fx-background-color: #FFFFFF;"
                );

        vbProjectBox.getStyleClass().add("project-element-section");

        Label lblProjectname = new Label(project.get_strProjectName());
        lblProjectname.getStyleClass().add("project-element-section");
        lblProjectname.setStyle
                (
                        "-fx-background-color: #CFD8DC;"
                        + "-fx-min-width: 27em;"
                );

        MenuItem miChange = new MenuItem("ändern");
        miChange.setOnAction(ProjectScreenController::handleChangeProject);

        MenuItem miDelete = new MenuItem("löschen");
        miDelete.setOnAction(ProjectScreenController::handleDeleteProject);

        mbtnProjectOptions = new MenuButton("...", null, miChange, miDelete);
        mbtnProjectOptions.getStyleClass().add("project-element-section");

        Label lblProjectDescription = new Label(project.get_strProjectDescription());
        lblProjectDescription.setWrapText(true);
        lblProjectDescription.setStyle
                (
                    "-fx-min-width: 29.8em;"
                    + "-fx-max-width: 29.8em;"
                    + "-fx-min-height: 17em;"
                    + "-fx-max-height: 17em;"
                    + "-fx-alignment: top-left;"
                    + "-fx-content-display: left;"
                    + "-fx-padding: 0 0 0 10;"
                );

        HBox hbProjectHead = new HBox();
        hbProjectHead.getStyleClass().add("header-section");

        hbProjectHead.getChildren().addAll(lblProjectname, mbtnProjectOptions);
        vbProjectBox.getChildren().addAll(hbProjectHead, lblProjectDescription);

       psc.gpProjectScreen.add(vbProjectBox, calcColIndex(index), calcRowIndex(index));
    }

    private int calcColIndex(int index){
        return index%2;
    }

    private int calcRowIndex(int index){
       return index/2;
    }
}
