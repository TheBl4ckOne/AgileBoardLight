package models;

import programm.Programm;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseAgent {

    private Connection _myConnection = null;
    private String _url = "jdbc:mysql://localhost:3306/agileboarddb?useUnicode=true&serverTimezone=CET";
    private String _driverClass = "com.mysql.cj.jdbc.Driver";
    private String _user = "root";
    private String _password = "P@ssw0rd";

    public DatabaseAgent() {
    }

    //Projects
    public void getAllProjects() {
        try {
            //Treiber Laden und Verbindung aufbauen
            _myConnection = DriverManager.getConnection(_url, _user, _password);
            Statement statement = _myConnection.createStatement();

            String strProjectQuery = "SELECT * FROM projects";
            ResultSet rsProjects = statement.executeQuery(strProjectQuery);
            while (rsProjects.next()) {
                String strProjctId = rsProjects.getString("projectsId");
                String strProjectName = rsProjects.getString("projectName");
                String strProjectDeadline = rsProjects.getString("projectDeadline");
                String strProjectDescription = rsProjects.getString("projectDescription");
                LocalDate ldtProjectdeadline = LocalDate.parse(strProjectDeadline);
                //Achtung die Arraylists für Employees und Tasks werden nicht zwischengespeichert
                //Sie stehen als Funktionsaufruf mit entsprechendem Rückgabewert im Konstruktor

                Project p = new Project(strProjctId, strProjectName, strProjectDescription, ldtProjectdeadline,getEmployees(strProjctId), getTasks(strProjctId));
                Programm.projects.add(p);
            }
            rsProjects.close();
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

            _myConnection.close();
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    //Employees
    public void saveEmployeeToDatabase(Employee employee){
        try {
            //Treiber Laden und Verbindung aufbauen
            _myConnection = DriverManager.getConnection(_url, _user, _password);

            String strIntoEployees = "INSERT INTO employees (employeeName, projectId) VALUES (?,?)";

            PreparedStatement prepStatementEmployees = _myConnection.prepareStatement(strIntoEployees);
            prepStatementEmployees.setString(1,employee.get_strEmployeeName());
            prepStatementEmployees.setInt(2,employee.get_intProjectId());

            _myConnection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployees (String projectId){
        ArrayList<Employee> alEmployees = new ArrayList<>();

        try {
            _myConnection = DriverManager.getConnection(_url, _user, _password);
            String strSelectEmployees ="SELECT *  FROM employees WHERE projectId = ?";

            PreparedStatement prepStatementEmployees = _myConnection.prepareStatement(strSelectEmployees);
            prepStatementEmployees.setString(1,projectId);

            ResultSet rsEmployees = prepStatementEmployees.executeQuery();
            while (rsEmployees.next()){
                int intEmployeeId = rsEmployees.getInt("employeeId");
                String strEmployeeName = rsEmployees.getString("employeeName");
                int intProjectId = rsEmployees.getInt("projectId");

                alEmployees.add(new Employee(strEmployeeName,intProjectId,intEmployeeId));
            }

            rsEmployees.close();
            _myConnection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return alEmployees;
    }

    //Tasks
    public void saveTaskToDatabase(Task task){
        try {
            //Treiber Laden und Verbindung aufbauen
            _myConnection = DriverManager.getConnection(_url, _user, _password);

            String strIntoTasks = "INSERT INTO tasks (taskName, taskDescription, taskStatus, projectID) VALUES (?,?,?,?)";

            PreparedStatement prepStatementTask = _myConnection.prepareStatement(strIntoTasks);
            prepStatementTask.setString(1,task.get_strTaskName());
            prepStatementTask.setString(2,task.get_strTaskDescription());
            prepStatementTask.setString(3,task.get_strTaskCatgory());
            prepStatementTask.setInt(4,task.get_intProjectId());
            prepStatementTask.execute();

            _myConnection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Task> getTasks(String projectId){
        ArrayList<Task> alTasks = new ArrayList<>();
        try {
            _myConnection = DriverManager.getConnection(_url, _user, _password);
            String strSelectTasks = "SELECT * FROM tasks WHERE projectID = ?";
            PreparedStatement prepStatementTask = _myConnection.prepareStatement(strSelectTasks);
            prepStatementTask.setString(1,projectId);

            ResultSet rsTasks = prepStatementTask.executeQuery();

            while (rsTasks.next()){
                int intTaskId = rsTasks.getInt("taskid");
                String strTaskName = rsTasks.getString("taskName");
                String strTaskDescription = rsTasks.getString("taskDescription");
                String strTaskStatus = rsTasks.getString("taskStatus");
                int intProjectId = rsTasks.getInt("projectID");

                Task t = new Task(intTaskId,strTaskName,strTaskDescription,strTaskStatus,getEmployeesInTask(intTaskId),intProjectId);
                alTasks.add(t);

                rsTasks.close();
                _myConnection.close();
            }
        } catch (Exception e ){
            e.printStackTrace();
        }
        return alTasks;
    }

    public ArrayList<Employee> getEmployeesInTask (int taskId){
        ArrayList<Employee> alEmployeesInTask = new ArrayList<>();

        try {
            _myConnection = DriverManager.getConnection(_url, _user, _password);
            String strSelectEmployeesInTasks = "SELECT * FROM employees e INNER JOIN employees_in_tasks eit on e.employeeId = eit.employeeId WHERE eit.taskid= ?";
            PreparedStatement prepEmployeesInTasks = _myConnection.prepareStatement(strSelectEmployeesInTasks);
            prepEmployeesInTasks.setInt(1,taskId);

            ResultSet rsEmployeesInTask = prepEmployeesInTasks.executeQuery();
            while (rsEmployeesInTask.next()){
                int intEmployeeId = rsEmployeesInTask.getInt("employeeId");
                String strEmployeeName = rsEmployeesInTask.getString("employeeName");
                int intProjectId = rsEmployeesInTask.getInt("projectId");
                Employee e = new Employee(strEmployeeName,intProjectId,intEmployeeId);
                alEmployeesInTask.add(e);
            }

        }catch (Exception e ){
            e.printStackTrace();
        }
        return  alEmployeesInTask;
    }

}