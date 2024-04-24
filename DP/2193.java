import java.io.*;

public class Baek_2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N == 1 || N == 2)
            System.out.println(1);
        else if(N == 3)
            System.out.println(2);
        else {
            long [] dp = new long[N+1];
            dp[2] = 1L;
            dp[3] = 2L;

            for(int i=4; i<=N; i++)
                dp[i] = dp[i-2] + dp[i-1];

            System.out.println(dp[N]);
        }
    }
}
