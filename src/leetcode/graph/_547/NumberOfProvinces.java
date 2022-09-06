package leetcode.graph._547;

/**
 * Problem link: <a href="https://leetcode.com/problems/number-of-provinces/">547. Number of Provinces</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */

public class NumberOfProvinces {
    class Solution {
        public int findCircleNum(int[][] isConnected) {
            boolean[] visited = new boolean[isConnected.length];
            int connectedComponents = 0;
            for (int i = 0; i < isConnected.length; i++) {
                if (!visited[i]) {
                    ++connectedComponents;
                    dfs(isConnected, i, visited);
                }
            }
            return connectedComponents;
        }

        private void dfs(int[][] isConnected, int from, boolean[] visited) {
            if (visited[from]) {
                return;
            }
            visited[from] = true;
            for (int i = 0; i < isConnected.length; i++) {
                if (isConnected[from][i] != 0) {
                    dfs(isConnected, i, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        int numberOfProvinces = new NumberOfProvinces().new Solution().findCircleNum(new int[][]{{1, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 1}});
        System.out.println("numberOfProvinces = " + numberOfProvinces);
    }
}
