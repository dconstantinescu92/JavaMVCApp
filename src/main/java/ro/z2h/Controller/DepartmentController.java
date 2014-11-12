package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Department;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/11/2014.
 */
@MyController(urlPath = "/department")
public class DepartmentController {

    @MyRequestMethod(urlPath = "/all" )

    public List<Department> getAllDepartments(){
        List<Department> listd=new ArrayList<Department>();

        Department dep= new Department();
        Department dep1= new Department();

        dep.setId(1l);
        dep.setDepartmentName("IT");

        dep1.setId(2l);
        dep1.setDepartmentName("Economics");

        listd.add(dep);
        listd.add(dep1);

        return (listd);
    }
}
