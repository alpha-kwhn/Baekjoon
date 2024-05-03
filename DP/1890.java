import java.io.*;
import java.util.*;

public class Baek_1890 {
    static int N;
    static int[] dir_x = {0, 1};
    static int[] dir_y = {1, 0};

    public static boolean isOK(int a, int b) {
        return (0<=a && a<N && 0<=b && b<N);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        long [][] dp = new long[N][N];
        int [][] maze = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++)
                maze[i][j] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = 1;

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int cost = maze[i][j];
                if (cost > 0) {
                    for (int d = 0; d < 2; d++) {
                        int dx = i + (dir_x[d] * cost);
                        int dy = j + (dir_y[d] * cost);
                        if (isOK(dx, dy))
                            dp[dx][dy] += dp[i][j];
                    }
                }
            }
        }

        System.out.println(dp[N-1][N-1]);
    }
}
