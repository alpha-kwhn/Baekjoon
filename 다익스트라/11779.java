import java.util.*;
import java.io.*;

public class Baek_11779 {
    static int bus;
    static int city;
    static ArrayList<Node>[] maze;
    static int target_s;
    static int target_e;
    static class Node implements Comparable<Node> {
        int num;
        int cost;
        ArrayList<Integer> passed;
        Node(int num, int cost, ArrayList<Integer> arr) {
            this.num = num;
            this.cost = cost;
            this.passed = arr;
        }
        @Override
        public int compareTo(Node node) {
            if(this.cost == node.cost)
                return this.num - node.num;
            return this.cost - node.cost;
        }
    }
    static void Dijkstra() {
        boolean [] visited = new boolean[city+1];
        int[] cost = new int[city+1];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[target_s] = 0;
        queue.add(new Node(target_s, 0, new ArrayList<>()));
        int cityNum = 0;
        ArrayList<Integer> history = new ArrayList<>();

        while(!queue.isEmpty()) {
            Node target = queue.poll();
            if(visited[target.num]) continue;
            visited[target.num] = true;
            target.passed.add(target.num);

            if(target.num == target_e) {
                history = new ArrayList<>(target.passed);
                cityNum = history.size();
                break;
            }

            for (Node nde : maze[target.num]) {
                if (cost[nde.num] >= cost[target.num] + nde.cost) {
                    cost[nde.num] = cost[target.num] + nde.cost;
                    ArrayList<Integer> newPath = new ArrayList<>(target.passed);
                    queue.add(new Node(nde.num, cost[nde.num], newPath));
                }
            }
        }

        int minimum = cost[target_e];

        System.out.println(minimum);
        System.out.println(cityNum);
        history.stream().forEach(e->System.out.print(e + " "));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        city = Integer.parseInt(br.readLine());
        bus = Integer.parseInt(br.readLine());
        maze = new ArrayList[city+1];

        for(int i=0; i<city+1; i++)
            maze[i] = new ArrayList<>();

        for(int i=0; i<bus; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            maze[start].add(new Node(end, cost, new ArrayList<>()));
        }

        st = new StringTokenizer(br.readLine());
        target_s = Integer.parseInt(st.nextToken());
        target_e = Integer.parseInt(st.nextToken());

        Dijkstra();
    }
}
