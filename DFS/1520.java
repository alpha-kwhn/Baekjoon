import java.io.*;
import java.util.*;
public class Baek_1520 {
    static int M, N;
    static int[][] maze;
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static int x = 0;
    static int y = 0;
    static int[][] visited;
    public static boolean isOK(int a, int b) {
        return (0<=a && a < M && 0<=b && b < N);
    }
    public static int DFS(int a, int b) {
        if (a == M - 1 && b == N - 1)
            return 1;
        if(visited[a][b] != -1) {
            return visited[a][b];
        } else {
            visited[a][b] = 0;
            for (int i = 0; i < 4; i++) {
                int dx = a + dir_x[i];
                int dy = b + dir_y[i];
                if (isOK(dx, dy)) {
                    if (maze[dx][dy] < maze[a][b])
                        visited[a][b] += DFS(dx, dy);
                }
            }
        }
        return visited[a][b];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        maze = new int[M][N];
        visited = new int[M][N];

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;
            }
        }

        System.out.println(DFS(x, y));
    }
}
