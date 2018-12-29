package models;

import controller.ProjectCreateController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Project {

    private String _strProjectId;
    private String _strProjectName;
    private String _strProjectDescription;
    private LocalDate _ldtDeadline;
    private ArrayList<Employee> _employees;

    public Project() {
        //TODO: strProjectname hierher übergeben???
    }

    public  Project(String strProjectName, String strProjectDescription, LocalDate ldtDeadline, ArrayList<Employee> employees){
        _strProjectName = strProjectName;
        _strProjectDescription = strProjectDescription;
        _ldtDeadline = ldtDeadline;
        _employees = employees;
    }
}
