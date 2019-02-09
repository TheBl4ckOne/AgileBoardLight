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
        try {
            _myConnection = DriverManager.getConnection(_url, _user, _password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize(){
        try {
            _myConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Projects
    public void SelectAllProjects() {
        try {
            //Rücksetzen des lokalen Projektspeichers
            Programm.projects.clear();

            Statement statement = _myConnection.createStatement();

            String strProjectQuery = "SELECT * FROM projects";
            ResultSet rsProjects = statement.executeQuery(strProjectQuery);
            while (rsProjects.next()) {
                String strProjectId = rsProjects.getString("projectsId");
                String strProjectName = rsProjects.getString("projectName");
                String strProjectDeadline = rsProjects.getString("projectDeadline");
                String strProjectDescription = rsProjects.getString("projectDescription");
                LocalDate ldtProjectdeadline = LocalDate.parse(strProjectDeadline);
                //Achtung die Arraylists für Employees und Tasks werden nicht zwischengespeichert
                //Sie stehen als Funktionsaufruf mit entsprechendem Rückgabewert im Konstruktor

                Project p = new Project(strProjectId, strProjectName, strProjectDescription, ldtProjectdeadline, SelectEmployeesOfProject(strProjectId), SelectTasksOfProject(strProjectId));
                Programm.projects.add(p);
            }
            rsProjects.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void InsertProjectIntoDatabase(Project project){
        try {

            String strIntoProject = "INSERT INTO projects (projectName, projectDescription,projectDeadline) VALUES(?,?,?)";

            PreparedStatement prepStatementProject = _myConnection.prepareStatement(strIntoProject);
            prepStatementProject.setString(1,project.get_strProjectName());
            prepStatementProject.setString(2,project.get_strProjectDescription());
            prepStatementProject.setString(3,project.get_ldtDeadline().toString());
            prepStatementProject.execute();

            String strSelectLatestProject = "SELECT MAX(projectsId) FROM projects";
            Statement staLatestProject = _myConnection.createStatement();
            ResultSet rsLatestProject = staLatestProject.executeQuery(strSelectLatestProject);
            while (rsLatestProject.next()){
                for (Employee e : project.get_employees()) {
                    e.set_intProjectId(rsLatestProject.getInt("MAX(projectsId)"));
                    InsertEmployeeIntoDatabase(e);
                }
            }

            } catch (SQLException e) {

            e.printStackTrace();
        }
        //Neuladen des lokalen Projektspeichers inkl. des neuen Projektes
        SelectAllProjects();
    }

    //Employees
    public void InsertEmployeeIntoDatabase(Employee employee){
        try {

            String strIntoEployees = "INSERT INTO employees (employeeName, projectId) VALUES (?,?)";

            PreparedStatement prepStatementEmployees = _myConnection.prepareStatement(strIntoEployees);
            prepStatementEmployees.setString(1,employee.get_strEmployeeName());
            prepStatementEmployees.setInt(2,employee.get_intProjectId());
            prepStatementEmployees.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> SelectEmployeesOfProject(String projectId){
        ArrayList<Employee> alEmployees = new ArrayList<>();

        try {
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

        }catch (Exception e){
            e.printStackTrace();
        }

        return alEmployees;
    }

    //Tasks
    public void InsertTaskIntoDatabase(Task task){
        try {
            String strIntoTasks = "INSERT INTO tasks (taskName, taskDescription, taskStatus, projectID) VALUES (?,?,?,?)";

            PreparedStatement prepStatementTask = _myConnection.prepareStatement(strIntoTasks);
            prepStatementTask.setString(1,task.get_strTaskName());
            prepStatementTask.setString(2,task.get_strTaskDescription());
            prepStatementTask.setString(3,task.get_strTaskCatgory());
            prepStatementTask.setInt(4,task.get_intProjectId());
            prepStatementTask.execute();

            for (Employee e : task.get_alTaskEmployees()) {
                InsertEmployeesInTasks(e.get_intEmployeeId());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        //Stellt sicher, das der neue Task aus der Datenbank in den lokalen Speicher geladen wird
        SelectAllProjects();
    }

    public ArrayList<Task> SelectTasksOfProject(String projectId){
        ArrayList<Task> alTasks = new ArrayList<>();
        try {

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

                Task t = new Task(intTaskId,strTaskName,strTaskDescription,strTaskStatus, SelectEmployeesInTask(intTaskId),intProjectId);
                alTasks.add(t);
            }
        rsTasks.close();

        } catch (Exception e ){
            e.printStackTrace();
        }
        return alTasks;
    }

    private void InsertEmployeesInTasks(int intEmployeeId){
        try {
            String strSelectLatestTask = "SELECT MAX(taskid) FROM tasks";
            Statement staLatestTask = _myConnection.createStatement();
            ResultSet rsLatestProject = staLatestTask.executeQuery(strSelectLatestTask);
            int intTaskId = 0;
            while (rsLatestProject.next()){
                intTaskId = rsLatestProject.getInt("MAX(taskid)");
            }

           String strIntoEmployeesInTasks = "INSERT INTO employees_in_tasks (taskid, employeeId) VALUES (?,?)";
           PreparedStatement prepIntoEmployeesInTasks = _myConnection.prepareStatement(strIntoEmployeesInTasks);
           prepIntoEmployeesInTasks.setInt(1,intTaskId);
           prepIntoEmployeesInTasks.setInt(2,intEmployeeId);
           prepIntoEmployeesInTasks.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> SelectEmployeesInTask(int taskId){
        ArrayList<Employee> alEmployeesInTask = new ArrayList<>();

        try {
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