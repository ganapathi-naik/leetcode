package leetcode.sorting._347;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Problem link: <a href="https://leetcode.com/problems/top-k-frequent-elements/">347. Top K Frequent Elements</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class TopKFrequentElements {

    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            String[] frequencyArray = new String[nums.length + 1];
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                String value = frequencyArray[entry.getValue()];
                if (value == null) {
                    frequencyArray[entry.getValue()] = "" + entry.getKey();
                } else {
                    frequencyArray[entry.getValue()] = value + "," + entry.getKey();
                }
            }
            int[] topKFrequent = new int[k];
            int count = 0;
            for (int i = frequencyArray.length - 1; i > 0; i--) {
                if (count == k) {
                    break;
                }
                if (frequencyArray[i] != null) {
                    String value = frequencyArray[i];
                    String[] s = value.split(",");
                    for(int j = 0; j < s.length && count != k; j++) {
                        topKFrequent[count++] = Integer.valueOf(s[j]);
                    }
                }
            }
            return topKFrequent;
        }
    }

    public static void main(String[] args) {
//        int[] topKFrequent = new TopKFrequentElements().new Solution().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
//        System.out.println(Arrays.toString(topKFrequent));
        int[] topKFrequent = new TopKFrequentElements().new Solution().topKFrequent(new int[]{-1, -1}, 1);
        System.out.println(Arrays.toString(topKFrequent));
    }
}