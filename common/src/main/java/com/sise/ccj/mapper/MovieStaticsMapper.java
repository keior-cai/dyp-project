package com.sise.ccj.mapper;


import com.github.pagehelper.Page;
import com.sise.ccj.pojo.common.MovieStaticsPO;
import org.apache.ibatis.annotations.Param;


public interface MovieStaticsMapper {
    MovieStaticsPO queryMovieStaticsById(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    Page<MovieStaticsPO> queryMovieStatics(@Param("dbPrefix") String dbPrefix);

    void addMovieStatics(MovieStaticsPO moviePO);

    void delMovieStatics(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    void updateMovieStatics(MovieStaticsPO moviePO);

    void insertUpdateStatics(MovieStaticsPO moviePO);
}
