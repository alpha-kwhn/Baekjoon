import java.util.*;
import java.util.stream.*;
import java.io.*;
public class Baek_1260 {
    static boolean[] visited;
    static ArrayList<Integer>[] arr;
    static ArrayList<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        arr = new ArrayList[N+1];

        for(int i=0; i<=N; i++)
            arr[i] = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        for(int i=1; i<=N; i++)
            Collections.sort(arr[i]);

        visited = new boolean[N+1];
        DFS(V);

        for(Integer n : answer)
            System.out.print(n + " ");

        System.out.print("\n");

        visited = new boolean[N+1];
        answer.clear();

        BFS(V);

        for(Integer n : answer)
            System.out.print(n + " ");
    }

    public static void BFS(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);
        visited[x] = true;
        while(!queue.isEmpty()) {
            int num = queue.poll();
            answer.add(num);
            for (int i = 0; i < arr[num].size(); i++) {
                if (!visited[arr[num].get(i)]) {
                    queue.add(arr[num].get(i));
                    visited[arr[num].get(i)] = true;
                }
            }
        }
    }

    public static void DFS(int x) {
        if(visited[x])
            return;
        answer.add(x);
        visited[x] = true;
        for(int i=0; i<arr[x].size(); i++) {
            if(!visited[arr[x].get(i)]) {
                DFS(arr[x].get(i));
            }
        }
    }
}
