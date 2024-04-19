import java.io.*;
import java.util.*;

public class Baek_1916 {
    static class Vertex implements Comparable<Vertex> {
        int num;
        int cost;
        Vertex(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] answer = new int[N+1]; // 각 도시로 가는 최소 비용 저장 공간
        Arrays.fill(answer, Integer.MAX_VALUE);

        boolean[] checked = new boolean[N+1]; // 도시 방문 여부 체크

        ArrayList<Vertex> [] costs = new ArrayList[N+1]; // 비용 저장

        for(int i=0; i<=N; i++)
            costs[i] = new ArrayList<>();

        PriorityQueue<Vertex> queue = new PriorityQueue<>();

        StringTokenizer st;

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            costs[from].add(new Vertex(to, cost));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        answer[start] = 0;
        queue.add(new Vertex(start, 0));

        while(!queue.isEmpty()) {
            Vertex vt = queue.poll();
            if(!checked[vt.num]) {
                checked[vt.num] = true;
                for(Vertex vertex : costs[vt.num]) {
                    if(answer[vertex.num] > answer[vt.num] + vertex.cost) {
                        answer[vertex.num] = answer[vt.num] + vertex.cost;
                        queue.add(new Vertex(vertex.num, answer[vertex.num]));
                    }
                }
            }
        }

        System.out.println(answer[end]);
    }
}
