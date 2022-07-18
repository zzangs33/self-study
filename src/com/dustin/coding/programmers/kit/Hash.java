package com.dustin.coding.programmers.kit;

import java.util.*;
import java.util.stream.Collectors;

public class Hash {
    public static void main(String[] args) {
        Hash hash = new Hash();

        System.out.println(hash.phoneketmon(new int[]{3, 1, 2, 3}));
        System.out.println(hash.phoneketmon(new int[]{3, 3, 3, 2, 2, 4}));
        System.out.println(hash.phoneketmon(new int[]{3, 3, 3, 2, 2, 2}));

        System.out.println(hash.theNotFinished(new String[]{"leo", "kiki", "eden"}, new String[]{"eden", "kiki"}));
        System.out.println(hash.theNotFinished(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"}));
        System.out.println(hash.theNotFinished(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"}));

        System.out.println(hash.telNumList(new String[]{"119", "97674223", "1195524421"}));
        System.out.println(hash.telNumList(new String[]{"123", "456", "789"}));
        System.out.println(hash.telNumList(new String[]{"12", "123", "1235", "567", "88"}));

        System.out.println(hash.camouflage(new String[][]{{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}}));
        System.out.println(hash.camouflage(new String[][]{{"crowmask", "face"}, {"bluesunglasses", "face"}, {"smoky_makeup", "face"}}));

        System.out.println(hash.bestAlbum(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500}));
    }

    public int phoneketmon(int[] nums) {
        Map<Integer, Integer> dic = new HashMap<>();
        for (int i : nums) {
            Integer num = dic.get(i);
            dic.put(i, num == null ? 1 : ++num);
        }
        return Math.min(dic.size(), nums.length / 2);
    }

    public String theNotFinished(String[] participant, String[] completion) {
        String answer = "";
        Map<String, Integer> marathoners = new HashMap<>();

        for (String comp : completion) {
            Integer same = marathoners.get(comp);
            marathoners.put(comp, same == null ? 1 : ++same);
        }

        for (String part : participant) {
            Integer same = marathoners.get(part);

            if (same == null || same <= 0) return part;

            marathoners.put(part, --same);
        }

        return answer;
    }

    public boolean telNumList(String[] phone_book) {
        Set<String> bookDic = new HashSet<>(Arrays.asList(phone_book));

        for (String number : phone_book) {
            bookDic.remove(number);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < number.length(); i++) {
                sb.append(number.charAt(i));

                if (bookDic.contains(sb.toString())) return false;
            }
            bookDic.add(number);
        }
        return true;
    }

    public int camouflage(String[][] clothes) {
        Map<String, Integer> clothSet = new HashMap<>();
        for (String[] cloth : clothes) {
            String type = cloth[1];

            Integer num = clothSet.get(type);
            clothSet.put(type, num == null ? 1 : ++num);
        }

        return clothSet.values().stream().map(i -> ++i).reduce(1, (integer, integer2) -> integer * integer2) - 1;
    }

    public int[] bestAlbum(String[] genres, int[] plays) {
        Map<String, Genre> genreMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String curGenre = genres[i];
            int curPlays = plays[i];

            Genre genre = genreMap.get(curGenre);

            if (genre == null) {
                genre = new Genre();
                genre.totalPlay = curPlays;
                genre.first = i;
            } else {
                genre.totalPlay += curPlays;
                if (curPlays > plays[genre.first]) {
                    genre.second = genre.first;
                    genre.first = i;
                } else if (genre.second == -1 || curPlays > plays[genre.second]) genre.second = i;
            }

            genreMap.put(curGenre, genre);
        }

        List<Genre> result = genreMap.values().stream()
                .sorted((a, b) -> a.totalPlay >= b.totalPlay ? -1 : 1)
                .collect(Collectors.toList());

        List<Integer> answer = new ArrayList<>();
        for (Genre genre : result) {
            answer.add(genre.first);
            if (genre.second != -1) {
                answer.add(genre.second);
            }
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static class Genre {
        int totalPlay;
        int first = -1;
        int second = -1;
    }
}
