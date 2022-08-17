package leetcode.graph._210;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Problem link: <a href="https://leetcode.com/problems/course-schedule-ii/">210. Course Schedule II</a><br/>
 * Author: <a href="https://github.com/ganapathi-naik">Ganapathi Naik</a><br/>
 */
public class CourseSchedule2 {

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
        private List<Integer>[] coursePrerequisites = null;
        private HashSet<Integer> visitingCourses = new HashSet<>();
        private int[] courseOrder = null;
        private int courseOrderIndex = 0;

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            coursePrerequisites = (ArrayList<Integer>[]) Array.newInstance(ArrayList.class, numCourses);
            courseOrder = new int[numCourses];

            for (int i = 0; i < numCourses; i++) {
                coursePrerequisites[i] = new ArrayList<>();
            }

            for (int i = 0; i < prerequisites.length; i++) {
                int courseIndex = prerequisites[i][0];
                int prerequisiteCourseIndex = prerequisites[i][1];
                coursePrerequisites[courseIndex].add(prerequisiteCourseIndex);
            }

            for (int i = 0; i < numCourses; i++) {
                if (!canFinish(i)) {
                    return new int[0];
                }
            }

            return courseOrder;
        }

        private boolean canFinish(int courseIndex) {
            if (visitingCourses.contains(courseIndex)) {
                return false;
            }
            if (coursePrerequisites[courseIndex].size() == 0) {
                return true;
            }

            visitingCourses.add(courseIndex);
            for (int prerequisiteIndex : coursePrerequisites[courseIndex]) {
                if (!canFinish(prerequisiteIndex)) {
                    return false;
                }
            }
            visitingCourses.remove(courseIndex);
            coursePrerequisites[courseIndex] = new ArrayList<>();
            courseOrder[courseOrderIndex++] = courseIndex;
            return true;
        }
    }

    public static void main(String[] args) {
        int[] order = new CourseSchedule2().new Solution().findOrder(2, new int[][]{{1, 0}});
        System.out.println("order = " + Arrays.toString(order));
    }
}
/**
 * class Solution:
 *     def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
 *         prereq = {c: [] for c in range(numCourses)}
 *         for crs, pre in prerequisites:
 *             prereq[crs].append(pre)
 *
 *         output = []
 *         visit, cycle = set(), set()
 *
 *         def dfs(crs):
 *             if crs in cycle:
 *                 return False
 *             if crs in visit:
 *                 return True
 *
 *             cycle.add(crs)
 *             for pre in prereq[crs]:
 *                 if dfs(pre) == False:
 *                     return False
 *             cycle.remove(crs)
 *             visit.add(crs)
 *             output.append(crs)
 *             return True
 *
 *         for c in range(numCourses):
 *             if dfs(c) == False:
 *                 return []
 *         return output
 */