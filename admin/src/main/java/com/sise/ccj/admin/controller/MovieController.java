package com.sise.ccj.admin.controller;

import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.mapper.LogMapper;
import com.sise.ccj.pojo.admin.LogPO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.MoviePO;
import com.sise.ccj.request.move.MovieRequest;
import com.sise.ccj.service.MovieService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName MoveController
 * @Description
 * @Author CCJ
 * @Date 2019/12/29 15:38
 **/
@RestController
@RequestMapping("/management/movie")
public class MovieController {
    @Autowired
    private MovieService moveService;

    @Autowired
    private LogMapper logMapper;

    @PostMapping("/addMovie")
    public HttpBody addMove(@RequestBody MoviePO param) {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        moveService.addMovie(param, userPO);
        logMapper.insertLog(LogPO.builder("添加电影:" + param.getName(), userPO.getId(), userPO.getIp(), userPO.getTableSpace()));

        return HttpBody.SUCCESS;
    }

    @GetMapping("/queryMovie")
    public HttpBody selectMove(MovieRequest param) {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(moveService.selectMovie(param, userPO, 1));
    }

    @PostMapping("/updateMovie")
    public HttpBody updateMove(@RequestBody MoviePO param) {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        moveService.updateMovie(param, userPO);
        logMapper.insertLog(LogPO.builder("修改电影:" + param.getName(), userPO.getId(), userPO.getIp(), userPO.getTableSpace()));
        return HttpBody.SUCCESS;
    }

    @PostMapping("/delMovie/{id}")
    public HttpBody delMove(@PathVariable Integer id) {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        moveService.delMovie(id, userPO);
        logMapper.insertLog(LogPO.builder("删除电影:" + id, userPO.getId(), userPO.getIp(), userPO.getTableSpace()));
        return HttpBody.SUCCESS;
    }

    @PostMapping("/insertUpdate")
    public HttpBody insertUpdate(@RequestBody MoviePO param) {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        moveService.insertUpdate(param, userPO);
        logMapper.insertLog(LogPO.builder("操作电影:" + param.getName(), userPO.getId(), userPO.getIp(), userPO.getTableSpace()));
        return HttpBody.SUCCESS;
    }

    @GetMapping("/loadMovie")
    public HttpBody loadMovie() {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(moveService.loadMovie(userPO.getTableSpace()));
    }

    @GetMapping("/queryMovieById/{id}")
    public HttpBody queryMovieById(@PathVariable Integer id) {
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(moveService.findMovieById(id, userPO.getTableSpace()));
    }
}
