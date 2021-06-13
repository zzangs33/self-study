package com.dustin.coding.boj;

import java.util.Scanner;

/**
 * @link "https://www.acmicpc.net/problem/1712"
 * TODO: Not solved
 */
public class P1712 {
    public static void main(String[] args) {
        P1712 p1712 = new P1712();
        Scanner sc = new Scanner(System.in);
        int x, y, z;
        x = sc.nextInt();
        y = sc.nextInt();
        z = sc.nextInt();

        System.out.println(p1712.earning(x, y, z));

        sc.close();
    }

    public int earning(int x, int y, int z) {
        if (y >= z) return -1;
        int cnt = 1;
        while (x + cnt * y >= cnt * z) {
            cnt++;
        }
        return cnt;
    }
}
