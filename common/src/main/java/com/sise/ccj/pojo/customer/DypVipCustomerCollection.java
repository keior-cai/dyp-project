package com.sise.ccj.pojo.customer;

import com.sise.ccj.constant.MongoDbConstant;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = MongoDbConstant.VIP_COLLECTION)
public class DypVipCustomerCollection {
}
