package ro.z2h.service;

import ro.z2h.dao.JobDao;
import ro.z2h.domain.Job;
import ro.z2h.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/13/2014.
 */
public class JobServiceImpl implements JobService {



    @Override
    public List<Job> findAllJobs() {

            ArrayList<Job> job = new ArrayList<>();
            Connection con;
            JobDao jobDao = new JobDao();

            con = DatabaseManager.getConnection("ZTH_03", "passw0rd");

            try {
               job = jobDao.getAllJobs(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return job;


        }

    @Override
    public List<Job> findOneJob() {
        return null;
    }

    // @Override
    public Job findOneJob(String id) {

        Job job= new Job();
        Connection con;
        JobDao jobDao= new JobDao();

        con=DatabaseManager.getConnection("ZTH_03","passw0rd");

        try {
            job=jobDao.getJobById(con, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return job;
    }
}
