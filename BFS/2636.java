import java.io.*;
import java.util.*;
public class Baek_2636 {
    static int[][] maze;
    static int H;
    static int W;
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
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

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        maze = new int[H][W];

        int cheese = 0;

        for(int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) {
                int num = Integer.parseInt(st.nextToken());
                maze[i][j] = num;
                if(num == 1)
                    cheese++;
            }
        }

        int time = 0;
        while(true) {
            time++;
            int history = cheese;

            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(0, 0));

            boolean[][] visited = new boolean[H][W];
            visited[0][0] = true;

            while (!queue.isEmpty()) {
                Point pt = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int dx = pt.x + dir_x[i];
                    int dy = pt.y + dir_y[i];
                    if (dx >= 0 && dy >= 0 && dx < H && dy < W) {
                        if(!visited[dx][dy]) {
                            if (maze[dx][dy] == 0) {
                                queue.add(new Point(dx, dy));
                                visited[dx][dy] = true;
                            } else {
                                maze[dx][dy] = 0;
                                visited[dx][dy] = true;
                                cheese--;
                            }
                        }
                    }
                }
            }

            if(cheese == 0) {
                System.out.println(time);
                System.out.println(history);
                break;
            }
        }
    }
}
