import java.util.*;
import java.io.*;

public class Baek_11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long[][] dp = new long[1001][10];
        long[] sum = new long[1001];

        for (int i = 0; i < 10; i++)
            dp[1][i] = 1;

        sum[1] = 10;

        if (N == 1) {
            System.out.println(10);
        } else {
            for (int i = 2; i <= N; i++) {
                long val = sum[i - 1] % 10007;
                dp[i][0] = val;
                for (int j = 1; j < 9; j++) {
                    val = (val - dp[i - 1][j - 1]) % 10007;
                    if (val < 0)
                        val += 10007;
                    dp[i][j] = val % 10007;
                }
                dp[i][9] = 1;
                long sums = 0L;
                for (int j = 0; j < 10; j++)
                    sums = (sums + dp[i][j]) % 10007;
                sum[i] = sums;
            }
            System.out.println(sum[N] % 10007);
        }
    }
}
