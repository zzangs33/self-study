package com.dustin.coding;

public class Testing {

    private int globalN;
    /*

     */
    private int[] cache;

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(11));

    }

    public static String solution(int n) {
        StringBuilder answer = new StringBuilder();

        while (n > 3) {
            answer.insert(0, n % 3);
            n /= 3;
        }
        int left = n % 3;
        switch (left) {
            case 1:
                answer.insert(0, "1");
                break;
            case 2:
                answer.insert(0, "2");
                break;
            case 0:
                answer.insert(0, "4");
                break;
        }

        return answer.toString();
    }

    public static int solution1(int n) {
        int answer = 0;

        for (int j = 1; j <= n; j++) {
            int cur = 0;
            for (int i = j; i <= n; i++) {
                cur += i;

                if (cur == n) {
                    answer++;
                    break;
                } else if (cur > n) break;
            }
        }
        return answer;
    }

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        int[][] dishMatrix = new int[26][26];

        for (String order : orders) {
            for (int i = 0; i < order.length(); i++) {
                char dish1 = order.charAt(i);
                for (int j = i + 1; j < order.length(); j++) {
                    char dish2 = order.charAt(j);
                    int x = dish1 - 'A';
                    int y = dish2 - 'A';

                    dishMatrix[x][y]++;
                    dishMatrix[y][x]++;
                }
            }
        }
        for (int[] row : dishMatrix) {


        }


        return answer;
    }


}
