package com.dustin.coding.boj;

import java.util.Scanner;

/**
 * @link "https://www.acmicpc.net/problem/14681"
 */
public class P14681 {

    public static void main(String[] args) {
        P14681 p14681 = new P14681();

        Scanner sc = new Scanner(System.in);
        int x, y;

        x = sc.nextInt();
        y = sc.nextInt();

        System.out.println(p14681.quadrant(x, y));

        sc.close();
    }

    public int quadrant(int x, int y) {
        int quad;
        if (x == 0 || y == 0) return -1;

        if (x > 0) {
            quad = y > 0 ? 1 : 4;
        } else {
            quad = y > 0 ? 2 : 3;
        }

        return quad;
    }
}
