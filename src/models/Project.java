package models;

import controller.ProjectCreateController;

import java.util.ArrayList;
import java.util.Date;

public class Project {

    private String _strProjectId;
    private String _strProjectName;
    private String _strProjectDescription;
    private Date _dtdeadline;
    private ArrayList<Employee> _employees;

    public Project() {
        //TODO: strProjectname hierher übergeben???
    }
}
