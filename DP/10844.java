import java.io.*;

public class Baek_10844 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long [][] dp = new long[101][10];
        long [] answer = new long[N+1];

        for(int i=1; i<10; i++)
            dp[1][i] = 1;

        answer[1] = 9;

        for(int i=2; i<=N; i++) {
            long sums = 0;
            for(int j=0; j<10; j++) {
                if(j == 0)
                    dp[i][j] = dp[i-1][1];
                else if(j == 9)
                    dp[i][j] = dp[i-1][8];
                else {
                    if(dp[i - 1][j - 1] + dp[i - 1][j + 1] >= 1000000000)
                        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
                    else
                        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]);
                }

                if(sums + dp[i][j] >= 1000000000)
                    sums = (sums + dp[i][j]) % 1000000000;
                else
                    sums += dp[i][j];
            }
            answer[i] = sums;
        }

        System.out.println(answer[N]);
    }
}
