package models;

public class Employee {

    private String _strEmployeeId;
    private String _strEmployeeName;

    public Employee() {

    }

    public Employee(String strEmployeeName){
        _strEmployeeName = strEmployeeName;
    }

    //Get-Methoden

    public String get_strEmployeeId() {
        return _strEmployeeId;
    }

    public String get_strEmployeeName() {
        return _strEmployeeName;
    }

    //Set-Methoden

    public void set_strEmployeeId(String _strEmployeeId) {
        this._strEmployeeId = _strEmployeeId;
    }

    public void set_strEmployeeName(String _strEmployeeName) {
        this._strEmployeeName = _strEmployeeName;
    }
}
