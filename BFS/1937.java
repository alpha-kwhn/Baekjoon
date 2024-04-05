import java.io.*;
import java.util.*;
public class Baek_1937 {
    static int N;
    static int[][] maze;
    static int[][] dp;
    static int[] dir_x = {-1, 0, 1, 0};
    static int[] dir_y = {0, 1, 0, -1};
    static int answer = 0;
    public static boolean isOK(int a, int b) {
        return (0 <= a && a <N && 0 <= b && b < N);
    }
    public static int DFS(int a, int b) {
        if(dp[a][b] > -1)
            return dp[a][b];

        dp[a][b] = 1;
        int fins = 0;
        for(int i=0; i<4; i++) {
            int val = dp[a][b];
            int dx = a + dir_x[i];
            int dy = b + dir_y[i];
            if(isOK(dx, dy)) {
                if(maze[dx][dy] > maze[a][b])
                    val += DFS(dx, dy);
            }
            fins = Math.max(val, fins);
        }

        dp[a][b] = fins;
        return dp[a][b];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        maze = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++)
            Arrays.fill(dp[i], -1);

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                maze[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++)
            for(int j = 0; j < N; j++)
                answer = Math.max(DFS(i, j), answer);

        System.out.println(answer);
    }
}
