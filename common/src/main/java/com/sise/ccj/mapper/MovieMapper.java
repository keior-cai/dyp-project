package com.sise.ccj.mapper;


import com.github.pagehelper.Page;
import com.sise.ccj.pojo.admin.SpacePO;
import com.sise.ccj.pojo.common.MoviePO;
import org.apache.ibatis.annotations.Param;


public interface MovieMapper {
    SpacePO queryMovieById(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    Page<SpacePO> queryMovie(@Param("dbPrefix") String dbPrefix);

    void addMovie(SpacePO spacePO);

    void delMovie(@Param("dbPrefix") String dbPrefix, @Param("id") Integer id);

    void updateMovie(MoviePO moviePO);
}
