import java.util.*;
import java.io.*;
public class Baek_5014 {
    static class Stair {
        int floor;
        int moved;
        Stair(int floor, int moved) {
            this.floor = floor;
            this.moved = moved;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken()); // 전체 층수
        int S = Integer.parseInt(st.nextToken()); // 현재 층수
        int G = Integer.parseInt(st.nextToken()); // 목적지 층수
        int U = Integer.parseInt(st.nextToken()); // 위칸 이동 수
        int D = Integer.parseInt(st.nextToken()); // 아래칸 이동

        boolean [] visited = new boolean[F+1];

        Queue<Stair> queue = new LinkedList<>();
        queue.add(new Stair(S, 0));

        int answer = 0;
        while(!queue.isEmpty()) {
            Stair now = queue.poll();
            if(G == now.floor) {
                answer = now.moved;
                break;
            }
            if(!visited[now.floor]) {
                visited[now.floor] = true;
                if(now.floor + U <= F)
                    queue.add(new Stair(now.floor + U, now.moved+1));
                if(now.floor - D >= 1)
                    queue.add(new Stair(now.floor - D, now.moved+1));
            }
        }

        if(S == G)
            System.out.println(0);
        else if(answer == 0)
            System.out.println("use the stairs");
        else
            System.out.println(answer);
    }
}
