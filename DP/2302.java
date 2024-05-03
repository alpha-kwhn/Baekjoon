import java.io.*;

public class Baek_2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int recent = 0;
        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i<=N; i++)
            dp[i] = dp[i-1] + dp[i-2];

        int vip = Integer.parseInt(br.readLine());
        int answer = 1;

        for(int i=0; i<vip; i++) {
            int a = Integer.parseInt(br.readLine());
            answer *= dp[a - recent - 1];
            recent = a;
        }

        answer *= dp[N - recent];

        System.out.println(answer);
    }
}
