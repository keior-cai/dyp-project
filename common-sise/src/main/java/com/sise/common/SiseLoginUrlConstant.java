package com.sise.common;

public class SiseLoginUrlConstant {

    public static final  String defaultIpPort = "class.sise.com.cn:7001";
    // 登录接口URL
    public static String loginUrl = "http://${ipPort}/sise/login_check_login.jsp";

    // 获取入口
    public static String indexUrl = "http://${ipPort}/sise/module/student_states/student_select_class/main.jsp";

    // 登录页面
    public static String loginPageUrl = "http://${ipPort}/sise/login.jsp";

    // 用户信息页面地址
    public static String userInfoUrl = "http://${ipPort}/SISEWeb/pub/course/courseViewAction.do?${key}";

}
