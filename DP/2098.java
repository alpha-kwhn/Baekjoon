import java.util.*;
import java.io.*;

public class Baek_2098 {
    static int city;
    static int [][] maze;
    static int [][] dp;

    static int DFS(int node, int visitMask) { // 현재 도시, 지금도시오기까지 거쳐간 도시 목록

        if(dp[node][visitMask] != -1)
            return dp[node][visitMask];

        for(int i=1; i<city; i++) {
            if((visitMask & (1 << i)) == 0 && maze[node][i] != 0) {
                int next = visitMask | (1 << i);
                int cost = DFS(i, next) + maze[node][i];

                if(dp[node][visitMask] == -1)
                    dp[node][visitMask] = cost;
                else
                    dp[node][visitMask] = Math.min(dp[node][visitMask], cost);
            }
        }

        if(dp[node][visitMask] == -1)
            return 11000000;

        return dp[node][visitMask];
    }


    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        city = Integer.parseInt(br.readLine());
        maze = new int[city][city]; // 도시간의 거리정보 저장 배열
        dp = new int[city][(1 << city) - 1]; // 행에는 도시 인덱스, 열에는 특정 도시에서 이때까지 방문한 도시정보를 비트마스킹으로 저장

        for(int i=0; i<city; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<city; j++)
                maze[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<city; i++)
            Arrays.fill(dp[i], -1);

        for(int i=1; i<city; i++) { // 모두 거쳤을때 원점으로 돌아가는 비용값 미리 산정, 못돌아가는경우는 MAX 값 투입
            if(maze[i][0] == 0)
                dp[i][(1 << city) - 2] = 11000000;
            else
                dp[i][(1 << city) - 2] = maze[i][0];
        }

        System.out.println(DFS(0, 0));

    }
}
