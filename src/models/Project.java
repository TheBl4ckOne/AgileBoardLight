package models;

import controller.ProjectCreateController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Project {

    private String _strProjectId;
    private String _strProjectName;
    public String _strProjectDescription;
    private LocalDate _ldtDeadline;
    private ArrayList<Employee> _employees;

    public  Project(String strProjectName, String strProjectDescription, LocalDate ldtDeadline, ArrayList<Employee> alEmployees){
        _strProjectName = strProjectName;
        _strProjectDescription = strProjectDescription;
        _ldtDeadline = ldtDeadline;
        _employees = alEmployees;
    }

    public Project(String strProjectId, String strProjectName, String strProjectDescription, LocalDate ldtDeadline, ArrayList<Employee> employees) {
        this._strProjectId = strProjectId;
        this._strProjectName = strProjectName;
        this._strProjectDescription = strProjectDescription;
        this._ldtDeadline = ldtDeadline;
        this._employees = employees;
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
