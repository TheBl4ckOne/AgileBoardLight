package models;

import controller.ProjectCreateController;

import java.util.ArrayList;
import java.util.Date;

public class Project {

    private String _strProjectId;
    private String _strProjectName;
    private String _strProjectDescription;
    private Date _dtDeadline;
    private ArrayList<Employee> _employees;

    public Project() {
        //TODO: strProjectname hierher übergeben???
    }

    public  Project(String strProjectName, String strProjectDescription, Date dtDeadline, ArrayList<Employee> employees){
        _strProjectName = strProjectName;
        _strProjectDescription = strProjectDescription;
        _dtDeadline = dtDeadline;
        _employees = employees;
    }
}
