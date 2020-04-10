package com.sise.ccj;

public class Test {
    public static void main(String[] args) {
        lengthOfLongestSubstring("ssgomfgkasdhjkfgbjksnfkjagshfffagf");
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char [] chars = s.toCharArray();
        int lenght = chars.length;
        int[] table = new int[lenght+1];
        table[1] = 0;
        for (int i = 2; i <= chars.length; i++) {
            int j = i-1;
            int n = i-2;
            while(n >=0 && chars[j] != chars[n]){
                n--;
            }
            table[i] = (j-n) +1;
        }
        System.out.println(table[lenght]);
        return table[lenght];
    }


}
