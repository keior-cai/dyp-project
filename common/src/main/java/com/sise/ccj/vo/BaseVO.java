package com.sise.ccj.vo;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BaseVO<T> {

    private long total;

    private List<T> details;


    public static BaseVO builder(Page data){
        return new BaseVO(data.getTotal(), data.getResult());
    }
}
