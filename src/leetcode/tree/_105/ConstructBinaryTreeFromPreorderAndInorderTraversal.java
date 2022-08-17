package leetcode.tree._105;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem link: <a href="https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/">105. Construct Binary Tree from Preorder and Inorder Traversal</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

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
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0 || inorder.length == 0) {
                return null;
            }

            TreeNode root = new TreeNode(preorder[0]);

            int mid = index(inorder, preorder[0]);

            int[] preorderSubArray1 = getSubArray(preorder, 1, mid);
            int[] inorderSubArray1 = getSubArray(inorder, 0, mid - 1);
            root.left = buildTree(preorderSubArray1, inorderSubArray1);

            int[] preorderSubArray2 = getSubArray(preorder, mid + 1, preorder.length - 1);
            int[] inorderSubArray2 = getSubArray(inorder, mid + 1, inorder.length - 1);
            root.right = buildTree(preorderSubArray2, inorderSubArray2);

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

        private void inorder(TreeNode root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }

        private void postorder(TreeNode root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            inorder(root.right);
            System.out.print(root.val + " ");
        }
    }

    public static void main(String[] args) {
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();

        TreeNode root = solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
        solution.print(root);
//        solution.inorder(root);
//        System.out.println();
//        solution.postorder(root);
//        System.out.println();
//        TreeNode root1 = solution.buildTree(new int[]{3, 9, 7, 20, 15, 18, 25}, new int[]{9, 7, 3, 15, 20, 18, 25});
//        solution.print(root1);
//        solution.inorder(root1);
//        System.out.println();
//        solution.postorder(root1);
    }
}
