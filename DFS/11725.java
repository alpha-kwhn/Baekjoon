import java.util.*;
import java.io.*;
import java.util.stream.*;
public class Baek_11725 {
    static int[] parent;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        arr = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<N+1; i++)
            arr[i] = new ArrayList<>();

        for(int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        DFS(1);

        for(int i=2; i<=N; i++)
            System.out.println(parent[i]);
    }

    public static void DFS(int x) {
        visited[x] = true;
        for(int i=0; i<arr[x].size(); i++) {
            if(!visited[arr[x].get(i)]) {
                parent[arr[x].get(i)] = x;
                DFS(arr[x].get(i));
            }
        }
    }
}
