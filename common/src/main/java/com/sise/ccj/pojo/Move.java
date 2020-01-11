package com.sise.ccj.pojo;

import com.sise.ccj.constant.MongoDbConstant;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @ClassName MoveService
 * @Description
 * @Author CCJ
 * @Date 2019/12/29 15:22
 **/
@Data
@Document(collection = MongoDbConstant.MOVE_TABLE)
public class Move {

    @Id
    private ObjectId id;

    private String name;

    private String title;

    private String context;

    private Double price;

    /** 影院ID **/
    private Integer yyId;

    private Date createTime;

    private Date updateTime;
}
