package leetcode.tree._95;

import java.util.List;
import java.util.LinkedList;

/**
 * Problem link: <a href="https://leetcode.com/problems/unique-binary-search-trees-ii/">95. Unique Binary Search Trees II</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class UniqueBinarySearchTrees_II {
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
        public List<TreeNode> generateTrees(int n) {
            return generateTrees(1, n);
        }

        private List<TreeNode> generateTrees(int start, int end) {
            List<TreeNode> trees = new LinkedList<>();
            if(start > end) {
                trees.add(null);
                return trees;
            }

            if(start == end) {
                trees.add(new TreeNode(start));
                return trees;
            }

            for(int i = start; i <= end; i++) {
                List<TreeNode> leftSubTrees = generateTrees(start, i-1);
                List<TreeNode> rightSubTrees = generateTrees(i+1, end);

                for(TreeNode lRoot: leftSubTrees) {
                    for(TreeNode rRoot: rightSubTrees) {
                        TreeNode treeNode = new TreeNode(i, lRoot, rRoot);
                        trees.add(treeNode);
                    }
                }
            }
            return trees;
        }
    }
    public static void main(String[] args) {
        System.out.println(new UniqueBinarySearchTrees_II().new Solution().generateTrees(3));
    }
}