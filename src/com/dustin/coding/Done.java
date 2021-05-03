package com.dustin.coding;

import java.util.Stack;

public class Done {


    public String solution(int[] numbers) {
        String answer = "";


        return answer;
    }

    private int standard(String str) {
        if (str.equals("")) return 0;

        int p = 0;
        int q = 0;

        for (int i = 0; i < str.length(); i += 2) {
            char c = str.charAt(i);
            if (c == '(') p++;
            else q++;

            if (p == q) return i;
        }

        return str.length();
    }

    private boolean isRight(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(') stack.push(c);
            else stack.pop();
        }

        return stack.isEmpty();
    }
}
