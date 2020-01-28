package com.sise.ccj.mapper;


import com.github.pagehelper.Page;
import com.sise.ccj.pojo.common.MoviePO;
import org.apache.ibatis.annotations.Param;


public interface MovieMapper {
    MoviePO queryMovieById(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    Page<MoviePO> queryMovie(@Param("dbPrefix") String dbPrefix);

    void addMovie(MoviePO moviePO);

    void delMovie(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    void updateMovie(MoviePO moviePO);

    void insertUpdate(MoviePO moviePO);
}
