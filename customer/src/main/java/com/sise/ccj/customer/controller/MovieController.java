package com.sise.ccj.customer.controller;

import com.sise.ccj.request.move.MovieRequest;
import com.sise.ccj.service.MovieService;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MovieController
 * @Description
 * @Author CCJ
 * @Date 2020/1/28 21:28
 **/
@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/queryMovie")
    public HttpBody queryMovie(MovieRequest param){
        return null;
    }
}
