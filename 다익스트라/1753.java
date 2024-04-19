import java.util.*;
import java.io.*;
public class Baek_1753 {
    static class Vertex implements Comparable<Vertex> {
        int num;
        int cost;
        Vertex(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
        @Override
        public int compareTo(Vertex v) {
            return this.cost - v.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        ArrayList<Vertex> [] cost = new ArrayList[V+1];

        for(int i=0; i<=V; i++)
            cost[i] = new ArrayList<>();

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            cost[from].add(new Vertex(to, distance));
        }

        boolean[] visited = new boolean[V+1];

        int[] answer = new int[V+1];
        Arrays.fill(answer, Integer.MAX_VALUE);
        answer[start] = 0;

        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(new Vertex(start, 0));

        while(!queue.isEmpty()) {
            Vertex vt = queue.poll();
            if(!visited[vt.num]) {
                visited[vt.num] = true;
                for(Vertex vertex : cost[vt.num]) {
                    if(answer[vertex.num] > answer[vt.num] + vertex.cost) {
                        answer[vertex.num] = answer[vt.num] + vertex.cost;
                        queue.add(new Vertex(vertex.num, answer[vertex.num]));
                    }
                }
            }
        }

        for(int i = 1; i<=V; i++) {
            if(answer[i] == Integer.MAX_VALUE)
                System.out.println("INF");
            else
                System.out.println(answer[i]);
        }
    }
}
