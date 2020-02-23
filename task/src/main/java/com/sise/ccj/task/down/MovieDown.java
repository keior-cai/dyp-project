package com.sise.ccj.task.down;

import com.github.pagehelper.Page;
import com.sise.ccj.mapper.DypDbMapper;
import com.sise.ccj.mapper.MovieMapper;
import com.sise.ccj.pojo.common.MoviePO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MovieDown {

    @Autowired
    private DypDbMapper dypDbMapper;

    @Autowired
    private MovieMapper movieMapper;

    @Scheduled(fixedRate = 2000)
    public void execute(){
        List<String> dbName = dypDbMapper.queryDb();
        for (String db : dbName) {
            if (db.equals("dyp_business")) continue;
            Page<MoviePO> moviePOS = movieMapper.queryMovie(db);
            for (MoviePO moviePO : moviePOS.getResult()){
                if (moviePO.getDownTime().getTime() >= System.currentTimeMillis()){
                    moviePO.setStatus(0);
                    movieMapper.insertUpdate(moviePO);
                    log.info("moviePO = {}", moviePO);
                }
            }
        }
    }
}
