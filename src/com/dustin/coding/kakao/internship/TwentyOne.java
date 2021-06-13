package com.dustin.coding.kakao.internship;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TwentyOne {
    public static void main(String[] args) {
        TwentyOne mains = new TwentyOne();
//        System.out.println(mains.solution3(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"}));
        System.out.println(mains.solution3(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
    }

    public int solution1(String s) {

        s = s.replace("zero", "0");
        s = s.replace("one", "1");
        s = s.replace("two", "2");
        s = s.replace("three", "3");
        s = s.replace("four", "4");
        s = s.replace("five", "5");
        s = s.replace("six", "6");
        s = s.replace("seven", "7");
        s = s.replace("eight", "8");
        s = s.replace("nine", "9");

        return Integer.parseInt(s);
    }

    public int[] solution2(String[][] places) {
        int[] answer = {1, 1, 1, 1, 1};

        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            for (int y = 0; y < 5; y++) {
                if (answer[i] == 0) break;

                for (int x = 0; x < 5; x++) {
                    char cur = place[y].charAt(x);
                    if (cur == 'P') {
                        if (checkInTwo(x, y, place)) {
                            answer[i] = 0;
                            break;
                        }
                    }
                }
            }
        }

        return answer;
    }

    public String solution3(int n, int k, String[] cmd) {
        List<Integer> rows = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            rows.add(i);
            sb.append("O");
        }

        Stack<Integer> deleted = new Stack<>();
        Integer selected = k;

        for (String op : cmd) {
            char val = op.charAt(0);

            switch (val) {
                case 'D':
                    int upIdx = op.charAt(2) - '0';
                    for (int i = 0; i < upIdx && selected < rows.size() - 1; i++) selected++;
                    break;
                case 'U':
                    int downIdx = op.charAt(2) - '0';
                    for (int i = 0; i < downIdx && selected > 0; i++) selected--;
                    break;
                case 'C':
                    deleted.add(selected);
                    rows.remove(selected);
                    if (selected == rows.size() - 1) selected--;
                    break;
                case 'Z':
                    Integer deleteLast = deleted.pop();
                    if (deleteLast >= rows.size()) rows.add(deleteLast);
                    else rows.add(deleteLast, deleteLast);
                    break;
                default:
            }

        }

        while (!deleted.isEmpty()) {
            Integer lastDelete = deleted.pop();
            sb.replace(lastDelete, lastDelete + 1, "X");
        }

        return sb.toString();
    }

    private boolean checkInTwo(int x, int y, String[] place) {
        try {
            if (place[y + 1].charAt(x) == 'P') return true;
        } catch (Exception e) {
        }
        try {
            if (place[y].charAt(x + 1) == 'P') return true;
        } catch (Exception e) {
        }
        try {
            if (place[y].charAt(x + 2) == 'P') {
                if (place[y].charAt(x + 1) != 'X') return true;
            }
        } catch (Exception e) {
        }
        try {
            if (place[y + 1].charAt(x + 1) == 'P') {
                if (place[y].charAt(x + 1) != 'X' || place[y + 1].charAt(x) != 'X') return true;
            }
        } catch (Exception e) {
        }
        try {
            if (place[y].charAt(x + 2) == 'P') {
                if (place[y].charAt(x + 1) != 'X') return true;
            }

        } catch (Exception e) {
        }
        return false;
    }
}
