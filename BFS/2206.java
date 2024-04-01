import java.io.*;
import java.util.*;

public class Baek_2206 {
    static char[][] maze;
    static int N;
    static int M;
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static class Point {
        int x;
        int y;
        int cost;
        boolean broken;
        Point(int x, int y, int cost, boolean broken) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.broken = broken;
        }
    }
    public static boolean isOK(int a, int b) {
        return (0<=a && a<N && 0<=b && b<M);
    }
    public static void simulation(boolean [][][] visited) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, 1, false));
        while(!queue.isEmpty()) {
            Point target = queue.poll();
            if(target.x == N-1 && target.y == M-1) {
                System.out.println(target.cost);
                return;
            }

            for(int i=0; i<4; i++) {
                int dx = target.x + dir_x[i];
                int dy = target.y + dir_y[i];
                if(isOK(dx, dy)) {
                    if(maze[dx][dy] == '0') {
                        if(!visited[dx][dy][0] && !target.broken) {
                            visited[dx][dy][0] = true;
                            queue.add(new Point(dx, dy, target.cost+1, false));
                        } else if(!visited[dx][dy][1] && target.broken) {
                            visited[dx][dy][1] = true;
                            queue.add(new Point(dx, dy, target.cost+1, true));
                        }
                    } else if(maze[dx][dy] == '1') {
                        if(!target.broken) {
                            visited[dx][dy][1] = true;
                            queue.add(new Point(dx, dy, target.cost + 1, true));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];

        for(int i=0; i<N; i++) {
            String info = br.readLine();
            for(int j=0; j<M; j++)
                maze[i][j] = info.charAt(j);
        }

        simulation(new boolean[N][M][2]);
    }
}
