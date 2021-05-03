package com.dustin.coding.programmers.kit;

import java.util.LinkedList;
import java.util.Queue;

public class QueueStack {
    public static void main(String[] args) {
        QueueStack queueStack = new QueueStack();

        System.out.println(queueStack.truckThroughBridge(2, 10, new int[]{7, 4, 5, 6}));
        System.out.println(queueStack.truckThroughBridge(100, 100, new int[]{10}));
        System.out.println(queueStack.truckThroughBridge(100, 100, new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10}));

        System.out.println(queueStack.stockValue(new int[]{1, 2, 3, 2, 3}));
    }

    public int truckThroughBridge(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> onBridge = new LinkedList<>();

        int time = 0;
        int curWeight = 0;

        for (int i = 0; i < bridge_length; i++) onBridge.add(0);

        for (int i = 0; i < truck_weights.length; i++) {
            time++;

            int truck = truck_weights[i];
            curWeight -= onBridge.remove();

            if (curWeight + truck <= weight) {
                onBridge.add(truck);
                curWeight += truck;
            } else {
                onBridge.add(0);
                i--;
            }
        }

        return onBridge.size() + time;
    }

    public int[] stockValue(int[] prices) {

        return new int[0];
    }

    public int[] developModule(int[] progresses, int[] speeds) {
        return new int[0];
    }

    public int printer(int[] priorities, int location) {
        return 0;
    }
}
