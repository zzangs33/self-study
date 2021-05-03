package com.dustin.coding.kakao.blind;

import java.util.*;

public class Eighteen {
    public static void main(String[] args) {
        Eighteen eighteen = new Eighteen();

        System.out.println(Arrays.toString(eighteen.compress("KAKAO")));
        System.out.println(Arrays.toString(eighteen.compress("TOBEORNOTTOBEORTOBEORNOT")));
        System.out.println(Arrays.toString(eighteen.compress("ABABABABABABABAB")));

    }

    public int[] compress(String msg) {
        List<Integer> res = new ArrayList<>();

        Map<String, Integer> dic = new HashMap<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            dic.put(c + "", dic.size() + 1);
        }

        while (!msg.isEmpty()) {
            for (int j = 1; j <= msg.length(); j++) {
                String cur = msg.substring(0, j);

                if (!dic.containsKey(cur)) {
                    res.add(dic.get(cur.substring(0, j - 1)));
                    dic.put(cur, dic.size() + 1);
                    msg = msg.substring(j - 1);
                    break;
                } else if (j == msg.length()) {
                    res.add(dic.get(cur.substring(0, j)));
                    msg = "";
                }
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }
}
