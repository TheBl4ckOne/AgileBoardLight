package views;


import controller.ProjectScreenController;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
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

    public static Button btnProjectOptions;

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

        _scene = new Scene(_parent, Programm.width, Programm.height);
        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        for (Project p : Programm.projects) {
            createProjectElement(p);
        }
    }

    public void showProjectScreen() {
        _mainStage.show();
    }


    public void createProjectElement(Project project) { // angezeigte Projektelemente auf der Startseite

        //TODO: Abfragen ob bereits Content vorhanden ist in den anderen Zellen?

        GridPane gp = (GridPane) _parent.lookup("#gpProjectScreen");

        boolean bCellFilled = true; //false für leer, true für voll
        boolean colrow = false; //false für col erhöhen, true für row erhöhen
        int col = 0;
        int row = 0;

        while (bCellFilled = true) {

            Node result = null;
            ObservableList<Node> childrens = gp.getChildren();

            for (Node node : childrens) {
                if (gp.getRowIndex(node) == row && gp.getColumnIndex(node) == col) {
                    result = node;

                    VBox vbProjectBox = new VBox(10);
                    vbProjectBox.setOnMouseClicked(ProjectScreenController::handleProject);

                    Label lblProjectname = new Label(project.get_strProjectName());
                    btnProjectOptions = new Button("...");
                    btnProjectOptions.setOnAction(ProjectScreenController::handleProjectOptions);
                    TextArea txtaProjectDescription = new TextArea(project.get_strProjectDescription());
                    HBox hbProjectHead = new HBox();

                    hbProjectHead.getChildren().addAll(lblProjectname, btnProjectOptions);
                    vbProjectBox.getChildren().addAll(hbProjectHead, txtaProjectDescription);

                    gp.add(vbProjectBox, col, row);

                    break;


                } else {

                    if (colrow == false) {
                        col = col + 1;
                    } else {
                        row = row + 1;
                    }

                }
            }
        }


    }

}
