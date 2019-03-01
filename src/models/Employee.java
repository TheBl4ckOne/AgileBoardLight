package models;

public class Employee {

    private Integer _intEmployeeId;
    private String _strEmployeeName;
    private  Integer _intProjectId;

    public Employee(String strEmployeeName, Integer intProjectId){
        //Zum Erstellen eines Mitarbeiters wenn das Projekt schon vorhanden ist
        _strEmployeeName = strEmployeeName;
        _intProjectId = intProjectId;
    }

    public Employee(String strEmployeeName, Integer intProjectId, Integer intEmployeeId){
        //Zum Erstellen eines Mitarbeiters aus der Datenbank
        _intEmployeeId = intEmployeeId;
        _strEmployeeName = strEmployeeName;
        _intProjectId = intProjectId;
    }

    public Employee(String strEmployeeName) {
        //Zum Erstellen eines Employee Objekt wenn das zugehörige Projekt noch keine ID duch die DB erhalten hat
        this._strEmployeeName = strEmployeeName;
    }

    @Override
    public String toString(){
        return _strEmployeeName;
    }

    //Get-Methoden

    public Integer get_intProjectId() {
        return _intProjectId;
    }

    public Integer get_intEmployeeId() {
        return _intEmployeeId;
    }

    public String get_strEmployeeName() {
        return _strEmployeeName;
    }

    //Set-Methoden

    public void set_intEmployeeId(Integer _intEmployeeId) {
        this._intEmployeeId = _intEmployeeId;
    }

    public void set_strEmployeeName(String _strEmployeeName) {
        this._strEmployeeName = _strEmployeeName;
    }

    public void set_intProjectId(Integer _intProjectId) {
        this._intProjectId = _intProjectId;
    }
}
