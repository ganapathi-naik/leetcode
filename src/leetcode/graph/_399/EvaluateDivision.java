package leetcode.graph._399;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateDivision {
    class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            Map<String, Map<String, Double>> adjacencyMap = new HashMap<>();
            for (int i = 0; i < equations.size(); i++) {
                Map<String, Double> map1 = adjacencyMap.getOrDefault(equations.get(i).get(0), new HashMap<>());
                Map<String, Double> map2 = adjacencyMap.getOrDefault(equations.get(i).get(1), new HashMap<>());
                map1.put(equations.get(i).get(1), values[i]);
                map2.put(equations.get(i).get(0), 1 / values[i]);

                adjacencyMap.put(equations.get(i).get(0), map1);
                adjacencyMap.put(equations.get(i).get(1), map2);
            }
            double[] result = new double[queries.size()];
            for (int i = 0; i < queries.size(); i++) {
                List<String> query = queries.get(i);
                Set<String> visited = new HashSet<>();
                Double equationValue = dfs(adjacencyMap, query.get(0), query.get(1), visited);
                result[i] = equationValue == null ? -1.0 : equationValue;
            }
            return result;
        }

        private Double dfs(Map<String, Map<String, Double>> adjacencyMap, String from, String to, Set<String> visited) {
            if (visited.contains(from)) {
                return null;
            }
            if (!adjacencyMap.containsKey(from) || !adjacencyMap.containsKey(to)) {
                return null;
            }
            if (adjacencyMap.get(from) != null && adjacencyMap.get(from).get(to) != null) {
                return adjacencyMap.get(from).get(to);
            }
            visited.add(from);
            Map<String, Double> fromNode = adjacencyMap.get(from);
            Set<Map.Entry<String, Double>> entries = fromNode.entrySet();
            for (Map.Entry<String, Double> entry : entries) {
                Double equationValue = dfs(adjacencyMap, entry.getKey(), to, visited);
                if (equationValue != null) {
                    return equationValue * entry.getValue();
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        double[] result = new EvaluateDivision().new Solution().calcEquation(
                Arrays.asList(
                        Arrays.asList("a", "b"), Arrays.asList("b", "c")
                ),
                new double[]{2.0, 3.0},
                Arrays.asList(
                        Arrays.asList("a", "c"),
                        Arrays.asList("b", "a"),
                        Arrays.asList("a", "e"),
                        Arrays.asList("a", "a"),
                        Arrays.asList("x", "x")
                ));
        System.out.println(Arrays.toString(result));
    }
}
