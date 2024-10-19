import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_1926 {
    public static boolean[][] visited;
    public static int[][] maze;
    public static int[][] dp;
    static int n;
    static int m;
    static int[] dir_x = {-1, 0, 1, 0};
    static int answer = 0;
    static int count = 0;
    static int total = 0;
    static int[] dir_y = {0, 1, 0, -1};
    public static boolean isOK(int a, int b) {
        return (0<=a && a<n && 0<=b && b<m);
    }
    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        maze = new int[n][m];
        dp = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++)
                maze[i][j] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[n][m];

        for(int i=0; i<n; i++) {
            for (int j = 0; j < m; j++) {
                if(maze[i][j] == 1 && !visited[i][j]) {
                    total++;
                    Queue<Point> queue = new ArrayDeque<>();
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    while (!queue.isEmpty()) {
                        Point pt = queue.poll();
                        count++;
                        for (int k = 0; k < 4; k++) {
                            int dx = pt.x + dir_x[k];
                            int dy = pt.y + dir_y[k];
                            if (isOK(dx, dy) && maze[dx][dy] == 1 && !visited[dx][dy]) {
                                queue.add(new Point(dx, dy));
                                visited[dx][dy] = true;
                            }
                        }
                    }
                    answer = Math.max(answer, count);
                    count = 0;
                }
            }
        }

        System.out.println(total);
        System.out.println(answer);
    }
}
