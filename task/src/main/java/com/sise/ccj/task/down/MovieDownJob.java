package com.sise.ccj.task.down;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sise.ccj.compant.SpringContext;
import com.sise.ccj.mapper.MovieMapper;
import com.sise.ccj.pojo.common.MoviePO;
import com.sise.ccj.request.move.MovieRequest;
import com.sise.ccj.task.cluster.master.MasterCache;
import com.sise.ccj.task.job.Job;
import com.sise.ccj.utils.Maps;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service("MovieDownJob")
@DisallowConcurrentExecution
public class MovieDownJob implements Job {

    @Autowired
    private MovieMapper movieMapper;

    private static final Map<String, List<String>> downMap = new ConcurrentHashMap<>();

    private static final Map<String, List<String>> finishMap = new ConcurrentHashMap<>();

    private static final Object o = new Object();

    @Override
    public void execute(JobExecutionContext var1) {
        log.info("MovieDownJob load db = {}", finishMap);
        if (!downMap.isEmpty()) {
            return;
        }
        MovieMapper movieMapperd = SpringContext.getBeanByType(MovieMapper.class);
        for (String db : MasterCache.dbList) {
            MovieRequest param = new MovieRequest();
            param.setDbPrefix(db);
            Page<MoviePO> moviePOS = movieMapperd.queryMovie(param);
            moviePOS.forEach(e -> {
                if (!downMap.containsKey(db)) {
                    downMap.put(db, new ArrayList<>());
                    finishMap.put(db, new ArrayList<>());
                }
                downMap.get(db).add(e.getId() + "");
            });
        }

    }

    @Override
    public Object getJob() {
        if (downMap.isEmpty()) {
            return new Object();
        }
        String job = null, id = null;
        for (Map.Entry<String, List<String>> entry : downMap.entrySet()) {
            if (CollectionUtils.isEmpty(entry.getValue())) {
                continue;
            }
            job = entry.getKey();
            id = entry.getValue().remove(0);
        }
        if (!StringUtils.isEmpty(job) && !StringUtils.isEmpty(id)) {
            finishMap.get(job).add(id);
            return Maps.of("job", job, "id", id);
        }
        return o;
    }

    @Override
    public void finishJob(JSONObject jobInfo) {
        String key = jobInfo.getString("job");
        String id = jobInfo.getString("id");
        List<String> jobList = finishMap.get(key);
        if (jobInfo.containsKey(id)) {
            jobList.remove(key);
        }
    }

    @Override
    public Object executeJob(JSONObject json) {
        String db = json.getString("job");
        String id = json.getString("id");
        MovieRequest param = new MovieRequest();
        param.setDbPrefix(db);
        MoviePO moviePO = movieMapper.queryMovieById(db, Integer.parseInt(id));
        moviePO.setDbPrefix(db);
        if (moviePO.getDownTime().getTime() <= System.currentTimeMillis()) {
            moviePO.setStatus(2);
            movieMapper.insertUpdate(moviePO);
            log.info("moviePO = {}", moviePO);
        }
        return o;
    }
}
