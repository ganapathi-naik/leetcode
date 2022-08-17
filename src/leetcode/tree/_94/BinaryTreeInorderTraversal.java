package leetcode.tree._94;

import java.util.List;
import java.util.LinkedList;

/**
 * Problem link: <a href="https://leetcode.com/problems/binary-tree-inorder-traversal/">94. Binary Tree Inorder Traversal</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class BinaryTreeInorderTraversal {
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
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> inorderTraversal = new LinkedList<>();
            if (root != null) {
                inorderTraversal(root, inorderTraversal);
            }
            return inorderTraversal;
        }

        private void inorderTraversal(TreeNode node, List<Integer> result) {
            if (node == null) {
                return;
            }
            inorderTraversal(node.left, result);
            result.add(node.val);
            inorderTraversal(node.right, result);
        }
    }

    public static void main(String[] args) {
        System.out.println(new BinaryTreeInorderTraversal().new Solution().inorderTraversal(null));
    }
}
