package models;

import programm.Programm;

import java.util.ArrayList;

import static programm.Programm.enmTaskStatus.*;

public class Task {

    private Integer _intTaskId;
    private String _strTaskName;
    private String _strTaskDescription;
    private String _strTaskStatus;
    private Integer _intProjectId;
    private ArrayList<Employee> _alTaskEmployees;

    //Konstuktoren
    public Task(String strTaskName, String strTaskDescription, String strTaskStatus, ArrayList<Employee> alTaskEmployees, Integer intProjectId) {
        //Konstruktor zum erstellen eines Task aus Nutzereingaben
        this._strTaskName = strTaskName;
        this._strTaskDescription = strTaskDescription;
        this._strTaskStatus = strTaskStatus;
        this._alTaskEmployees = alTaskEmployees;
        this._intProjectId = intProjectId;
        this._strTaskStatus = ToDo.toString();

        Programm.dbAgent.InsertTaskIntoDatabase(this);
    }

    public Task(Integer intTaskId, String strTaskName, String strTaskDescription, String strTaskStatus, ArrayList<Employee> alTaskEmployees, Integer intProjectId) {
        //Konstruktor zum erstellen eines Task aus Datenbank
        this._intTaskId = intTaskId;
        this._strTaskName = strTaskName;
        this._strTaskDescription = strTaskDescription;
        this._strTaskStatus = strTaskStatus;
        this._alTaskEmployees = alTaskEmployees;
        this._intProjectId = intProjectId;
    }

    public Task(String strTaskName, String strTaskDescription, String strTaskStatus, ArrayList<Employee> alTaskEmployees) {
        //Nur zum Debuggen
        this._strTaskName = strTaskName;
        this._strTaskDescription = strTaskDescription;
        this._strTaskStatus = strTaskStatus;
        this._alTaskEmployees = alTaskEmployees;
    }

    //Methoden
    public void upTask(){
        switch (this._strTaskStatus) {
            case "ToDo":
                this.set_strTaskStatus(InProgress.toString());
                break;
            case "InProgress":
                this.set_strTaskStatus(Done.toString());
                break;
            case "Done":
                break;
        }
        Programm.dbAgent.UpdateTask(this);
    }

    public  void downTask(){
        switch (this._strTaskStatus) {
            case "ToDo":
                break;
            case "InProgress":
                this.set_strTaskStatus(ToDo.toString());
                break;
            case "Done":
                this.set_strTaskStatus(InProgress.toString());
                break;
        }
        Programm.dbAgent.UpdateTask(this);
    }

    public void deleteTask(){
        Programm.dbAgent.DeleteTask(this);
    }

    //Get-Methoden
    public Integer get_intTaskId() {
        return _intTaskId;
    }

    public String get_strTaskName() {
        return _strTaskName;
    }

    public String get_strTaskDescription() {
        return _strTaskDescription;
    }

    public String get_strTaskStatus() {
        return _strTaskStatus;
    }

    public ArrayList<Employee> get_alTaskEmployees() {
        return _alTaskEmployees;
    }

    public Integer get_intProjectId() {
        return _intProjectId;
    }

    //Set-Methoden
    public void set_intTaskId(Integer _intTaskId) {
        this._intTaskId = _intTaskId;
    }

    public void set_strTaskName(String _strTaskName) {
        this._strTaskName = _strTaskName;
    }

    public void set_strTaskDescription(String _strTaskDescription) {
        this._strTaskDescription = _strTaskDescription;
    }

    public void set_strTaskStatus(String _strTaskStatus) {
        this._strTaskStatus = _strTaskStatus;
    }

    public void set_alTaskEmployees(ArrayList<Employee> _alTaskEmployees) {
        this._alTaskEmployees = _alTaskEmployees;
    }

    public void set_intProjectId(Integer _intProjectId) {
        this._intProjectId = _intProjectId;
    }
}
