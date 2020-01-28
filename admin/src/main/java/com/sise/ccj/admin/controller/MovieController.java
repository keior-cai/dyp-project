package com.sise.ccj.admin.controller;

import com.sise.ccj.config.SessionContextHolder;
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

    @PostMapping("/addMovie")
    public HttpBody addMove(@RequestBody MoviePO param){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        moveService.addMovie(param, userPO);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/queryMovie")
    public HttpBody selectMove(MovieRequest param){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        return HttpBody.getSucInstance(moveService.selectMovie(param, userPO));
    }

    @PostMapping("/updateMovie")
    public HttpBody updateMove(@RequestBody MoviePO param){
        UserPO userPO = SessionContextHolder.getAccountAndValid();
        moveService.updateMovie(param, userPO);
        return HttpBody.SUCCESS;
    }

    @PostMapping("/delMovie/{id}")
    public HttpBody delMove(@PathVariable Integer id){
        moveService.delMovie(id, SessionContextHolder.getAccountAndValid());
        return HttpBody.SUCCESS;
    }
    @PostMapping("/insertUpdate")
    public HttpBody insertUpdate(@RequestBody MoviePO param){
        moveService.insertUpdate(param, SessionContextHolder.getAccountAndValid());
        return HttpBody.SUCCESS;
    }
}
