package com.taurus.jobapp.service;

import com.taurus.jobapp.model.Jobpost;
import com.taurus.jobapp.repo.Jobrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class JobService {

    @Autowired
    Jobrepo repo;
    public Boolean addJob(Jobpost jobpost) throws SQLException {
        if(repo.addjob(jobpost)){
            return true;
        }
        return false;


    }

    public List<Jobpost> viewAllJobs() {
        List<Jobpost>allJobs = repo.viewAllJobs();
        return  allJobs;
    }
}
