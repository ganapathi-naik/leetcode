package leetcode.tree._99;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/recover-binary-search-tree/">99. Recover Binary Search Tree</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class RecoverBinarySearchTree {

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

        private TreeNode first;
        private TreeNode second;
        private TreeNode prev = new TreeNode(Integer.MIN_VALUE);

        /**
         * Time complexity  = O(log n)
         * Space complexity = O(n)
         */
        public void recoverTree(TreeNode root) {
            inorder(root);
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

        private void inorder(TreeNode root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            if (first == null && prev.val > root.val) {
                first = prev;
            }
            if (first != null && prev.val > root.val) {
                second = root;
            }
            prev = root;
            inorder(root.right);
        }

        /**
         * Time complexity  = O(log n)
         * Space complexity = O(1)
         */
        public void recoverTreeUsingMorrisThreadedBinaryTreeTraversal(TreeNode root) {
            while (root != null) {
                TreeNode left = root.left;
                if (left == null) {
                    if (first == null && prev.val > root.val) {
                        first = prev;
                    }
                    if (first != null && prev.val > root.val) {
                        second = root;
                    }
                    prev = root;
                    root = root.right;
                } else {
                    TreeNode rightMostNode = getRightMostNode(left, root);
                    if (rightMostNode.right == null) {
                        rightMostNode.right = root;
                        root = root.left;
                    } else {
                        rightMostNode.right = null;
                        if (first == null && prev.val > root.val) {
                            first = prev;
                        }
                        if (first != null && prev.val > root.val) {
                            second = root;
                        }
                        prev = root;
                        root = root.right;
                    }
                }
            }
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }

        private TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != curr) {
                node = node.right;
            }
            return node;
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
        TreeNode left = new RecoverBinarySearchTree().new TreeNode(5, null, null);
        TreeNode right_right = new RecoverBinarySearchTree().new TreeNode(8, null, null);
        TreeNode right = new RecoverBinarySearchTree().new TreeNode(9, null, right_right);
        TreeNode root = new RecoverBinarySearchTree().new TreeNode(6, left, right);
        new RecoverBinarySearchTree().print(root);
        new RecoverBinarySearchTree().new Solution().recoverTreeUsingMorrisThreadedBinaryTreeTraversal(root);
        new RecoverBinarySearchTree().print(root);
        new RecoverBinarySearchTree().new Solution().recoverTree(root);
        //new RecoverBinarySearchTree().print(root);
    }
}
