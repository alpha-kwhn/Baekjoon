import java.io.*;
import java.util.*;
public class Baek_2589 {
    static int L; // 세로
    static int W; // 가로
    static char[][] maze; // 지도
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static int result = Integer.MIN_VALUE;

    public static boolean isOK(int a, int b) {
        return (0<=a && a<L && 0<=b && b<W);
    }
    public static class Point {
        int x, y;
        int cost;
        Point(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public static int BFS(int a, int b, boolean[][] visit) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(a, b, 0));
        int answer = 0;

        while(!queue.isEmpty()) {
            Point ar = queue.poll();
            answer = ar.cost;
            for (int i = 0; i < 4; i++) {
                int dx = ar.x + dir_x[i];
                int dy = ar.y + dir_y[i];
                if (isOK(dx, dy)) {
                    if (!visit[dx][dy] && maze[dx][dy] == 'L') {
                        visit[dx][dy] = true;
                        queue.add(new Point(dx, dy, ar.cost+1));
                    }
                }
            }
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        maze = new char[L][W];

        for (int i = 0; i < L; i++){
            String row = br.readLine();
            for (int j = 0; j < W; j++)
                maze[i][j] = row.charAt(j);
        }

        for (int i = 0; i < L; i++) {
            for(int j = 0; j < W; j++) {
                if(maze[i][j] == 'L') {
                    boolean[][] visit = new boolean[L][W];
                    visit[i][j] = true;
                    result = Math.max(BFS(i, j, visit), result);
                }
            }
        }

        System.out.println(result);
    }
}
