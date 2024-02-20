import java.util.*;
import java.io.*;

public class Baek_2156 {
    static int [] wine;
    static int [] dp;

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        wine = new int[N+1];
        dp = new int[N+1];

        for(int i=1; i<N+1; i++)
            wine[i] = Integer.parseInt(br.readLine());

        for(int i=1; i<N+1; i++) {
            if(i==1)
                dp[1] = wine[1];
            else if(i==2)
                dp[2] = wine[1] + wine[2];
            else {
                int target = Math.max(dp[i-1], Math.max(wine[i-1] + dp[i-3], dp[i-2]) + wine[i]);
                dp[i] = target;
            }
        }

        System.out.println(dp[dp.length-1]);
    }
}
