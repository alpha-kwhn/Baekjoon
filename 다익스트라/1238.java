import java.io.*;
import java.util.*;

public class Baek_1238 {
    static ArrayList<ArrayList<Spot>> graph = new ArrayList<>();
    static int N;
    static int X;
    static int [] distance;
    static int [] go;
    static int [] back;
    static class Spot implements Comparable<Spot> {
        int end;
        int distance;
        Spot(int end, int distance) {
            this.end = end;
            this.distance = distance;
        }

        //Comparable<T> 인터페이스를 구현하여 자동으로 Spot 객체들 간의 오름차순 정렬이 가능하게 함
        @Override
        public int compareTo(Spot spot) {
            return Integer.compare(this.distance, spot.distance);
        }
    }

    static void BFS() {
        boolean [] visited;
        distance = new int[N+1];
        PriorityQueue<Spot> queue = new PriorityQueue<>();

        for(int i=1; i<N+1; i++) {
            Arrays.fill(distance, Integer.MAX_VALUE);
            visited = new boolean[N+1];
            distance[i] = 0;
            queue.add(new Spot(i, 0));

            while(!queue.isEmpty()) {
                Spot target = queue.poll();

                if (!visited[target.end]) {
                    visited[target.end] = true;

                    for (Spot node : graph.get(target.end)) {
                        if ((distance[node.end] > distance[target.end] + node.distance) && !visited[node.end]) {
                            distance[node.end] = distance[target.end] + node.distance;
                            queue.add(new Spot(node.end, distance[node.end]));
                        }
                    }
                }
            }
            go[i] = distance[X];
        }

        Arrays.fill(distance, Integer.MAX_VALUE);
        visited = new boolean[N+1];
        distance[X] = 0;
        queue.add(new Spot(X, 0));

        while(!queue.isEmpty()) {
            Spot target = queue.poll();
            if(!visited[target.end]) {
                visited[target.end] = true;
                for(Spot node : graph.get(target.end)) {
                    if((distance[node.end] > distance[target.end] + node.distance) && !visited[node.end]) {
                        distance[node.end] = distance[target.end] + node.distance;
                        queue.add(new Spot(node.end, distance[node.end]));
                    }
                }
            }
        }
        back = distance;
    }

    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> answer = new PriorityQueue<>(Comparator.reverseOrder());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        distance = new int[N+1];
        go = new int[N+1];
        back = new int[N+1];

        for(int i=0; i<N+1; i++)
            graph.add(new ArrayList<>());

        for(int i=1;i<M+1;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int lengths = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Spot(end, lengths));
        }

        BFS();

        for(int i=1; i<N+1; i++)
            if(i != X)
                answer.add(go[i] + back[i]);

        System.out.println(answer.poll());
    }
}
