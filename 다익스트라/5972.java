import java.io.*;
import java.util.*;

public class Baek_5972 {
    static ArrayList<Spot> [] graph;
    static PriorityQueue<Spot> queue = new PriorityQueue<>();
    static int [] distance;
    static boolean [] visited;
    static int N;
    static class Spot implements Comparable<Spot> {
        int end;
        int cow;
        Spot(int end, int cow) {
            this.end = end;
            this.cow = cow;
        }
        @Override
        public int compareTo(Spot node) { // 소를 최대한 적게 마주치게 정렬
            if(this.cow < node.cow) return -1;
            else return 1;
        }
    }
    static void Dijkstra() {
        queue.add(new Spot(1, 0));
        distance[1] = 0;

        while(!queue.isEmpty()) {
            Spot target = queue.poll();
            if (!visited[target.end]) {
                visited[target.end] = true;
                for (Spot node : graph[target.end]) {
                    if ((distance[node.end] > distance[target.end] + node.cow) && !visited[node.end]) {
                        distance[node.end] = distance[target.end] + node.cow;
                        queue.add(new Spot(node.end, distance[node.end]));
                    }
                }
            }
        }
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        distance = new int[N+1];
        visited = new boolean[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        graph = new ArrayList[N+1];
        for(int i=1; i<N+1; i++)
            graph[i] = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());

            graph[start].add(new Spot(end, cow));
            graph[end].add(new Spot(start, cow));
        }
        Dijkstra();

        System.out.println(distance[N]);
    }
}
