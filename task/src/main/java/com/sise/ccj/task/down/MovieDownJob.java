package com.sise.ccj.task.down;

import com.github.pagehelper.Page;
import com.sise.ccj.mapper.MovieMapper;
import com.sise.ccj.pojo.common.MoviePO;
import com.sise.ccj.request.move.MovieRequest;
import com.sise.ccj.task.job.Job;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieDownJob implements Job {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public void execute(String db) {
        MovieRequest param = new MovieRequest();
        param.setDbPrefix(db);
        Page<MoviePO> moviePOS = movieMapper.queryMovie(param);
        for (MoviePO moviePO : moviePOS.getResult()){
            moviePO.setDbPrefix(db);
            if (moviePO.getDownTime().getTime() <= System.currentTimeMillis()){
                moviePO.setStatus(2);
                movieMapper.insertUpdate(moviePO);
                log.info("moviePO = {}", moviePO);
            }
        }
    }
}
