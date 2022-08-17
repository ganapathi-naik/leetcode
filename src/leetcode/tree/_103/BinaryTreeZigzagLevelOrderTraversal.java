package leetcode.tree._103;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/">103. Binary Tree Zigzag Level Order Traversal</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class BinaryTreeZigzagLevelOrderTraversal {

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
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> zigzagLevelOrder = new ArrayList<>(0);
            if (root == null) {
                return zigzagLevelOrder;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean leftToRight = true;
            while (!queue.isEmpty()) {
                int size = queue.size();
                Integer[] level = new Integer[size];
                for (int i = size; i > 0; i--) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    int index = leftToRight ? (size - i) : (i - 1);
                    level[index] = node.val;
                }
                zigzagLevelOrder.add(Arrays.asList(level));
                leftToRight = !leftToRight;
            }
            return zigzagLevelOrder;
        }
    }

    public static void main(String[] args) {
        TreeNode left = new BinaryTreeZigzagLevelOrderTraversal().new TreeNode(9, null, null);
        TreeNode right_left = new BinaryTreeZigzagLevelOrderTraversal().new TreeNode(15, null, null);
        TreeNode right_right = new BinaryTreeZigzagLevelOrderTraversal().new TreeNode(7, null, null);
        TreeNode right = new BinaryTreeZigzagLevelOrderTraversal().new TreeNode(20, right_left, right_right);
        TreeNode root = new BinaryTreeZigzagLevelOrderTraversal().new TreeNode(3, left, right);

        System.out.println(new BinaryTreeZigzagLevelOrderTraversal().new Solution().zigzagLevelOrder(root));
    }
}
