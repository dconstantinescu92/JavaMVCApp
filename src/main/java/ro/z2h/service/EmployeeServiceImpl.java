package ro.z2h.service;

import ro.z2h.dao.EmployeeDao;
import ro.z2h.domain.Employee;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/12/2014.
 */
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public List<Employee> findAllEmployees() {
        List<Employee> list= new ArrayList<Employee>();
        Connection con;
        EmployeeDao employeeDao= new EmployeeDao();

        con= DatabaseManager.getConnection("ZTH_03","passw0rd");
        try {
             list = employeeDao.getAllEmployees(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

       return list;
    }

    @Override
    public Employee findOneEmployee(Long id) {

        Employee employee= new Employee();
        Connection con;
        EmployeeDao employeeDao= new EmployeeDao();

        con=DatabaseManager.getConnection("ZTH_03","passw0rd");

        try {
            employee=employeeDao.getEmployeeById(con,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

}
