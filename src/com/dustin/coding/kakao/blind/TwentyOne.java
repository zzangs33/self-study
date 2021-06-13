package com.dustin.coding.kakao.blind;

import java.util.*;
import java.util.stream.Collectors;

public class TwentyOne {
    public static void main(String[] args) {
        smallDB(new String[]{
                "java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"
        }, new String[]{
                "java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"});
    }

    static String wrongId(String badInput) {
        String res = badInput.toLowerCase();

        res = res.replaceAll("[\\W&&[^_.-]]", "");

        res = res.replaceAll("\\.{2,}", ".");

        res = res.replaceAll("^\\.", "");
        if (res.length() > 0 && res.charAt(res.length() - 1) == '.') res = res.substring(0, res.length() - 1);

        if (res.equals("")) res = "a";

        if (res.length() > 15) {
            res = res.substring(0, 15);
            if (res.charAt(res.length() - 1) == '.') res = res.substring(0, res.length() - 1);
        }

        StringBuilder stringBuilder = new StringBuilder(res);
        char last = res.charAt(res.length() - 1);
        while (stringBuilder.length() < 3) {
            stringBuilder.append(last);
        }
        res = stringBuilder.toString();

        return res;
    }

    static String[] courseCuisine(String[] orders, int[] course) {
        Map<Character, Integer> dishes = new HashMap<>();
        Map<Integer, Integer> sizes = new HashMap<>();
        Set<String> res = new HashSet<>();

        Arrays.stream(orders).forEach(s -> {
            sizes.put(s.length(), sizes.containsKey(s.length()) ? sizes.get(s.length()) + 1 : 1);

            for (int i = 0; i < s.length(); i++) {
                char single = s.charAt(i);
                dishes.put(single, dishes.containsKey(single) ? dishes.get(single) + 1 : 1);
            }
        });

        List<Character> filtered = dishes.entrySet().stream().filter(e -> e.getValue() >= 2).map(Map.Entry::getKey).collect(Collectors.toList());


        for (int i = 0; i < course.length; i++) {
            int curSize = sizes.get(course[i]);
            if (curSize >= 2) {
                int curSelected = 0;
            }
            res.add("");
        }

        return (String[]) res.stream().collect(Collectors.toList()).toArray();
    }

    static int[] smallDB(String[] info, String[] query) {
        int[] cnt = new int[query.length];
        List<Integer> bitInfo = Arrays.stream(info).map(str -> {
            int res = 0;
            String[] tokens = str.split(" ");
            switch (tokens[0]) {
                case "cpp":
                    res += 1;
                    break;
                case "java":
                    res += 2;
                    break;
                case "python":
                    res += 3;
                    break;
                default:
            }
            switch (tokens[1]) {
                case "backend":
                    res += 4;
                    break;
                case "frontend":
                    res += 8;
                    break;
                default:
            }
            switch (tokens[2]) {
                case "junior":
                    res += 16;
                    break;
                case "senior":
                    res += 32;
                    break;
                default:
            }
            switch (tokens[3]) {
                case "chicken":
                    res += 64;
                    break;
                case "pizza":
                    res += 128;
                    break;
                default:
            }
            return res;
        }).collect(Collectors.toList());
        List<Integer> scoreParsed = Arrays.stream(info).map(str -> Integer.parseInt(str.replaceAll("\\D", ""))).collect(Collectors.toList());

        for (int i = 0; i < query.length; i++) {
            String curQuery = query[i];
            String[] tokens = curQuery.split("and");
            cnt[i] = 0;

            final int lang;
            switch (tokens[0].trim()) {
                case "cpp":
                    lang = 1;
                    break;
                case "java":
                    lang = 2;
                    break;
                case "python":
                    lang = 3;
                    break;
                default:
                    lang = 0;
                    break;
            }

            final int pos;
            switch (tokens[1].trim()) {
                case "backend":
                    pos = 4;
                    break;
                case "frontend":
                    pos = 8;
                    break;
                default:
                    pos = 0;
            }

            final int lev;
            switch (tokens[2].trim()) {
                case "junior":
                    lev = 16;
                    break;
                case "senior":
                    lev = 32;
                    break;
                default:
                    lev = 0;
            }

            final int food;
            switch (tokens[3].substring(0, tokens[3].lastIndexOf(" ")).trim()) {
                case "chicken":
                    food = 64;
                    break;
                case "pizza":
                    food = 128;
                    break;
                default:
                    food = 0;
            }

            final int score;
            String scr = curQuery.replaceAll("\\D", "");
            if (scr.equals("")) score = 0;
            else score = Integer.parseInt(scr);

            for (int j = 0; j < info.length; j++) {
                boolean isOk = false;
                if (scoreParsed.get(j) >= score) {
                    isOk = lang == 0 || (bitInfo.get(j) & lang) == lang;
                    isOk &= pos == 0 || (bitInfo.get(j) & pos) == pos;
                    isOk &= lev == 0 || (bitInfo.get(j) & lev) == lev;
                    isOk &= food == 0 || (bitInfo.get(j) & food) == food;
                }
                if (isOk) cnt[i]++;
            }
        }
        return cnt;
    }
}
