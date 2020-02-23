
package com.sise.ccj.pojo.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogPO {

    private Date createTime;
    private Integer id;
    private String ip;
    private String text;
    private Integer userId;


    //==================
    private String dbPrefix;

    public LogPO(String text, Integer userId, String ip, String dbPrefix) {
        this.text = text;
        this.userId = userId;
        this.ip = ip;
        this.dbPrefix = dbPrefix;
    }

    public static LogPO builder(String text, Integer userId, String ip, String dbPrefix) {
        return new LogPO(text, userId, ip, dbPrefix);
    }

}
