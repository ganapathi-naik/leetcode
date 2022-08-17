package leetcode.array._994._1;

/**
 * Problem link: <a href="https://leetcode.com/problems/rotting-oranges/">994. Rotten Oranges</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */

public class RottenOranges {
    class Solution {

        public boolean isSafe(int[][] grid, int i, int j) {
            int n = grid.length;
            int m = grid[0].length;
            return (i >= 0 && j >= 0 && i < n && j < m);
        }

        public int orangesRotting(int[][] grid) {
            int days = 2;
            boolean flag = false;
            int n = grid.length;
            int m = grid[0].length;
            while (true) {

                for (int i = 0; i < n; i++) {

                    for (int j = 0; j < m; j++) {

                        if (grid[i][j] == days) {
                            if (isSafe(grid, i + 1, j) && grid[i + 1][j] == 1) {
                                grid[i + 1][j] = grid[i][j] + 1;
                                flag = true;
                            }
                            if (isSafe(grid, i, j + 1) && grid[i][j + 1] == 1) {
                                grid[i][j + 1] = grid[i][j] + 1;
                                flag = true;
                            }
                            if (isSafe(grid, i - 1, j) && grid[i - 1][j] == 1) {
                                grid[i - 1][j] = grid[i][j] + 1;
                                flag = true;
                            }
                            if (isSafe(grid, i, j - 1) && grid[i][j - 1] == 1) {
                                grid[i][j - 1] = grid[i][j] + 1;
                                flag = true;
                            }
                        }
                    } // end of for j
                } // end of for i
                if (flag == false) {
                    break;
                }
                flag = false;
                days++;
            } // end of while


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == 1) {
                        days = -1;
                    }
                }
            }
            return days == -1 ? days : days - 2;
        }
    }

    public static void main(String[] args) {
        new RottenOranges().new Solution().orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
    }

}
