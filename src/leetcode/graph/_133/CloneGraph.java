package leetcode.graph._133;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem link: <a href="https://leetcode.com/problems/clone-graph/">133. Clone Graph</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class CloneGraph {

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    class Solution {

        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }

            Node[] visitedNodes = new Node[101];

            Node copy = new Node(node.val);
            visitedNodes[node.val] = copy;

            for (Node currentNode : node.neighbors) {
                if (visitedNodes[currentNode.val] == null) {
                    Node newNode = new Node(currentNode.val);
                    copy.neighbors.add(newNode);
                    dfs(currentNode, newNode, visitedNodes);
                } else {
                    copy.neighbors.add(visitedNodes[currentNode.val]);
                }
            }
            return copy;
        }

        private void dfs(Node currentNode, Node currentClonedNode, Node[] visitedNodes) {
            visitedNodes[currentClonedNode.val] = currentClonedNode;
            for (Node node : currentNode.neighbors) {
                if (visitedNodes[node.val] == null) {
                    Node newNode = new Node(node.val);
                    visitedNodes[currentClonedNode.val].neighbors.add(newNode);
                    dfs(node, newNode, visitedNodes);
                } else {
                    visitedNodes[currentClonedNode.val].neighbors.add(visitedNodes[node.val]);
                }
            }
        }
    }

    public static void main(String[] args) {
        Node node1 = new CloneGraph().new Node(1, new ArrayList<>());
        Node node2 = new CloneGraph().new Node(2, new ArrayList<>());
        Node node3 = new CloneGraph().new Node(3, new ArrayList<>());
        Node node4 = new CloneGraph().new Node(4, new ArrayList<>());
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);

        new CloneGraph().new Solution().cloneGraph(node1);
    }
}
