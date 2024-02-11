import java.util.*;
import java.io.*;

public class Baek_1003 {
    static int [][] dp;
    static int [] Fibonacci(int num) {
        for(int i=0; i<=num; i++) {
            if(i == 0) {
                dp[i][0] = 1;
                dp[i][1] = 0;
            } else if(i == 1) {
                dp[i][0] = 0;
                dp[i][1] = 1;
            } else {
                dp[i][0] = dp[i - 2][0] + dp[i - 1][0];
                dp[i][1] = dp[i - 2][1] + dp[i - 1][1];
            }
        }
        return dp[num];
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            int size = Integer.parseInt(br.readLine());

            dp = new int[size+1][2];

            int [] answer = Fibonacci(size);
            System.out.println(answer[0] + " " + answer[1]);
        }
    }
}
