import java.io.*;
import java.util.*;
public class Baek_1149 {
    static int answer = Integer.MAX_VALUE;
    static int N;
    static int [][] info;
    static int [][] dp;
    public static void DFS(int color, int idx, int cost) {
        if(idx == N)
            return;

        for(int i=0; i<3; i++) {
            if(color != i) {
                if(dp[idx][i] > cost + info[idx][i]) {
                    dp[idx][i] = cost + info[idx][i];
                    DFS(i, idx + 1, dp[idx][i]);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        info = new int[N][3];
        dp = new int[N][3];

        for(int i=0; i<N; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        StringTokenizer st;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            info[i][0] = a;
            info[i][1] = b;
            info[i][2] = c;
        }

        DFS(0, 1, info[0][0]);
        DFS(1, 1, info[0][1]);
        DFS(2, 1, info[0][2]);

        for(int i=0; i<3; i++)
            answer = Math.min(answer, dp[N-1][i]);

        System.out.println(answer);
    }
}
