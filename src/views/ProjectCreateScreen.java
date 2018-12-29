package views;

import controller.ProjectCreateController;
import controller.ProjectScreenController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

import static programm.Programm.mainStage;

public class ProjectCreateScreen {

    public static String strProjectname;
    public static String strProjectDescription;
    public static String strProjectTeam;
    public static String strDeadline;

    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;

    private Rectangle2D bounds = Screen.getPrimary().getBounds(); //Untere Windowstaskleiste wird dadurch sichtbar

    public ProjectCreateScreen(Stage stage) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("ProjectCreateDisplay.fxml"));
            _parent = _loader.load();
            ProjectCreateController pcc = _loader.getController();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initForm() {

        mainStage.setX(bounds.getMinX());
        mainStage.setY(bounds.getMinY());

        int width = (int) Screen.getPrimary().getBounds().getWidth();
        int height = (int) Screen.getPrimary().getBounds().getHeight();

        _scene = new Scene(_parent, width, height);
        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());
    }

    public void showProjectCreate() {
        _mainStage.show();
    }

}
