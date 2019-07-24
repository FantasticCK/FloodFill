package com.CK;

import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        int[] a = {1, 1, 1};
        int[] b = {1, 1, 0};
        int[] c = {1, 0, 1};

        int[][] floodFill = {a, b, c};
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.floodFill(floodFill, 1, 1, 2)));
    }
}

class Solution {
    private int[][] direction = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private boolean[][] visited;
    private Stack<int[]> dfsStack = new Stack<>();

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image.length == 0 || image[0].length == 0) return image;
        visited = new boolean[image.length][image[0].length];
        dfsStack.push(new int[]{sr, sc});
        dfs(image,dfsStack,newColor,image[sr][sc]);
        image[sr][sc] = newColor;
        return image;
    }

    private void dfs(int[][] image, Stack<int[]> dfsStack, int newColor, int toFindColor) {
        while (!dfsStack.isEmpty()) {
            int[] origin = dfsStack.pop();
            visited[origin[0]][origin[1]] = true;
            for (int i = 0; i < 4; i++) {
                int nextR = origin[0] + direction[i][0];
                int nextC = origin[1] + direction[i][1];
                if (validMove(image, nextR, nextC, toFindColor) && !visited[nextR][nextC]) {
                    dfsStack.push(new int[]{nextR,nextC});
                    image[nextR][nextC] = newColor;
                    visited[nextR][nextC] = true;
                    dfs(image, dfsStack, newColor, toFindColor);
                }
            }
        }
    }

    private boolean validMove(int[][] image, int r, int c, int color) {
        return r >= 0 && r < image.length && c >= 0 && c < image[0].length && image[r][c] == color;
    }
}

// DFS
class Solution2 {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void fill(int[][] image, int sr, int sc, int color, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) return;
        image[sr][sc] = newColor;
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }
}