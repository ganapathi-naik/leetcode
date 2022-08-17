package leetcode.heap._692;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Problem link: <a href="https://leetcode.com/problems/top-k-frequent-words/">692. Top K Frequent Words</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class TopKFrequentWords {

    class Solution {
        public List<String> topKFrequent(String[] words, int k) {
            Map<String, Integer> map = new HashMap<>();
            for (String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }

            PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>((entry1, entry2) -> {
                if (entry1.getValue().equals(entry2.getValue())) {
                    return entry2.getKey().compareTo(entry1.getKey());
                }
                return entry1.getValue() - entry2.getValue();
            });
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                priorityQueue.offer(entry);
                if (priorityQueue.size() > k) {
                    priorityQueue.poll();
                }
            }
            List<String> list = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                list.add(priorityQueue.poll().getKey());
            }
            Collections.reverse(list);
            return list;
        }
    }

    public static void main(String[] args) {
        List<String> topKFrequent = new TopKFrequentWords().new Solution().topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2);
        System.out.println(topKFrequent);
        topKFrequent = new TopKFrequentWords().new Solution().topKFrequent(new String[]{"i", "love", "leetcode"}, 2);
        System.out.println(topKFrequent);
    }
}
