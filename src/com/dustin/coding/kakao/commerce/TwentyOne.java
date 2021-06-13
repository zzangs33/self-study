package com.dustin.coding.kakao.commerce;

import java.util.*;
import java.util.stream.Collectors;

public class TwentyOne {
    private int globalN;
    private int[] cache;

    public static void main(String[] args) {
        TwentyOne twentyOne = new TwentyOne();

//        System.out.println(twentyOne.first());
//        System.out.println(twentyOne.second());
    }

    public int giftCards(int[] gift_cards, int[] wants) {
        Map<Integer, Integer> giftMap = new HashMap<>();
        for (int gift_card : gift_cards) {
            Integer val = giftMap.get(gift_card);
            if (val == null) {
                giftMap.put(gift_card, 1);
            } else {
                giftMap.put(gift_card, ++val);
            }
        }
        for (int want : wants) {
            Integer val = giftMap.get(want);
            if (val == null) {
                giftMap.put(want, -1);
            } else {
                giftMap.put(want, --val);
            }
        }

        return (int) giftMap.entrySet().stream().filter(e -> e.getValue() < 0).count();
    }

    public int components(int[][] needs, int r) {
        Map<Integer, Integer> needMap = new HashMap<>();
        List<Product> products = new ArrayList<>();

        for (int[] need : needs) {
            Product product = new Product();
            product.needs = new ArrayList<>();
            for (int i = 0; i < need.length; i++) {
                int comp = need[i];
                if (comp == 1) {
                    product.needs.add(i);

                    Integer val = needMap.get(i);
                    if (val == null) {
                        needMap.put(i, 1);
                    } else {
                        needMap.put(i, ++val);
                    }
                }
            }

            products.add(product);
        }

        Set<Integer> sortedComp = new HashSet<>(needMap
                .entrySet().stream()
                .sorted((e1, e2) -> e1.getValue() > e2.getValue() ? -1 : 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList())
                .subList(0, r));


        return (int) products.stream().filter(pr -> {
            boolean can = true;
            for (Integer comp : pr.needs) {
                can &= sortedComp.contains(comp);
            }
            return can;
        }).count();
    }

    public int[] trainLine(int n, int[] passenger, int[][] train) {
        int[] answer = {};
        this.globalN = n;
        this.cache = new int[n];
        List<Station> stations = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Station station = new Station();
            station.id = i;
            station.passenger = passenger[i];
            for (int[] linkInfo : train) {
                if (linkInfo[0] == i) station.toVisit.add(linkInfo[1]);
            }

            stations.add(station);
        }

        Station start = stations.get(0);
        Stack<Station> left = new Stack<>();
        for (int toVisit : start.toVisit) {
            left.push(stations.get(toVisit));
        }
        stations.remove(0);

        for (Station target : stations) {
            while (left.isEmpty()) {
                Station visited = left.peek();
                visited.visited = true;
                for (int toVisit : visited.toVisit) {
                    Station next = stations.get(toVisit);
                    if (!next.visited) left.push(next);
                }
            }
        }


        return answer;
    }

    private int smallPass(List<Station> stations, int target) {
        int cached = this.cache[target];
        if (cached != 0) return cached;

        return 0;
    }

    private static class Product {
        List<Integer> needs;
    }

    private static class Station {
        int id;
        List<Integer> toVisit;
        boolean visited;
        int passenger;
    }
}
