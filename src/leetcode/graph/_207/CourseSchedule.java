package leetcode.graph._207;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Problem link: <a href="https://leetcode.com/problems/course-schedule/">207. Course Schedule</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class CourseSchedule {

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
        private List<Integer>[] coursePrerequisite = null;
        private HashSet<Integer> visitingCourses = new HashSet<>();

        public boolean canFinish(int numCourses, int[][] prerequisites) {
            coursePrerequisite = (ArrayList<Integer>[]) Array.newInstance(ArrayList.class, numCourses);
            for (int i = 0; i < numCourses; i++) {
                coursePrerequisite[i] = new ArrayList<>();
            }

            for (int i = 0; i < prerequisites.length; i++) {
                int courseIndex = prerequisites[i][0];
                int prerequisiteCourseIndex = prerequisites[i][1];
                coursePrerequisite[courseIndex].add(prerequisiteCourseIndex);
            }

            for (int i = 0; i < numCourses; i++) {
                if (!dfs(i)) {
                    return false;
                }
            }

            return true;
        }

        private boolean dfs(int courseIndex) {
            if (visitingCourses.contains(courseIndex)) {
                return false;
            }
            if (coursePrerequisite[courseIndex].size() == 0) {
                return true;
            }
            visitingCourses.add(courseIndex);
            for (int prerequisiteCourseIndex : coursePrerequisite[courseIndex]) {
                if (!dfs(prerequisiteCourseIndex)) {
                    return false;
                }
            }
            visitingCourses.remove(courseIndex);
            coursePrerequisite[courseIndex] = new ArrayList<>();
            return true;
        }
    }

    public static void main(String[] args) {
        boolean canFinish = new CourseSchedule().new Solution().canFinish(2, new int[][]{{1, 0}});
        System.out.println("canFinish = " + canFinish);
    }
}
/**
 * class Solution:
 * def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
 * prereq = {c: [] for c in range(numCourses)}
 * for crs, pre in prerequisites:
 * prereq[crs].append(pre)
 * <p>
 * output = []
 * visit, cycle = set(), set()
 * <p>
 * def dfs(crs):
 * if crs in cycle:
 * return False
 * if crs in visit:
 * return True
 * <p>
 * cycle.add(crs)
 * for pre in prereq[crs]:
 * if dfs(pre) == False:
 * return False
 * cycle.remove(crs)
 * visit.add(crs)
 * output.append(crs)
 * return True
 * <p>
 * for c in range(numCourses):
 * if dfs(c) == False:
 * return []
 * return output
 */