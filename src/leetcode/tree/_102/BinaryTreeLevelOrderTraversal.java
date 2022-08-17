package leetcode.tree._102;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/">102. Binary Tree Level Order Traversal</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class BinaryTreeLevelOrderTraversal {
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> levelOrder = new ArrayList<>();
            if(root == null) {
                return levelOrder;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while(!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> level = new ArrayList<>(size);
                while(size > 0) {
                    TreeNode node = queue.poll();
                    if(node.left != null) {
                        queue.add(node.left);
                    }
                    if(node.right != null) {
                        queue.add(node.right);
                    }
                    level.add(node.val);
                    size--;
                }
                levelOrder.add(level);
            }
            return levelOrder;
        }
    }

    public static void main(String[] args) {
        TreeNode left = new BinaryTreeLevelOrderTraversal().new TreeNode(5, null, null);
        TreeNode right_right = new BinaryTreeLevelOrderTraversal().new TreeNode(8, null, null);
        TreeNode right = new BinaryTreeLevelOrderTraversal().new TreeNode(9, null, right_right);
        TreeNode root = new BinaryTreeLevelOrderTraversal().new TreeNode(6, left, right);
        System.out.println(new BinaryTreeLevelOrderTraversal().new Solution().levelOrder(root));
    }
}
