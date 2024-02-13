import java.io.*;

public class Baek_1463 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] dp = new int[N+1];

        for(int i=2; i<N+1; i++) {
            if(i == 2 || i == 3) dp[i] = 1;
            else {
                if(i % 2 != 0 && i % 3 != 0){
                    dp[i] = Math.min(dp[i-1] + 1, dp[i-2] + 2);
                } else if(i % 2 == 0 && i % 3 ==0) {
                    dp[i] = Math.min(Math.min(dp[i / 3], dp[i / 2]), dp[i-1]) + 1;
                } else if(i % 2 == 0)
                    dp[i] = Math.min(dp[i / 2] + 1, dp[i-1] + 1);
                else
                    dp[i] = Math.min(dp[i / 3] + 1, dp[i-1] + 1);
            }
        }

        System.out.println(dp[N]);
    }
}
