package leetcode.tree._124;

import leetcode.tree._103.BinaryTreeZigzagLevelOrderTraversal;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/">124. Binary Tree Maximum Path Sum</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class BinaryTreeMaximumPathSum {

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
        private int globalSum = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            maxPathSumHelper(root);
            return globalSum;
        }

        private int maxPathSumHelper(TreeNode root) {
            if (root == null) {
                return 0;
            }

            /**
             * self          = x
             * self_left     = x + y
             * self_right    = x + z
             * all           = x + y + z
             *
             * y = -1
             * z = -2
             * x = +2
             *
             *
             *      2
             *    /   \
             * -1      -2
             *
             *
             * int maxSum = Math.sum(
             *     self,
             *     self_left,
             *     self_right,
             *     all
             * );
             *
             * int maxSum = Math.sum(
             *     x + 0,
             *     x + y,
             *     x + z,
             *     x + y + z
             * );
             *
             * int maxSum = x + Math.sum(
             *     0,
             *     y,
             *     z,
             *     y + z
             * );
             *
             *
             * int maxSum = x + Math.sum(
             *     y,
             *     z,
             *     y + z
             * );
             */

            int left = maxPathSumHelper(root.left); //   y
            int right = maxPathSumHelper(root.right); // z

            int maxSum = root.val + Math.max(0, Math.max(Math.max(left, right), left + right));
            //int maxSum = x      + math.max(Math.max( y   ,  z  ),  y    +  z  );

            // int maxSum = 2      + math.max(Math.max( -1   ,  -2 ),  -1    +  -2);

            // 1

            int linkableMaxSum = root.val + Math.max(right, left);

            globalSum = Math.max(maxSum, globalSum);

            return linkableMaxSum;
        }

        public void print(TreeNode root) {
            if (root == null) {
                return;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {

                int numberOfNodeInQueue = queue.size();

                while (numberOfNodeInQueue > 0) {
                    TreeNode node = queue.poll();
                    System.out.print(node.val + " ");
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    numberOfNodeInQueue--;
                }
                System.out.println();
            }
        }
    }


    public static void main(String[] args) {
        Solution solution = new BinaryTreeMaximumPathSum().new Solution();
        TreeNode left = new BinaryTreeMaximumPathSum().new TreeNode(-1, null, null);
        TreeNode right = new BinaryTreeMaximumPathSum().new TreeNode(-2, null, null);
        TreeNode root = new BinaryTreeMaximumPathSum().new TreeNode(2, left, right);
        solution.print(root);
        System.out.println();
        System.out.println(new BinaryTreeMaximumPathSum().new Solution().maxPathSum(root));
    }
}
