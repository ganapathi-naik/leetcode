package leetcode.monotonicqueue._1438;

import java.util.ArrayDeque;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    class Solution {

        public int longestSubarray(int[] nums, int limit) {
            int longestSubarrayLength = -1;

            if (nums == null || nums.length == 0) return longestSubarrayLength;

            int start = 0;
            int end = 0;
            ArrayDeque<Integer> increasingQueue = new ArrayDeque<>();
            ArrayDeque<Integer> decreasingQueue = new ArrayDeque<>();

            while (start < nums.length && end < nums.length) {
                while (!increasingQueue.isEmpty() && increasingQueue.peekLast() > nums[end]) {
                    increasingQueue.pollLast();
                }

                while (!decreasingQueue.isEmpty() && decreasingQueue.peekLast() < nums[end]) {
                    decreasingQueue.pollLast();
                }

                increasingQueue.offerLast(nums[end]);
                decreasingQueue.offerLast(nums[end]);

                if (decreasingQueue.peekFirst() - increasingQueue.peekFirst() <= limit) {
                    longestSubarrayLength = end - start + 1;
                    end++;
                } else {
                    if(!increasingQueue.isEmpty() && increasingQueue.peekFirst() == nums[start]) {
                        increasingQueue.pollFirst();
                    }

                    if(!decreasingQueue.isEmpty() && decreasingQueue.peekFirst() == nums[start]) {
                        decreasingQueue.pollFirst();
                    }
                    start++;
                    end++;
                }
            }
            return longestSubarrayLength;
        }
    }

    public static void main(String[] args) {
        int longestSubarrayLength = new LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit().new Solution().longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5);
        System.out.println("longestSubarrayLength = " + longestSubarrayLength);
    }
}
