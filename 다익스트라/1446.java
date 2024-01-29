import java.io.*;
import java.util.*;

public class Main {
    static class Spot {
        int start;
        int distance;

        Spot(int start, int distance) {
            this.start = start;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위함
        StringTokenizer st = new StringTokenizer(br.readLine()); // StringTokenizer는 1회용

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int answer = 0;

        int [] distance = new int[10001];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        ArrayList<Spot>[] graph = new ArrayList[10001]; // ArrayList를 담는 크기 10001짜리의 배열선언, 내부에 ArrayList 넣어줘야함

        for(int i=0; i<10001; i++)
            graph[i] = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int hidden = Integer.parseInt(st.nextToken());

            if (end-start > hidden)
                graph[end].add(new Spot(start, hidden));
        }

        for(int i=1; i<=D; i++) {
            if (graph[i].isEmpty())
                distance[i] = distance[i-1] + 1;
            else {
                for(Spot spot: graph[i]) {
                    if(distance[spot.start] + spot.distance > distance[i]) continue;
                    distance[i] = Math.min(distance[i-1]+1, distance[spot.start] + spot.distance);
                }
            }
        }

        System.out.println(distance[D]);
    }
}
