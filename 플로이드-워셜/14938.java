import java.util.*;
import java.io.*;

public class Baek_14938 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Integer> answer = new PriorityQueue<>(Comparator.reverseOrder());

        int region = Integer.parseInt(st.nextToken());
        int range = Integer.parseInt(st.nextToken());
        int road = Integer.parseInt(st.nextToken());

        int [][] graph = new int[region][region]; // 지역의 개수만큼 길이의 ArrayList 가지는 고정크기 배열
        int [] items = new int[region];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<region;i++) {
            items[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(graph[i], 16);
            graph[i][i] = 0;
        }

        for(int i=0;i<road;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int lengths = Integer.parseInt(st.nextToken());
            graph[start-1][end-1] = lengths;
            graph[end-1][start-1] = lengths;
        }

        // 플로이드-워셜 알고리즘을 사용할때는 무조건 최단경로부터 다 구해줘야함
        for(int a=0;a<region;a++) {
            for(int b=0;b<region;b++) {
                for(int c=0;c<region;c++) {
                    if(graph[b][a] + graph[a][c] < graph[b][c]) {
                        graph[b][c] = Math.min(graph[b][c], graph[b][a] + graph[a][c]);
                    }
                }
            }
        }

        for(int a=0; a<region; a++) {
            int count = items[a];
            for(int b=0; b<region;b++) {
                if(graph[a][b] > 0 && graph[a][b] <= range)
                    count += items[b];
            }
            answer.add(count);
        }
        System.out.println(answer.poll());
    }
}
