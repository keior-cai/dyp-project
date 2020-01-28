package com.sise.ccj.mapper;


import com.github.pagehelper.Page;
import com.sise.ccj.pojo.common.MovieStaticsPO;
import org.apache.ibatis.annotations.Param;


public interface MovieStaticsMapper {
    MovieStaticsPO queryMovieById(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    Page<MovieStaticsPO> queryMovie(@Param("dbPrefix") String dbPrefix);

    void addMovie(MovieStaticsPO moviePO);

    void delMovie(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    void updateMovie(MovieStaticsPO moviePO);

    void insertUpdate(MovieStaticsPO moviePO);
}
