package leetcode.tree._222;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/count-complete-tree-nodes/">222. Count Complete Tree Nodes</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class CountCompleteTreeNodes {

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
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int leftHeight = leftHeight(root);
            int rightHeight = rightHeight(root);
            if (leftHeight == rightHeight) {
                return (1 <<leftHeight + 1) - 1; // Math.pow(2, leftHeight + 1)
            } else {
                int leftNodes = countNodes(root.left);
                int rightNodes = countNodes(root.right);
                int res = 1 + leftNodes + rightNodes;
                return res;
            }
        }

        private int rightHeight(TreeNode root) {
            if (root == null) {
                return -1;
            }
            return 1 + rightHeight(root.right);
        }

        private int leftHeight(TreeNode root) {
            if (root == null) {
                return -1;
            }
            return 1 + leftHeight(root.left);
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
        Solution solution = new CountCompleteTreeNodes().new Solution();
        TreeNode right_left = new CountCompleteTreeNodes().new TreeNode(6, null, null);
        TreeNode right_right = new CountCompleteTreeNodes().new TreeNode(7, null, null);
        TreeNode left_left = new CountCompleteTreeNodes().new TreeNode(4, null, null);
        TreeNode left_right = new CountCompleteTreeNodes().new TreeNode(5, null, null);
        TreeNode left = new CountCompleteTreeNodes().new TreeNode(2, left_left, left_right);
        TreeNode right = new CountCompleteTreeNodes().new TreeNode(3, right_left, null);
        TreeNode root = new CountCompleteTreeNodes().new TreeNode(1, left, right);
        solution.print(root);
        System.out.println();
        System.out.println(new CountCompleteTreeNodes().new Solution().countNodes(root));
        System.out.println("4 << 3 = " + (4<<3));
    }
}
