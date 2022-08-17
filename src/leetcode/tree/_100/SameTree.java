package leetcode.tree._100;

/**
 * Problem link: <a href="https://leetcode.com/problems/same-tree/">100. Same Tree</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */

public class SameTree {
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
        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            } else if (p == null || q == null) {
                return false;
            } else {
                if (p.val != q.val) {
                    return false;
                }
                return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode left = new SameTree().new TreeNode(5, null, null);
        TreeNode right_right = new SameTree().new TreeNode(8, null, null);
        TreeNode right = new SameTree().new TreeNode(9, null, right_right);
        TreeNode root = new SameTree().new TreeNode(6, left, right);
        System.out.println("Same tree(root, root): " + new SameTree().new Solution().isSameTree(root, root));
        TreeNode root_1 = new SameTree().new TreeNode(16, left, right);
        System.out.println("Same tree(root, root_1): " + new SameTree().new Solution().isSameTree(root, root_1));
    }
}
