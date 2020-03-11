package com.sise.ccj.task.cluster.master;

import java.util.HashSet;
import java.util.Set;

public class MasterCache {

    public volatile static String masterEnv = "";

    public volatile static Boolean isMaster = false;

    public static final Set<String> dbList = new HashSet<>();

    public volatile static int tryCount = 10;

}
