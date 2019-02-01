package models;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Employee {

    private Integer _intEmployeeId;
    private String _strEmployeeName;
    private  Integer _intProjectId;

    public Employee(String strEmployeeName, Integer intProjectId){
        //Zum Erstellen eines Mitarbeiters aus Nutzereingeben
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
        //Nur Zum Debuggen
        this._strEmployeeName = strEmployeeName;
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
