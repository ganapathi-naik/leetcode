package leetcode.array._994;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * Problem link: <a href="https://leetcode.com/problems/two-sum/">1. Two Sum</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        /*
        1. Create a hashmap which will have key as <target-nums[index]> and value as <index>
        2. loop through the nums array return if hashmap contains nums[index]
        3. or else update hashmap with target-nums[index]
        */

        Map<Integer, Integer> requiredSum = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (requiredSum.get(nums[i]) != null) {
                return new int[]{requiredSum.get(nums[i]), i};
            }
            int diff = target - nums[i];
            requiredSum.put(diff, i);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Case 1: " + Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println("Case 2: " + Arrays.toString(twoSum(new int[]{3, 2, 4}, 6)));
        System.out.println("Case 3: " + Arrays.toString(twoSum(new int[]{3, 3}, 6)));
    }
}
