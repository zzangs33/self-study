package com.dustin.coding.programmers.challenge;

import java.util.Arrays;

public class SeasonOne {
    public static void main(String[] args) {
        SeasonOne seasonOne = new SeasonOne();

        System.out.println(Arrays.toString(seasonOne.repeatBinaryConversion("110010101001")));
        System.out.println(Arrays.toString(seasonOne.repeatBinaryConversion("01110")));
        System.out.println(Arrays.toString(seasonOne.repeatBinaryConversion("1111111")));


    }

    public int[] repeatBinaryConversion(String s) {
        int total = 0;
        int loop = 0;

        while (!s.equals("1")) {
            int curNum = 0;
            loop++;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '0') {
                    curNum++;
                }
            }
            s = s.replace("0", "");

            total += curNum;

            if (s.equals("1")) break;
            s = Integer.toBinaryString(s.length());
        }


        return new int[]{loop, total};
    }
}
