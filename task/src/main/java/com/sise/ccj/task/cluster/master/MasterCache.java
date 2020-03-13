package com.sise.ccj.task.cluster.master;

import com.alibaba.fastjson.JSONObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MasterCache {

    public volatile static String masterEnv = "";

    public volatile static Boolean isMaster = false;

    public volatile static Boolean isFirst = true;

    public static final Set<String> dbList = new HashSet<>();

    public static final Map<String, JSONObject> slaveList = new ConcurrentHashMap<>();

    public volatile static int tryCount = 10;

}
