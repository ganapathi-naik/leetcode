package leetcode.tree._106;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/">106. Construct Binary Tree from Inorder and Postorder Traversal</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

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
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (inorder.length == 0 && postorder.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(postorder[postorder.length - 1]);
            int mid = index(inorder, postorder[postorder.length - 1]);
            int[] inorderSubArray1 = getSubArray(inorder, 0, mid - 1);
            int[] postorderSubArray1 = getSubArray(postorder, 0, mid - 1);
            root.left = buildTree(inorderSubArray1, postorderSubArray1);

            int[] inorderSubArray2 = getSubArray(inorder, mid + 1, postorder.length - 1);
            int[] postorderSubArray2 = getSubArray(postorder, mid, postorder.length - 2);
            root.right = buildTree(inorderSubArray2, postorderSubArray2);
            return root;
        }

        private int index(int[] array, int value) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == value) {
                    return i;
                }
            }
            return -1;
        }

        private int[] getSubArray(int[] array, int startIndex, int endIndex) {
            int length = endIndex - startIndex + 1;
            int[] subArray = new int[length];
            int index = 0;
            for (int i = startIndex; i <= endIndex; i++) {
                subArray[index++] = array[i];
            }
            return subArray;
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
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();

        TreeNode root = solution.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
        solution.print(root);
//        System.out.println();
//        TreeNode root1 = solution.buildTree(new int[]{9, 7, 3, 15, 20, 18, 25}, new int[]{9, 7, 15, 20, 18, 25, 3});
//        solution.print(root1);

    }
}
