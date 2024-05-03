import java.io.*;
import java.util.*;
public class Baek_3085 {
    static char[][] maze;
    static int N;
    static ArrayList<Point[]> points = new ArrayList<>();
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static int answer =0;
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static boolean isOK(int a, int b) {
        return (0<=a && a<N && 0<=b && b<N);
    }
    
    public static int DFS(int a, int b, char[][] test, int dir, char color) {
        if(isOK(a+dir_x[dir], b+dir_y[dir])) {
            if(test[a+dir_x[dir]][b+dir_y[dir]] == color)
                return 1 + DFS(a+dir_x[dir], b+dir_y[dir], test, dir, color);
            else
                return 0;
        }
        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        maze = new char[N][N];

        for(int i=0; i<N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++)
                maze[i][j] = row.charAt(j);
        }

        for(int i=0; i<N; i++) {
            for (int j = 0; j < N-1; j++) {
                for(int k=0; k<4; k++) {
                    int dx = i + dir_x[k];
                    int dy = j + dir_y[k];
                    if(isOK(dx, dy)) {
                        if(maze[i][j] != maze[dx][dy]) {
                            Point[] pt = new Point[2];
                            pt[0] = new Point(i, j);
                            pt[1] = new Point(dx, dy);
                            points.add(pt);
                        }
                    }
                }
            }
        }

        for(Point[] pt : points) {
            Point pt1 = pt[0];
            Point pt2 = pt[1];
            char [][] test = new char[N][N];

            for(int a=0; a<N; a++)
                for(int b=0; b<N; b++)
                    test[a][b] = maze[a][b];

            char tmp = test[pt1.x][pt1.y];
            test[pt1.x][pt1.y] = test[pt2.x][pt2.y];
            test[pt2.x][pt2.y] = tmp;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    for(int k=0; k<4; k++) {
                        answer = Math.max(answer, 1 + DFS(i, j, test, k, test[i][j]));
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
