import java.util.*;
import java.io.*;

public class Baek_1719 {
    static int [][] graph;
    static int n;
    static int m;
    static int [][] pass;
    static void Floyd() {
        for(int a=1; a<n+1; a++) {
            for(int b=1; b<n+1; b++) {
                for(int c=1; c<n+1; c++) {
                    if(graph[b][c] > graph[b][a] + graph[a][c]) {
                        graph[b][c] = graph[b][a] + graph[a][c];
                        pass[b][c] = pass[b][a];
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n+1][n+1];
        pass = new int[n+1][n+1];

        for(int a=0; a<n+1; a++) {
            Arrays.fill(graph[a], 10000);
            pass[a][a] = 0;
            graph[a][a] = 0;
        }

        for(int a=0; a<m; a++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph[start][end] = distance;
            graph[end][start] = distance;
            pass[start][end] = end;
            pass[end][start] = start;
        }

        Floyd();

        String [][] answer = new String[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(answer[i], "-");
        }

        for(int i=1; i<n+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(i != j)
                    answer[i-1][j-1] = Integer.toString(pass[i][j]);
            }
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(j < n-1) System.out.print(answer[i][j] + " ");
                else System.out.print(answer[i][j] + "\n");
            }
        }
    }
}
