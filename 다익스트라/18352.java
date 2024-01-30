import java.util.*;
import java.io.*;

public class Baek_18352 {
    public static void main(String [] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<Integer> [] road = new List[N+1]; // 순서가 정해져 있는 List를 사용하여 해결한다

        for(int i=1; i<N+1; i++)
            road[i] = new ArrayList<>();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(bf.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            road[start].add(end);
        }

        // poll, add가 활발한 경우에는 ArrayDeque를 주로 사용
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(X);

        int [] distance = new int[N+1];
        Arrays.fill(distance, 300001);
        distance[X] = 0;

        ArrayList<Integer> answer = new ArrayList<>();

        while(!queue.isEmpty()) {
            int target = queue.poll();
            if (distance[target] == K) {
                answer.add(target);
                continue;
            }
            if (distance[target] > K) continue;
            for(Integer node: road[target]) {
                if(distance[node] > distance[target] + 1) {
                    distance[node] = distance[target] + 1;
                    queue.add(node);
                }
            }
        }

        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        for(Integer item : answer) {
            sb.append(item).append("\n");
        }

        System.out.print(answer.isEmpty() ? -1 : sb);
    }
}
