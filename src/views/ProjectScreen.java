package views;

import controller.ProjectScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
            psc.setPs(this);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initForm() {

        _scene = new Scene(_parent, Programm.width, Programm.height);
        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        for (Project p : Programm.projects) {
            drawProjectElement(p,Programm.projects.indexOf(p));
        }

    }

    public void showProjectScreen() {
        _mainStage.show();
    }


    private void drawProjectElement(Project project, int index) { // angezeigte Projektelemente auf der Startseite

        VBox vbProjectBox = new VBox();
        //id der VBox ist gleich der projektid um Project-objekt in der Arraylist
        //Die ID der VB-Box ist der Index des Projektes im Project-Array. Dieser Index wird bei bedarf weitergeben
        vbProjectBox.setId(Integer.toString(index));
        vbProjectBox.setOnMouseClicked(ProjectScreenController::handleSelectProject);
        vbProjectBox.getStyleClass().add("project-element-section-vbProjectBox");
        vbProjectBox.setUserData(project);

        vbProjectBox.getStyleClass().add("project-element-section");

        Label lblProjectname = new Label(project.get_strProjectName());
        lblProjectname.getStyleClass().add("project-element-section-lblProjectname");

        //Wenn die MenuItems zu einem Menubutton gehören haben sie kein Earentelement
        //--> um die Verbindung zur VBox nicht zu verlieren wird diese als UserData übergeben
        MenuItem miChange = new MenuItem("ändern");
        miChange.setOnAction(ProjectScreenController::handleChangeProject);
        miChange.setUserData(vbProjectBox);

        MenuItem miDelete = new MenuItem("löschen");
        miDelete.setOnAction(ProjectScreenController::handleDeleteProject);
        miDelete.setUserData(vbProjectBox);

        mbtnProjectOptions = new MenuButton("...", null, miChange, miDelete);
        mbtnProjectOptions.getStyleClass().add("project-element-section");

        Label lblProjectDescription = new Label(project.get_strProjectDescription());
        lblProjectDescription.setWrapText(true);
        lblProjectDescription.getStyleClass().add("project-element-section-lblProjectDescription");

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

    public void removeProjectElement(VBox vbCurrentTaskElement){
        psc.gpProjectScreen.getChildren().remove(vbCurrentTaskElement);
    }
}
