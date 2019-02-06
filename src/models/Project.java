package models;

import programm.Programm;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project {

    private String _strProjectId;
    private String _strProjectName;
    public String _strProjectDescription;
    private LocalDate _ldtDeadline;
    private ArrayList<Employee> _employees;
    private ArrayList<Task> _tasks;

    //Konstruktor mit allen vom Nutzer bei der erstellung eingegeben Daten
    //ACHTUNG erstellen eines Projektobjektes OHNE ProjektID führt zu einem INSERT in die Datenbank, da diese die ID vergibt
    public  Project(String strProjectName, String strProjectDescription, LocalDate ldtDeadline, ArrayList<Employee> alEmployees){
        _strProjectName = strProjectName;
        _strProjectDescription = strProjectDescription;
        _ldtDeadline = ldtDeadline;
        _employees = alEmployees;

        Programm.dbAgent.InsertProjectIntoDatabase(this);
    }

    //Konstruktor mit allen Parametern wenn aus der DB geladen wird
    public Project(String strProjectId, String strProjectName, String strProjectDescription, LocalDate ldtDeadline, ArrayList<Employee> employees, ArrayList<Task> tasks) {
        this._strProjectId = strProjectId;
        this._strProjectName = strProjectName;
        this._strProjectDescription = strProjectDescription;
        this._ldtDeadline = ldtDeadline;
        this._employees = employees;
        this._tasks = tasks;
    }

    public Project(String strProjectId, String strProjectName, String strProjectDescription, LocalDate ldtDeadline){
        //Nur für Testzwecke
        this._strProjectId = strProjectId;
        this._strProjectName = strProjectName;
        this._strProjectDescription = strProjectDescription;
        this._ldtDeadline = ldtDeadline;
    }

    public ArrayList<Task> get_tasks() {
        return _tasks;
    }

    public void set_tasks(ArrayList<Task> _tasks) {
        this._tasks = _tasks;
    }



    //Get Methoden
    public String get_strProjectId() {
        return _strProjectId;
    }

    public String get_strProjectName() {
        return _strProjectName;
    }

    public String get_strProjectDescription() {
        return _strProjectDescription;
    }

    public LocalDate get_ldtDeadline() {
        return _ldtDeadline;
    }

    public ArrayList<Employee> get_employees() {
        return _employees;
    }

    //Set-Methoden
    public void set_strProjectId(String _strProjectId) {
        this._strProjectId = _strProjectId;
    }

    public void set_strProjectName(String _strProjectName) {
        this._strProjectName = _strProjectName;
    }

    public void set_strProjectDescription(String _strProjectDescription) {
        this._strProjectDescription = _strProjectDescription;
    }

    public void set_ldtDeadline(LocalDate _ldtDeadline) {
        this._ldtDeadline = _ldtDeadline;
    }

    public void set_employees(ArrayList<Employee> _employees) {
        this._employees = _employees;
    }
}
