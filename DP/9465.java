import java.io.*;
import java.util.*;
public class Baek_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;


        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            int [][] maze = new int[n+1][2];
            int answer;
            int [][] dp = new int[n+1][2];

            for(int j=1; j>=0; j--) {
                st = new StringTokenizer(br.readLine());
                for(int k=1; k<=n; k++)
                    maze[k][j] = Integer.parseInt(st.nextToken());
            }

            dp[1][0] = maze[1][0];
            dp[1][1] = maze[1][1];

            if(n == 1)
                answer = Math.max(maze[1][0], maze[1][1]);
            else {
                for(int j=2; j<=n; j++) {
                    for(int k=0; k<2; k++) {
                        dp[j][k] = Math.max(dp[j-1][(k+1)%2], dp[j-2][(k+1)%2]) + maze[j][k];
                    }
                }
                answer = Math.max(dp[n][0], dp[n][1]);
            }

            System.out.println(answer);
        }
    }
}
