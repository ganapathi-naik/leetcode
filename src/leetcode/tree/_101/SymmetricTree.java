package leetcode.tree._101;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/symmetric-tree/">101. Symmetric Tree</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class SymmetricTree {
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
        public boolean isSymmetric(TreeNode root) {
            if(root == null) {
                return true;
            }
            return isSymmetric(root.left, root.right);
        }

        private boolean isSymmetric(TreeNode left, TreeNode right) {
            if(left == null && right == null) {
                return true;
            } else if(left == null || right == null) {
                return false;
            } else {
                if (left.val != right.val) {
                    return false;
                }
                return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
            }
        }
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

    public static void main(String[] args) {
        TreeNode left = new SymmetricTree().new TreeNode(5, null, null);
        TreeNode right_right = new SymmetricTree().new TreeNode(8, null, null);
        TreeNode right = new SymmetricTree().new TreeNode(9, null, right_right);
        TreeNode root = new SymmetricTree().new TreeNode(6, left, right);

        System.out.println("\nInput tree:");
        new SymmetricTree().print(root);

        System.out.println("Is binary tree symmetric: " + new SymmetricTree().new Solution().isSymmetric(root));

        TreeNode left_left = new SymmetricTree().new TreeNode(3, null, null);
        TreeNode left_right = new SymmetricTree().new TreeNode(4, null, null);
        TreeNode right_left = new SymmetricTree().new TreeNode(4, null, null);
        right_right = new SymmetricTree().new TreeNode(3, null, null);
        left = new SymmetricTree().new TreeNode(2, left_left, left_right);
        right = new SymmetricTree().new TreeNode(2, right_left, right_right);
        root = new SymmetricTree().new TreeNode(1, left, right);

        System.out.println("\nInput tree:");
        new SymmetricTree().print(root);

        System.out.println("Is binary tree symmetric: " + new SymmetricTree().new Solution().isSymmetric(root));
    }
}
