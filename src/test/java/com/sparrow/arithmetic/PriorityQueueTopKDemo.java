package com.sparrow.arithmetic;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PriorityQueueTopKDemo {
    //找出前k个最大数，采用小顶堆实现
    public static int[] findKMax(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Integer::compareTo);
        //队列默认自然顺序排列，小顶堆，不必重写compare
        for (int num : nums) {
            if (pq.size() < k) {
                pq.add(num);
            } else {
                if (pq.peek() < num) {//如果堆顶元素 < 新数，则删除堆顶，加入新数入堆
                    pq.poll();
                    pq.add(num);
                } else {
                    System.out.println("skip" + num);
                }
            }
        }

        int[] result = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            result[i++] = pq.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 6, 2, 3, 5, 4, 8, 7, 9, 10, 0, 1, 3, 2, 1};
        System.out.println(Arrays.toString(findKMax(arr, 5)));
    }
}
