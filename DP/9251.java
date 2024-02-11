import java.util.*;
import java.io.*;

public class Baek_9251 {
    static char [] arr_a;
    static char [] arr_b;
    static int [][] dp;
    public static void DP() {
        for(int i=1; i<=arr_a.length; i++) {
            for(int j=1; j<=arr_b.length; j++) {
                if(Objects.equals(arr_a[i-1], arr_b[j-1]))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr_a = br.readLine().toCharArray();
        arr_b = br.readLine().toCharArray();

        dp = new int[arr_a.length+1][arr_b.length+1];

        DP();

        System.out.println(dp[arr_a.length][arr_b.length]);
    }
}
