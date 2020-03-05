package com.sise.ccj.customer.controller;

import com.alibaba.fastjson.JSON;
import com.sise.ccj.config.SessionContextHolder;
import com.sise.ccj.config.redis.RedisUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.constant.TimeConstant;
import com.sise.ccj.pojo.admin.CustomerPO;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.request.move.MovieRequest;
import com.sise.ccj.service.MovieService;
import com.sise.ccj.vo.BaseVO;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/queryMovie")
    public HttpBody queryMovie(MovieRequest param){
        CustomerPO logPo = SessionContextHolder.getAccountAndValid(null);
        logPo.setTableSpace(CommonConstant.TABLE_SPACE
                .replace(CommonConstant.TABLE_SPACE_ID, param.getId()+""));
        redisUtil.set(CommonConstant.KEY_LOGIN_TOKEN
                .replace(CommonConstant.REPLACE_TOKEN, logPo.getToken()),
                JSON.toJSONString(logPo), TimeConstant.SERVEN_DAY_MILLIS);
        BaseVO baseVO = movieService.selectMovie(param, new UserPO(logPo.getTableSpace()), 2);
        return HttpBody.getSucInstance(baseVO);
    }

    @GetMapping("/queryMovieById/{id}")
    public HttpBody queryMovieById(@PathVariable Integer id){
        CustomerPO logPo = SessionContextHolder.getAccountAndValid(null);
        return HttpBody.getSucInstance(movieService.findMovieById(id, logPo.getTableSpace()));
    }
}
