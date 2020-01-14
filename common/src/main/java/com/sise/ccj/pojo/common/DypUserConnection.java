package com.sise.ccj.pojo.common;

import com.sise.ccj.constant.MongoDbConstant;
import com.sise.ccj.pojo.admin.DypYYCollection;
import com.sise.ccj.pojo.customer.DypOrderCollection;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = MongoDbConstant.DYP_USER_COLLECTION)
public class DypUserConnection {
    @Id
    private ObjectId id;

    private String name;

    private String userName;

    private String password;

    private String avatar;

    private Boolean role;

    private Boolean deleted;

    private Date createTime;

    private Date updateTime;

    private List<DypYYCollection> yyList;

    private List<DypOrderCollection> orderList;

    public class Field {
        private Field() {
        }

        public static final String ID = "id";

        public static final String NAME = "name";

        public static final String USER_NAME = "userName";

        public static final String PASSWORD = "password";

        public static final String AVATAR = "avatar";

        public static final String ROLE = "role";

        public static final String DELETED = "deleted";

        public static final String CREATE_TIME = "createTime";

        public static final String UPDAE_TIME = "updateTime";
    }
}
