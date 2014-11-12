package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Employee;
import ro.z2h.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.List;

@MyController(urlPath = "/employee")


public class EmployeeController {

    @MyRequestMethod(urlPath = "/all" )

    public List<Employee> getAllEmployees(){


        List<Employee> employees= new ArrayList<Employee>();
        EmployeeServiceImpl allemployees= new EmployeeServiceImpl();
        return  allemployees.findAllEmployees();

        /*List<Employee> list = new ArrayList<Employee>();
        Employee emp= new Employee();
        emp.setId(100l);
        emp.setFirstName("gigi");
        list.add(emp);

        return list;
        */

    }

    @MyRequestMethod(urlPath = "/one" )

    public Employee getOneEmployee(){
        Long id=100l;
        //Employee employee= new Employee();
        EmployeeServiceImpl allemployees= new EmployeeServiceImpl();
        return  allemployees.findOneEmployee(id);


    }


}
