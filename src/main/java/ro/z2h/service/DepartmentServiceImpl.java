package ro.z2h.service;

import ro.z2h.dao.DepartmentDao;
import ro.z2h.domain.Department;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/13/2014.
 */
public class DepartmentServiceImpl implements DepartmentService{


    @Override
    public List<Department> findAllDepartments() {
        ArrayList<Department> department = new ArrayList<>();
        Connection con;
        DepartmentDao departmentDao = new DepartmentDao();

        con = DatabaseManager.getConnection("ZTH_03", "passw0rd");

        try {
            department = departmentDao.getAllDepartments(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;

    }
}


