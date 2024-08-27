package com.taurus.jobapp.repo;

import com.taurus.jobapp.model.Jobpost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

@Repository
public class Jobrepo {

    private JdbcTemplate jdbc;

    public Boolean addjob(Jobpost jobpost) throws SQLException {
        String sql = "INSERT INTO public.jobs(\n" +
                "\tpostid, postprofile, postdesc, reqexperience, posttechstack)\n" +
                "\tVALUES (?, ?, ?, ?, ?);";
        Array sqlArray = jdbc.getDataSource().getConnection().createArrayOf("varchar", jobpost.getPostTechStack().toArray());
        int rows = jdbc.update(sql, jobpost.getPostId(),jobpost.getPostProfile(),jobpost.getPostDesc(),jobpost.getReqExperience(),sqlArray);
        System.out.println("Student is added successfully \n"+rows+" rows affected");

        if(rows!=0){
            return true;
        }
        return false;


    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Jobpost> viewAllJobs() {
        String sql = "Select * from jobs;";
        RowMapper<Jobpost>mapper = new RowMapper<Jobpost>() {
            @Override
            public Jobpost mapRow(ResultSet rs, int rowNum) throws SQLException {
                Jobpost s = new Jobpost();
                s.setPostId((rs.getInt("postid")));
                s.setPostDesc(rs.getString("postprofile"));
                s.setPostProfile(rs.getString("postprofile"));
                s.setReqExperience(rs.getInt("reqexperience"));
                Array sqlArray = rs.getArray("posttechstack");
                String[] array = (String[]) sqlArray.getArray();  // Cast to String[]
                List<String> techStack = Arrays.asList(array);    // Convert to List<String>
                s.setPostTechStack(techStack);

                return s;
            }

        };
        return jdbc.query(sql,mapper);
    }
}
