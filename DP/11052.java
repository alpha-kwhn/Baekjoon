import java.util.*;
import java.io.*;
public class Baek_11052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] card = new int[N+1];
        int [] dp = new int[N+1];

        for(int i=1; i<=N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
            dp[i] = card[i];
        }

        for(int i=2; i<=N; i++) {
            if(i % 2 == 1) {
                for(int j=i; j>(i/2); j--)
                    dp[i] = Math.max(dp[i], dp[j] + dp[i-j]);
            } else {
                for(int j=i; j>=(i/2); j--)
                    dp[i] = Math.max(dp[i], dp[j] + dp[i-j]);
            }
        }

        System.out.println(dp[N]);
    }
}
