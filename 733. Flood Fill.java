/*
733.
An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

Example 1:
Input: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: 
From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
by a path of the same color as the starting pixel are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected
to the starting pixel.
Note:

The length of image and image[0] will be in the range [1, 50].
The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
*/
//BFS 10% 5%
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if(color == newColor) return image;
        Stack<List<Integer>> stack = new Stack<>();
        List<Integer> first = new ArrayList<>();
        first.add(sr);
        first.add(sc);
        stack.push(first);
        

        while(!stack.isEmpty()){
            List<Integer> temp = stack.pop();
            int x = temp.get(0), y = temp.get(1);
            image[x][y] = newColor;
            List<Integer> l;
            if(x-1 >= 0 && image[x-1][y] == color){
                l = new ArrayList<>();
                l.add(x-1);
                l.add(y);
                stack.push(l);
            }
            if(y-1 >= 0 && image[x][y-1] == color){
                l = new ArrayList<>();
                l.add(x);
                l.add(y-1);
                stack.push(l);
            }
            if(x+1 < image.length && image[x+1][y] == color){
                l = new ArrayList<>();
                l.add(x+1);
                l.add(y);
                stack.push(l);
            }
            if(y+1 < image[0].length && image[x][y+1] == color){
                l = new ArrayList<>();
                l.add(x);
                l.add(y+1);
                stack.push(l);
            }
        }
        return image;
        
    }
}
//DFS 63% 7%
class Solution {
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