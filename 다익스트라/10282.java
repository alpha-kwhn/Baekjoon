import java.util.*;
import java.io.*;
public class Baek_10282 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken()); // 의존성 개수
            int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터

            boolean[] visited = new boolean[n+1];

            ArrayList<Vertex> [] arr = new ArrayList[n+1];

            for(int p=0; p<=n; p++)
                arr[p] = new ArrayList<>();

            int[] answer = new int[n+1];
            Arrays.fill(answer, Integer.MAX_VALUE);
            answer[c] = 0;

            PriorityQueue<Vertex> queue = new PriorityQueue<>();
            queue.add(new Vertex(c, 0));

            for(int j=0; j<d; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                arr[to].add(new Vertex(from, cost));
            }

            while(!queue.isEmpty()) {
                Vertex vt = queue.poll();
                if(!visited[vt.num]) {
                    visited[vt.num] = true;
                    for(Vertex vertex : arr[vt.num]) {
                        if(answer[vertex.num] > answer[vt.num] + vertex.cost) {
                            answer[vertex.num] = answer[vt.num] + vertex.cost;
                            queue.add(new Vertex(vertex.num, answer[vertex.num]));
                        }
                    }
                }
            }

            int time = 0;
            for(int k=1; k<=n; k++)
                if(answer[k] != Integer.MAX_VALUE)
                    time = Math.max(time, answer[k]);

            int cnt = 0;
            for(int k=1; k<=n; k++)
                if(visited[k])
                    cnt++;

            System.out.println(cnt + " " + time);
        }
    }
}
