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
                int intProjectId = rsProjects.getInt("projectsId");
                String strProjectName = rsProjects.getString("projectName");
                String strProjectDeadline = rsProjects.getString("projectDeadline");
                String strProjectDescription = rsProjects.getString("projectDescription");
                LocalDate ldtProjectdeadline = LocalDate.parse(strProjectDeadline);
                //Achtung die Arraylists für Employees und Tasks werden nicht zwischengespeichert
                //Sie stehen als Funktionsaufruf mit entsprechendem Rückgabewert im Konstruktor

                Project p = new Project(intProjectId, strProjectName, strProjectDescription, ldtProjectdeadline, SelectEmployeesOfProject(intProjectId), SelectTasksOfProject(intProjectId));
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

            PreparedStatement prepStatementInsProject = _myConnection.prepareStatement(strIntoProject);
            prepStatementInsProject.setString(1,project.get_strProjectName());
            prepStatementInsProject.setString(2,project.get_strProjectDescription());
            prepStatementInsProject.setString(3,project.get_ldtDeadline().toString());
            prepStatementInsProject.execute();

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

    public void UpdateProject (Project project){
        //Für das Update werden alle Spalten erneuert, um Differenzermittlung zu vermeiden
            try {
                String strUpdateProject = "UPDATE projects " +
                                            "SET projectName = ?, projectDescription = ?, projectDeadline = ?" +
                                            "WHERE projectsId = ?";
                PreparedStatement prepStatementUpdProject = _myConnection.prepareStatement(strUpdateProject);
                prepStatementUpdProject.setString(1,project.get_strProjectName());
                prepStatementUpdProject.setString(2,project.get_strProjectDescription());
                prepStatementUpdProject.setString(3,project.get_ldtDeadline().toString());
                prepStatementUpdProject.setInt(4,project.get_intProjectId());
                prepStatementUpdProject.execute();

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    public void DeleteProject(Project project){
        try {
            //Um Inkonsistenz zu vermeiden müssen auch alle mti dem Projekt verknüpften Daten in der DB gelöscht werden
            for (Task t: project.get_tasks()) {
                DeleteTask(t);
            }

            for (Employee e:project.get_employees()) {
                DeleteEmployee(e);
            }

            String strDeleteProject ="DELETE FROM projects WHERE projectsId = ?";
            PreparedStatement prepStatemntDelProject = _myConnection.prepareStatement(strDeleteProject);
            prepStatemntDelProject.setInt(1,project.get_intProjectId());
            prepStatemntDelProject.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
        //Neuladen des lokalen Projektspeichers
        SelectAllProjects();
    }


    //Employees
    public void InsertEmployeeIntoDatabase(Employee employee){
        try {

            String strIntoEployees = "INSERT INTO employees (employeeName, projectId) VALUES (?,?)";

            PreparedStatement prepStatementInsEmployees = _myConnection.prepareStatement(strIntoEployees);
            prepStatementInsEmployees.setString(1,employee.get_strEmployeeName());
            prepStatementInsEmployees.setInt(2,employee.get_intProjectId());
            prepStatementInsEmployees.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private ArrayList<Employee> SelectEmployeesOfProject(int projectId){
        ArrayList<Employee> alEmployees = new ArrayList<>();

        try {
            String strSelectEmployees ="SELECT *  FROM employees WHERE projectId = ?";

            PreparedStatement prepStatementSelEmployees = _myConnection.prepareStatement(strSelectEmployees);
            prepStatementSelEmployees.setInt(1,projectId);

            ResultSet rsEmployees = prepStatementSelEmployees.executeQuery();
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

    public void UpdateEmployee (Employee employee){
        try {
            String strUpdEmployee = "UPDATE employees SET employeeName = ? WHERE employeeId = ?";
            PreparedStatement prepStatementUpdEmployee = _myConnection.prepareStatement(strUpdEmployee);
            prepStatementUpdEmployee.setString(1,employee.get_strEmployeeName());
            prepStatementUpdEmployee.setInt(2,employee.get_intEmployeeId());
            prepStatementUpdEmployee.execute();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DeleteEmployee (Employee employee){
        try {
            //Um Inkonsistenzen zu vermeiden müssen auch alle Zuordnungen des Mitarbeiters zu Aufgaben gelöscht werden
            DeleteEmployeeInTaskByEmployee(employee.get_intEmployeeId());

            String strDeleteEmployee ="DELETE FROM employees WHERE employeeId  = ?";
            PreparedStatement prepStatemntDelEmployee = _myConnection.prepareStatement(strDeleteEmployee);
            prepStatemntDelEmployee.setInt(1,employee.get_intEmployeeId());
            prepStatemntDelEmployee.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


    //Tasks
    public void InsertTaskIntoDatabase(Task task){
        try {
            String strIntoTasks = "INSERT INTO tasks (taskName, taskDescription, taskStatus, projectID) VALUES (?,?,?,?)";

            PreparedStatement prepStatementInsTask = _myConnection.prepareStatement(strIntoTasks);
            prepStatementInsTask.setString(1,task.get_strTaskName());
            prepStatementInsTask.setString(2,task.get_strTaskDescription());
            prepStatementInsTask.setString(3,task.get_strTaskStatus());
            prepStatementInsTask.setInt(4,task.get_intProjectId());
            prepStatementInsTask.execute();

            for (Employee e : task.get_alTaskEmployees()) {
                InsertEmployeesInTasks(e.get_intEmployeeId());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        //Stellt sicher, das der neue Task aus der Datenbank in den lokalen Speicher geladen wird
        SelectAllProjects();
    }

    public ArrayList<Task> SelectTasksOfProject(int projectId){
        ArrayList<Task> alTasks = new ArrayList<>();
        try {

            String strSelectTasks = "SELECT * FROM tasks WHERE projectID = ?";
            PreparedStatement prepStatementSelTask = _myConnection.prepareStatement(strSelectTasks);
            prepStatementSelTask.setInt(1,projectId);

            ResultSet rsTasks = prepStatementSelTask.executeQuery();

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

    public void UpdateTask (Task task){
        try{
            String strUpdTask = "UPDATE tasks SET taskName = ?, taskDescription = ?, taskStatus = ? WHERE taskid = ?";
            PreparedStatement prepStatementUpdTask = _myConnection.prepareStatement(strUpdTask);
            prepStatementUpdTask.setString(1,task.get_strTaskName());
            prepStatementUpdTask.setString(2,task.get_strTaskDescription());
            prepStatementUpdTask.setString(3,task.get_strTaskStatus());
            prepStatementUpdTask.setInt(4,task.get_intTaskId());
            prepStatementUpdTask.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DeleteTask(Task task){
        try {
            //Um Inkonsistenzen zu vermeiden müssen auch alle Zuordnungen des Mitarbeiters zu Aufgaben gelöscht werden
            DeleteEmployeeInTaskByTask(task.get_intTaskId());

            String strDeleteTask ="DELETE FROM tasks WHERE taskid = ?";
            PreparedStatement prepStatementDelTask = _myConnection.prepareStatement(strDeleteTask);
            prepStatementDelTask.setInt(1,task.get_intTaskId());
            prepStatementDelTask.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //EmployeesInTasks
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
           PreparedStatement prepInsEmployeesInTasks = _myConnection.prepareStatement(strIntoEmployeesInTasks);
           prepInsEmployeesInTasks.setInt(1,intTaskId);
           prepInsEmployeesInTasks.setInt(2,intEmployeeId);
           prepInsEmployeesInTasks.execute();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> SelectEmployeesInTask(int taskId){
        ArrayList<Employee> alEmployeesInTask = new ArrayList<>();

        try {
            String strSelectEmployeesInTasks = "SELECT * FROM employees e INNER JOIN employees_in_tasks eit on e.employeeId = eit.employeeId WHERE eit.taskid= ?";
            PreparedStatement prepSelEmployeesInTasks = _myConnection.prepareStatement(strSelectEmployeesInTasks);
            prepSelEmployeesInTasks.setInt(1,taskId);

            ResultSet rsEmployeesInTask = prepSelEmployeesInTasks.executeQuery();
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

    public void DeleteEmployeeInTaskByEmployee(int emplyeeId){
        try {
            String strDeleteEmployeeInTask ="DELETE FROM employees_in_tasks WHERE employeeId = ?";
            PreparedStatement prepStatementDelTask = _myConnection.prepareStatement(strDeleteEmployeeInTask);
            prepStatementDelTask.setInt(1,emplyeeId);
            prepStatementDelTask.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void DeleteEmployeeInTaskByTask (int taskId){
        try {
            String strDeleteEmployeeInTask ="DELETE FROM employees_in_tasks WHERE taskid = ?";
            PreparedStatement prepStatementDelTask = _myConnection.prepareStatement(strDeleteEmployeeInTask);
            prepStatementDelTask.setInt(1,taskId);
            prepStatementDelTask.execute();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}