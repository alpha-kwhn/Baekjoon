import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baek_17484 {
    static int N;
    static int M;
    static int[][][] ship;
    static int[][][] dp;
    static int[] dir_y = {-1, 0, 1};
    public static boolean isOK(int a, int b) {
        return (0<=a && a<N && 0<=b && b<M);
    }
    public static void DFS(int direction, int a, int b, int cost) {
        for(int i=0; i<3; i++) {
            if(i != direction) {
                int dx = a + 1;
                int dy = b + dir_y[i];
                if(isOK(dx, dy)) {
                    if(dp[dx][dy][i] > cost + ship[dx][dy][i]) {
                        dp[dx][dy][i] = cost + ship[dx][dy][i];
                        DFS(i, dx, dy, dp[dx][dy][i]);
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ship = new int[N][M][3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int num = Integer.parseInt(st.nextToken());
                for (int k = 0; k < 3; k++)
                    ship[i][j][k] = num;
            }
        }

        dp = new int[N][M][3];

        for(int i=0; i<N; i++)
            for(int j=0; j<M; j++)
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);

        for(int i=0; i<M; i++)
            for(int k=0; k<3; k++)
                DFS(k, 0, i, ship[0][i][k]);

        int answer = Integer.MAX_VALUE;
        for (int j = 0; j < M; j++)
            for (int k = 0; k < 3; k++)
                answer = Math.min(answer, dp[N-1][j][k]);

        System.out.println(answer);

    }
}
