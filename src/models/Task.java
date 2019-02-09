package models;

import programm.Programm;

import java.util.ArrayList;

public class Task {

    private Integer _intTaskId;
    private String _strTaskName;
    private String _strTaskDescription;
    private String _strTaskCatgory;
    private String _strTaskStatus;
    private Integer _intProjectId;
    private ArrayList<Employee> _alTaskEmployees;




    public Task(String strTaskName, String strTaskDescription, String strTaskStatus, ArrayList<Employee> alTaskEmployees, Integer intProjectId) {
        //Konstruktor zum erstellen eines Task aus Nutzereingaben
        this._strTaskName = strTaskName;
        this._strTaskDescription = strTaskDescription;
        this._strTaskCatgory = strTaskStatus;
        this._alTaskEmployees = alTaskEmployees;
        this._intProjectId = intProjectId;
        this._strTaskStatus = Programm.enmTaskStatus.ToDo.toString();

        Programm.dbAgent.InsertTaskIntoDatabase(this);
    }

    public Task(Integer intTaskId, String strTaskName, String strTaskDescription, String strTaskStatus, ArrayList<Employee> alTaskEmployees, Integer intProjectId) {
        //Konstruktor zum erstellen eines Task aus Datenbank
        this._intTaskId = intTaskId;
        this._strTaskName = strTaskName;
        this._strTaskDescription = strTaskDescription;
        this._strTaskCatgory = strTaskStatus;
        this._alTaskEmployees = alTaskEmployees;
        this._intProjectId = intProjectId;
    }

    public Task(String strTaskName, String strTaskDescription, String strTaskStatus, ArrayList<Employee> alTaskEmployees) {
        //Nur zum Debuggen
        this._strTaskName = strTaskName;
        this._strTaskDescription = strTaskDescription;
        this._strTaskCatgory = strTaskStatus;
        this._alTaskEmployees = alTaskEmployees;
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

    public String get_strTaskCatgory() {
        return _strTaskCatgory;
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

    public void set_strTaskCatgory(String _strTaskCatgory) {
        this._strTaskCatgory = _strTaskCatgory;
    }

    public void set_alTaskEmployees(ArrayList<Employee> _alTaskEmployees) {
        this._alTaskEmployees = _alTaskEmployees;
    }

    public void set_intProjectId(Integer _intProjectId) {
        this._intProjectId = _intProjectId;
    }
}
