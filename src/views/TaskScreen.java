package views;

import controller.TaskScreenController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Employee;
import models.Task;
import programm.Programm;

import java.io.IOException;

public class TaskScreen {
    private Parent _parent;
    private Stage _mainStage;
    private Scene _scene;
    private FXMLLoader _loader;
    private TaskScreenController _tsc;

    public TaskScreen(Stage stage, int intCurrProject) {
        _mainStage = stage;

        try {
            _loader = new FXMLLoader(getClass().getResource("TaskScreenDisplay.fxml"));
            _parent = _loader.load();
            _tsc = _loader.getController();
            _tsc.set_intCurrentProjectIndex(intCurrProject);
            _tsc.set_ts(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initForm() {
        _scene = new Scene(_parent, Programm.width, Programm.height);
        _mainStage.setScene(_scene);

        _scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        String strProjectName = Programm.projects.get(_tsc.get_intCurrentProjectIndex()).get_strProjectName();
        _tsc.lblProjectname.setText(strProjectName);

        VBox vbToDo = new VBox();
        vbToDo.getChildren().add(new Label("Noch zu machen"));
        vbToDo.getStyleClass().add("task-section");
        VBox vbAtWork = new VBox();
        vbAtWork.getChildren().add(new Label("In Arbeit"));
        vbAtWork.getStyleClass().add("task-section");
        VBox vbReady = new VBox();
        vbReady.getChildren().add(new Label("Fertig"));
        vbReady.getStyleClass().add("task-section");

        GridPane gp = (GridPane) _parent.lookup("#gpTaskCategories");

        Button btnAddTask = new Button("+");
        btnAddTask.getStyleClass().add("task-section");
        btnAddTask.setOnAction(_tsc::handleTaskCreate);
        gp.add(btnAddTask, 0,1);

        gp.add(vbToDo, 0, 0);
        gp.add(vbAtWork, 1, 0);
        gp.add(vbReady, 2, 0);


        for (Task t : Programm.projects.get(_tsc.get_intCurrentProjectIndex()).get_tasks()) {
            createTaskElement(t,Programm.projects.get(_tsc.get_intCurrentProjectIndex()).get_tasks().indexOf(t));
        }

    }

    private void createTaskElement(Task task, int index){

        VBox vbTaskElement = new VBox();
        vbTaskElement.getStyleClass().add("task-element");
        vbTaskElement.setId(Integer.toString(index));
        vbTaskElement.setUserData(task);

        HBox hbTaskInfo = new HBox();
        hbTaskInfo.getStyleClass().add("task-element-hbTaskinfo");

        ListView<String> lvEmployees = new ListView<>();
        for (Employee e :task.get_alTaskEmployees()) {
            lvEmployees.getItems().add(e.get_strEmployeeName());
        }
        lvEmployees.getStyleClass().add("task-element-lvEmployees");

        MenuItem miUp = new MenuItem(">");
        miUp.setOnAction(_tsc::handleUpTask);
        miUp.setUserData(vbTaskElement);

        MenuItem miDown = new MenuItem("<");
        miDown.setOnAction(_tsc::handleDownTask);
        miDown.setUserData(vbTaskElement);


        MenuItem miChange = new MenuItem("ändern");
        miChange.setOnAction(_tsc::handleChangeTask);
        miChange.setUserData(vbTaskElement);


        MenuItem miDelete = new MenuItem("löschen");
        miDelete.setOnAction(_tsc::handleDeleteTask);
        miDelete.setUserData(vbTaskElement);

        MenuButton mbtnTaskOptions = new MenuButton("...", null, miUp, miDown, miChange, miDelete);
        mbtnTaskOptions.getStyleClass().add("task-element-section");

        Label lblTaskName = new Label(task.get_strTaskName());
        lblTaskName.getStyleClass().add("task-element-section");
        lblTaskName.setOnMouseClicked(_tsc::handleOpenTask);
        lblTaskName.setUserData(vbTaskElement);


        hbTaskInfo.getChildren().addAll(lblTaskName,mbtnTaskOptions);

        vbTaskElement.getChildren().addAll(hbTaskInfo,lvEmployees);
        drawTaskElement(vbTaskElement);
    }

    private void drawTaskElement(VBox vbTaskElement){
        Task currentTask = (Task) vbTaskElement.getUserData();
        String strStatus = currentTask.get_strTaskStatus();

        switch (strStatus) {
            case "ToDo":
                _tsc.vbToDo.getChildren().add(vbTaskElement);
                break;
            case "InProgress":
                _tsc.vbInProgress.getChildren().add(vbTaskElement);
                break;
            case "Done":
                _tsc.vbDone.getChildren().add(vbTaskElement);
                break;
        }
    }

    public void updateTaskElement(VBox vbCurrentTaskElement){
        removeTaskElement(vbCurrentTaskElement);
        drawTaskElement(vbCurrentTaskElement);
    }

    public void removeTaskElement(VBox vbCurrentTaskElement){
        VBox vBox = (VBox) vbCurrentTaskElement.getParent();
        vBox.getChildren().remove(vbCurrentTaskElement);
    }

    public void showTaskScreen() {
        _mainStage.show();
    }
}


