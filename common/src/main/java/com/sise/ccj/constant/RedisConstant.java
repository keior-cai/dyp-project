package com.sise.ccj.constant;

public interface RedisConstant {
    String STATICS_CUSTOMER_COUNT = "dyp:aw:tmp:statics:customer:count";

    String ORDER_SN_KEY  = "dyp:customer:order:sn:count";

    /**
     *  key =======> hashKey======>val
     *  ORDER_COUNT    ${yId}       count
     */
    String ORDER_COUNT = "dyp:aw:order:count";

    /**
     *  key =======> hashKey======>val
     *  ORDER_TOTAL    ${yId}       total
     */
    String ORDER_TOTAL = "dyp:aw:order:total";
}
