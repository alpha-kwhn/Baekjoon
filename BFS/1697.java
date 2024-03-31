import java.io.*;
import java.util.*;
public class Baek_1697 {
    static int answer = 100001;
    static boolean[] visited = new boolean[100001];
    static class Point {
        int num;
        int time;
        Point(int num, int time) {
            this.num = num;
            this.time = time;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(N, 0));

        while(!queue.isEmpty()) {
            Point target = queue.poll();
            if(target.num > 100000 || target.num < 0)
                continue;
            if(target.num == K) {
                answer = target.time;
                break;
            }
            if(!visited[target.num]) {
                visited[target.num] = true;
                queue.add(new Point(target.num + 1, target.time + 1));
                queue.add(new Point(target.num - 1, target.time + 1));
                queue.add(new Point(target.num * 2, target.time + 1));
            }

        }
        System.out.println(answer);
    }
}
