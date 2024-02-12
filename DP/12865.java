import java.util.*;
import java.io.*;
public class Baek_12865 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][] dp = new int[101][100001];
        int [][] items = new int[101][2];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int wei = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            items[i][0] = wei;
            items[i][1] = val;
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                if(items[i][0] <= j) {
                    if((items[i][1] + dp[i-1][j-items[i][0]]) > dp[i-1][j])
                        dp[i][j] = items[i][1] + dp[i-1][j-items[i][0]];
                    else
                        dp[i][j] = dp[i-1][j];
                } else
                    dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][K]);
    }
}
