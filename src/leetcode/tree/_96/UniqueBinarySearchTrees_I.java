package leetcode.tree._96;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem link: <a href="https://leetcode.com/problems/unique-binary-search-trees/">96. Unique Binary Search Trees</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class UniqueBinarySearchTrees_I {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int numTrees(int n) {
            List<Integer> numTrees = new ArrayList<>(n);
            numTrees.add(0, 1);
            numTrees.add(1,1);

            for(int i = 2; i <= n; i++) {
                int sum = 0;
                for(int j = 1; j <= i; j++) {
                    int leftSubTrees = numTrees.get(j-1);
                    int rightSubTrees = numTrees.get(i-j);
                    sum += (leftSubTrees * rightSubTrees);
                }
                numTrees.add(i, sum);
            }
            return numTrees.get(n);
        }
    }
    public static void main(String[] args) {
        System.out.println(new UniqueBinarySearchTrees_I().new Solution().numTrees(4));
    }
}
