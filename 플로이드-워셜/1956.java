import java.util.*;
import java.io.*;
public class Baek_1956 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] distance = new int[V+1][V+1];

        for(int i=0; i<V+1; i++)
            Arrays.fill(distance[i], 4_000_001);

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            distance[from][to] = Math.min(cost, distance[from][to]);
        }

        // b -> c vs b->a + a->c
        for(int a=1; a<=V; a++) {
            for(int b=1; b<=V; b++) {
                for(int c=1; c<=V; c++) {
                    distance[b][c] = Math.min(distance[b][a] + distance[a][c], distance[b][c]);
                }
            }
        }

        // a -> b / b -> a 서로의 경로가 존재하는지 확인해주면 됨
        int answer = 4_000_001;
        for(int a=1; a<=V; a++) {
            for(int b=a+1; b<=V; b++) {
                if(distance[a][b] != 4_000_001 && distance[b][a] != 4_000_001)
                    answer = Math.min(answer, distance[a][b] + distance[b][a]);
            }
        }

        if(answer == 4_000_001)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}
