package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;
import ro.z2h.service.DepartmentServiceImpl;

import java.util.List;

/**
 * Created by user on 11/11/2014.
 */
@MyController(urlPath = "/department")
public class DepartmentController {

    @MyRequestMethod(urlPath = "/all" )

    public List<Department> getAllDepartments(){
        DepartmentServiceImpl departmentService= new DepartmentServiceImpl();
        return  departmentService.findAllDepartments();
    }
}
