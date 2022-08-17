package leetcode.tree._108;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/">108. Convert Sorted Array to Binary Search Tree</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class ConvertSortedArrayToBinarySearchTree {

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
        public TreeNode sortedArrayToBST(int[] nums) {
            if (nums.length == 1) {
                return new TreeNode(nums[0]);
            }
            return constructTree(nums);
        }

        private TreeNode constructTree(int[] nums) {
            if (nums.length == 0) {
                return null;
            }
            if(nums.length == 1) {
                return new TreeNode(nums[0]);
            }
            int mid = nums.length / 2;
            int[] left = new int[mid];
            int[] right = new int[nums.length - mid - 1];

            for (int i = 0; i < left.length; i++) {
                left[i] = nums[i];
            }
            for (int i = 0, j = mid + 1; i < right.length; i++) {
                right[i] = nums[j++];
            }
            TreeNode midNode = new TreeNode(nums[mid]);
            midNode.left = constructTree(left);
            midNode.right = constructTree(right);
            return midNode;
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
        Solution solution = new ConvertSortedArrayToBinarySearchTree().new Solution();

        TreeNode root = solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        solution.print(root);
//        System.out.println();
//        TreeNode root1 = solution.buildTree(new int[]{9, 7, 3, 15, 20, 18, 25}, new int[]{9, 7, 15, 20, 18, 25, 3});
//        solution.print(root1);

    }
}
