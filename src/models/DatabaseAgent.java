package models;

import programm.Programm;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseAgent {

    Connection _myConnection = null;
    String _url = "jdbc:mysql://localhost:3306/agileboarddb?useUnicode=true&serverTimezone=CET";
    String _driverClass = "com.mysql.cj.jdbc.Driver";
    String _user = "root";
    String _password = "P@ssw0rd";

    public DatabaseAgent() {
    }

    public void getAllProjects() {
        try {
            //Treiber Laden und Verbindung aufbauen
            _myConnection = DriverManager.getConnection(_url, _user, _password);
            Statement statement = _myConnection.createStatement();

            String query = "SELECT * FROM projects";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String strProjctId = rs.getString("projectsId");
                String strProjectName = rs.getString("projectName");
                String strProjectDeadline = rs.getString("projectDeadline");
                String strProjectDescription = rs.getString("projectDescription");
                LocalDate ldtProjectdeadline = LocalDate.parse(strProjectDeadline);

                Project p = new Project(strProjctId, strProjectName, strProjectDescription, ldtProjectdeadline);
                Programm.projects.add(p);
            }
            rs.close();
            _myConnection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveProjectToDatabase(Project project){
        try {
            //Treiber Laden und Verbindung aufbauen
            _myConnection = DriverManager.getConnection(_url,_user,_password);

            String strIntoProject = "INSERT INTO projects (projectName, projectDescription,projectDeadline) VALUES(?,?,?)";

            PreparedStatement prepStatementProject = _myConnection.prepareStatement(strIntoProject);
            prepStatementProject.setString(1,project.get_strProjectName());
            prepStatementProject.setString(2,project.get_strProjectDescription());
            prepStatementProject.setString(3,project.get_ldtDeadline().toString());
            prepStatementProject.execute();

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public void saveTaskToDatabase(Task task){
        try {
            //Treiber Laden und Verbindung aufbauen
            _myConnection = DriverManager.getConnection(_url, _user, _password);

            String strIntoTasks = "INSERT INTO tasks (taskName, taskDescription, taskStatus, projectID) VALUES (?,?,?,?)";

            PreparedStatement prepStatementTast = _myConnection.prepareStatement(strIntoTasks);
            prepStatementTast.setString(1,task.get_strTaskName());
            prepStatementTast.setString(2,task.get_strTaskDescription());
            prepStatementTast.setString(3,task.get_strTaskCatgory());
            prepStatementTast.setInt(4,task.get_intProjectId());

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void saveEmployeeToDatabase(Employee employee){
        try {
            //Treiber Laden und Verbindung aufbauen
            _myConnection = DriverManager.getConnection(_url, _user, _password);

            String strIntoEployees = "INSERT INTO employees (employeeName, projectId) VALUES (?,?)";

            PreparedStatement prepStatementEmployees = _myConnection.prepareStatement(strIntoEployees);
            prepStatementEmployees.setString(1,employee.get_strEmployeeName());
            prepStatementEmployees.setInt(2,employee.get_intProjectId());

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}


