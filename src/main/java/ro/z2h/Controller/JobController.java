package ro.z2h.controller;

import ro.z2h.annotation.MyController;
import ro.z2h.annotation.MyRequestMethod;
import ro.z2h.domain.Job;
import ro.z2h.service.JobServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/13/2014.
 */
@MyController(urlPath = "/job")


public class JobController {

    @MyRequestMethod(urlPath = "/all" )

    public List<Job> getAllJobs(){


        List<Job> jobs= new ArrayList<Job>();
        JobServiceImpl alljobs= new JobServiceImpl();
        return  alljobs.findAllJobs();

        /*List<Employee> list = new ArrayList<Employee>();
        Employee emp= new Employee();
        emp.setId(100l);
        emp.setFirstName("gigi");
        list.add(emp);

        return list;
        */

    }
    public Job getOneJob(String idJob){
        // Long id=100l;
        //Employee employee= new Employee();
        JobServiceImpl alljobs= new JobServiceImpl();
        return  alljobs.findOneJob(idJob);


    }



}

