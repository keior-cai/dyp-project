package com.sise.ccj.vo;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class BaseVO<T> {

    private long total;

    private List<T> details;


    public static BaseVO builder(Page data){
        if (data.getTotal() == 0){
            return new BaseVO(0, Collections.emptyList());
        }
        return new BaseVO(data.getTotal(), data.getResult());
    }
}
