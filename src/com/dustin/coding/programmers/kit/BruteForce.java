package com.dustin.coding.programmers.kit;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class BruteForce {

    public int[] virtualTest(int[] answers) {
        Map<Integer, Integer> correct = new HashMap<>();
        int[][] myWay = new int[][]{{5, 1, 2, 3, 4}, {5, 2, 1, 2, 3, 2, 4, 2}, {5, 3, 3, 1, 1, 2, 2, 4, 4, 5}};
        correct.put(1, 0);
        correct.put(2, 0);
        correct.put(3, 0);

        for (int i = 1; i <= answers.length; i++) {
            int answer = answers[i - 1];
            if (myWay[0][i % 5] == answer) {
                Integer val = correct.get(1);
                correct.put(1, ++val);
            }
            if (myWay[1][i % 8] == answer) {
                Integer val = correct.get(2);
                correct.put(2, ++val);
            }
            if (myWay[2][i % 10] == answer) {
                Integer val = correct.get(3);
                correct.put(3, ++val);
            }
        }

        int[] rank = correct.entrySet().stream()
                .sorted((e1, e2) -> {
                    if (e1.getValue() < e2.getValue()) return 1;
                    else if (e1.getValue() > e2.getValue()) return -1;

                    return e1.getKey() < e2.getKey() ? -1 : 1;
                })
                .map(Map.Entry::getKey).mapToInt(Integer::intValue).toArray();

        if (!correct.get(rank[0]).equals(correct.get(rank[1]))) return new int[]{rank[0]};
        else if (!correct.get(rank[1]).equals(correct.get(rank[2]))) return new int[]{rank[0], rank[1]};
        return rank;
    }

    public int findPrimeNumber(String numbers) {

        return 0;
    }

    private boolean isPrime(int number) {
        return number > 1
                && IntStream.rangeClosed(2, (int) Math.sqrt(number))
                .noneMatch(n -> (number % n == 0));
    }

    public int[] carpet(int brown, int yellow) {
        return new int[0];
    }
}
