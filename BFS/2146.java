import java.io.*;
import java.util.*;

public class Baek_2146 {
    static int[][] maze;
    static int N;
    static int answer = Integer.MAX_VALUE;
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static boolean[][] visited;
    static class Spot {
        int x;
        int y;
        int cost;
        Spot(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
    public static boolean isOK(int a, int b) {
        return (0 <= a && a < N && 0 <= b && b < N);
    }
    public static void DFS(int a, int b, int number) { // 섬 그룹핑
        visited[a][b] = true;
        maze[a][b] = number;

        for(int i=0; i<4; i++) {
            int dx = a + dir_x[i];
            int dy = b + dir_y[i];
            if(isOK(dx, dy))
                if(!visited[dx][dy] && maze[dx][dy] != 0)
                    DFS(dx, dy, number);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        maze = new int[N][N];
        visited = new boolean[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                maze[i][j] = n;
            }
        }

        int number = 1;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(maze[i][j] != 0 && !visited[i][j]) {
                    DFS(i, j, number);
                    number++;
                }
            }
        }

        for(int i=1; i<number; i++) {
            int [][] test = maze.clone();
            BFS(i, test);
        }

        System.out.println(answer);
    }

    public static void BFS(int area, int [][] maps) {
        Queue<Spot> queue = new LinkedList<>();
        boolean[][] checked = new boolean[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(maps[i][j] == area) {
                    queue.add(new Spot(i, j, 0));
                    checked[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Spot target = queue.poll();
            for (int j = 0; j < 4; j++) {
                int dx = target.x + dir_x[j];
                int dy = target.y + dir_y[j];
                if (isOK(dx, dy)) {
                    if(maps[dx][dy] != area && maps[dx][dy] != 0) {
                        if (target.cost != 0)
                            answer = Math.min(answer, target.cost);
                    } else if (maps[dx][dy] == 0 && !checked[dx][dy]) {
                        checked[dx][dy] = true;
                        Spot newOne = new Spot(dx, dy, target.cost+1);
                        queue.add(newOne);
                    }

                }
            }
        }
    }
}
