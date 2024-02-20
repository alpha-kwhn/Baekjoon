import java.util.*;
import java.io.*;

public class Baek_1912 {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[N];
        int [][] dp = new int[N][2]; // 0번지: 최대값, 1번지: 현재 덧셈값

        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxi = 0;

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(i == 0)
                maxi = arr[i];
            else {
                if (maxi <= arr[i])
                    maxi = arr[i];
            }
        }

        if(maxi <= 0)
            System.out.println(maxi);
        else {
            for (int i = 0; i < N; i++) {
                if (i == 0) {
                    dp[0][0] = arr[0];
                    dp[0][1] = arr[0];
                } else {
                    int target = dp[i - 1][1] + arr[i];

                    if (target > 0) {
                        dp[i][0] = Math.max(dp[i - 1][0] + arr[i], Math.max(target, arr[i]));
                        dp[i][1] = Math.max(target, arr[i]);
                    } else {
                        dp[i][1] = 0;
                        if (dp[i - 1][0] >= 0)
                            dp[i][0] = 0;
                        else
                            dp[i][0] = dp[i - 1][0];
                    }
                }
            }

            int answer = dp[0][0];

            for (int i = 1; i < N; i++)
                if (dp[i][0] > answer)
                    answer = dp[i][0];

            System.out.println(answer);
        }
    }
}
