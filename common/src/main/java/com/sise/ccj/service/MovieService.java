package com.sise.ccj.service;

import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.MoviePO;
import com.sise.ccj.request.move.MovieRequest;
import com.sise.ccj.vo.BaseVO;

import java.util.List;

public interface MovieService {
    void addMovie(MoviePO param, UserPO logPo);

    void delMovie(Integer id, UserPO logPo);

    BaseVO selectMovie(MovieRequest param, UserPO logPo);

    MoviePO findMovieById(Integer id, UserPO logPo);

    void updateMovie(MoviePO param, UserPO logPo);

    void insertUpdate(MoviePO param, UserPO logPo);

    List<MoviePO> loadMovie(String dbPrefix);

}
