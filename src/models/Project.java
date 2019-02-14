package models;

import programm.Programm;

import java.time.LocalDate;
import java.util.ArrayList;

public class Project {

    private int _intProjectId;
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
    public Project(int intProjectId, String strProjectName, String strProjectDescription, LocalDate ldtDeadline, ArrayList<Employee> employees, ArrayList<Task> tasks) {
        this._intProjectId = intProjectId;
        this._strProjectName = strProjectName;
        this._strProjectDescription = strProjectDescription;
        this._ldtDeadline = ldtDeadline;
        this._employees = employees;
        this._tasks = tasks;
    }

    public Project(int intProjectId, String strProjectName, String strProjectDescription, LocalDate ldtDeadline){
        //Nur für Testzwecke
        this._intProjectId = intProjectId;
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
    public int get_intProjectId() {
        return _intProjectId;
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
    public void set_intProjectId(int intProjectId) {
        this._intProjectId = intProjectId;
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
