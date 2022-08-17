package leetcode.array._994._2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/rotting-oranges/">994. Rotten Oranges</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */

class RottenOranges {
    class Solution {
        Queue<Pair> q = new LinkedList<>();
        public int orangesRotting(int[][] grid) {
            int freshOranges = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 2) {
                        q.offer(new Pair(i, j));
                    } else if (grid[i][j] == 1) {
                        freshOranges += 1;
                    }
                }
            }

            if (q.isEmpty()) { // No oranges OR No rotten oranges
                return freshOranges > 0 ? 0 : -1;
            } else { // There are some rotten oranges
                int time = -1;

                while (!q.isEmpty()) {
                    int rottenOrangesFoundLastMin = q.size();

                    while (rottenOrangesFoundLastMin-- > 0) {
                        Pair p = q.poll();
                        addAllValidNeighboursToQueue(p, grid);
                    }
                    time++;
                    freshOranges -= q.size();
                }

                return freshOranges > 0 ? -1 : time;
            }
        }

        private void addAllValidNeighboursToQueue(Pair p, int[][] grid) {
            add(p.r - 1, p.c, grid);
            add(p.r + 1, p.c, grid);
            add(p.r, p.c - 1, grid);
            add(p.r, p.c + 1, grid);
        }

        private void add(int row, int col, int[][] grid) {
            if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1) {
                grid[row][col] = 2; // Fresh Orange turned to rotten
                q.offer(new Pair(row, col)); // Adding rotten Orange to queue
            }
        }

        class Pair {
            int r;
            int c;

            public Pair(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Total time need in min = " + new RottenOranges().new Solution().orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 2}}));
    }

}
