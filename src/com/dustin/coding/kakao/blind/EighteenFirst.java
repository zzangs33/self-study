package com.dustin.coding.kakao.blind;

import java.util.*;
import java.util.stream.Collectors;

public class EighteenFirst {
    public static void main(String[] args) {
        EighteenFirst eighteenFirst = new EighteenFirst();

        System.out.println(Arrays.toString(eighteenFirst.compress("KAKAO")));
        System.out.println(Arrays.toString(eighteenFirst.compress("TOBEORNOTTOBEORTOBEORNOT")));
        System.out.println(Arrays.toString(eighteenFirst.compress("ABABABABABABABAB")));

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

    public int newsClustering(String str1, String str2) {
        Map<String, Integer> firstSet = mapBuilder(str1);
        Map<String, Integer> secondSet = mapBuilder(str2);

        Map<String, Integer> small = new HashMap<>();
        Map<String, Integer> large = new HashMap<>();

        for (Map.Entry<String, Integer> entry : firstSet.entrySet()) {
            Integer sameThing = secondSet.get(entry.getKey());

            if (sameThing != null) small.put(entry.getKey(), Math.min(sameThing, entry.getValue()));
        }

        for (Map.Entry<String, Integer> entry : firstSet.entrySet()) {
            Integer sameInSmall = secondSet.get(entry.getKey());
            if (sameInSmall != null) {
                large.put(entry.getKey(), Math.max(sameInSmall, entry.getValue()));
                secondSet.remove(entry.getKey());
            } else large.put(entry.getKey(), entry.getValue());
        }
        large.putAll(secondSet);

        if (large.size() == 0) return 65536;
        int smalls = small.values().stream().mapToInt(val -> val).sum();
        int larges = large.values().stream().mapToInt(val -> val).sum();



        double res = (float) smalls / (float) larges * 65536;
        return (int) res;
    }

    private Map<String, Integer> mapBuilder(String str) {
        Map<String, Integer> map = new HashMap<>();
        str = str.replace(' ', '%');

        for (int i = 0; i < str.length() - 1; i++) {
            String split = str.charAt(i) + "" + str.charAt(i + 1);
            if (split.matches("[a-zA-Z]+")) {
                split = split.toLowerCase();
                Integer cur = map.get(split);

                map.put(split, cur == null ? 1 : ++cur);
            }

        }

        return map;
    }

    public static void secretMap(int n, int[] arr1, int[] arr2) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int bin = arr1[i] | arr2[i];

            res.add(Integer.toBinaryString(bin).replace('1', '#').replace('0', ' '));
        }

        for (String str : res) {
            System.out.println(str);
        }
    }

    public static void dartGame(String str) {
        List<Integer> num = Arrays.stream(str.split("\\D")).filter(s -> !s.equals("")).map(Integer::parseInt).collect(Collectors.toList());
        List<String> opr = Arrays.stream(str.split("\\d")).filter(s -> !s.equals("")).collect(Collectors.toList());

        for (int i = 0; i < num.size(); i++) {
            for (int j = 0; j < opr.get(i).length(); j++) {
                char curOpr = opr.get(i).charAt(j);
                switch (curOpr) {
                    case 'S':
                        num.set(i, (int) Math.pow(num.get(i), 1));
                        break;
                    case 'D':
                        num.set(i, (int) Math.pow(num.get(i), 2));
                        break;
                    case 'T':
                        num.set(i, (int) Math.pow(num.get(i), 3));
                        break;
                    case '#':
                        num.set(i, num.get(i) * -1);
                        break;
                    case '*':
                        num.set(i, num.get(i) * 2);
                        if (i > 0) {
                            num.set(i - 1, num.get(i - 1) * 2);
                        }
                        break;
                    default:
                }
            }
        }
        System.out.println(num.stream().mapToInt(Integer::intValue).sum());
    }

    public static void cache(int cacheSize, String[] cities) {
        Map<String, Object> stored = new HashMap<>();
        Queue<String> history = new LinkedList<>();
        int time = 0;

        if (cacheSize == 0) {
            System.out.println(5 * cities.length);
            return;
        }

        for (String city : cities) {
            city = city.toLowerCase();
            if (stored.containsKey(city)) time += 1;
            else {
                time += 5;
                if (stored.size() >= cacheSize) {
                    stored.remove(history.poll());
                }
                stored.put(city, 1);
                history.add(city);
            }
        }

        System.out.println(time);
    }
}
