package com.sise.ccj.service.impl;

import com.github.pagehelper.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.mapper.MovieMapper;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.MoviePO;
import com.sise.ccj.request.move.MovieRequest;
import com.sise.ccj.service.MovieService;
import com.sise.ccj.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MoveServiceImpl
 * @Description
 * @Author CCJ
 * @Date 2019/12/29 15:29
 **/
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;


    @Override
    public void addMovie(MoviePO param, UserPO logPo) {
        param.setDbPrefix(logPo.getTableSpace());
        movieMapper.addMovie(param);
    }

    @Override
    public void delMovie(Integer id, UserPO logPo) {
        movieMapper.delMovie(logPo.getTableSpace(), id);
    }

    @Override
    public BaseVO selectMovie(MovieRequest param, UserPO logPo, int type) {
        PageHelper.startPage(param.getPage(), param.getSize());
        param.setDbPrefix(logPo.getTableSpace());
        if (type == 1){
            return BaseVO.builder(movieMapper.queryMovie2(param));
        }
        return BaseVO.builder(movieMapper.queryMovie(param));
    }

    @Override
    public MoviePO findMovieById(Integer id, String prefix) {
        return movieMapper.queryMovieById(prefix, id);
    }

    @Override
    public void updateMovie(MoviePO param, UserPO logPo) {
        param.setDbPrefix(logPo.getTableSpace());
        movieMapper.updateMovie(param);
    }

    @Override
    public void insertUpdate(MoviePO param, UserPO logPo) {
        param.setDbPrefix(logPo.getTableSpace());
        param.setStatus(1);
        movieMapper.insertUpdate(param);
    }

    @Override
    public List<MoviePO> loadMovie(String dbPrefix) {
        return movieMapper.loadMovie(dbPrefix);
    }
}
