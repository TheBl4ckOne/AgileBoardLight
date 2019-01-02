package models;

import controller.ProjectCreateController;

import java.util.ArrayList;

public class Task {

    private String _strTaskId;
    private String _strTaskName;
    private String _strTaskDescription;
    private String _strTaskCatgory;
    private ArrayList<Employee> _alTaskEmploees;

    public Task() {

    }

    public Task(String strTaskName, String strTaskDescription, String strTaskCatgory, ArrayList<Employee> alTaskEmploees) {
        this._strTaskName = strTaskName;
        this._strTaskDescription = strTaskDescription;
        this._strTaskCatgory = strTaskCatgory;
        this._alTaskEmploees = alTaskEmploees;
    }

    public Task(String strTaskId, String strTaskName, String strTaskDescription, String strTaskCatgory, ArrayList<Employee> alTaskEmploees) {
        this._strTaskId = strTaskId;
        this._strTaskName = strTaskName;
        this._strTaskDescription = strTaskDescription;
        this._strTaskCatgory = strTaskCatgory;
        this._alTaskEmploees = alTaskEmploees;
    }

    //Get-Methoden
    public String get_strTaskId() {
        return _strTaskId;
    }

    public String get_strTaskName() {
        return _strTaskName;
    }

    public String get_strTaskDescription() {
        return _strTaskDescription;
    }

    public String get_strTaskCatgory() {
        return _strTaskCatgory;
    }

    public ArrayList<Employee> get_alTaskEmploees() {
        return _alTaskEmploees;
    }

    //Set-Methoden
    public void set_strTaskId(String _strTaskId) {
        this._strTaskId = _strTaskId;
    }

    public void set_strTaskName(String _strTaskName) {
        this._strTaskName = _strTaskName;
    }

    public void set_strTaskDescription(String _strTaskDescription) {
        this._strTaskDescription = _strTaskDescription;
    }

    public void set_strTaskCatgory(String _strTaskCatgory) {
        this._strTaskCatgory = _strTaskCatgory;
    }

    public void set_alTaskEmploees(ArrayList<Employee> _alTaskEmploees) {
        this._alTaskEmploees = _alTaskEmploees;
    }
}
